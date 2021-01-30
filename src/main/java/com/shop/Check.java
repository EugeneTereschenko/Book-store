package com.shop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = { "/Authenticator" }, servletNames = { "Authenticator" })
public class Check implements Filter {
    public void destroy() {
        System.out.println("Filter destroy()");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Filter --->>> before doFilter()");
        chain.doFilter(req, resp);
        System.out.println("Filter <<<--- after doFilter()");
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("Filter init()");
    }

}
