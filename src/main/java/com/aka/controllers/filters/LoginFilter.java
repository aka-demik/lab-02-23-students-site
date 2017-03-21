package com.aka.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        request.setCharacterEncoding("UTF-8");
        String loginURI = req.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("logged") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI)
                || req.getRequestURI().equals(req.getContextPath() + "/registration");

        if (loggedIn || loginRequest) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}
