package com.internet.shop;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.math.BigDecimal;

public class WebShopApp {
    private static final String LINE_DIVIDER = "=================================================";
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

        System.out.println(LINE_DIVIDER);
        System.out.println("hw-part-2-user");
        UserService userService = (UserService) injector.getInstance(UserService.class);

        User petya = new User("Petya", "123", "456");
        User vasya = new User("Vasya", "234", "567");
        User kolya = new User("Kolya", "321", "654");

        userService.create(petya);
        userService.create(vasya);
        userService.create(kolya);

        System.out.println(userService.get(1L));

        System.out.println(LINE_DIVIDER);
        userService.getAll().forEach(System.out::println);

        System.out.println(LINE_DIVIDER);
        User updatedUser = new User("Petya", "111", "222");
        updatedUser.setId(1L);
        userService.update(updatedUser);
        userService.delete(3L);
        userService.getAll().forEach(System.out::println);

        System.out.println(LINE_DIVIDER);
        System.out.println("hw-part-2-shoppingCart");

        ShoppingCartService cartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        ShoppingCart shoppingCart = new ShoppingCart(petya.getId());
        cartService.create(shoppingCart);
        System.out.println("id = " + shoppingCart.getId());
        cartService.addProduct(shoppingCart, productService.get(1L));
        cartService.addProduct(shoppingCart, productService.get(2L));
        cartService.get(1L).getProducts().forEach(System.out::println);
        System.out.println("Delete " + testProduct);
        cartService.deleteProduct(shoppingCart, testProduct);
        shoppingCart.getProducts().forEach(System.out::println);
        System.out.println("Clear " + shoppingCart);
        cartService.clear(shoppingCart);
        System.out.println(shoppingCart + " After clear");

        System.out.println(LINE_DIVIDER);
        System.out.println("hw-part-2-order");
        ShoppingCart shoppingCart2 = new ShoppingCart(vasya.getId());
        cartService.create(shoppingCart2);
        cartService.addProduct(shoppingCart, productService.get(1L));
        cartService.addProduct(shoppingCart2, productService.get(2L));

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

        System.out.println("All orders by id:");
        orderService.completeOrder(shoppingCart);
        orderService.completeOrder(shoppingCart2);
        orderService.getAll().forEach(System.out::println);
        System.out.println("Get by order id");
        System.out.println(orderService.get(1L));
        System.out.println("Delete order id = 2");
        orderService.delete(2L);
        System.out.println("Print all after delete");
        orderService.getAll().forEach(System.out::println);

        System.out.println("Shopping cart id = 1");
        System.out.println(cartService.get(1L));
        System.out.println("Shopping cart id = 2");
        System.out.println(cartService.get(2L));
        System.out.println("Get user orders by id:");
        System.out.println(orderService.getUserOrders(1L));
        System.out.println("Get orders by removed id:");
        System.out.println(orderService.getUserOrders(2L));
    }
}
