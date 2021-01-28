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
<%@page import="net.codeshop.java.db.BookDAO"%>
<%@page import="net.codeshop.java.entity.Book"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center">
    <h1>Welcome to Bookshop Website Admin Panel</h1>
    <%

        List<Book> book = (List<Book>) session.getAttribute("books");
        out.println("<table>");
        for(int i=0; null!=book && i < book.size(); i++) {
            out.println("<tr><td>" + book.get(i).getTitle() + "</td><td><img src = \"./images/" + book.get(i).getImage() + "\" height = \"100\" width = \"100\">");
        }
        out.println("</table>");
    %>
    <a href="/BookStore_war_exploded/logout">Logout</a>
</div>
</body>
</html>
