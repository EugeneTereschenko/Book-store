package com.shop;


import com.shop.db.*;
import com.shop.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

@WebServlet("/authentication")
public class BookShopServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();

        switch(action)
        {
            case "/authentication":
                loginUser((HttpServletRequest) request, response);
                break;
            case "/booksorder":
                try {
                    booksorder(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/items":
                try {
                    items(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/registration":
                try {
                    registration(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/books":
                try {
                    viewBooks(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/addressdata":
                try {
                    addressdata(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/deliverydata":
                try {
                    deliverydata(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/paymentdata":
                try {
                    paymentdata(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "/confirmdata":
                try {
                    confirmdata(request, response);
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


        switch(action)
        {
            case "/logout":
                logoutUser(request, response);
                break;
            case "/address":
                address(request, response);
                break;
            case "/delivery":
                delivery(request, response);
                break;
            case "/payment":
                payment(request, response);
                break;
            case "/confirm":
                try {
                    confirm(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "/complete":
                complete(request, response);
                break;
            case "/calendar":
                calendar(request, response);
                break;
            default:
                break;
        }

    }

    private void calendar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("calendar check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/calendar.jsp").include(request, response);
    }

    private void complete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("payment check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/complete.jsp").include(request, response);
    }

    private void confirmdata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String promocode = request.getParameter("promocode");

        System.out.println(promocode);

        if (promocode != null) {
            int userId = 0;
            int cartId = 0;
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String valueID = cookies[i].getValue();
                if (name.equals("userid")) {
                    userId = Integer.parseInt(valueID);
                }
                if (name.equals("cartid")) {
                    cartId = Integer.parseInt(valueID);
                }
            }

            if (Integer.parseInt(promocode) == 123123) {
            Cart cart = new Cart();
            CartDAO cartDAO = new CartDAO();
            cart = cartDAO.checkCartByStep(cartId, "confirm");
            cartDAO.updateCartCoupon(cartId, Integer.parseInt(promocode));
            cartDAO.updateCart(cartId, "payment");

            Delivery delivery = new Delivery();
            DeliveryDAO deliveryDAO = new DeliveryDAO();
            delivery = deliveryDAO.checkDeliveryById(cart.getDelivery_id());

            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(Integer.toString(delivery.getPrice() + cart.order_total_price - 5));
            out.flush();
            out.close();
            }
        }

    }

    private void confirm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        System.out.println("confirm check");

//

        int userId = 0;
        int cartId = 0;
        int totppriceId = 0;
        int ordpriceId = 0;
        int totpriceId = 0;


        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            String valueID = cookies[i].getValue();
            if (name.equals("userid")) {
                userId = Integer.parseInt(valueID);
            }
            if (name.equals("cartid")) {
                cartId = Integer.parseInt(valueID);
            }
            if (name.equals("totppriceid")) {
                totppriceId = Integer.parseInt(valueID);
            }
            if (name.equals("ordpriceid")) {
                ordpriceId = Integer.parseInt(valueID);
            }
            if (name.equals("totpriceid")) {
                totpriceId = Integer.parseInt(valueID);
            }
        }

        System.out.println("cart and user  confirm " + userId + " cart" + cartId + " totppriceid " + totppriceId + " ordpriceid " + ordpriceId + " totpriceid " + totpriceId);


        HttpSession session = request.getSession();

        session.setAttribute("totalProductPrice", Integer.toString(totppriceId));
        session.setAttribute("orderProductPrice", Integer.toString(ordpriceId));
        session.setAttribute("totalPrice", Integer.toString(totpriceId));


        request.getRequestDispatcher("./checkout/confirm.jsp").include(request, response);
    }

    private void payment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("./checkout/payment.jsp").include(request, response);
    }

    private void paymentdata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException, ParseException {

        String paymentMethod = request.getParameter("paymentMethod");
        String ccname = request.getParameter("credname");
        String ccnumber = request.getParameter("crednumber");
        String ccexpiration = request.getParameter("credexpiration");
        String cccvv = request.getParameter("credcvv");

        System.out.println(" paymentMethod " + paymentMethod + " ccname " + ccname + " ccnumber " + ccnumber + " ccexpiration " + ccexpiration + " cccvv " + cccvv);

        if (ccname != null && ccnumber != null) {

            int userId = 0;
            int cartId = 0;

            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String valueID = cookies[i].getValue();
                if (name.equals("userid")) {
                    userId = Integer.parseInt(valueID);
                }
                if (name.equals("cartid")) {
                    cartId = Integer.parseInt(valueID);
                }
            }



            Cart cart = new Cart();
            CartDAO cartDAO = new CartDAO();
            cart = cartDAO.checkCartByStep(cartId, "payment");
            cartDAO.updateCart(cartId, "complete");

            System.out.println(" cart.getDelivery_id() " + cart.getDelivery_id());

            Card card = new Card();
            CardDAO cardDAO = new CardDAO();

            card = CardDAO.insertCard(userId, ccexpiration, Integer.parseInt(cccvv), ccname, ccnumber);

            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/complete.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/payment.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    private void delivery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("delivery check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/delivery.jsp").include(request, response);
    }

    private void deliverydata(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        String deliveryTO = request.getParameter("delivery");
        String datetimeOrderTO = request.getParameter("timedateOrder");

        DeliveryDAO deliveryDAO = new DeliveryDAO();
        Delivery delivery = new Delivery();

        int totalDeliver = 0;

        if (deliveryTO != null && datetimeOrderTO != null){

            if (deliveryTO.equals("expected")){
                delivery = deliveryDAO.insertDelivery(deliveryTO, datetimeOrderTO, 5);
                totalDeliver = 5;
            }
            if (deliveryTO.equals("standard")){
                delivery = deliveryDAO.insertDelivery(deliveryTO, datetimeOrderTO, 10);
                totalDeliver = 10;
            }
            if (deliveryTO.equals("collect")){
                delivery = deliveryDAO.insertDelivery(deliveryTO, datetimeOrderTO, 15);
                totalDeliver = 15;
            }
            if (deliveryTO.equals("online")){
                delivery = deliveryDAO.insertDelivery(deliveryTO, datetimeOrderTO, 1);
                totalDeliver = 1;
            }

            int userId = 0;
            int cartId = 0;

            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String valueID = cookies[i].getValue();
                if (name.equals("userid")) {
                    userId = Integer.parseInt(valueID);
                }
                if (name.equals("cartid")) {
                    cartId = Integer.parseInt(valueID);
                }
            }


            HttpSession session = request.getSession();
            String addressId=(String)session.getAttribute("addressid");
            System.out.println(" addressId " + addressId);


            Cart cart = new Cart();
            CartDAO cartDAO = new CartDAO();
            cart = cartDAO.checkCartByStep(cartId, "delivery");

            Address address = new Address();
            AddressDAO addressDAO = new AddressDAO();
            address = addressDAO.checkAddressById(Integer.parseInt(addressId));


            System.out.println(" address.getCity() " + address.getCity() + " cartid " + cartId + " Delivery " + delivery.getId() + " cart id " + cart.getId());

            OrderitemDAO orderitemDAO = new OrderitemDAO();

            orderitemDAO.insertOrderitem(delivery.getId(), cart.getId());

            cart = cartDAO.checkCartByStep(cartId, "delivery");
            System.out.println(" cart before " + cart.getId());
            cartDAO.updateCartDelivery(cartId, delivery.getId(), "confirm");

            System.out.println("update cart " + cart.getId());

            addressDAO.updateDeliveryById(Integer.parseInt(addressId), delivery.getId(), userId);


            String message = null;
            JSONObject json = new JSONObject();
            
            json.put("totppriceid", cart.item_total_price);
            json.put("ordpriceid", cart.order_total_price);
            json.put("totpriceid", totalDeliver + cart.order_total_price);
            
            message = json.toString();
            System.out.println(message);

            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(message);
            out.flush();
            out.close();

        }


        System.out.println(" delivery " + deliveryTO + " datetimeOrder " + datetimeOrderTO);
    }

    private void address(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("address check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/address.jsp").include(request, response);
    }

    private void addressdata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String addressTo = request.getParameter("address");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String state = request.getParameter("state");
        String sameaddress = request.getParameter("same-address");
        String saveinfo = request.getParameter("save-info");
        String zip = request.getParameter("zip");

        System.out.println(" firstname " + firstName + " lastname " + lastName + " username " + username + " email " + email + " address " + addressTo);
        System.out.println(" phone " + phone + " country " + country + " state " + state + " sameaddress " + sameaddress);
        System.out.println(" saveinfo " + saveinfo + " zip " + zip);

        if (email != null) {
            int userId = 0;
            int cartId = 0;

            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String valueID = cookies[i].getValue();
                if (name.equals("userid")) {
                    userId = Integer.parseInt(valueID);
                }
                if (name.equals("cartid")) {
                    cartId = Integer.parseInt(valueID);
                }
            }

            System.out.println(" cart id " + cartId);
            System.out.println(" user id " + userId);

            AddressDAO addressDAO = new AddressDAO();
            Address address = new Address();
            address = addressDAO.insertAddress("shipping", firstName, lastName, addressTo, state, Integer.parseInt(zip), country, phone, userId);

            CartDAO cartDAO = new CartDAO();

            cartDAO.updateCart(cartId,"delivery");

            Cookie cookie = new Cookie("addressid", Integer.toString(address.getId()));
            cookie.setMaxAge(1800);
            response.addCookie(cookie);

            HttpSession session = request.getSession();
            session.setAttribute("addressid", Integer.toString(address.getId()));

            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/delivery.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/address.jsp");
            requestDispatcher.forward(request, response);
        }


    }

    private static void items(HttpServletRequest request, HttpServletResponse response) throws ServletException, ClassNotFoundException, IOException {

        String userId = null;
        String temp = request.getParameter("books");
        BookDAO bookDAO = new BookDAO();
        CartDAO cartDAO = new CartDAO();
        ItemDAO itemDAO = new ItemDAO();
        Cart cart = new Cart();
        int totalPrice = bookDAO.totalPrice(temp);

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            String valueID = cookies[i].getValue();
            if (name.equals("userid")){
                userId = valueID;
            }
        }

        cart = cartDAO.insertCart(Integer.parseInt(userId), totalPrice + 2, totalPrice, "address");
        System.out.println(" userId " + userId + " totalPrice " + totalPrice + " cart id " + cart.getId());
        itemDAO.insertItem(temp, cart.getId());

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(Integer.toString(cart.getId()));
        out.flush();
        out.close();

    }

    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, ClassNotFoundException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("password_confirm");

        String language = request.getParameter("lang");

        System.out.println(" username " + username + " email " + email + " password " + password + " password_confirm " + password_confirm);
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();

        User user = userDAO.inputUser(username, email, password, password_confirm);
            try {
                String destPage = "index.jsp";
                if (user != null) {
                    HttpSession session = request.getSession();
                    List<Book> books = bookDAO.findFromTO(1, 4);
                    session.setAttribute("books", books);
                    session.setAttribute("username", user.getEmail());
                    session.setAttribute("userid", user.getId());

                    if (language.equals("1")) {
                        session.setAttribute("idlocal", "en");
                    }
                    if (language.equals("2")){
                        session.setAttribute("idlocal", "ru");
                    } else {
                        session.setAttribute("idlocal", "en");
                    }


                    String str = Integer.toString(user.getId());
                    Cookie cookie = new Cookie("userid", str);
                    cookie.setMaxAge(1800);
                    response.addCookie(cookie);

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

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String email = request.getParameter("login");
        String encrypted_password = request.getParameter("pass");

        String language = request.getParameter("lang");

        System.out.println(" email " + email + " encrypted_password " + encrypted_password + "test input or enter" + " choose language " + language);
        UserDAO userDAO = new UserDAO();
        BookDAO bookDAO = new BookDAO();

        try {
            User user = userDAO.checkLoginandPassword(email, encrypted_password);

            String destPage = "index.jsp";

            if (user != null){
                HttpSession session = request.getSession();
                List<Book> books = bookDAO.findFromTO(1, 4);
                session.setAttribute("books", books);

                session.setAttribute("username", user.getEmail());
                session.setAttribute("userid", user.getId());

                session.setAttribute("roleid", "admin");


                if (language.equals("1")) {
                    session.setAttribute("idlocal", "en");
                }
                if (language.equals("2")){
                    session.setAttribute("idlocal", "ru");
                    response.setContentType("text/html; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");

                } else {
                    session.setAttribute("idlocal", "en");
                }

                String str = Integer.toString(user.getId());
                Cookie cookie = new Cookie("userid", str);
                cookie.setMaxAge(1800);
                response.addCookie(cookie);


                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.print(str);
                out.flush();
                out.close();

            } else {
                response.setContentType("text/plain");
                PrintWriter out = response.getWriter();
                out.print("stop");
                out.flush();
                out.close();
            }

        } catch (Exception ex) {
            throw new ServletException(ex);
        }


    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        System.out.println("test connect");

        if (session != null) {

            session.removeAttribute("books");
            session.removeAttribute("idlocal");
            session.removeAttribute("userid");

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

        String addTo = properties.getProperty("fieldAddtoCart");

        JSONArray ja = new JSONArray();

        for(int i=0; null!=book && i < book.size(); i++) {
            JSONObject json = new JSONObject();
            json.put("bookid", book.get(i).getId());
            json.put("imageid", book.get(i).getImage());
            json.put("priceid", book.get(i).getPrice());
            json.put("titleid", book.get(i).getTitle());
            json.put("authorid", book.get(i).getAuthor());
            json.put("local", addTo);
            ja.put(json);
        }

        JSONObject mainObj = new JSONObject();
        mainObj.put("books", ja);

        //response.setContentType("text/plain");
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(mainObj);
        out.flush();
        out.close();

    }

    private void booksorder(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String rows = request.getParameter("temp1");
        String typeOrder = request.getParameter("temp2");
        List<Book> book = null;


        int rowsOfBook = Integer.parseInt(rows);
        System.out.println(" " + rows + " " + typeOrder);

        String str = null;
        System.out.println("servlet");


        BookDAO bookDAO = new BookDAO();

        if (typeOrder.equals("title")) {
            book = bookDAO.findOrderTOByTitle(rowsOfBook);
        }

        if (typeOrder.equals("author")) {
            book = bookDAO.findOrderTOByAuthor(rowsOfBook);
        }

        if (typeOrder.equals("price")) {
            book = bookDAO.findOrderTOByPrice(rowsOfBook);
        }

        if (typeOrder.equals("year")) {
            book = bookDAO.findOrderTOByYear(rowsOfBook);
        }

        StringBuilder sb = new StringBuilder();


        for(int i=0; null!=book && i < book.size(); i++) {
            // sb.append("<tr><td><img src = \"./images/" + book.get(i).getImage() + "\" height = \"100\" width = \"100\"></td></tr><tr><td height = \"100\" width=\"200\">" + book.get(i).getTitle() + "</td></tr>");
            sb.append("<tr><td colspan=\"5\"><img src = \"./images/" + book.get(i).getImage() + "\" height=\"100\" width=\"100\"/></td></tr>");
            sb.append("<tr id=\"" + book.get(i).getId() + "\"><td colspan=\"5\" class=\"row-data\" id_cost=\"" + book.get(i).getPrice() + "\" id_image=\"./images/" + book.get(i).getImage()  + "\" id=\"col" + book.get(i).getId() + "\"height = \"100\" width=\"200\">" + book.get(i).getTitle() + "</td></tr>");
            sb.append("<tr><td colspan=\"5\" >" + book.get(i).getAuthor() + "</td></tr>");
            sb.append("<tr><td>$" + book.get(i).getPrice() + ".00</td><td colspan=\"4\" align=\"center\"><button id=\"" + book.get(i).getId() + "\" class=\"me btn btn-primary btn-block\">Add to cart</button></td></tr>");
            System.out.println(" title " + book.get(i).getTitle());

        }

        str = sb.toString();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();

    }



}
