package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectDataController extends HttpServlet {
    public static final User USER_1 = new User("Vasya", "simplyVasya","123");
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/injectData.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String check = req.getParameter("check");
        if (check.equals("true")) {
            userService.create(USER_1);
            cartService.create(new ShoppingCart(USER_1.getId()));
            req.setAttribute("message", "Data injected");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req,resp);
        } else {
            req.setAttribute("message", "Data wasn't injected");
            req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req,resp);
        }
    }
}
