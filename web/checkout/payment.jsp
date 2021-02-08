<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 03.02.21
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="../shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <script src=".././ownscript.js"></script>
</head>
<body>
<h1 align="center">Welcome to Bookshop</h1>
<div class="alert alert-primary" role="alert">
    <table><tr><td>Step1</td><td>Step2</td><td><button type="button" class="btn btn-primary btn-sm" id="id_button">Step3</button></td><td>Settings</td><td><%= session.getAttribute("username") %></td></tr></table>
</div>
<table align="center"><tr><td>
<div class="col-md-8 order-md-1">
    <form class="needs-validation" novalidate action='paymentdata' method="POST">
        <hr class="mb-4">
        <h4 class="mb-3">Payment</h4>
        <div class="d-block my-3">
            <div class="custom-control custom-radio">
                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
                <label class="custom-control-label" for="credit">Credit card</label>
            </div>
            <div class="custom-control custom-radio">
                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                <label class="custom-control-label" for="debit">Debit card</label>
            </div>
            <div class="custom-control custom-radio">
                <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                <label class="custom-control-label" for="paypal">Paypal</label>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="cc-name">Name on card</label>
                <input type="text" class="form-control" id="cc-name" name="credname" placeholder="" required>
                <small class="text-muted">Full name as displayed on card</small>
                <div class="invalid-feedback">
                    Name on card is required
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="cc-number">Credit card number</label>
                <input type="text" class="form-control" id="cc-number" name="crednumber" placeholder="" required>
                <div class="invalid-feedback">
                    Credit card number is required
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 mb-3">
                <label for="cc-expiration">Expiration</label>
                <input type="text" class="form-control" id="cc-expiration" name="credexpiration" placeholder="" required>
                <div class="invalid-feedback">
                    Expiration date required
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <label for="cc-expiration">CVV</label>
                <input type="text" class="form-control" id="cc-cvv" name="credcvv" placeholder="" required>
                <div class="invalid-feedback">
                    Security code required
                </div>
            </div>
        </div>
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
    </form>
</div>
</td></tr></table>
</body>
</html>
