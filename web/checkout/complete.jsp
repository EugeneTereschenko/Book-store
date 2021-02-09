<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 03.02.21
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Complete</title>
    <link rel="stylesheet" href="../shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <script src=".././ownscript.js"></script>

    <script>
        $(document).ready(function() {

            $("#idcompletepost").click(function (){
                window.location.replace("http://localhost:8080/bookstore/logout");
            });
        });
    </script>


</head>
<body>
<div class="alert alert-primary" role="alert">
    <table><tr><td>Step1</td><td>Step2</td><td>Step3</td><td>Step4</td><td><button type="button" class="btn btn-primary btn-sm" id="id_button">Step5</button></td><td>Settings</td><td><%= session.getAttribute("username") %></td></tr></table>
</div>

<table align="center"><tr><td>
<div class="card" style="width: 18rem;">
    <div class="card-body">
        <h5 class="card-title">Books</h5>
        <h6 class="card-subtitle mb-2 text-muted">Costs (USD)</h6>
        <%
            String totalPrice = (String) session.getAttribute("totalPrice");
            int temp = Integer.parseInt(totalPrice);
            temp -= 5;
            out.println("<p class=\"card-text\">" + temp + "</p>");
        %>
    </div>
</div>
</td></tr><tr><td>
<div class="card-body">
    <button type="button" id="idcompletepost" class="btn btn-lg btn-block btn-primary">Complete</button>
</div>
</td></tr></table>

</body>
</html>
