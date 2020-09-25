package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.ShoppingCartDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
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
public class ShoppingCartJdbcImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart getByUserId(Long userId) {
        String query = "SELECT * FROM shopping_carts WHERE user_id = ? AND cart_deleted = FALSE;";
        ShoppingCart shoppingCart = new ShoppingCart();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        shoppingCart.setUserId(userId);
                        long cartId = resultSet.getLong("cart_id");
                        shoppingCart.setId(cartId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("ShoppingCart with  userId=" + " not found", e);
        }
        shoppingCart.setProducts(getProductsFromCart(shoppingCart.getId()));
        return shoppingCart;
    }

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        String query = "INSERT INTO shopping_carts (user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, cart.getUserId());
                preparedStatement.executeUpdate();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cart.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("ShoppingCart for userId=" + cart.getUserId()
                    + " wasn't created.", e);
        }
        return cart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        String query = "SELECT * FROM shopping_carts_products WHERE cart_id = ?;";
        ShoppingCart shoppingCart = new ShoppingCart();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        shoppingCart.setId(resultSet.getLong("cart_id"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("ShoppingCart id=" + id + " wasn't found.", e);
        }
        shoppingCart.setProducts(getProductsFromCart(id));
        return Optional.of(shoppingCart);
    }

    @Override
    public List<ShoppingCart> getAll() {
        String query = "SELECT cart_id, user_id "
                + "FROM shopping_carts "
                + "WHERE cart_deleted = FALSE;";
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long cartId = resultSet.getLong("cart_id");
                        long userId = resultSet.getLong("user_id");
                        shoppingCarts.add(new ShoppingCart(cartId, userId));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get all ShoppingCarts", e);
        }
        for (ShoppingCart cart : shoppingCarts) {
            cart.setProducts(getProductsFromCart(cart.getId()));
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        deleteProductsFromCart(cart);
        String query = "INSERT INTO shopping_carts_products (cart_id, product_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            for (Product product : cart.getProducts()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setLong(1, cart.getId());
                    preparedStatement.setLong(2, product.getId());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("ShoppingCart id=" + cart.getId()
                    + " wasn't updated.", e);
        }
        return get(cart.getId()).get();
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE shopping_carts "
                + "SET cart_deleted = TRUE "
                + "WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                return preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("ShoppingCart id=" + id + " wasn't deleted.", e);
        }
    }

    private List<Product> getProductsFromCart(long cartId) {
        String query = "SELECT * FROM products p "
                + "INNER JOIN shopping_carts_products scp ON p.product_id = scp.product_id "
                + "WHERE cart_id = ? AND product_deleted = FALSE;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, cartId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong("product_id");
                        String name = resultSet.getString("product_name");
                        BigDecimal price = resultSet.getBigDecimal("product_price");
                        products.add(new Product(id, name, price));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't add products to cart from DB", e);
        }
        return products;
    }

    private ShoppingCart deleteProductsFromCart(ShoppingCart cart) {
        String deleteProductsFromCart = "DELETE FROM shopping_carts_products WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(deleteProductsFromCart)) {
                preparedStatement.setLong(1, cart.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update cartId=" + cart.getId(), e);
        }
        return cart;
    }
}
