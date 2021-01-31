$(document).ready(function(){

    var sumOfTotalProduct = 0;
    var sumOfShipping = 2;
    var sumOfTotal = 0;
    var rows = 0;

    $("#idBookTable").on('click', '.me', function(){

/*
        $("#idBookTable tr td").each(function(){
            var texto = $(this).text();
            alert(texto);
        });
*/
        var num = this.id;
        var text2 = $('#col' + num).text();
        var image = $('#col' + num).attr('id_image');
        var cost = $('#col' + num).attr('id_cost');

        rows = rows + 1;

        sumOfTotalProduct = +sumOfTotalProduct + +cost;
        sumOfTotal = +sumOfTotalProduct + +sumOfShipping;

        $('#totalproduct').text('$' + sumOfTotalProduct  + '.00');
        $('#totalshipping').text('$' + sumOfShipping + '.00');
        $('#totalcost').text('$' + sumOfTotal  + '.00');


        $('#idCartTable').prepend('<tr><td><img src =\"' + image + '\"/></td><td>' + text2 + '</td><td><input class="form-control" id=\"valcartedit' + num + '\"  type="text" value="1">\n' +
            '<button id=\"' + num + '\" class="btn btn-default itercost" ><i class="fa fa-pencil"></i></button>\n' +
            '<button id=\"' + num + '\" class="btn btn-primary iterdeletecost" rel="tooltip" ><i class="fa fa-trash-o"></i></button></td><td id=\"sumone' + num + '\" id_cost=\"' + cost + '\">$' + cost + '.00</td><td id=\"sumall' + num + '\" id_sum=\"' + cost + '\">$' + cost + '.00</td></tr>');

        //$('#idCartTable').append('<tr><td>' + text2 + '</td></tr>');

    });

    $("#idCartTable").on('click', '.iterdeletecost', function(){
        var num = this.id;
        var valueIt = $('#valcartedit' + num).val();
        var valueItid = $('#valcartedit' + num).attr('id');
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

       // alert(sumOf);

        sumOfTotalProduct = +sumOfTotalProduct + +sumOf - +sum*(+valueOf - 1);
        sumOfTotal = +sumOfTotalProduct + +sumOfShipping;


        $('#totalproduct').text('$' + sumOfTotalProduct  + '.00');
        $('#totalshipping').text('$' + sumOfShipping + '.00');
        $('#totalcost').text('$' + sumOfTotal  + '.00');

        $('#valcartedit' + num).val(valueOf);


        $('#sumall' + num).text('$' + sumOf.toString() + '.00');
        $('#sumall' + num).attr('id_sum').val(sumOf.toString());


    });


    /*
    $(".me").click(function () {
        alert("help me i");
        alert(this.id);
        alert("stop");


        var rows = $('#idBookTable tr').length;
        alert(rows);



        var rowId = event.target.parentNode.parentNode.id;

        var data = document.getElementById(rowId).querySelectorAll(".row-data");


        var name = data[0].innerHTML;
        var age = data[1].innerHTML;

        alert("Name: " + name + "\nAge: " + age);

    });
*/


        var temp1 = 2;
        var temp2 = 3;

        $("#id_button").click(function () {
            alert("help me");

            window.location.replace("http://localhost:8080/BookStore_war_exploded/logout");

            /*$(location).attr('href', 'http://stackoverflow.com')

             $.ajax({
                url: "/BookStore_war_exploded/logout",
                 type: 'GET',
                 success: function(data){
                     console.log(data);
                     if (data.d == true) {
                         alert("You will now be redirected.");
                         window.location = "/BookStore_war_exploded/logout";
                     }
                 },
                 failure: function (data) {
                     console.log(data);
                     alert(data);
                 }
             });

             */
        });

        $("#left_button").click(function () {
            var rows = $('#idBookTable tr').length;
            var rowsplus = rows + 4;

            $.ajax({
                url: '/BookStore_war_exploded/books',
                type: 'POST',
                data: {"temp1": rows, "temp2": rowsplus},
                success: function (data) {
                    console.log(data);
                    //alert(data);
                    //$('#idBookTable').append('<tr><td>' + data + '</td></tr>');
                    $('#idBookTable').append(data);
                },
                failure: function (data) {
                    console.log(data);
                    alert(data);
                }
            });
        });

});
