<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 27.01.21
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shop.db.BookDAO"%>
<%@page import="com.shop.entity.Book"%>
<%@page import="com.shop.entity.User"%>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.util.ResourceBundle" %>
<html>
<head>
    <title>Bookshop</title>
    <link rel="stylesheet" href="shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <script src="./ownscript.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<h1 align="center">Welcome to Bookshop</h1>
<%
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

    String exit = properties.getProperty("fieldExit");
    String usersitemy = properties.getProperty("fieldUser");
    String booksitemy = properties.getProperty("fieldBook");

    String title = properties.getProperty("fieldTitle");
    String author = properties.getProperty("fieldAuthor");
    String price = properties.getProperty("fieldPrice");
    String year = properties.getProperty("fieldYear");
    String nextpages = properties.getProperty("fieldNextP");
    String addTo = properties.getProperty("fieldAddtoCart");
    String order = properties.getProperty("fieldOrder");

    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("roleid");

    System.out.println(" role " + role + " username " + username);

    List<Book> book = (List<Book>) session.getAttribute("books");

    out.println("<div class=\"container\">");
    out.println("<div class=\"row\">");
    out.println("<div class=\"col\">");
    out.println("</div>");
    out.println("<div class=\"col col-md-auto\">");
    out.println("<ul class=\"nav nav-pills\">");
    out.println("<li role=\"presentation\" class=\"active\"><button type=\"button\" class=\"btn btn-primary btn-sm\" id=\"id_button\">" + exit +  "</button></li>");
    out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary btn-sm\" id=\"my_name\" value=\"" + username + "\" /></li>");
    if (role.equals("administrator")) {
        out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"idusersitemy\" value=\"" + usersitemy + "\" /></li>");
    }
    if (role.equals("administrator") || role.equals("manager")) {
        out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"idbooksitemy\" value=\"" + booksitemy + "\" /></li>");
        out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"idbookordermy\" value=\"" + order + "\" /></li>");
    }
    out.println("</ul>");
    out.println("</div>");
    out.println("<div class=\"col\">");
    out.println("</div>");
    out.println("</div>");
    out.println("<div class=\"row\">");
    out.println("<div class=\"col\">");
    out.println("");
    out.println("</div>");
    out.println("<div class=\"col\">");
    out.println("<div class=\"card panel-shadow panel panel-info\" style=\"width: 600px;\">\n" +
            "<div class=\"card-header\">\n" +
            "Products</div>");
    out.println("<div class=\"panel panel-info table-responsive panel-shadow\">");
    out.println("<table class=\"table card-table table-success table-striped\" id=\"idBookTable\"><tbody>");
    out.println("<tr><td width =\"150\"><input type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_title\" value=\"" + title + "\"  /></td>" +
            "<td width =\"150\"><input type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_author\" value=\"" + author + "\" /></td>" +
            "<td ><input type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_price\" value=\"" + price + "\" /></td>" +
            "<td ><input type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_year\" value=\"" + year + "\" /></td>" +
            "<td><button type=\"button\" class=\"btn btn-primary btn-block\" id=\"takepagesnew\" >" + nextpages + "</button></td>" +
            "</tr>");

    out.println("<tr><td colspan=\"5\"  style=\"width: 600px;\" style=\"height: 20px;\"></td></tr>");
    for(int i=0; null!=book && i < book.size(); i++) {
        out.println("<tr><td colspan=\"5\" width =\"600\"><img src = \"./images/" + book.get(i).getImage() + "\" height=\"100\" width=\"100\"/></td></tr>");
        out.println("<tr id=\"" + book.get(i).getId() + "\"><td colspan=\"5\" class=\"row-data\" id_cost=\"" + book.get(i).getPrice() + "\" id_image=\"./images/" + book.get(i).getImage()  + "\" id=\"col" + book.get(i).getId() + "\" height = \"100\" width=\"400\">" + book.get(i).getTitle() + "</td></tr>");
        out.println("<tr><td colspan=\"5\" >" + book.get(i).getAuthor() + "</td></tr>");
        out.println("<tr><td>$" + book.get(i).getPrice() + ".00</td><td colspan=\"4\" align=\"center\"><input id=\"" + book.get(i).getId() + "\" class=\"me btn btn-primary btn-block\" height=\"20\" width=\"70\" value=\"" + addTo + "\"/></td></tr>");
        System.out.println(book.get(i).getId());
    }

    out.println("</tbody></table>");
    out.println("</div></div>");
    out.println("</div>");
    out.println("<div class=\"col\">");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css\">\n" +
            "<div class=\"container bootstrap snippets bootdey\">\n" +
            "    <div class=\"col-md-9 col-sm-8 content\">\n" +
            "        <div class=\"row\">\n" +
            "            <div class=\"col-md-12\">\n" +
            "                <ol class=\"breadcrumb\">\n" +
            "                    <li class=\"active\">Cart</li>\n" +
            "                    <!--\n" +
            "                    <li><a href=\"#\">Home</a></li>\n" +
            "                    <li class=\"active\">Cart</li>\n" +
            "                    -->\n" +
            "                </ol>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <div class=\"row\">\n" +
            "        <div class=\"col-md-12\">\n" +
            "            <div class=\"panel panel-info panel-shadow\">\n" +
            "                <div class=\"panel-heading\">\n" +
            "                    <h3>\n" +
            "                        <img class=\"img-circle img-thumbnail\" src=\"https://bootdey.com/img/Content/user_3.jpg\">\n" +
            "                    </h3>\n" +
            "                </div>\n" +
            "                <div class=\"panel-body\">\n" +
            "                    <div class=\"table-responsive\">\n" +
            "                        <table class=\"table\" id =\"idCartTable\">\n" +
            "                            <thead>\n" +
            "                            <tr>\n" +
            "                                <th>Product</th>\n" +
            "                                <th>Description</th>\n" +
            "                                <th>Qty</th>\n" +
            "                                <th>Price</th>\n" +
            "                                <th>Total</th>\n" +
            "                            </tr>\n" +
            "                            </thead>\n" +
            "                            <tbody>\n" +
            "                            <tr>\n" +
            "                                <td colspan=\"6\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "                            <tr>\n" +
            "                                <td colspan=\"4\" class=\"text-right\">Total Product</td>\n" +
            "                                <td id=\"totalproduct\" id_sum='0'>$00.00</td>\n" +
            "                            </tr>\n" +
            "                            <tr>\n" +
            "                                <td colspan=\"4\" class=\"text-right\">Total Shipping</td>\n" +
            "                                <td id=\"totalshipping\" id_sum='0'>$0.00</td>\n" +
            "                            </tr>\n" +
            "                            <tr>\n" +
            "                                <td colspan=\"4\" class=\"text-right\"><strong>Total</strong></td>\n" +
            "                                <td id=\"totalcost\" id_sum='0'>$00.00</td>\n" +
            "                            </tr>\n" +
            "                            </tbody>\n" +
            "                        </table>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <a href=\"#\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-arrow-left\"></span>&nbsp;Continue Shopping</a>\n" +
            "            <button id=\"nextcart\" class=\"btn btn-primary pull-right\">Next<span class=\"glyphicon glyphicon-chevron-right\"></span></button>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>");
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");


%>
</body>
</html>
