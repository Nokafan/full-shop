package com.internet.shop.controllers.cart.admin;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCartDetailsAdminController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("com.internet.shop");
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long cartId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("cart", cartService.get(cartId));
        req.getRequestDispatcher("/WEB-INF/views/carts/products/all.jsp").forward(req, resp);
    }
}
