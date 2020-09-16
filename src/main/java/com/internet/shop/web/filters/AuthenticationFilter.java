package com.internet.shop.web.filters;

import com.internet.shop.controllers.user.LoginController;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {
    private Set<String> accessibleUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accessibleUrls.add("/login");
        accessibleUrls.add("/registration");
        accessibleUrls.add("/products/all");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getServletPath();
        if (isAccessesible(url)) {
            chain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

    private boolean isAccessesible(String urlString) {
        return accessibleUrls.stream()
                .anyMatch(url -> url.equals(urlString));
    }
}
