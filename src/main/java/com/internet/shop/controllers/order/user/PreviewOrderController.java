package com.internet.shop.controllers.order.user;

import com.internet.shop.controllers.user.LoginUserController;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreviewOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(LoginUserController.USER_ID);
        List<Product> cartProduct = cartService.getByUserId(userId).getProducts();
        req.setAttribute("products", cartProduct);
        req.getRequestDispatcher("/WEB-INF/views/orders/confirm.jsp").forward(req, resp);
    }
}
