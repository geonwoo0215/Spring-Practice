package com.forwardingredirect.forwardingredirect.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        log.info("log filter doFilter start : {}", requestURI);
        chain.doFilter(request, response);
        log.info("log filter doFilter end : {}", requestURI);
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
