package com.woo.errorpage.filter;

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

        try{
            log.info("REQUEST [{}][{}]",request.getDispatcherType(),requestURI);
            log.info("필터호출!");
            chain.doFilter(request,response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("RESPONSE [{}][{]]", request.getDispatcherType(), requestURI);
        }

    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
