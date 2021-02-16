package com.shop;

import com.shop.db.BookDAO;
import com.shop.db.UserDAO;
import com.shop.entity.Book;
import com.shop.entity.User;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/store")
public class BookShopStore extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch(action) {
            case "/insertbook":
                try {
                    insertbook(request, response);
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


        switch(action) {
            case "/showbooks":
                try {
                    showbooks(request, response);
                 } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            }
            break;
            case "/showusers":
                try {
                    showusers(request, response);
                } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            break;
        default:
        break;
    }

    }

    private void insertbook(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String temp = request.getParameter("book");

        System.out.println(temp);

            JSONObject myJsonObject = new JSONObject(temp);

            String image = myJsonObject.getString("image");
            String title = myJsonObject.getString("title");
            String author = myJsonObject.getString("author");
            String price = myJsonObject.getString("price");
            String year = myJsonObject.getString("year");
            String description = myJsonObject.getString("description");
            String height = myJsonObject.getString("height");
            String width = myJsonObject.getString("width");
            String depth = myJsonObject.getString("depth");
            String instock = myJsonObject.getString("instock");
            String materials = myJsonObject.getString("materials");


        BookDAO bookDAO = new BookDAO();
        boolean flag = bookDAO.insertBook(title, description, image, materials, Integer.parseInt(price), Integer.parseInt(height), Integer.parseInt(width), Integer.parseInt(depth), year, Integer.parseInt(instock), author);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(flag);
        out.flush();
        out.close();

    }

    private void showbooks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        BookDAO bookDAO = new BookDAO();
        HttpSession session = request.getSession();
        List<Book> viewbooks = bookDAO.findAllBooks();
        session.setAttribute("viewbooks", viewbooks);

        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/books.jsp");
        dispatcher.forward(request, response);
    }


    private void showusers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        List<User> viewusers = userDAO.findAllUsers();
        session.setAttribute("viewusers", viewusers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/users.jsp");
        dispatcher.forward(request, response);
    }

}
