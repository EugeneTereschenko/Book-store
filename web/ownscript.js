$(document).ready(function(){


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