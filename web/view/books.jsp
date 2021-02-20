<%@ page import="com.shop.entity.Book" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Properties" %><%--
  Created by IntelliJ IDEA.
  User: yevhen
  Date: 15.02.21
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Books</title>
    <link rel="stylesheet" href="shopstyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

<h1 align="center">Bookshop Product</h1>
<script type="text/javascript">

    $(document).ready(function() {


        $("#dialog").dialog({
            autoOpen: false,
            modal: true
        });

        $("#add").on("click", function (e) {
            e.preventDefault();
            $("#dialog").dialog("open");
        });

        $("#id_book_close").click(function (e) {
            e.preventDefault();
            $("#dialog").dialog("close");
        });

        $("#id_book_but").click(function () {
           // alert("TEST");

            var book = {};

            var imagebook = $('#image_book').val();
            var titlebook = $('#title_book').val();
            var authorbook = $('#author_book').val();
            var pricebook = $('#price_book').val();
            var yearbook = $('#year_book').val();
            var descriptionbook = $('#description_book').val();
            var heightbook = $('#height_book').val();
            var widthbook = $('#width_book').val();
            var depthbook = $('#depth_book').val();
            var instockbook = $('#in_stock_book').val();
            var materialsbook = $('#materials_book').val();

            book.image = imagebook;
            book.title = titlebook;
            book.author = authorbook;
            book.price = pricebook;
            book.year = yearbook;
            book.description = descriptionbook;
            book.height = heightbook;
            book.width = widthbook;
            book.depth = depthbook;
            book.instock = instockbook;
            book.materials = materialsbook;

            $.ajax({
                url: '/bookstore/insertbook',
                type: 'POST',
                data: 'book=' + JSON.stringify(book),
                dataType: 'JSON',
                beforeSend: function(x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data) {
                   // alert('success'+data);
                    console.log(data);
                    $("#sentstatus").html(data.localid.toString());
                    window.location.replace("http://localhost:8080/bookstore/showbooks.jsp");



                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                    $("#sentstatus").html("error");
                }
            });


        });

        $("#view").dialog({
            autoOpen: false,
            modal: true
        });


        $("#viewbooks").on('click', '.booksnewview', function(e) {

            alert(this.id);
            e.preventDefault();
            $("#view").dialog("open");
            $.ajax({
                url: '/bookstore/showonebook',
                type: 'POST',
                data: {"idbook": this.id},
                success: function (data) {
                    //alert('success'+data);

                    console.log(data);
                    $('#image_book_view').html(data.imageid);
                    $('#title_book_view').html(data.titleid);
                    $('#author_book_view').html(data.authorid);
                    $('#price_book_view').html(data.priceid);
                    $('#year_book_view').html(data.yearid);
                    $('#description_book_view').html(data.descrid);
                    $('#materials_book_view').html(data.materid);
                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);

                }
            });


        });

        $("#id_book_close_view").click(function (e) {
            e.preventDefault();
            $("#view").dialog("close");
        });

        $("#edit").dialog({
            autoOpen: false,
            modal: true
        });


        $("#viewbooks").on('click', '.booksnewedit', function(e) {
            e.preventDefault();
            $("#edit").dialog("open");
            //alert(this.id);

            $.ajax({
                url: '/bookstore/showonebook',
                type: 'POST',
                data: {"idbook": this.id},
                success: function (data) {
                    //alert('success'+data);

                    console.log(data);
                    $('#id_book_edit').val(data.bookid);
                    $('#image_book_edit').val(data.imageid);
                    $('#title_book_edit').val(data.titleid);
                    $('#author_book_edit').val(data.authorid);
                    $('#price_book_edit').val(data.priceid);
                    $('#year_book_edit').val(data.yearid);
                    $('#description_book_edit').val(data.descrid);
                    $('#materials_book_edit').val(data.materid);
                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);

                }
            });

        });

        $("#id_book_but_edit").click(function () {
            // alert("TEST");

            var book = {};

            var idbook = $('#id_book_edit').val();
            var imagebook = $('#image_book_edit').val();
            var titlebook = $('#title_book_edit').val();
            var authorbook = $('#author_book_edit').val();
            var pricebook = $('#price_book_edit').val();
            var yearbook = $('#year_book_edit').val();
            var descriptionbook = $('#description_book_edit').val();
            var heightbook = $('#height_book_edit').val();
            var widthbook = $('#width_book_edit').val();
            var depthbook = $('#depth_book_edit').val();
            var instockbook = $('#in_stock_book_edit').val();
            var materialsbook = $('#materials_book_edit').val();



            book.bookid = idbook;
            book.image = imagebook;
            book.title = titlebook;
            book.author = authorbook;
            book.price = pricebook;
            book.year = yearbook;
            book.description = descriptionbook;
            book.height = heightbook;
            book.width = widthbook;
            book.depth = depthbook;
            book.instock = instockbook;
            book.materials = materialsbook;

            $.ajax({
                url: '/bookstore/updatebook',
                type: 'POST',
                data: 'book=' + JSON.stringify(book),
                dataType: 'JSON',
                beforeSend: function (x) {
                    if (x && x.overrideMimeType) {
                        x.overrideMimeType("application/j-son;charset=UTF-8");
                    }
                },
                success: function (data) {
                    // alert('success'+data);
                    console.log(data);
                    $("#sentstatus_edit").html(data.localid.toString());

                },
                failure: function (data) {
                    console.log(data);
                    // alert(data);
                    $("#sentstatus_edit").html("error");
                }
            });
        });

        $("#id_book_close_edit").click(function (e) {
            e.preventDefault();
            $("#edit").dialog("close");
        });

        $("#viewbooks").on('click', '.booksnewdelete', function() {

            //alert(this.id);
            $(this).closest("tr").remove();
            $.ajax({
                url: '/bookstore/deletebook',
                type: 'POST',
                data: {"idbook": this.id},
                success: function (data) {
                    //alert('success'+data);
                    console.log(data);
                    //$(this).closest("tr").remove();
                    //window.location.replace("http://localhost:8080/bookstore/showbooks");
                },
                failure: function (data) {
                    console.log(data);
                    alert(data);

                }
            });
        });


        $("#id_button").click(function () {
            window.location.replace("http://localhost:8080/bookstore/logout");
        });

        $("#id_button_back").click(function () {
            window.location.replace("http://localhost:8080/bookstore/shop");
        });

        $("#idusersitemy").click(function () {
            window.location.replace("http://localhost:8080/bookstore/showusers");
        });

        $("#id_idcartsitemy").click(function () {
            window.location.replace("http://localhost:8080/bookstore/showcarts");
        });


    });


