<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 16.02.21
  Time: 08:53
  To change this template use File | Settings | File Templates.


<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251"%>
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page session="false" %>
<%
    String lang = "ru";
    ResourceBundle RB = ResourceBundle.getBundle("app", new Locale(lang));

    Properties properties = new Properties();
    InputStream stream = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("app_ru.properties");
    InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
    properties.load(reader);

    String host = properties.getProperty("greeting");
    //ResourceBundle lbs = ResourceBundle.getBundle("app");
%>

<html>
<head>

    <title>Title</title>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"\>
    <!-- <meta http-equiv="content-type" content="text/html; charset=cp1251"> -->
</head>
<body>
<p>
    <%= RB.getString("greeting") %>
</p>
<p>
    <%= host %>
</p>
</body>
</html>
