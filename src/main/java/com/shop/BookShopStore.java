package com.shop;

import com.shop.db.BookDAO;
import com.shop.db.CartDAO;
import com.shop.db.UserDAO;
import com.shop.entity.Book;
import com.shop.entity.Cart;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/store")
public class BookShopStore extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch(action) {
            case "/deletebook":
                try {
                    deletebook(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/updatebook":
                try {
                    updatebook(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/insertbook":
                try {
                    insertbook(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/showonebook":
                try {
                    showOneBook(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/insertuser":
                try {
                    insertuser(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/showoneuser":
                try {
                    showOneUser(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/updateuser":
                try {
                    updateuser(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/deleteuser":
                try {
                    deleteuser(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/updateorder":
                try {
                    updateorder(request, response);
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
            case "/showcart":
                try {
                    showcart(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        default:
        break;
    }

    }

    private void showOneBook(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String idbook = request.getParameter("idbook");

        Book book = new Book();
        BookDAO bookDAO = new BookDAO();
        book = bookDAO.checkBookById(Integer.parseInt(idbook));

        JSONObject json = new JSONObject();
        json.put("bookid", book.getId());
        json.put("imageid", book.getImage());
        json.put("titleid", book.getTitle());
        json.put("authorid", book.getAuthor());
        json.put("priceid", book.getPrice());
        json.put("yearid", book.getYear());
        json.put("descrid", book.getDescription());
        json.put("materid", book.getMaterials());

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();

    }

    private void deletebook(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("idbook");
        System.out.println("deletebook");

        BookDAO bookDAO = new BookDAO();
        boolean flag = bookDAO.deleteBook(Integer.parseInt(temp));


        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (flag) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
        out.flush();
        out.close();
    }

    private void updatebook(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("book");

        System.out.println("updatebook");

        System.out.println(temp);

        JSONObject myJsonObject = new JSONObject(temp);

        String boid = myJsonObject.getString("bookid");
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
        boolean flag = bookDAO.updateBook(title, description, image, materials, Integer.parseInt(price), Integer.parseInt(height), Integer.parseInt(width), Integer.parseInt(depth), year, Integer.parseInt(instock), author, Integer.parseInt(boid));



        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (flag) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
        out.flush();
        out.close();
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



        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (flag) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
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


    private void insertuser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String temp = request.getParameter("user");

        System.out.println(temp);

        JSONObject myJsonObject = new JSONObject(temp);



        String emailuser = myJsonObject.getString("emailuser");
        String nameuser = myJsonObject.getString("nameuser");
        String roleuser = myJsonObject.getString("roleuser");
        String password = myJsonObject.getString("password");
        String confirmpassword = myJsonObject.getString("confirmpassword");



        UserDAO userDAO = new UserDAO();
        User user = userDAO.inputUser(nameuser, emailuser, password, confirmpassword, roleuser);

        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (user != null) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
        out.flush();
        out.close();

    }

    private void showOneUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String iduser = request.getParameter("iduser");
        System.out.println(" iduser " + iduser);
        User user = new User();
        UserDAO userDAO = new UserDAO();
        user = userDAO.checkUserbyId(Integer.parseInt(iduser));

        JSONObject json = new JSONObject();
        json.put("userid", user.getId());
        json.put("emailid", user.getEmail());
        json.put("nameid", user.getName());
        json.put("roleid", user.getUid());
        json.put("remembid", user.getRemember_created_at());
        json.put("currentid", user.getCurrent_sign_in_at());

        System.out.println(json);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();

    }

    private void updateuser(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("user");

        System.out.println("updateuser");

        System.out.println(temp);

        JSONObject myJsonObject = new JSONObject(temp);

        String usid = myJsonObject.getString("userid");
        String name = myJsonObject.getString("name");
        String email = myJsonObject.getString("email");
        String role = myJsonObject.getString("role");
        String passwd = myJsonObject.getString("passwd");
        String rememberuser = myJsonObject.getString("rememberuser");
        //String currentuser = myJsonObject.getString("currentuser");



        UserDAO userDAO = new UserDAO();
        User user = new User();

        System.out.println("usid" + usid + " name " + name + " email " + email + " role " + role + " passwd " + passwd + " remember " + rememberuser);

        user = userDAO.updateUser(name, email, passwd, passwd, role, Integer.parseInt(usid), rememberuser);


        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (user != null) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
        out.flush();
        out.close();
    }


    private void deleteuser(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("iduser");
        System.out.println("deleteuser");

        UserDAO userDAO = new UserDAO();
        boolean flag = userDAO.deleteUser(Integer.parseInt(temp));


        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (flag) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
        out.flush();
        out.close();
    }
    private void showcart(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
        CartDAO cartDAO = new CartDAO();
        List<Cart> viewcarts = cartDAO.findAllCarts();
        HttpSession session = request.getSession();
        User user = new User();
        UserDAO userDAO = new UserDAO();
        List<User> viewcartusers = new ArrayList<>();
        List<Book> books = new ArrayList<>();



        Map<Integer, String> treemapbooks = new HashMap<>();

        for (int i = 0; i < viewcarts.size(); i++) {
            StringBuilder sb = new StringBuilder();
            user = userDAO.checkUserbyId(viewcarts.get(i).getUser_id());
            viewcartusers.add(user);
            books = cartDAO.findallBooksByCartID(i);

            for (Book book : books){
                sb.append(book.getTitle()).append(".\t");
                treemapbooks.put(i, sb.toString());
                System.out.println(" i " + i + " " + book.getAuthor());
            }

        }

        session.setAttribute("treemapbooks", treemapbooks);
        session.setAttribute("viewcart", viewcarts);
        session.setAttribute("viewcartusers", viewcartusers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/cart.jsp");
        dispatcher.forward(request, response);
    }

    private void updateorder(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("idorder");

        System.out.println(temp);

        JSONObject myJsonObject = new JSONObject(temp);

        String cartid = myJsonObject.getString("cartid");
        String name = myJsonObject.getString("confirm");


        CartDAO cartDAO = new CartDAO();
        Cart cart = new Cart();
        cart = cartDAO.updateCart(Integer.parseInt(cartid), name);

        HttpSession session = request.getSession(true);
        String setLocal = (String) session.getAttribute("idlocal");
        String fileproper = null;
        if (setLocal.equals("ru")){
            fileproper = "app_ru.properties";
        } else {
            fileproper = "app_en.properties";
        }

        Properties properties = new Properties();
        InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileproper);
        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        properties.load(reader);

        String addToTrue = properties.getProperty("fieldSuccess");
        String addToFalse = properties.getProperty("fieldError");
        JSONObject jsonMain = new JSONObject();
        if (cart != null) {

            jsonMain.put("flagid", "true");
            jsonMain.put("localid", addToTrue);
        } else {
            jsonMain.put("flagid", "flag");
            jsonMain.put("localid", addToFalse);
        }

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonMain);
        out.flush();
        out.close();

    }

}
