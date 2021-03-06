package com.internet.shop.web.filters;

import com.internet.shop.controllers.user.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/admin/cart/remove", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/carts/edit", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/order/remove", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/edit", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/add", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/users/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/edit", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/products/remove", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/cart/clear", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/cart/detail", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/carts/cart", Set.of(Role.RoleName.USER));
        protectedUrls.put("/products/buy", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders/all", Set.of(Role.RoleName.USER));
        protectedUrls.put("/user/cart/remove", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order/create", Set.of(Role.RoleName.USER));
        protectedUrls.put("/order/confirm", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requstedUrl = req.getServletPath();
        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        if (!protectedUrls.containsKey(requstedUrl)
                || isAuthorized(userService.get(userId), protectedUrls.get(requstedUrl))) {
            chain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorisedRoles) {
        return user.getRoles().stream()
                .map(Role::getRoleName)
                .anyMatch(authorisedRoles::contains);
    }
}
