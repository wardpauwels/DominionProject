$(document).ready(function () {
    var name1;
});

function setBoard(){
    var request = $.ajax({ cache: false,
        url: "/BoardServlet",
        type: "GET",
        data:{operation: 'initialize'} ,
        success: function (data) {
            alert(data.name1);
        },
        error: function (data) {
            alert("ERROR: " + data.status);
        }
    });
}