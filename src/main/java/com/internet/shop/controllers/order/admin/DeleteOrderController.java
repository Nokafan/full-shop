package com.internet.shop.controllers.order.admin;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.interfaces.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("id"));
        orderService.delete(orderId);
        resp.sendRedirect(req.getContextPath() + "/orders/edit");
    }
}
