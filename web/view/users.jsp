<%@ page import="com.shop.entity.User" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Properties" %><%--
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

        $("#id_user_close").click(function (e) {
            e.preventDefault();
            $("#dialog").dialog("close");
        });

        $("#id_user_but").click(function () {
            // alert("TEST");

            var user = {};

            var emailuser = $('#email_user').val();
            var nameuser = $('#name_user').val();
            var roleuser = $('#role_user').val();
            var password = $('#password').val();
            var confirmpassword = $('#confirm_password').val();


            user.emailuser = emailuser;
            user.nameuser = nameuser;
            user.roleuser = roleuser;
            user.password = password ;
            user.confirmpassword = confirmpassword;

            $.ajax({
                url: '/bookstore/insertuser',
                type: 'POST',
                data: 'user=' + JSON.stringify(user),
                dataType: 'JSON',
                beforeSend: function(x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data) {
                    // alert('success'+data);
                    console.log(data);
                    $("#sentstatus").html(data.localid.toString());
                    window.location.replace("http://localhost:8080/bookstore/showusers");

                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                    $("#sentstatus").html("error");
                }
            });


        });

        $("#view").dialog({
            autoOpen: false,
            modal: true
        });


        $("#viewusers").on('click', '.usersnewview', function(e) {

            alert(this.id);
            e.preventDefault();
            $("#view").dialog("open");
            $.ajax({
                url: '/bookstore/showoneuser',
                type: 'POST',
                data: {"iduser": this.id},
                success: function (data) {
                    //alert('success'+data);

                    console.log(data);
                    $('#name_user_view').html(data.nameid);
                    $('#role_user_view').html(data.roleid);
                    $('#email_user_view').html(data.emailid)
                    $('#remembercreatedat_user_view').html(data.remembid);
                    $('#currentsigninat_user_view').html(data.currentid);
                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);

                }
            });


        });

        $("#id_user_close_view").click(function (e) {
            e.preventDefault();
            $("#view").dialog("close");
        });

        $("#edit").dialog({
            autoOpen: false,
            modal: true
        });


        $("#viewusers").on('click', '.usersnewedit', function(e) {
            e.preventDefault();
            $("#edit").dialog("open");
            //alert(this.id);

            $.ajax({
                url: '/bookstore/showoneuser',
                type: 'POST',
                data: {"iduser": this.id},
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

        $("#id_user_close_edit").click(function (e) {
            e.preventDefault();
            $("#edit").dialog("close");
        });

        $("#viewusers").on('click', '.usersnewdelete', function() {

            //alert(this.id);
            $(this).closest("tr").remove();
            $.ajax({
                url: '/bookstore/deleteuser',
                type: 'POST',
                data: {"iduser": this.id},
                success: function (data) {
                    //alert('success'+data);
                    console.log(data);
                    //$(this).closest("tr").remove();
                    //window.location.replace("http://localhost:8080/bookstore/showbooks");
                },
                failure: function (data) {
                    console.log(data);
                    alert(data);

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
            <option selected>user</option>
            <option value="1">admin</option>
            <option value="2">manager</option>
            <option value="3">user</option>
        </select>
    </div>

    <div class="input-group input-group-sm mb-3">
        <!-- Password-->
        <label class="control-label" for="password">Password</label>
        <div class="controls">
            <input type="password" id="password" name="pass" placeholder="" class="input-xlarge">
            <p class="help-block">Password should be at least 4 characters</p>
        </div>
    </div>
    <div class="input-group input-group-sm mb-3">
        <!-- Password-->
        <label class="control-label" for="password">Confirm Password</label>
        <div class="controls">
            <input type="password" id="confirm_password" name="pass" placeholder="" class="input-xlarge">
            <p class="help-block">Password should be at least 4 characters</p>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="sentstatus">status</span>
        </div>
        <button id="id_user_but" class="btn-default btn btn-outline-secondary" type="button" >Sent</button>
        <button id="id_user_close" type="button" class="btn btn-secondary">Close</button>
    </div>

</div>


<div id="edit" title="Edit user">

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-smedit">id</span>
        </div>
        <input type="text" class="form-control" id="id_user_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm1edit">email</span>
        </div>
        <input type="text" class="form-control" id="email_user_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm2edit">name</span>
        </div>
        <input type="text" class="form-control" id="name_user_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>

    <div class="control-group" align = "center">
        <select class="form-select" aria-label="Default select example" id="role_user_edit" name="role_user" value = "3">
            <option selected>user</option>
            <option value="1">admin</option>
            <option value="2">manager</option>
            <option value="3">user</option>
        </select>
    </div>

    <div class="input-group input-group-sm mb-3">
        <!-- Password-->
        <label class="control-label" for="password">Password</label>
        <div class="controls">
            <input type="password" id="password_edit" name="pass" placeholder="" class="input-xlarge">
            <p class="help-block">Password should be at least 4 characters</p>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <!-- Password-->
        <label class="control-label" for="password">Confirm Password</label>
        <div class="controls">
            <input type="password" id="password_confirm_edit" name="pass" placeholder="" class="input-xlarge">
            <p class="help-block">Password should be at least 4 characters</p>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm10edit">remembercreatedat</span>
        </div>
        <input type="text" class="form-control" id="remembercreatedat_user_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="sentstatus_edit">status</span>
        </div>
        <button id="id_user_but_edit" class="btn-default btn btn-outline-secondary" type="button" >Sent</button>
        <button id="id_user_close_edit" type="button" class="btn btn-secondary">Close</button>
    </div>

</div>

<div id="view" title="View user">

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="emailidview">email</span>
            <span class="input-group-text" id="email_user_view" value=""></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="nameidview">name</span>
            <span class="input-group-text" id="name_user_view" value=""></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="roleidview">role</span>
            <span class="input-group-text" id="role_user_view" value=""></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="remembercreatedatidview">remember_created_at</span>
            <span class="input-group-text" id="remembercreatedat_user_view" value=""></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="currentsigninatedatidview">current_sign_in_at</span>
            <span class="input-group-text" id="currentsigninat_user_view" value=""></span>
        </div>
    </div>
    <div class="input-group input-group-sm mb-3">
        <button id="id_book_close_view" type="button" class="btn btn-secondary">Close</button>
    </div>

</div>

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
    out.println("<div class=\"col\">");
    out.println("<table class=\"table card-table table-success table-striped\" id=\"viewusers\" width = \"800\"><tbody>");
    out.println("<tr><td>id</td><td>email</td><td>name</td><td>UID</td><td>remember_created</td><td>current_sign_in_at</td><td colspan =\"3\"></td></tr>");
    for(int i=0; null!=user && i < user.size(); i++) {
        out.println("<tr><td>" + user.get(i).getId() + "</td><td>" + user.get(i).getEmail() + "</td><td>" + user.get(i).getName() + "</td><td>" + user.get(i).getUid() + "</td><td>" + user.get(i).getRemember_created_at() + "</td><td>" + user.get(i).getCurrent_sign_in_at() + "</td>");
        out.println("<td><button id=\"" + user.get(i).getId() + "\" class=\"btn usersnewview btn-primary btn-block\">View</button></td>");
        out.println("<td><button id=\"" + user.get(i).getId() + "\" class=\"btn usersnewedit btn-primary btn-block\">Edit</button></td>");
        out.println("<td><button id=\"" + user.get(i).getId() + "\" class=\"btn usersnewdelete btn-primary btn-block\">Delete</button></td><tr>");
    }
    out.println("<tr><td colspan = \"11\" align = \"right\"><button id=\"add\" class=\"btn btn-primary btn-block\">Add</button></td></tr>");
    out.println("</tbody></table>");
    out.println("</div>");
    out.println("<div class=\"col\">");

    out.println("</div>");
    out.println("</div>");
    out.println("</div>");






%>


</body>
</html>
