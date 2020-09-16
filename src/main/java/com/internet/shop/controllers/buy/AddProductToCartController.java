package com.internet.shop.controllers.buy;

import com.internet.shop.controllers.user.LoginUserController;
import com.internet.shop.lib.Injector;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductToCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("id"));
        Long userId = (Long) req.getSession().getAttribute(LoginUserController.USER_ID);
        cartService.addProduct(cartService.getByUserId(userId),
                productService.get(productId));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
