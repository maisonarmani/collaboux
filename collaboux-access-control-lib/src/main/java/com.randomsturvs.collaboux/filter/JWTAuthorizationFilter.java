package com.randomsturvs.collaboux.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class JWTAuthorizationFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(String.format("this is just another: %s", servletRequest.getLocalAddr()));
    }
}