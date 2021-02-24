package com.shop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(urlPatterns = { "/authentication" }, servletNames = { "authentication" })
public class Check implements Filter {

    static final Logger logger = Logger.getLogger(String.valueOf(Check.class));
    private ServletContext context;

    public void destroy() {
        logger.info("Filter destroy()");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        String uri = ((HttpServletRequest) req).getRequestURI();

        HttpSession session = ((HttpServletRequest) req).getSession(false);
        Object user_o = ((HttpServletRequest) req).getSession().getAttribute("username");


        logger.info(" username " + user_o);

        if (user_o == null && !(uri.endsWith("index.jsp") || uri.endsWith("shop.jsp") ||  uri.endsWith("/authentication") || uri.endsWith("registration.jsp") || uri.endsWith("/registration"))) {
             RequestDispatcher requestDispatcher = request
                 .getRequestDispatcher("index.jsp");
         requestDispatcher.forward(request, response);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        logger.info("Filter init()");
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");

    }

}
