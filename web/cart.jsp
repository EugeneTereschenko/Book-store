<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 16.02.21
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="shopstyle.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script
        src="https://code.jquery.com/jquery-1.12.4.min.js"
        integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
<script src="./ownscript.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<div class="container bootstrap snippets bootdey">
    <div class="col-md-9 col-sm-8 content">
        <div class="row">
            <div class="col-md-12">
                <ol class="breadcrumb">
                    <li class="active">Cart</li>
                    <!--
                    <li><a href="#">Home</a></li>
                    <li class="active">Cart</li>
                    -->
                </ol>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info panel-shadow">
                <div class="panel-heading">
                    <h3>
                        <img class="img-circle img-thumbnail" src="https://bootdey.com/img/Content/user_3.jpg">
                        Matew darfkmoun
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table" id ="idCartTable">
                            <thead>
                            <tr>
                                <th>Product</th>
                                <th>Description</th>
                                <th>Qty</th>
                                <th>Price</th>
                                <th>Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td colspan="6">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="text-right">Total Product</td>
                                <td id="totalproduct" id_sum='0'>$00.00</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="text-right">Total Shipping</td>
                                <td id="totalshipping" id_sum='0'>$0.00</td>
                            </tr>
                            <tr>
                                <td colspan="4" class="text-right"><strong>Total</strong></td>
                                <td id="totalcost" id_sum='0'>$00.00</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <a href="#" class="btn btn-success"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Continue Shopping</a>
            <button id="nextcart" class="btn btn-primary pull-right">Next<span class="glyphicon glyphicon-chevron-right"></span></button>
        </div>
    </div>
</div>
</body>
</html>
