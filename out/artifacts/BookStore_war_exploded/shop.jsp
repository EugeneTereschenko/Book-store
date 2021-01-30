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
<div class="alert alert-primary" role="alert">
    A simple primary alert—check it out!
</div>
<button type="button" class="btn btn-primary btn-sm" id="id_button">Small button</button>
<div style="text-align: center">
    <h1>Welcome to Bookshop Website Panel</h1>
    <table align = "center" class="table" height="800">
        <tr><td align = "right" height="5"><button type="button" class="btn btn-primary btn-sm" id="left_button">next pages</button></td><td rowspan="2" width = "300"></td><td rowspan="2" width = "600"><link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
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
                                        <table class="table">
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
                                                <td><img src="https://via.placeholder.com/400x200/FFB6C1/000000" class="img-cart"></td>
                                                <td><strong>Product 1</strong><p>Size : 26</p></td>
                                                <td>
                                                    <form class="form-inline">
                                                        <input class="form-control" type="text" value="1">
                                                        <button rel="tooltip" class="btn btn-default"><i class="fa fa-pencil"></i></button>
                                                        <a href="#" class="btn btn-primary"><i class="fa fa-trash-o"></i></a>
                                                    </form>
                                                </td>
                                                <td>$54.00</td>
                                                <td>$54.00</td>
                                            </tr>
                                            <tr>
                                                <td><img src="https://via.placeholder.com/400x200/87CEFA/000000" class="img-cart"></td>
                                                <td><strong>Product 2</strong><p>Size : M</p></td>
                                                <td>
                                                    <form class="form-inline">
                                                        <input class="form-control" type="text" value="2">
                                                        <button class="btn btn-default" ><i class="fa fa-pencil"></i></button>
                                                        <a href="#" class="btn btn-primary" rel="tooltip" ><i class="fa fa-trash-o"></i></a>
                                                    </form>
                                                </td>
                                                <td>$16.00</td>
                                                <td>$32.00</td>
                                            </tr>
                                            <tr>
                                                <td colspan="6">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" class="text-right">Total Product</td>
                                                <td>$86.00</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" class="text-right">Total Shipping</td>
                                                <td>$2.00</td>
                                            </tr>
                                            <tr>
                                                <td colspan="4" class="text-right"><strong>Total</strong></td>
                                                <td>$88.00</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Continue Shopping</a>
                            <a href="#" class="btn btn-primary pull-right">Next<span class="glyphicon glyphicon-chevron-right"></span></a>
                        </div>
                    </div>
                </div>
            </div></td></tr>
        <tr><td align = "right" height="400">
    <%
        List<Book> book = (List<Book>) session.getAttribute("books");
        out.println("<div class=\"span3\" ><div class=\"panel panel-info table-responsive panel-shadow\"><table class=\"table table-success table-striped\" id=\"idBookTable\" >");
        for(int i=0; null!=book && i < book.size(); i++) {
            out.println("<tr><td><img src = \"./images/" + book.get(i).getImage() + "\" height = \"100\" width = \"100\"></td></tr><tr><td height = \"100\" width=\"200\">" + book.get(i).getTitle() + "</td></tr>");
        }
        out.println("</table></div><div>");
    %>
        </td></tr>

    </table>
    <a href="/BookStore_war_exploded/logout">Logout</a>
</div>
</body>
</html>
