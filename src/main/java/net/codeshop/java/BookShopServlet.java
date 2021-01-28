package net.codeshop.java;

import net.codeshop.java.db.BookDAO;
import net.codeshop.java.db.UserDAO;
import net.codeshop.java.entity.Book;
import net.codeshop.java.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
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
            default:
                break;
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();

        System.out.println("test logout");

        switch(action)
        {
            case "/logout":
                logoutUser(request, response);
                break;
            default:
                break;
        }

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
                List<Book> books = bookDAO.findAllBooks();
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
        if (session != null) {

            session.removeAttribute("user");


            //session.getAttribute("user");

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

}
