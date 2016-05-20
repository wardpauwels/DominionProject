$(document).ready(function () {
    console.log("voor werkt");
    setBoard();
    console.log("na werkt");

});

function setBoard() {
    console.log("set board werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'getCards'

        }
    });

    request.done(function (data) {
        //$('#player_one_name').html(data.name1);
        //$('#player_two_name').html(data.name2);
        console.log(data);
        console.log(data.CardNames);
        var obj = JSON.parse(data);
        console.log(obj.CardNames);
        generateVisualCardNames(obj.CardNames);
        

    });
    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}

function generateVisualCardNames(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Big%20cards/' + array[i].toLowerCase() + '.jpg';
        html += '<img alt="' + array[i].toLowerCase() + '"  title="' + array[i].toLowerCase() + '" src="' + src + '" />';
        html += '</li>';
        $("#baraja-el").append(html);
    }
}
