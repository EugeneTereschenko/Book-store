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
  </head>
  <body>
  <div align = "right">
    <p>authorization</p>
      <form action = "Authenticator" method = "post">
        <p>
          User <input type="user" name="login" required />
        </p>
        <p>
          Password <input type="password" name="pass" required />
        </p>
        <p>
          <input type="submit" value="RUN" />
        </p>
      </form>
  </div>
  </body>
</html>
