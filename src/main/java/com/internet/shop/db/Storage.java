package com.internet.shop.db;

import com.internet.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();

    private static Long userCount = 1L;
    private static Long cartId = 1L;
    private static Long productCount = 1L;
    private static Long orderCount = 1L;

    public static void addProduct(Product product) {
        product.setId(productCount++);
        products.add(product);
    }
}
