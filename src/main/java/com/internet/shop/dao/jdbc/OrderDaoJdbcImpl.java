package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.OrderDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUserOrders(Long id) {
        String query = "SELECT * FROM orders WHERE user_id = ? AND order_deleted = FALSE;";
        List<Order> ordersList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ordersList.add(getOrderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Culdn't get user order. User id =" + id, e);
        }
        for (Order order : ordersList) {
            order.setProducts(getOrderProducts(order));
        }
        return ordersList;
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, order.getUserId());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    order.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't create order id=" + order.getId(), e);
        }
        insertProductsToDB(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = "SELECT * FROM orders WHERE order_id = ? AND order_deleted = FALSE;";
        Order order = new Order();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    order.setId(resultSet.getLong("order_id"));
                    order.setUserId(resultSet.getLong("user_id"));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get order id=" + id, e);
        }
        order.setProducts(getOrderProducts(order));
        return Optional.of(order);
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT order_id, user_id FROM orders WHERE order_deleted = FALSE;";
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    orderList.add(getOrderFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get all orders from db", e);
        }
        for (Order order : orderList) {
            order.setProducts(getOrderProducts(order));
        }
        return orderList;
    }

    @Override
    public Order update(Order order) {
        deleteProductsFromOrder(order.getId());
        insertProductsToDB(order);
        String query = "UPDATE orders "
                + "SET user_id = ? "
                + "WHERE order_id = ? AND order_deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, order.getUserId());
                preparedStatement.setLong(2, order.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update order id=" + order.getId(), e);
        }
        return order;
    }

    @Override
    public boolean delete(Long id) {
        String deleteOrder = "UPDATE orders "
                + "SET order_deleted = TRUE "
                + "WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteOrder)) {
                preparedStatement.setLong(1, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete order id=" + id, e);
        }
    }

    private int deleteProductsFromOrder(Long id) {
        String deleteProducts = "DELETE FROM order_products WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(deleteProducts)) {
                preparedStatement.setLong(1, id);
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete products from order id=" + id, e);
        }
    }

    private List<Product> getOrderProducts(Order order) {
        String query = "SELECT * FROM products p "
                + "INNER JOIN order_products op USING (product_id) "
                + "WHERE op.order_id = ? AND p.product_deleted = FALSE;";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, order.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    productList.add(getProductFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cuoldn't set products to order from DB, "
                    + "where order id=" + order.getId(), e);
        }
        return productList;
    }

    private int insertProductsToDB(Order order) {
        String query = "INSERT INTO order_products (order_id, product_id) VALUES (?, ?);";
        long orderId = order.getId();
        int updatedProductsCount = 0;
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : order.getProducts()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setLong(1, orderId);
                    preparedStatement.setLong(2, product.getId());
                    updatedProductsCount += preparedStatement.executeUpdate();
                }
            }
            return updatedProductsCount;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't insert products to order_products", e);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        return new Order(orderId, userId);

    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("product_id");
        String name = resultSet.getString("product_name");
        BigDecimal price = resultSet.getBigDecimal("product_price");
        return new Product(id, name, price);
    }
}