</script>


<div id="dialog" title="Sent book">
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm2">image</span>
        </div>
        <input type="text" class="form-control" id="image_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm3">title</span>
        </div>
        <input type="text" class="form-control" id="title_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm4">author</span>
        </div>
        <input type="text" class="form-control" id="author_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm5">price</span>
        </div>
        <input type="text" class="form-control" id="price_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm6">year</span>
        </div>
        <input type="text" class="form-control" id="year_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm7">description</span>
        </div>
        <input type="text" class="form-control" id="description_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm8">height</span>
        </div>
        <input type="text" class="form-control" id="height_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm9">width</span>
        </div>
        <input type="text" class="form-control" id="width_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm10">depth</span>
        </div>
        <input type="text" class="form-control" id="depth_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm11">in_stock</span>
        </div>
        <input type="text" class="form-control" id="in_stock_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm12">materials</span>
        </div>
        <input type="text" class="form-control" id="materials_book"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="sentstatus">status</span>
        </div>
        <button id="id_book_but" class="btn-default btn btn-outline-secondary" type="button" >Sent</button>
        <button id="id_book_close" type="button" class="btn btn-secondary">Close</button>
    </div>

</div>


<div id="edit" title="Edit book">
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm1edit">id</span>
        </div>
        <input type="text" class="form-control" id="id_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm2edit">image</span>
        </div>
        <input type="text" class="form-control" id="image_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm3edit">title</span>
        </div>
        <input type="text" class="form-control" id="title_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm4edit">author</span>
        </div>
        <input type="text" class="form-control" id="author_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm5edit">price</span>
        </div>
        <input type="text" class="form-control" id="price_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm6edit">year</span>
        </div>
        <input type="text" class="form-control" id="year_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm7edit">description</span>
        </div>
        <input type="text" class="form-control" id="description_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm8edit">height</span>
        </div>
        <input type="text" class="form-control" id="height_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm9edit">width</span>
        </div>
        <input type="text" class="form-control" id="width_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm10edit">depth</span>
        </div>
        <input type="text" class="form-control" id="depth_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm11edit">in_stock</span>
        </div>
        <input type="text" class="form-control" id="in_stock_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-sm12edit">materials</span>
        </div>
        <input type="text" class="form-control" id="materials_book_edit"  aria-label="Small" aria-describedby="inputGroup-sizing-sm">
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="sentstatus_edit">status</span>
        </div>
        <button id="id_book_but_edit" class="btn-default btn btn-outline-secondary" type="button" >Sent</button>
        <button id="id_book_close_edit" type="button" class="btn btn-secondary">Close</button>
    </div>

</div>



