<%@ page import="com.shop.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 15.02.21
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Users</title>
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

    $(document).ready(function() {


        $("#dialog").dialog({
            autoOpen: false,
            modal: true
        });

        $("#add").on("click", function (e) {
            e.preventDefault();
            $("#dialog").dialog("open");
        });

        $("#id_user_but").click(function () {
            //alert("TEST");

        });

        $("#viewusers").on('click', '.usersnewview', function() {

            alert(this.id);

        });

        $("#viewusers").on('click', '.usersnewedit', function() {

            alert(this.id);

        });

        $("#viewusers").on('click', '.usersnewdelete', function() {

            alert(this.id);

        });


    });


</script>

<body>
<h1 align="center">Bookshop Product</h1>



<div id="dialog" title="Sent user">
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm1">email</span>
        </div>
        <input type="text" class="form-control" id="email_user"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm2">name</span>
        </div>
        <input type="text" class="form-control" id="name_user"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>

    <div class="control-group" align = "center">
        <select class="form-select" aria-label="Default select example" id="role_user" name="role_user" value = "3">
            <option selected>role</option>
            <option value="1">admin</option>
            <option value="2">manager</option>
            <option value="3">user</option>
        </select>
    </div>

    <div class="input-group input-group-sm mb-3">
        <button id="id_user_but" class="btn-default btn btn-outline-secondary" type="button" >Sent</button>
    </div>
</div>

<%
    List<User> user = (List<User>) session.getAttribute("viewusers");

    out.println("<table class=\"table card-table table-success table-striped\" id=\"viewusers\" width = \"800\"><tbody>");
    out.println("<tr><td>id</td><td>email</td><td>name</td><td>role</td><td>remember_created</td><td>current_sign_in_at</td><td colspan =\"3\"></td></tr>");

    for(int i=0; null!=user && i < user.size(); i++) {
        out.println("<tr><td>" + user.get(i).getId() + "</td><td>" + user.get(i).getEmail() + "</td><td>" + user.get(i).getName() + "</td><td></td><td>" + user.get(i).getRemember_created_at() + "</td><td>" + user.get(i).getCurrent_sign_in_at() + "</td>");
        out.println("<td><button id=\"" + user.get(i).getId() + "\" class=\"btn usersnewview btn-primary btn-block\">View</button></td>");
        out.println("<td><button id=\"" + user.get(i).getId() + "\" class=\"btn usersnewedit btn-primary btn-block\">Edit</button></td>");
        out.println("<td><button id=\"" + user.get(i).getId() + "\" class=\"btn usersnewdelete btn-primary btn-block\">Delete</button></td><tr>");
    }
    out.println("<tr><td colspan = \"11\" align = \"right\"><button id=\"add\" class=\"btn btn-primary btn-block\">Add</button></td></tr>");
    out.println("</tbody></table>");
%>


</body>
</html>
