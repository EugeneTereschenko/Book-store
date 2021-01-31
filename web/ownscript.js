$(document).ready(function(){

    $("#idBookTable").on('click', '.me', function(){
       // alert("button click on table");
        //alert(this.id);

        var num = this.id;

//var str1 = "col";
//var res = str1.concat(num);


/*
        $("#idBookTable tr td").each(function(){
            var texto = $(this).text();
            alert(texto);
        });
*/

        var text2 = $('#col' + num).text();
        alert(text2);

        $('#idCartTable').append('<tr><td>' + text2 + '</td></tr>');

       // var str = $('#addlist' + project_id).val();

/*
        var currentRow = 0;

        var rowvalues = [];

        $("#idBookTable tr").each(function(){
            alert($(this).attr(num));
        });
*/

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
            alert("help no mw");
            alert(rows);
            alert(rowsplus);

            $.ajax({
                url: '/BookStore_war_exploded/books',
                type: 'POST',
                data: {"temp1": rows, "temp2": rowsplus},
                success: function (data) {
                    console.log(data);
                    alert(data);
                    $('#idBookTable').append('<tr><td>' + data + '</td></tr>');
                },
                failure: function (data) {
                    console.log(data);
                    alert(data);
                }
            });
        });



});

function show() {
    alert(this.id);
}