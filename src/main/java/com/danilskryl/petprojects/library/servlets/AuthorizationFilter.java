package com.danilskryl.petprojects.library.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", value = "/cabinet.jsp")
public class AuthorizationFilter implements Filter {
    private final Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.debug("AuthorizationFilter started");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Cookie[] cookies = httpRequest.getCookies();
        boolean isLoggedIn = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    LOG.info("User [{}} was found", cookie.getValue());
                    isLoggedIn = true;
                    break;
                }
            }
        }

        if (!isLoggedIn) {
            LOG.debug("User isn't authorized. Redirect to login.jsp");
            httpResponse.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
