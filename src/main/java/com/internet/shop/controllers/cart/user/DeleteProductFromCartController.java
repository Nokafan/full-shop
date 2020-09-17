package com.internet.shop.controllers.cart.user;

import com.internet.shop.controllers.user.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductFromCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        Long productId = Long.valueOf(req.getParameter("id"));
        cartService.deleteProduct(cartService.getByUserId(userId), productService.get(productId));
        resp.sendRedirect(req.getContextPath() + "/carts/cart");
    }
}
