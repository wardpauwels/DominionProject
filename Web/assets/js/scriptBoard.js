$(document).ready(function () {
    console.log("voor werkt");
    setBoard();
    console.log("na werkt");
    updateActionCardBoard();

});


function setBoard() {
    console.log("set board werkt");

    //get handcards
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {action: 'getCards'}
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
}

function updateActionCardBoard() {

    console.log("update action werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateActionBoard'

        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.actionCardsOnBoard);
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

    //get names
    var requestNames = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {action: 'getNames'}
    });

    requestNames.done(function (data) {
        var obj = JSON.parse(data);
        if (obj.amount == "2"){
            $("#header").html("<div id='two_players'><p id='player_one_name' class='player_one_name'>" + obj.name1 + "</p><p id='player_two_name' class='player_two_name'>" + obj.name2 + "</p></div>");
        } else if (obj.amount == "3"){
            $('#header').html("<div id='three_players'><div class='player_one_name'><p>" + obj.name1 + "</p></div><div class='player_two_name'><p>" + obj.name2 + "</p></div><div class='player_third_name'><p>" + obj.name3 + "</p></div>");
        } else if (obj.amount == "4"){
            $('#header').html("<div id='four_players'><div class='player_one_name'><p>" + obj.name1 + "</p></div><div class='player_two_name'><p>" + obj.name2 + "</p></div> <div class='player_third_name'><p>" + obj.name3 + "</p></div><div class='player_fourth_name'><p>" + obj.name4 + "</p></div></div>");
        }
    });
    requestNames.fail(function (jqXHR, textStatus) {
        alert(jqXHR.status + ' ' + textStatus);
    });

}
function update() {
    updateHand();
}

function updateHand() {
    console.log("updateHand werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateHand'
        }
    });

    request.done(function (data) {
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