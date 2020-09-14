package com.internet.shop.controllers.cart;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserShoppingCartAdminController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products",cartService.getByUserId(USER_ID).getProducts());
        req.getRequestDispatcher("/WEB-INF/views/carts/edit.jsp").forward(req,resp);
    }
}
