package com.internet.shop.db;

import com.internet.shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();

    private static long userCount = 0;
    private static long cartId = 0;
    private static long productCount = 0;
    private static long orderCount = 0;

    public static void addProduct(Product product) {
        product.setId(productCount++);
        products.add(product);
    }
}
