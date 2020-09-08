package com.internet.shop.service;

import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart get(Long id);

    List<ShoppingCart> getAll();

    ShoppingCart addProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart deleteProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart update(ShoppingCart shoppingCart);

    ShoppingCart clear(ShoppingCart shoppingCart);

    boolean delete(Long id);
}
