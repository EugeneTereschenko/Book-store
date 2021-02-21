<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 01.02.21
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<script>
    $(function() {

        $("#id_book_register").click(function () {


            var text1 = $('#username').val();
            var text2 = $('#email').val();
            var text3 = $('#password').val();
            var text4 = $('#password_confirm').val();
            var text5 = "off";


            if($('#flexCheckDefault').prop('checked')){
                 text5 = "on";
             }
            var text6 = $('#lang').val();

            //alert(text5);

            $.ajax({
                url: '/bookstore/registration',
                type: 'POST',
                data: {"username": text1, "email": text2, "password": text3, "password_confirm": text4, "lang": text6},
                success: function (data) {
                    //console.log(data);
               //     temp = data;

                    //alert(data);
                    if (data != "stop") {

                      //  alert(text2);

                        if (text5 == "on") {
                            // alert("sendmessage");
                            $.ajax({
                                url: '/bookstore/sendmailuserregister',
                                type: 'POST',
                                data: {"email": text2},
                                success: function (data) {
                                    //   console.log(data)

                                },
                                failure: function (data) {
                                    //  console.log(data);
                                    // alert(data);
                                }
                            });
                        }



                        window.location.replace("http://localhost:8080/bookstore/shop.jsp");
                    }

                },
                failure: function (data) {
                    //  console.log(data);
                    // alert(data);
                }
            });

           // alert(temp);



        });
    });
</script>
<table align="center"><tr><td>
    <fieldset>
        <div id="legend">
            <legend class="">Register</legend>
        </div>
        <div class="control-group">
            <!-- Username -->
            <label class="control-label"  for="username">Username</label>
            <div class="controls">
                <input type="text" id="username" name="username" placeholder="" class="input-xlarge">
                <p class="help-block">Username can contain any letters or numbers, without spaces</p>
            </div>
        </div>

        <div class="control-group">
            <!-- E-mail -->
            <label class="control-label" for="email">E-mail</label>
            <div class="controls">
                <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
                <p class="help-block">Please provide your E-mail</p>
            </div>
        </div>

        <div class="control-group">
            <!-- Password-->
            <label class="control-label" for="password">Password</label>
            <div class="controls">
                <input type="password" id="password" name="password" placeholder="" class="input-xlarge">
                <p class="help-block">Password should be at least 4 characters</p>
            </div>
        </div>

        <div class="control-group">
            <!-- Password -->
            <label class="control-label"  for="password_confirm">Password (Confirm)</label>
            <div class="controls">
                <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="input-xlarge">
                <p class="help-block">Please confirm password</p>
            </div>
        </div>

        <div class="control-group" align = "center">
            <select class="form-select" aria-label="Default select example" id="lang" name="lang" >
                <option selected>Choose Language</option>
                <option value="1">English</option>
                <option value="2">Russian</option>
            </select>
            <div class="form-check" align = "left">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Sent me email after registration
                </label>
            </div>
        </div>

        <div class="control-group">
            <!-- Button -->
            <div class="controls">
                <button id="id_book_register" class="btn btn-default id_book_login" >Register</button>
            </div>
        </div>
    </fieldset>
</td></tr></table>
</body>
</html>
