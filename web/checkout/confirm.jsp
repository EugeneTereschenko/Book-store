<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 03.02.21
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm</title>
    <link rel="stylesheet" href="../shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <script src=".././ownscript.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <script>
        $(document).ready(function() {

            $("#idcomplete").click(function (){
                window.location.replace("http://localhost:8080/bookstore/complete");
            });
/*
            $("#idsendcode").click(function (){

                alert("valcartedit");

                var str = $("#myInput").val();
                alert(str);

                var valueIt3 =$("#promoCode").val();
                alert(valueIt3);


                var valueIt6 =$("#valcartedit").val();
                alert(valueIt6);


            });
*/
            $("#idpromocodetable").on('click', '.idsendcode', function(){

                var valueIt3 =$("#promoCode").val();
                alert(valueIt3);


                $.ajax({
                    url: '/bookstore/confirmdata',
                    type: 'POST',
                    data: {"promocode": valueIt3},
                    success: function (data) {
                        console.log(data);
                        alert(data);
                        $("#promoCodeOut").text(data);
                        //alert(data);
                        //$('#idBookTable').append('<tr><td>' + data + '</td></tr>');
                        //$('#idBookTable').append(data);
                    },
                    failure: function (data) {
                        console.log(data);
                        // alert(data);
                    }
                });

            });

            // Get value on button click and show alert
            $("#myBtn").click(function(){
                var str = $("#myInput").val();
                alert(str);

                var valueIt3 =$("#promoCode").val();
                alert(valueIt3);


                var valueIt6 =$("#valcartedit").val();
                alert(valueIt6);
            });

        });
    </script>
</head>
<body>

<div class="alert alert-primary" role="alert">
    <table><tr><td>Step1</td><td>Step2</td><td>Step3</td><td><button type="button" class="btn btn-primary btn-sm" id="id_button">Step4</button></td><td>Settings</td><td><%= session.getAttribute("username") %></td></tr></table>
</div>


<table align="center" id="idpromocodetable">
    <tr><td>
        <div class="card">
            <h5 class="card-header">Your cart</h5>
            <div class="card-body">
                <h5 class="card-title">Product name Books</h5>
                <p class="card-text">Total Product Price</p>
                <%
                    String price = (String) session.getAttribute("totalProductPrice");
                    out.println("<p class=\"card-text\">" + price + "</p>");
                %>
            </div>
            <div class="card-body">
                <p class="card-text">Order Product Price</p>
                <%
                    String orderPrice = (String) session.getAttribute("orderProductPrice");
                    out.println("<span class=\"text-muted\">" + orderPrice + "</span>");
                %>            </div>
            <div class="card-body">
                <p class="card-text">Total Price (USD)</p>
                <%
                    String totalPrice = (String) session.getAttribute("totalPrice");
                    out.println("<strong id=\"promoCodeOut\" class=\"text-muted\">" + totalPrice + "</strong>");
                %>
            </div>
            <div class="card-body">
                <p class="card-text">Promo code</p>
                <p class="card-text">-$5</p>
                <div class="input-group input-group-sm mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-sm">CODE</span>
                    </div>
                    <input type="text" class="form-control promoCode" id="promoCode" name="promoCode">
                </div>
                <button type="button" class="btn btn-lg btn-block btn-primary idsendcode">SEND CODE</button>
            </div>
            <div class="card-body">
                <button type="button" id="idcomplete" class="btn btn-lg btn-block btn-primary">Complete</button>
            </div>
        </div></td></tr></table>
</body>
</html>
