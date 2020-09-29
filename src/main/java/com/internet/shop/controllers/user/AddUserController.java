package com.internet.shop.controllers.user;

import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService cartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        String passwordRepeated = req.getParameter("pwd-repeat");

        if (userService.findByLogin(login).get().getLogin() != null) {
            req.setAttribute("message", "Your login are taken");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
        }
        if (password.equals(passwordRepeated)) {
            User user = new User(name, login, password);
            user.setRoles(Set.of(Role.of("USER")));
            userService.create(user);
            cartService.create(new ShoppingCart(user.getId()));
            req.setAttribute("message", "Congratulation " + name + " you are registered!");
            req.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Your password and repeated password are not the same");
            req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
        }
    }
}
