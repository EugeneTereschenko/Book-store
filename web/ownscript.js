$(document).ready(function(){

    var sumOfTotalProduct = 0;
    var sumOfShipping = 2;
    var sumOfTotal = 0;
    var rows = 0;
    var valuesid = [];
    var myrows = 4;
    var myorderrows = 4;

    $("#takepagesnew").click(function () {

        var rowsplus = myrows + 4;
        var temprows = myrows + 1;

        $.ajax({
            url: '/bookstore/books',
            type: 'POST',
            data: {"temp1": temprows, "temp2": rowsplus},
            success: function (data) {
                console.log(data);

                for (var i = 0; i < data.books.length; i++){

                    $('#idBookTable').append('<tr><td colspan="5"><img src = "./images/' + data.books[i].imageid + '" height="100" width="100"/></td></tr>');
                    $('#idBookTable').append('<tr id="' + data.books[i].bookid + '"><td colspan="5" class="row-data" id_cost="' + data.books[i].priceid + '" id_image=\"./images/' + data.books[i].imageid  + '" id="col' + data.books[i].bookid + '" height = "100" width="200">' + data.books[i].titleid + '</td></tr>');
                    $('#idBookTable').append('<tr><td colspan="5" >' + data.books[i].authorid  + '</td></tr>');
                    $('#idBookTable').append('<tr><td>$' + data.books[i].priceid + '.00</td><td colspan="4" align="center"><input id="' + data.books[i].bookid + '" class="me btn btn-primary btn-block"  height="20" width="70" value="' + data.books[i].local + '" /></td></tr>');
                }

            },
            failure: function (data) {
                console.log(data);
                // alert(data);
            }
        });

        myrows = myrows + 4;

    });

    $("#idBookTable").on('click', '.me', function(){

        valuesid.push(this.id);

        var num = this.id;
        var text2 = $('#col' + num).text();
        var image = $('#col' + num).attr('id_image');
        var cost = $('#col' + num).attr('id_cost');
        var valueIt = $('#valcartedit' + num).val();

        if (!valueIt >=1 ){
        rows = rows + 1;
        sumOfTotalProduct = +sumOfTotalProduct + +cost;
        sumOfTotal = +sumOfTotalProduct + +sumOfShipping;
        $('#totalproduct').text('$' + sumOfTotalProduct  + '.00');
        $('#totalshipping').text('$' + sumOfShipping + '.00');
        $('#totalcost').text('$' + sumOfTotal  + '.00');
        $('#idCartTable').prepend('<tr><td><img src =\"' + image + '\" height=\"100\" width=\"100\" /></td><td id=\"checkrow' + rows + '\">' + text2 + '</td><td><input class="form-control" id=\"valcartedit' + num + '\"  type="text" value="1">\n' +
            '<button id=\"' + num + '\" class="btn btn-default itercost" ><i class="fa fa-pencil"></i></button>\n' +
            '<button id=\"' + num + '\" class="btn btn-primary iterdeletecost" rel="tooltip" ><i class="fa fa-trash-o"></i></button></td><td id=\"sumone' + num + '\" id_cost=\"' + cost + '\">$' + cost + '.00</td><td id=\"sumall' + num + '\" id_sum=\"' + cost + '\">$' + cost + '.00</td></tr>');


        } else {
            alert("check your choose");
        }

    });

    $("#idCartTable").on('click', '.iterdeletecost', function(){
        var num = this.id;
        var valueIt = $('#valcartedit' + num).val();
        var sum = $('#sumone' + num).attr('id_cost');
        var valueOf = parseInt(valueIt);
        var sumOf = parseInt(sum) * valueOf;
        var tempSum = +sumOf;
        sumOfTotalProduct = +sumOfTotalProduct - +sumOf;
        sumOfTotal = sumOfTotal - +tempSum;
        rows = rows - 1;
        if (rows == 0){
            sumOfShipping = 0;
            sumOfTotal = 0;
            $('#totalshipping').text('$' + sumOfShipping + '.00');
        } else {
            sumOfShipping = 2;
            $('#totalshipping').text('$' + sumOfShipping + '.00');
        }
        sumOfShipping = 2;
        $('#totalcost').text('$' + sumOfTotal  + '.00');
        $('#totalproduct').text('$' + sumOfTotalProduct  + '.00');
        $(this).closest("tr").remove();
    });

    $("#idCartTable").on('click', '.itercost', function(){
        var num = this.id;
        var valueIt = $('#valcartedit' + num).val();
        var valueItid = $('#valcartedit' + num).attr('id');
        var sum = $('#sumone' + num).attr('id_cost');
        var valueOf = parseInt(valueIt) + 1;

        var sumOf = parseInt(sum) * valueOf;


        sumOfTotalProduct = +sumOfTotalProduct + +sumOf - +sum*(+valueOf - 1);
        sumOfTotal = +sumOfTotalProduct + +sumOfShipping;
        $('#totalproduct').text('$' + sumOfTotalProduct  + '.00');
        $('#totalshipping').text('$' + sumOfShipping + '.00');
        $('#totalcost').text('$' + sumOfTotal  + '.00');
        $('#valcartedit' + num).val(valueOf);
        $('#sumall' + num).text('$' + sumOf.toString() + '.00');
        $('#sumall' + num).attr('id_sum').val(sumOf.toString());
    });



        var temp1 = 2;
        var temp2 = 3;

        $("#id_button_step2").click(function () {
            alert("Help");
        });

        $("#idusersitemy").click(function () {
            window.location.replace("http://localhost:8080/bookstore/showusers");
        });

         $("#idbookordermy").click(function () {
             window.location.replace("http://localhost:8080/bookstore/showcarts");
        });

        $("#my_name").click(function () {
            window.location.replace("http://localhost:8080/bookstore/settings");
        });

        $("#idbooksitemy").click(function () {
            window.location.replace("http://localhost:8080/bookstore/showbooks");
        });

        $("#id_button").click(function () {
           // alert("help me");
            window.location.replace("http://localhost:8080/bookstore/logout");

        });

        $("#nextcart").click(function (){
            var books = {'books': []};
            for (var i = 0; i < valuesid.length; i++) {
                var valueIt = $('#valcartedit' + valuesid[i]).val();
                var book = {"id": valuesid[i],"value": valueIt};
                books.books.push(book);
            }

                $.ajax({
                    url: '/bookstore/items',
                    type: 'POST',
                    data: 'books=' + JSON.stringify(books),
                    dataType: 'JSON',
                    beforeSend: function(x) {
                        if (x && x.overrideMimeType) {
                            x.overrideMimeType("application/j-son;charset=UTF-8");
                        }
                    },
                    success: function (data) {

                        console.log(data);

                        document.cookie =  "cartid=" + data + ";";
                        window.location.replace("http://localhost:8080/bookstore/address");

                    },
                    failure: function (data) {
                        console.log(data);
                        // alert(data);
                    }
                });

        });



    $("#id_button_title").click(function () {

        $.ajax({
            url: '/bookstore/booksorder',
            type: 'POST',
            data: {"temp1": myrows, "temp2": "title"},
            success: function (data) {
                console.log(data);

                $("#idBookTable").find("tr:not(:nth-child(1)):not(:nth-child(2))").remove();

                $('#idBookTable').append(data);
            },
            failure: function (data) {
                console.log(data);

            }
        });

    });


    $("#id_button_author").click(function () {


        $.ajax({
            url: '/bookstore/booksorder',
            type: 'POST',
            data: {"temp1": myrows, "temp2": "author"},
            success: function (data) {
                console.log(data);

                $("#idBookTable").find("tr:not(:nth-child(1)):not(:nth-child(2))").remove();

                $('#idBookTable').append(data);
            },
            failure: function (data) {
                console.log(data);

            }
        });
    });



    $("#id_button_price").click(function () {


        $.ajax({
            url: '/bookstore/booksorder',
            type: 'POST',
            data: {"temp1": myrows, "temp2": "price"},
            success: function (data) {
                console.log(data);

                $("#idBookTable").find("tr:not(:nth-child(1)):not(:nth-child(2))").remove();

                $('#idBookTable').append(data);
            },
            failure: function (data) {
                console.log(data);

            }
        });

    });



    $("#id_button_year").click(function () {


        $.ajax({
            url: '/bookstore/booksorder',
            type: 'POST',
            data: {"temp1": myrows, "temp2": "year"},
            success: function (data) {
                console.log(data);

                $("#idBookTable").find("tr:not(:nth-child(1)):not(:nth-child(2))").remove();

                $('#idBookTable').append(data);
            },
            failure: function (data) {
                console.log(data);

            }
        });
    });

});
