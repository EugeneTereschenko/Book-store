package com.shop;


import com.shop.db.*;
import com.shop.entity.Book;
import com.shop.entity.Cart;
import com.shop.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/authentication")
public class BookShopServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    //static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=root&password=AcPqw.TO,CYU.dcP12";
    //static final String SQL_FIND_ALL_PRODUCTS = "SELECT * FROM users";



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getServletPath();

        switch(action)
        {
            case "/authentication":
                loginUser((HttpServletRequest) request, response);
                break;
            case "/cards":
                cards(request, response);
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
                deliverydata(request, response);
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
                confirm(request, response);
                break;
            case "/complete":
                complete(request, response);
                break;
            default:
                break;
        }

    }

    private void complete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("payment check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/complete.jsp").include(request, response);
    }

    private void confirm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("delivery check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/confirm.jsp").include(request, response);
    }

    private void payment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("payment check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/payment.jsp").include(request, response);
    }

    private void delivery(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("delivery check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/delivery.jsp").include(request, response);
    }

    private void address(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("address check");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("./checkout/address.jsp").include(request, response);
    }

    private void deliverydata(HttpServletRequest request, HttpServletResponse response){
        String delivery = request.getParameter("delivery");
        System.out.println(delivery);
    }

    private void addressdata(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String address2 = request.getParameter("address2");
        String phone = request.getParameter("phone");
        String country = request.getParameter("country");
        String state = request.getParameter("state");
        //String checkbox = request.getParameter("checkbox");
        String sameaddress = request.getParameter("same-address");
        String saveinfo = request.getParameter("save-info");
        String zip = request.getParameter("zip");

        System.out.println(" firstname " + firstName + " lastname " + lastName + " username " + username + " email " + email + " address " + address);
        System.out.println(" address2 " + address2 + " phone " + phone + " country " + country + " state " + state + " sameaddress " + sameaddress);
        System.out.println(" saveinfo " + saveinfo + " zip " + zip);

        if (email != null) {
            int userId = 0;

            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName();
                String valueID = cookies[i].getValue();
                if (name.equals("userid")) {
                    userId = Integer.parseInt(valueID);
                }
            }

            AddressDAO addressDAO = new AddressDAO();

            addressDAO.insertAddress("shipping", firstName, lastName, address, "Dnepr", Integer.parseInt(zip), country, phone, userId);

            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/delivery.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher("./checkout/address.jsp");
            requestDispatcher.forward(request, response);
        }


    }

    private static void items(HttpServletRequest request, HttpServletResponse response) throws ServletException, ClassNotFoundException {

        String userId = null;
        String temp = request.getParameter("books");
        BookDAO bookDAO = new BookDAO();
        CartDAO cartDAO = new CartDAO();
        ItemDAO itemDAO = new ItemDAO();
        Cart cart = new Cart();
        int totalPrice = bookDAO.totalPrice(temp);
        //System.out.println(totalPrice);


        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            String valueID = cookies[i].getValue();
            if (name.equals("userid")){
                userId = valueID;
            }
        }


        cart = cartDAO.insertCart(Integer.parseInt(userId), totalPrice, "address");
        System.out.println(" userId " + userId + " totalPrice " + totalPrice + " cart id " + cart.getId());
        itemDAO.insertItem(temp, cart.getId());
    }

    private void registration(HttpServletRequest request, HttpServletResponse response) throws ServletException, ClassNotFoundException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password_confirm = request.getParameter("password_confirm");
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

        System.out.println(" email " + email + " encrypted_password " + encrypted_password + "test input or enter");
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

                String str = Integer.toString(user.getId());
                Cookie cookie = new Cookie("userid", str);
                cookie.setMaxAge(1800);
                response.addCookie(cookie);


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
            // sb.append("<tr><td><img src = \"./images/" + book.get(i).getImage() + "\" height = \"100\" width = \"100\"></td></tr><tr><td height = \"100\" width=\"200\">" + book.get(i).getTitle() + "</td></tr>");
            sb.append("<tr><td ><img src = \"./images/" + book.get(i).getImage() + "\" height=\"100\" width=\"100\"/></td><td align=\"center\"><button id=\"" + book.get(i).getId() + "\" class=\"me btn btn-primary btn-sm\">\n" +
                    "purchase</button></td></tr>");
            sb.append("<tr id=\"" + book.get(i).getId() + "\"><td class=\"row-data\" id_cost=\"" + book.get(i).getPrice() + "\" id_image=\"./images/" + book.get(i).getImage()  + "\" id=\"col" + book.get(i).getId() + "\" colspan=\"2\" height = \"100\" width=\"200\">" + book.get(i).getTitle() + "</td></tr>");

        }

        str = sb.toString();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(str);
        out.flush();
        out.close();
    }

    private void cards(HttpServletRequest request, HttpServletResponse response){

    }


}
