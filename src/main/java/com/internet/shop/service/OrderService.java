package com.internet.shop.service;

import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import java.util.List;

public interface OrderService {
    Order create(Order order);

    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(Long id);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
