<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 03.02.21
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delivery</title>
    <link rel="stylesheet" href="../shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <script src="../ownscript.js"></script>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/checkout/">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $("#id_button_step2").click(function () {
            alert("Help");
            alert($("#selectedDtaeVal").text());
            alert($("#selectedDtaeVal").html());
            alert($("#selectedDtaeVal").value());
        });

        $("#id_expected").click(function (){

            var datetimeOrder = $("#selectedDtaeVal").text();

            $.ajax({
                url: '/bookstore/deliverydata',
                type: 'POST',
                data: {"delivery": "expected", "timedateOrder": datetimeOrder},
                success: function (data) {
                    console.log(data);
                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                }
            });

            window.location.replace("http://localhost:8080/bookstore/payment");
        });


        $("#id_standard").click(function (){

            var datetimeOrder = $("#selectedDtaeVal").text();

            $.ajax({
                url: '/bookstore/deliverydata',
                type: 'POST',
                data: {"delivery": "standard", "timedateOrder": datetimeOrder},
                success: function (data) {
                    console.log(data);
                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                }
            });

            window.location.replace("http://localhost:8080/bookstore/payment");
        });


        $("#id_collect").click(function (){

            var datetimeOrder = $("#selectedDtaeVal").text();

            $.ajax({
                url: '/bookstore/deliverydata',
                type: 'POST',
                data: {"delivery": "collect", "timedateOrder": datetimeOrder},
                success: function (data) {
                    console.log(data);
                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                }
            });

            window.location.replace("http://localhost:8080/bookstore/payment");
        });id_online



        $("#id_online").click(function (){

            var datetimeOrder = $("#selectedDtaeVal").text();

            $.ajax({
                url: '/bookstore/deliverydata',
                type: 'POST',
                data: {"delivery": "online", "timedateOrder": datetimeOrder},
                success: function (data) {
                    console.log(data);
                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                }
            });

            window.location.replace("http://localhost:8080/bookstore/payment");
        });

        $.datepicker.setDefaults({
            onClose:function(date, inst){
                $("#selectedDtaeVal").html(date);
            }
        });

        $("#datepicker").datepicker();



    });


</script>

</head>
<body class="bg-light">

<h1 align="center">Welcome to Bookshop</h1>
<div class="alert alert-primary" role="alert">
    <table><tr><td>Step1</td><td><button type="button" class="btn btn-primary btn-sm" id="id_button_step2">Step2</button></td><td>Step3</td><td><%= session.getAttribute("username") %></td><td><%= session.getAttribute("addressid") %></td><td>Settings</td></tr></table>
</div>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Delivery</h1>
    <p class="lead">In order to do our best to not pass on contaminants from party to party while moving from location to location, we will be changing our Proof of Delivery Process.</p>
</div>

    <div class="card-deck mb-3 text-center">
        <table align="center">
            <tr><td>
                <p>Date: <input type="text" id="datepicker"></p>
                <br/>
                <h4 id="selectedDtaeVal"></h4></td>
                <td>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Free</h4>
            </div>
            <div class="card-body">
                <h1 class="card-title pricing-card-title">$5 <small class="text-muted">/ mo</small></h1>
                <ul class="list-unstyled mt-3 mb-4">
                    <li>Nominated Day</li>
                    <li>Expected delivery</li>
                    <li>Between 7am - 9pm, Mon - Sat</li>
                    <li>on orders $5 or more</li>

                </ul>
                <button type="button" id="id_expected" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
            </div>
        </div>
            </td><td>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Pro</h4>
            </div>
            <div class="card-body">
                <h1 class="card-title pricing-card-title">$15 <small class="text-muted">/ mo</small></h1>
                <ul class="list-unstyled mt-3 mb-4">
                    <li>Standard Delivery</li>
                    <li>Order by: 7pm, Sun - Fri</li>
                    <li>Delivered within 1 day. Between</li>
                    <li>Help center access</li>
                </ul>
                <button type="button" id="id_standard" class="btn btn-lg btn-block btn-primary">Get started</button>
            </div>
        </div>
            </td><td>
        <div class="card mb-4 box-shadow">
            <div class="card-header">
                <h4 class="my-0 font-weight-normal">Click & Collect</h4>
            </div>
            <div class="card-body">
                <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1>
                <ul class="list-unstyled mt-3 mb-4">
                    <li>Click & Collect</li>
                    <li>Orders will be delivered</li>
                    <li>to one of the 1000s </li>
                    <li>of Collect+ stores or Post Offices</li>
                </ul>
                <button type="button" id="id_collect" class="btn btn-lg btn-block btn-primary">Contact us</button>
            </div>
        </div>
            </td></tr>
            <tr><td>
                <div class="card mb-4 box-shadow">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Click & Collect</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>Online</li>
                            <li>ebooks read</li>
                            <li>Free 30 Day Trial</li>
                            <li>only $8.99/mon.</li>
                        </ul>
                        <button type="button" id="id_online" class="btn btn-lg btn-block btn-primary">Contact us</button>
                    </div>
                </div>
            </td></tr>
        </table>
    </div>
</body>
</html>
