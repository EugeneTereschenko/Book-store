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
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <script src="./ownscript.js"></script>
</head>
<body>
<h1 align="center">Welcome to Bookshop</h1>
<div class="alert alert-primary" role="alert">
    <table><tr><td><button type="button" class="btn btn-primary btn-sm" id="id_button">Exit</button></td><td><%= session.getAttribute("username") %></td><td>A simple primary alert—check it out!</td></tr></table>
</div>


<div align="center">



    <table align = "right" class="table" height="800" width="800" >
        <tr><td width="500"></td><td align = "right" height="5" width = "250"><button type="button" class="btn btn-primary btn-sm" id="right_button">next pages</button></td>
            <td  width = "150"></td>
            <td width = "500"></td></tr><tr><td width="400"></td><td>
                <!--
    <table align = "center" border="1" >
    <td></td width="300" ><td align ="right" height="800">
  -->


    <%

        List<Book> book = (List<Book>) session.getAttribute("books");

        //out.println("<div class=\"card\" style=\"width: 550px;\">\n" +
        //"<div class=\"card-header\">\n" +
        //"Books</div>");
        out.println("<div class=\"card\" style=\"width: 550px;\">");
        out.println("<div class=\"table-wrapper-scroll-y my-custom-scrollbar\">");
        //"</div><div class=\"panel panel-info table-responsive panel-shadow\">");
        //out.println("<div class=\"table-responsive-md\">");
       // out.println("<div class=\"table-responsive\">");
        out.println("<div class=\"panel panel-info table-responsive panel-shadow\">");
        out.println("<table class=\"table card-table table-success table-striped\" id=\"idBookTable\"><tbody>");
        out.println("<tr><td width =\"150\"><button type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_title\" >Title</button></td>" +
                "<td width =\"150\"><button type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_author\" >Author</button></td>" +
                "<td ><button type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_price\" >Price</button></td>" +
                "<td ><button type=\"button\" class=\"btn btn-primary btn-block\" id=\"id_button_year\">Year</button></td></tr>");
        out.println("<tr><td colspan=\"4\"  style=\"width: 550px;\" style=\"height: 20px;\"></td></tr>");
        for(int i=0; null!=book && i < book.size(); i++) {
            out.println("<tr><td colspan=\"4\" width =\"550\"><img src = \"./images/" + book.get(i).getImage() + "\" height=\"100\" width=\"100\"/></td></tr>");
            out.println("<tr id=\"" + book.get(i).getId() + "\"><td colspan=\"4\" class=\"row-data\" id_cost=\"" + book.get(i).getPrice() + "\" id_image=\"./images/" + book.get(i).getImage()  + "\" id=\"col" + book.get(i).getId() + "\" height = \"100\" width=\"400\">" + book.get(i).getTitle() + "</td></tr>");
            out.println("<tr><td colspan=\"4\" >" + book.get(i).getAuthor() + "</td></tr>");
            out.println("<tr><td>$" + book.get(i).getPrice() + ".00</td><td colspan=\"3\" align=\"center\"><button id=\"" + book.get(i).getId() + "\" class=\"me btn btn-primary btn-block\" height=\"20\" width=\"70\">Add to cart</button></td></tr>");
            System.out.println(book.get(i).getId());
        }

        out.println("</tbody></table>");
        out.println("</div></div>");
        out.println("</div>");
//</div></div>
        //out.println("</tbody></table></div></div></div>");

    %>


        </td>
        <td></td>

        <td width="300" align="center">
            <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
            <div class="container bootstrap snippets bootdey">
                <div class="col-md-9 col-sm-8 content">
                    <div class="row">
                        <div class="col-md-12">
                            <ol class="breadcrumb">
                                <li><a href="#">Home</a></li>
                                <li class="active">Cart</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-info panel-shadow">
                                <div class="panel-heading">
                                    <h3>
                                        <img class="img-circle img-thumbnail" src="https://bootdey.com/img/Content/user_3.jpg">
                                        Matew darfkmoun
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table" id ="idCartTable">
                                            <thead>
                                            <tr>
                                                <th>Product</th>
                                                <th>Description</th>
                                                <th>Qty</th>
                                                <th>Price</th>
                                                <th>Total</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td colspan="6">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" class="text-right">Total Product</td>
                                                <td id="totalproduct" id_sum='0'>$00.00</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" class="text-right">Total Shipping</td>
                                                <td id="totalshipping" id_sum='0'>$0.00</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" class="text-right"><strong>Total</strong></td>
                                                <td id="totalcost" id_sum='0'>$00.00</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Continue Shopping</a>
                            <button id="nextcart" class="btn btn-primary pull-right">Next<span class="glyphicon glyphicon-chevron-right"></span></button>
                        </div>
                    </div>
                </div>
            </div>

        </td>

        </tr>
    </table>

    <a href="/bookstore/logout">Logout</a>
</div>

</body>
</html>
