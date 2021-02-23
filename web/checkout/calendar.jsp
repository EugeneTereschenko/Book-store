<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 07.02.21
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/custom.tld"%>

<html lang="ru">
<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<p>Date: <input type="text" id="datepicker"></p>
<br/>
<h4 id="selectedDtaeVal"></h4>
<script>
    $(function() {


        $("#id_button_step2").click(function () {
            alert("Help");
            alert($("#selectedDtaeVal").text());
            alert($("#selectedDtaeVal").html());
            alert($("#selectedDtaeVal").value());
        });

        $.datepicker.setDefaults({
            onClose:function(date, inst){
                $("#selectedDtaeVal").html(date);
            }
        });

        $("#datepicker").datepicker();

        //$('.selectpicker').selectpicker();

        $('#selectedLanguage').on('click',function() {
            alert($(this).val());
            console.log($(this).val());
        });

        $("#dialog").dialog({
           autoOpen: false,
           modal: true
        });

        $("#myButton").on("click", function(e){
            e.preventDefault();
            $("#dialog").dialog("open");
        });

        $("#id_book_but").click(function () {
            //alert("TEST");
            var text3 = $('#id_book').val();
            alert(text3);
        });

        $("#id_book_but_new").click(function () {

            window.location.replace("http://localhost:8080/bookstore/shop.jsp");

        });



        $("#pdfsentemail").click(function () {

           // var value = getCookie("cartid");

            $.ajax({
                url: '/bookstore/sendmailcartid',
                type: 'POST',
                data: {"cartid": 2},
                success: function (data) {
                    //   console.log(data)

                },
                failure: function (data) {
                    //  console.log(data);
                    // alert(data);
                }
            });

        });


    });
</script>
<button type="button" class="btn btn-primary btn-sm" id="id_button_step2">Step2</button>
<select class="selectpicker" data-width="fit" id="selectedLanguage">
    <option data-content='<span class="flag-icon flag-icon-us"></span> English'>English</option>
    <option  data-content='<span class="flag-icon flag-icon-mx"></span> Español'>Español</option>
</select>

<button id="myButton">click!</button>
<button id="id_book_but_new" class="btn btn-default id_book_but" >index</button>
<button type="button" id="pdfsentemail" class="btn btn-lg btn-block btn-primary">Sent me email</button>
<div id="dialog" title="Dialog box">
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm">Small</span>
        </div>
        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm">
        <input class="form-control" id="id_book"  type="text" value="1">
        <button id="id_book_but" class="btn btn-default id_book_but" >test</button>
    </div>
</div>


<form action="upload" method="post" enctype="multipart/form-data">
    <input type="text" name="description" />
    <input type="file" name="file" />
    <input type="submit" />
</form>


<img class = "img-circle img-thumbnail" src = "../WEB-INF/uploads/29.jpg" alt="xerox" height="100" width="100" />
<img src = "../WEB-INF/uploads/29.jpg" alt="xerox" height="100" width="100" />
<img src = "./images/user/29.jpg" alt="xerox" height="100" width="100" />
<img src = "./images/9780393635546.jpg" height="100" width="100"/>

<ex:infoFooter/>

<footer class="bg-light text-center text-lg-start">
    <!-- Copyright -->
    <div class="text-center p-3" align = "center" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2020 Copyright:
        <a class="text-dark" href="https://mdbootstrap.com/">MDBootstrap.com</a>
    </div>
    <!-- Copyright -->
</footer>


</body>
</html>