<div id="view" title="View book">

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="imageidview">image</span>
            <span class="input-group-text" id="image_book_view" value=""></span>
        </div>
    </div>
    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="titleidview">title</span>
            <span class="input-group-text" id="title_book_view" value=""></span>
        </div>
    </div>


    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="authoridview">author</span>
            <span class="input-group-text" id="author_book_view" value=""></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="priceidview">price</span>
            <span class="input-group-text" id="price_book_view"></span>
        </div>
    </div>


    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="yearidview">year</span>
            <span class="input-group-text" id="year_book_view"></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend" >
            <span class="input-group-text" id="descridview">desciption</span>
            <span class="input-group-text" id="description_book_view"></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="materialsidview">materials</span>
            <span class="input-group-text" id="materials_book_view"></span>
        </div>
    </div>

    <div class="input-group input-group-sm mb-3">
        <button id="id_book_close_view" type="button" class="btn btn-secondary">Close</button>
    </div>

</div>






<%
    String setLocal = (String) session.getAttribute("idlocal");
    String fileproper = null;
    if (setLocal.equals("ru")){
        fileproper = "app_ru.properties";
    } else {
        fileproper = "app_en.properties";
    }

    Properties properties = new Properties();
    InputStream stream = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(fileproper);
    InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
    properties.load(reader);

    String exit = properties.getProperty("fieldExit");
    String usersitemy = properties.getProperty("fieldUser");

    String cartsitemy = properties.getProperty("fieldOrder");

    String back = properties.getProperty("fieldBack");
    String role = (String) session.getAttribute("roleid");

    List<Book> book = (List<Book>) session.getAttribute("viewbooks");

    out.println("<div class=\"container\">");
        out.println("<div class=\"row\">");
            out.println("<div class=\"col\">");
            out.println("");
            out.println("</div>");
            out.println("<div class=\"col\">");
            out.println("<ul class=\"nav nav-pills\">");
            out.println("<li role=\"presentation\" class=\"active\"><button type=\"button\" class=\"btn btn-primary btn-sm\" id=\"id_button\">" + exit +  "</button></li>");
            out.println("<li role=\"presentation\" class=\"active\"><button type=\"button\" class=\"btn btn-primary btn-sm\" id=\"id_button_back\">" + back +  "</button></li>");
            if (role.equals("administrator")) {
                out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"idusersitemy\" value=\"" + usersitemy + "\" /></li>");
            }
            if (role.equals("administrator") || role.equals("manager")) {
                out.println("<li role=\"presentation\" class=\"active\"><input type=\"button\" class=\"btn btn-primary newclass btn-sm\" id=\"id_idcartsitemy\" value=\"" + cartsitemy + "\" /><li>");
            }
            out.println("</ul>");
            out.println("</div>");
            out.println("<div class=\"col\">");
            out.println("");
            out.println("</div>");
        out.println("</div>");
            out.println("<div class=\"row\">");
            out.println("<div class=\"col\">");
            out.println("");
            out.println("</div>");
            out.println("<div class=\"col\">");
            out.println("<table class=\"table card-table table-success table-striped\" id=\"viewbooks\" width = \"800\"><tbody>");
            out.println("<tr><td>id</td><td>image</td><td>title</td><td>author</td><td>price</td><td>year</td><td>description</td><td>materials</td><td colspan =\"3\"></td></tr>");
            for(int i=0; null!=book && i < book.size(); i++) {
            out.println("<tr><td>" + book.get(i).getId() + "</td><td><img src = \"./images/" + book.get(i).getImage() + "\" height=\"100\" width=\"100\"/></td>");
            out.println("<td>" + book.get(i).getTitle() + "</td><td>" + book.get(i).getAuthor() + "</td><td>$" + book.get(i).getPrice() + ".00</td>");
            out.println("<td>" + book.get(i).getYear() + "</td><td>" + book.get(i).getDescription() + "</td><td>" + book.get(i).getMaterials() + "</td>");
            out.println("<td><button id=" + book.get(i).getId() + " class=\"btn btn-primary booksnewview btn-block\">View</button></td>");
            out.println("<td><button id=" + book.get(i).getId() + " class=\"btn btn-primary booksnewedit btn-block\">Edit</button></td>");
            out.println("<td><button id=" + book.get(i).getId() + " class=\"btn btn-primary booksnewdelete btn-block\">Delete</button></td><tr>");
            }
            out.println("<tr><td colspan = \"11\" align = \"right\"><button id=\"add\" class=\"btn btn-primary btn-block\">Add</button></td></tr>");
            out.println("</tbody></table>");
            out.println("</div>");
            out.println("<div class=\"col\">");
            out.println("");
            out.println("</div>");
        out.println("</div>");
    out.println("</div>");

%>
</div>
</body>
</html>
