package com.shop;

import com.shop.db.BookDAO;
import com.shop.db.UserDAO;
import com.shop.entity.Book;
import com.shop.entity.User;
import jdk.nashorn.api.scripting.JSObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Authenticator")
public class BookShopServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=root";
    static final String SQL_FIND_ALL_PRODUCTS = "SELECT * FROM users";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();

        switch(action)
        {
            case "/Authenticator":
                loginUser((HttpServletRequest) request, response);
                break;
            case "/books":
                try {
                    viewBooks(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();

        System.out.println("test logout");

        System.out.println(action.toString());

        switch(action)
        {
            case "/logout":
                logoutUser(request, response);
                break;
            default:
                break;
        }

    }

    private void viewBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String idBookBegin = request.getParameter("temp1");
        String idBookEnd = request.getParameter("temp2");

        int begin = Integer.parseInt(idBookBegin);
        int end = Integer.parseInt(idBookEnd);

        System.out.println(" " + idBookBegin + " " + idBookEnd);

        String str = null;
        System.out.println("servlet");

        BookDAO bookDAO = new BookDAO();

        List<Book> book = bookDAO.findFromTO(begin, end);

        StringBuilder sb = new StringBuilder();

        for(int i=0; null!=book && i < book.size(); i++) {
            sb.append("<tr><td><img src = \"./images/" + book.get(i).getImage() + "\" height = \"100\" width = \"100\"></td></tr><tr><td height = \"100\" width=\"200\">" + book.get(i).getTitle() + "</td></tr>");
        }

        str = sb.toString();

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();


    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String email = request.getParameter("login");
        String encrypted_password = request.getParameter("pass");

        System.out.println(" email " + email + " encrypted_password " + encrypted_password + "test input or enter");
        UserDAO userDAO = new UserDAO();


        BookDAO bookDAO = new BookDAO();

        try {

            User user = userDAO.checkLogin(email, encrypted_password);
            String destPage = "index.jsp";

            if (user != null){
                HttpSession session = request.getSession();
                List<Book> books = bookDAO.findFromTO(1, 4);
                session.setAttribute("books", books);

                //request.setAttribute("data", data);
                request.getRequestDispatcher("shop.jsp").forward(request, response);

                destPage = "shop.jsp";
            } else {
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            throw new ServletException(ex);
        }


    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        System.out.println("test connect");

        if (session != null) {

            session.removeAttribute("books");

            System.out.println("test connect2");
            //session.getAttribute("user");

            ServletContext servletContext = getServletContext();
            System.out.println(getServletInfo().toString());
            System.out.println(request.toString());
            System.out.println(response.toString());

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

    }

}
