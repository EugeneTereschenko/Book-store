<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 19.02.21
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shop.entity.User" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Properties" %>

<html>
<head>
    <title>Settings</title>
    <link rel="stylesheet" href="shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<script type="text/javascript">
    function getCookie(name) {
        // Split cookie string and get all individual name=value pairs in an array
        var cookieArr = document.cookie.split(";");

        // Loop through the array elements
        for(var i = 0; i < cookieArr.length; i++) {
            var cookiePair = cookieArr[i].split("=");

            /* Removing whitespace at the beginning of the cookie name
            and compare it with the given string */
            if(name == cookiePair[0].trim()) {
                // Decode the cookie value and return
                return decodeURIComponent(cookiePair[1]);
            }
        }

        // Return null if not found
        return null;
    }


    $(document).ready(function() {

        var value = getCookie("userid");

//        alert(value);

        $.ajax({
            url: '/bookstore/showoneuser',
            type: 'POST',
            data: {"iduser": value},
            success: function (data) {
                //alert('success'+data);

                console.log(data);
                $('#id_user_edit').val(data.userid);
                $('#name_user_edit').val(data.nameid);
                $('#role_user_edit').val(data.roleid);
                $('#email_user_edit').val(data.emailid)
                $('#remembercreatedat_user_edit').val(data.remembid);
                $('#currentsigninat_user_edit').val(data.currentid);
            },
            failure: function (data) {
                console.log(data);
                // alert(data);

            }
        });



        $("#id_user_but_edit").click(function () {
            // alert("TEST");

            var user = {};

            var id = $('#id_user_edit').val();
            var nameuser = $('#name_user_edit').val();
            var emailuser = $('#email_user_edit').val();
            var roleuser = $('#role_user_edit').val();
            var passwduser = $('#password_edit').val();
            var rememberuser = $('#remembercreatedat_user_edit').val();
            var currentuser = $('#currentsigninat_user_edit').val();



            user.userid = id;
            user.name = nameuser;
            user.email = emailuser;
            user.role = roleuser;
            user.passwd = passwduser;
            user.rememberuser = rememberuser;
            user.currentuser = currentuser;

            $.ajax({
                url: '/bookstore/updateuser',
                type: 'POST',
                data: 'user=' + JSON.stringify(user),
                dataType: 'JSON',
                beforeSend: function (x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data) {
                    // alert('success'+data);
                    console.log(data);
                    $("#sentstatus_edit").html(data.localid.toString());

                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                    $("#sentstatus_edit").html("error");
                }
            });
        });




        $("#id_button").click(function () {
            window.location.replace("http://localhost:8080/bookstore/logout");
        });

        $("#id_button_back").click(function () {
            window.location.replace("http://localhost:8080/bookstore/shop");
        });


        $("#idbooksitemy").click(function () {
            window.location.replace("http://localhost:8080/bookstore/showbooks");
        });

        $("#id_idcartsitemy").click(function () {
            window.location.replace("http://localhost:8080/bookstore/showcarts");
        });


    });


</script>

<body>
<h1 align="center">Bookshop Product</h1>

<%
    List<User> user = (List<User>) session.getAttribute("viewusers");

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

    String booksitemy = properties.getProperty("fieldBook");
    String cartsitemy = properties.getProperty("fieldOrder");

    String back = properties.getProperty("fieldBack");
    String role = (String) session.getAttribute("roleid");



    out.println("<div class=\"container\">");
    out.println("<div class=\"row\">");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("<div class=\"col\">");
    out.println("<ul class=\"nav nav-pills\">");
    out.println("<li role=\"presentation\" class=\"active\"><button type=\"button\" class=\"btn btn-primary btn-sm\" id=\"id_button\">" + exit +  "</button></li>");
    out.println("<li role=\"presentation\" class=\"active\"><button type=\"button\" class=\"btn btn-primary btn-sm\" id=\"id_button_back\">" + back +  "</button></li>");
    if (role.equals("administrator") || role.equals("manager")) {
        out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"idbooksitemy\" value=\"" + booksitemy + "\" /></li>");
        out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"id_idcartsitemy\" value=\"" + cartsitemy + "\" /><li>");
    }
    out.println("</ul>");
    out.println("</div>");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("</div>");
    out.println("<div class=\"row\">");
    out.println("<div class=\"col\">");


    out.println("</div>");
    out.println("</div>");
    out.println("<div class=\"row\">");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("<div class=\"col\">");
    out.println("\n" +
            "<div id=\"edit\" align=\"center\">\n" +
            "    <div class=\"input-group input-group-sm mb-3\">\n" +
            "        <div class=\"input-group-prepend\">\n" +
            "            <span class=\"input-group-text\" id=\"inputGroup-sizing-sm1edit\">email</span>\n" +
            "        </div>\n" +
            "        <input type=\"text\" class=\"form-control\" id=\"email_user_edit\"  aria-label=\"Small\" aria-describedby=\"inputGroup-sizing-sm\">\n" +
            "    </div>\n" +
            "    <div class=\"input-group input-group-sm mb-3\">\n" +
            "        <div class=\"input-group-prepend\">\n" +
            "            <span class=\"input-group-text\" id=\"inputGroup-sizing-sm2edit\">name</span>\n" +
            "        </div>\n" +
            "        <input type=\"text\" class=\"form-control\" id=\"name_user_edit\"  aria-label=\"Small\" aria-describedby=\"inputGroup-sizing-sm\">\n" +
            "    </div>\n" +
            "    <div class=\"input-group input-group-sm mb-3\">\n" +
            "        <!-- Password-->\n" +
            "        <label class=\"control-label\" for=\"password\">Password</label>\n" +
            "        <div class=\"controls\">\n" +
            "            <input type=\"password\" id=\"password_edit\" name=\"pass\" placeholder=\"\" class=\"input-xlarge\">\n" +
            "            <p class=\"help-block\">Password should be at least 4 characters</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"input-group input-group-sm mb-3\">\n" +
            "        <!-- Password-->\n" +
            "        <label class=\"control-label\" for=\"password\">Confirm Password</label>\n" +
            "        <div class=\"controls\">\n" +
            "            <input type=\"password\" id=\"password_confirm_edit\" name=\"pass\" placeholder=\"\" class=\"input-xlarge\">\n" +
            "            <p class=\"help-block\">Password should be at least 4 characters</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
                 "<div class=\"input-group input-group-sm mb-3\">\n" +
            "<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                    "    <input type=\"text\" name=\"description\" />\n" +
                    "    <input type=\"file\" name=\"file\" />\n" +
                    "    <input type=\"submit\" />\n" +
                    "</form>\n" +
            "    </div>\n" +
            "    <div class=\"input-group input-group-sm mb-3\">\n" +
            "        <div class=\"input-group-prepend\">\n" +
            "            <span class=\"input-group-text\" id=\"sentstatus_edit\">status</span>\n" +
            "        </div>\n" +
            "        <button id=\"id_user_but_edit\" class=\"btn-default btn btn-outline-secondary\" type=\"button\" >Update User</button>\n" +
            "    </div>\n" +
            "\n" +
            "</div>\n");


    out.println("</div>");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("</div>");
    out.println("</div>");






%>


</body>
</html>
