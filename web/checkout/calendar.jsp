<%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 07.02.21
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    });
</script>
<button type="button" class="btn btn-primary btn-sm" id="id_button_step2">Step2</button>
</body>
</html>
