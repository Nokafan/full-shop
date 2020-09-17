package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    public static final User USER_1 = new User("a", "123","123");
    public static final Product PRODUCT_1 = new Product("Biscuite-1", 101);
    public static final Product PRODUCT_2 = new Product("Biscuite-2", 102);
    public static final Product PRODUCT_3 = new Product("Biscuite-3", 103);
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/inject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String check = req.getParameter("check");
        if (check.equals("true")) {
            USER_1.setRoles(Set.of(Role.of("ADMIN")));
            userService.create(USER_1);
            productService.create(PRODUCT_1);
            productService.create(PRODUCT_2);
            productService.create(PRODUCT_3);
            req.setAttribute("message", "Data injected");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Data wasn't injected");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
        }
    }
}
