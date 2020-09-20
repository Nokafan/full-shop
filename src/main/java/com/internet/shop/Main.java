package com.internet.shop;

import com.internet.shop.dao.jdbc.ProductDaoJdbcImpl;
import com.internet.shop.model.Product;

public class Main {

    public static void main(String[] args) {
        ProductDaoJdbcImpl productDaoJdbc = new ProductDaoJdbcImpl();
        Product cup = new Product("Cup", 25.40);
        Product pencil = new Product("Pencil", 8.25);
        Product table = new Product("Table", 12999.99);
        productDaoJdbc.create(cup);
        productDaoJdbc.create(pencil);
        productDaoJdbc.create(table);

        System.out.println(cup.getId());
        productDaoJdbc.getAll().forEach(System.out::println);

        System.out.println(productDaoJdbc.get(1L));
        Product test = productDaoJdbc.get(1L).get();
        test.setName("Cupuchin");
        productDaoJdbc.update(test);
        productDaoJdbc.delete(1L);
        productDaoJdbc.delete(2L);
        productDaoJdbc.delete(3L);
        productDaoJdbc.delete(4L);
        productDaoJdbc.delete(5L);
        productDaoJdbc.delete(6L);
        productDaoJdbc.delete(7L);
        productDaoJdbc.delete(34L);
        productDaoJdbc.getAll().forEach(System.out::println);
    }
}
