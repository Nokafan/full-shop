package com.internet.shop;

import com.internet.shop.dao.interfaces.ProductDao;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;

public class Main {
    private static Injector injector = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductDao productDao = (ProductDao) injector.getInstance(ProductDao.class);
        Product cup = new Product("Cup", 25.40);
        Product pencil = new Product("Pencil", 8.25);
        Product table = new Product("Table", 12999.99);
        productDao.create(cup);
        productDao.create(pencil);
        productDao.create(table);

        System.out.println(cup.getId());
        productDao.getAll().forEach(System.out::println);

        System.out.println(productDao.get(11L));
        Product test = productDao.get(11L).get();
        test.setName("Cupuchin");
        productDao.update(test);
        productDao.delete(1L);
        productDao.delete(2L);
        productDao.delete(3L);
        productDao.delete(4L);
        productDao.delete(5L);
        productDao.delete(6L);
        productDao.delete(7L);
        productDao.getAll().forEach(System.out::println);
    }
}
