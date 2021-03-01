package com.shop;

import com.itextpdf.text.DocumentException;
import com.shop.db.BookDAO;
import com.shop.db.CartDAO;
import com.shop.db.DeliveryDAO;
import com.shop.db.UserDAO;
import com.shop.entity.Book;
import com.shop.entity.Cart;
import com.shop.entity.Delivery;
import com.shop.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(BookShopStore.class);

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
            case "/showcarts":
                try {
                    showcart(request, response);
                } catch (ClassNotFoundException | DocumentException e) {
                    e.printStackTrace();
                }
                break;
        default:
        break;
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws IOException
     */
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

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */

    private void deletebook(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("idbook");
        logger.info("deletebook");

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

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */

    private void updatebook(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("book");

        logger.info("updatebook");
        logger.info(temp);


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

    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws IOException
     */

    private void insertbook(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String temp = request.getParameter("book");

        logger.info(temp);

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

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws ClassNotFoundException
     */

    private void showbooks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        BookDAO bookDAO = new BookDAO();
        HttpSession session = request.getSession();
        List<Book> viewbooks = bookDAO.findAllBooks();
        session.setAttribute("viewbooks", viewbooks);

        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/books.jsp");
        dispatcher.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     * @throws ClassNotFoundException
     */

    private void showusers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        List<User> viewusers = userDAO.findAllUsers();
        session.setAttribute("viewusers", viewusers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/users.jsp");
        dispatcher.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws IOException
     */

    private void insertuser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String temp = request.getParameter("user");

        logger.info(temp);


        JSONObject myJsonObject = new JSONObject(temp);



        String emailuser = myJsonObject.getString("emailuser");
        String nameuser = myJsonObject.getString("nameuser");
        String roleuser = myJsonObject.getString("roleuser");
        String password = myJsonObject.getString("password");
        String confirmpassword = myJsonObject.getString("confirmpassword");



        UserDAO userDAO = new UserDAO();
        Boolean flag = userDAO.inputUser(nameuser, emailuser, password, confirmpassword, roleuser);

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
        if (flag != null) {

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

    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws IOException
     */

    private void showOneUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String iduser = request.getParameter("iduser");

        logger.info(" iduser " + iduser);
        User user = new User();
        UserDAO userDAO = new UserDAO();
        user = userDAO.checkUserbyId(Integer.parseInt(iduser));
        logger.info(" userDao get id " + user.getId() + " userDao get email " + user.getEmail());
        JSONObject json = new JSONObject();
        json.put("userid", user.getId());
        json.put("emailid", user.getEmail());
        json.put("nameid", user.getName());
        json.put("roleid", user.getUid());
        json.put("remembid", user.getRemember_created_at());
        json.put("currentid", user.getCurrent_sign_in_at());

        logger.info(json);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }


    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void updateuser(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("user");

        logger.info("updateuser");

        logger.info(temp);

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


        if (role.equals("1")){
            role = "administrator";
        } else if (role.equals("2")){
            role = "manager";
        } if (role.equals("3")){
            role = "user";
        }


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

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */

    private void deleteuser(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("iduser");
        logger.info("deleteuser");

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

    /**
     *
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws ServletException
     * @throws IOException
     * @throws DocumentException
     */
    private void showcart(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException, DocumentException {
        CartDAO cartDAO = new CartDAO();
        List<Cart> viewcarts = cartDAO.findAllCarts();
        HttpSession session = request.getSession();
        User user = new User();
        UserDAO userDAO = new UserDAO();
        List<User> viewcartusers = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Delivery delivery = new Delivery();
        DeliveryDAO deliveryDAO = new DeliveryDAO();

        int quantity = 0;
        Map<Integer, String> treemapbooks = new HashMap<>();
        Map<Integer, String> treemaptotaldeliveries = new HashMap<>();

        int totalcost = 0;

        for (int i = 0; i < viewcarts.size(); i++) {
            StringBuilder sb = new StringBuilder();
            user = userDAO.checkUserbyId(viewcarts.get(i).getUser_id());
            viewcartusers.add(user);
            books = cartDAO.findallBooksByCartID(i+1);
            delivery = deliveryDAO.checkDeliveryById(viewcarts.get(i).getDelivery_id());
            totalcost = delivery.getPrice() + viewcarts.get(i).getOrder_total_price() - 5;

            treemaptotaldeliveries.put(i, Integer.toString(totalcost));

           // util.preparePDFreport(user.getEmail(), books.toArray(), viewcarts.get(i).getUser_id());
            //util.preparePDFreport(user.getEmail());


            int sum = 0;
            sb.append("<ul class=\"list-group list-inline\" width=\"200\">");

            for (Book book : books){
                quantity = cartDAO.findallBookValueByCartID(i+1, book.getId());
               // System.out.println("quantity " + quantity + "i" + i + "book get id" + book.getId());
                sum += quantity;
                sb.append("<li class=\"list-group-item list-group-item-action list-group-item-primary\">");
                sb.append(book.getTitle()).append("\tValue\t:\t").append(quantity).append("</li>");

                //System.out.println(" i " + i + " " + book.getAuthor());
            }
            sb.append("<table width=\"300\"><tr><td></td></tr></table>");
            sb.append("<li class=\"list-group-item list-group-item-action list-group-item-info\">").append("Total value:\t").append(sum);
            sb.append("</li>");
            treemapbooks.put(i, sb.toString());

        }

        session.setAttribute("treemapbooks", treemapbooks);
        session.setAttribute("viewcart", viewcarts);
        session.setAttribute("viewcartusers", viewcartusers);
        session.setAttribute("viewtreemaptotaldeliveries", treemaptotaldeliveries);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/carts.jsp");
        dispatcher.forward(request, response);
    }


    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void updateorder(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        String temp = request.getParameter("idorder");

        logger.info(temp);

        JSONObject myJsonObject = new JSONObject(temp);

        String cartid = myJsonObject.getString("cartid");
        String name = myJsonObject.getString("confirm");


        CartDAO cartDAO = new CartDAO();
        Boolean flag;
        flag = cartDAO.updateCart(Integer.parseInt(cartid), name);

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

}
