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
  <table align = "center"><tr><td>
  <form class="form-horizontal" action='authentication' method="POST">
    <fieldset>
      <div id="legend">
        <legend class="">Sing in</legend>
      </div>
      <div class="control-group">
        <!-- Username -->
        <label class="control-label"  for="username">Username</label>
        <div class="controls">
          <input type="text" id="username" name="login" placeholder="" class="input-xlarge">
          <p class="help-block">Username can contain any letters or numbers, without spaces</p>
        </div>
      </div>


      <div class="control-group">
        <!-- Password-->
        <label class="control-label" for="password">Password</label>
        <div class="controls">
          <input type="password" id="password" name="pass" placeholder="" class="input-xlarge">
          <p class="help-block">Password should be at least 4 characters</p>
        </div>
      </div>

      <div class="control-group">
        <!-- Button -->
        <div class="controls">
          <button class="btn btn-success">RUN</button>
        </div>
      </div>

      <div class="control-group">
        <p class="help-block">
          <a href="/bookstore/registration.jsp">Registration</a>
        </p>
      </div>
    </fieldset>
  </form>
  </td></tr></table>
  </body>
</html>
