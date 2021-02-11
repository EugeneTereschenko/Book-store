package com.shop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/authentication" }, servletNames = { "authentication" })
public class Check implements Filter {

    private ServletContext context;

    public void destroy() {
        System.out.println("Filter destroy()");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getServletPath();


        this.context.log("Requested Resource::"+uri);

        System.out.println(" check " + uri);

        HttpSession session = request.getSession(false);

      //  if (uri.endsWith("index.jsp")){
      //      System.out.println("Filter --->>> before doFilter()");
      //      chain.doFilter(req, resp);
      //      System.out.println("Filter <<<--- after doFilter()");
      //  }

        //if (uri.endsWith("confirm") && session == null ){
        //HttpSession session = request.getSession(false);
        if (session == null) {

        //if (!(uri.endsWith("jsp") || uri.endsWith("confirm"))) {+
            //if (session == null) {
             //   System.out.println("redirect to authentication");
              //  this.context.log("Unauthorized access request");
                // response.sendRedirect("bookstore/index.jsp");
        //request.getRequestDispatcher("./checkout/delivery.jsp").include(request, response);

                request.getRequestDispatcher("index.jsp").include(request, response);
              //  RequestDispatcher requestDispatcher = request
            //            .getRequestDispatcher("./bookstore/index.jsp");
            //    requestDispatcher.forward(request, response);
          //  }
        } else {
            System.out.println("Filter --->>> before doFilter()");
            chain.doFilter(req, resp);
            System.out.println("Filter <<<--- after doFilter()");
        }
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("Filter init()");
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

}
