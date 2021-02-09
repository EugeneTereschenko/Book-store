<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 03.02.21
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Address</title>
    <link rel="stylesheet" href="../shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <script src="../ownscript.js"></script>
</head>
<body>
<h1 align="center">Welcome to Bookshop</h1>
<div class="alert alert-primary" role="alert">
    <table><tr><td><button type="button" class="btn btn-primary btn-sm" id="id_button">Step1</button></td><td>Step2</td><td>Step3</td><td>Settings</td><td><%= request.getParameter("cartid") %></td><td><%= session.getAttribute("username") %></td><td><%= session.getAttribute("cartid") %></td></tr></table>
</div>
<table align="center"><tr><td>
<div class="col-md-8 order-md-1">
    <h4 class="mb-3">Billing address</h4>
    <form class="needs-validation" novalidate action='addressdata' method="POST">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="firstName">First name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="lastName">Last name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Valid last name is required.
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label for="username">Username</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">@</span>
                </div>
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
                <div class="invalid-feedback" style="width: 100%;">
                    Your username is required.
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label for="email">Email <span class="text-muted">(Optional)</span></label>
            <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com">
            <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
            </div>
        </div>

        <div class="mb-3">
            <label for="address">Address</label>
            <input type="text" class="form-control" name="address" id="address" placeholder="1234 Main St" required>
            <div class="invalid-feedback">
                Please enter your shipping address.
            </div>
        </div>

        <div class="mb-3">
            <label for="phone">phone <span class="text-muted">(Optional)</span></label>
            <input type="text" class="form-control" name="phone" id="phone" placeholder="phone">
        </div>


        <div class="row">
            <div class="col-md-5 mb-3">
                <label for="country">Country</label>
                <select class="custom-select d-block w-100" id="country" name="country" required>
                    <option value="">Choose...</option>
                    <option>United States</option>
                    <option>Ukraine</option>
                </select>
                <div class="invalid-feedback">
                    Please select a valid country.
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="state">State</label>
                <select class="custom-select d-block w-100" id="state" name="state" required>
                    <option value="">Choose...</option>
                    <option>California</option>
                    <option>Dnipro</option>
                </select>
                <div class="invalid-feedback">
                    Please provide a valid state.
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <label for="zip">Zip</label>
                <input type="text" class="form-control" id="zip" placeholder="" name="zip" required>
                <div class="invalid-feedback">
                    Zip code required.
                </div>
            </div>
        </div>
        <hr class="mb-4">
        <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" name="same-address" id="same-address">
            <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
        </div>
        <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" name="save-info" id="save-info">
            <label class="custom-control-label" for="save-info">Save this information for next time</label>
        </div>
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
    </form>
</div>
</td></tr></table>

</body>
</html>
