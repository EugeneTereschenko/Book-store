<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 26.01.21
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>BookShop</title>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  </head>
  <body>
  <script>
    $(function() {

      $("#id_book_login").click(function () {
        var text1 = $('#username').val();
        var text2 = $('#password').val();
        var text3 = $('#lang').val();

        $.ajax({
          url: '/bookstore/authentication',
          type: 'POST',
          data: {"login": text1, "pass": text2, "lang": text3},
          success: function (data) {
            console.log(data);

           // alert(data);

            if (data != "stop") {
              window.location.replace("http://localhost:8080/bookstore/shop.jsp");
            }

          },
          failure: function (data) {

          }
        });



        });
    });
  </script>
  <table align = "center"><tr><td>
    <fieldset>
      <div id="legend">
        <legend class="">Sing in</legend>
      </div>
      <div class="control-group">
        <!-- Username -->
        <label class="control-label"  for="username">Username</label>
        <div class="controls">
          <input type="text" id="username" name="login" placeholder="">
          <p class="help-block">Username can contain any letters or numbers, without spaces</p>
        </div>
      </div>


      <div class="control-group">
        <!-- Password-->
        <label class="control-label" for="password">Password</label>
        <div class="controls">
          <input type="password" id="password" name="pass" placeholder="">
          <p class="help-block">Password should be at least 4 characters</p>
        </div>
      </div>

      <div class="control-group" align = "center">
        <select class="form-select" aria-label="Default select example" id="lang" name="lang" >
          <option selected>Choose Language</option>
          <option value="1">English</option>
          <option value="2">Russian</option>
        </select>
      </div>

      <div class="control-group">
        <!-- Button -->
        <div class="controls">
          <button id="id_book_login" class="btn btn-default id_book_login" >login</button>
        </div>
      </div>

      <div class="control-group">
        <p class="help-block">
          <a href="/bookstore/registration.jsp">Registration</a>
        </p>
      </div>
    </fieldset>
  </td></tr></table>
  </body>
</html>
