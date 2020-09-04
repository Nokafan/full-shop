package com.internet.shop.db;

import com.internet.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();

    private static Long userId = 1L;
    private static Long cartId = 1L;
    private static Long productId = 1L;
    private static Long orderId = 1L;

    public static void addProduct(Product product) {
        product.setId(productId++);
        products.add(product);
    }
}
