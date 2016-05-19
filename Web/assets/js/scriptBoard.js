$(document).ready(function () {
    setBoard();
});

function setBoard(){
    var request = $.ajax({ cache: false,
        url: "/BoardServlet",
        type: "GET",
        data:{ operation: 'getNames'} ,
        success: function (data) {
            $('#player_one_name').html(data.name1);
            $('#player_two_name').html(data.name2);
        },
        error: function (data) {
            console.log(data);
            alert("ERROR: " + data.status);
        }
    });
}