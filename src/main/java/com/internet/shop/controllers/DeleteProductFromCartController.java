package com.internet.shop.controllers;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductFromCartController extends HttpServlet {
    public static final Long CART_ID = 1L;
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ShoppingCartService cartService = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService = (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        cartService.deleteProduct(cartService.get(CART_ID), productService.get(id));
        resp.sendRedirect(req.getContextPath() + "/carts/cart");
    }
}
