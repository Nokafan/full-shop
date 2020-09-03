package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.ProductService;
import java.math.BigDecimal;

public class WebShopApp {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product cup = new Product("Cup", 25.40);
        Product pencil = new Product("Pencil", 8.25);
        Product table = new Product("Table", 12999.99);

        productService.create(cup);
        productService.create(pencil);
        productService.create(table);

        for (Product product : productService.getAll()) {
            System.out.println(product);
        }

        System.out.println("\nPrice before sale");
        productService.getAll().stream()
                .peek(System.out::println)
                .forEach(product -> product.setPrice(
                        product.getPrice().multiply(BigDecimal.valueOf(0.9))));

        System.out.println("\nPrice after sales started, price -10%");
        productService.getAll().forEach(System.out::println);

        Product testProduct = new Product("Test Product", 22);
        testProduct.setId(cup.getId());
        productService.update(testProduct);
        System.out.println("\nAfter update by id = 0");
    }
}

