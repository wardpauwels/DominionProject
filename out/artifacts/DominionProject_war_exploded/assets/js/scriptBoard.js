$(document).ready(function () {
    setBoard();
});

function setBoard(){
    var request = $.ajax({ cache: false,
        url: "/BoardServlet",
        type: "GET",
        data:{ operation: 'getNames'} ,
        success: function (data) {
            $('#player_one_name').html("haha");
            $('#player_two_name').html("haha");
        },
        error: function (data) {
            alert("ERRORRR: " + data.status);
        }
    });
}