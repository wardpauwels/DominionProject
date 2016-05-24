$(document).ready(function () {
    update();
});
/*
function updateActionAmount(){
    for (var i = 0; i < array.length; i++) {
    html += '<p class="counteronactioncards">' + array[i].amount + '</p>';

}}*/

function setBoard() {
    console.log("set board werkt");
    updateHand();

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
function generateActionCardsOnBoard(array) {
    /* for (var i = 0; i < array.length; i++) {
     var html = '<li>';
     var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
     html += '<p class="counteronactioncards">0</p>';
     html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
     html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">'
     html += '</li>';
     $(".actioncards_on_table_print").append(html);
     }*/
    console.log(array);
    for (var i = 0; i < array.length; i++) {
        var parent = $('<li class="test"></li>');
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        var html = "";
        html += '<p class="counteronactioncards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        //html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">';
        var plusbutton = $('<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">');
        plusbutton.data("cardNumber", array[i].number);
        console.log(parent.data("cardNumber"));
        parent.html(html);
        parent.append(plusbutton);
        console.log(parent);
        $(".actioncards_on_table_print").append(parent);
    }
}

function generateVictoryCardsOnBoard(array){
    for (var i = 0; i < array.length; i++) {
        var parent = $('<li></li>');
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        var html = "";
        html += '<p class="counteronsmallcards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        //html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">';
        var plusbutton = $('<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">');
        plusbutton.data("cardNumber", array[i].number);
        console.log(parent.data("cardNumber"));
        parent.html(html);
        parent.append(plusbutton);
        console.log(parent);
        $(".victorycards_on_table_print").append(parent);
    }
}

function generateTreasureCardsOnBoard(array){
    for (var i = 0; i < array.length; i++) {
        var parent = $('<li></li>');
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        var html = "";
        html += '<p class="counteronsmallcards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        //html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">';
        var plusbutton = $('<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">');
        plusbutton.data("cardNumber", array[i].number);
        console.log(parent.data("cardNumber"));
        parent.html(html);
        parent.append(plusbutton);
        console.log(parent);
        $(".treasurecards_on_table_print").append(parent);
    }
}

$('#victory_cards').on('click', '.buyVictoryCardsandCoinCards', function () {


    console.log("kaart spelen werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType:"text",
        data: {
            action: 'buyVictoryCard',
            positionOnBoard: $(this).data('cardNumber') //TODO dylan index van 'li' moet door gegeven worden als ik + druk, gwn achter deze positionOnBoard zetten

        }
    });

    request.done(function (data) {
        //alert(data);
        update();

    });
    request.fail(function (jqXHR, textStatus) {
        alert("nie gelukt");
        alert(jqXHR.status + ' ' + textStatus);
    });


});

$('#money_cards').on('click', '.buyVictoryCardsandCoinCards', function () {


    console.log("kaart spelen werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType:"text",
        data: {
            action: 'buyTreasureCard',
            positionOnBoard: $(this).data('cardNumber') //TODO dylan index van 'li' moet door gegeven worden als ik + druk, gwn achter deze positionOnBoard zetten

        }
    });

    request.done(function (data) {
        //alert(data);
        update();

    });
    request.fail(function (jqXHR, textStatus) {
        alert("nie gelukt");
        alert(jqXHR.status + ' ' + textStatus);
    });


});

$('#actioncards_on_table').on('click', '.buyActionCard', function () {


    console.log("kaart spelen werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType:"text",
        data: {
            action: 'buyActionCard',
            positionOnBoard: $(this).data('cardNumber') //TODO dylan index van 'li' moet door gegeven worden als ik + druk, gwn achter deze positionOnBoard zetten

        }
    });

    request.done(function (data) {
        //alert(data);
        update();

    });
    request.fail(function (jqXHR, textStatus) {
        alert("nie gelukt");
        alert(jqXHR.status + ' ' + textStatus);
    });


});

function updateActionCardBoard() {
    var actionCards = $('.actioncards_on_table_print');
    actionCards.empty();
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
        //$('#player_one_name').html(data.name1);
        //$('#player_two_name').html(data.name2);
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.actionCardsOnBoard);
        generateActionCardsOnBoard(obj.actionCardsOnBoard);
        $("#actioncards_on_table ul li img:nth-of-type(1)").click(function () {
            console.log("gevonden");
            $actioncardOnTableName = $(this).attr("title");
            var src = "<img src='assets/images/Big%20cards/" + $actioncardOnTableName + ".jpg' title = '" + $actioncardOnTableName + "' alt = '" + $actioncardOnTableName + "'/><br>";
            console.log(src);
            $('#bigCard').html(src).css('visibility', 'visible');
        });
        $('#bigCard').click(function () {
            $('#bigCard').css('visibility', 'hidden');
        });
        /*$("#actioncards_on_table ul li img:nth-of-type(2)").click(function () {
         console.log("kopen werkt");
         buyActionCard();})*/


    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}

function updateVictoryCardBoard() {
    var victoryCards = $('.victorycards_on_table_print');
    victoryCards.empty();
    console.log("update victory werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateVictoryBoard'

        }
    });

    request.done(function (data) {

        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.victoryCardsOnBoard);
        generateVictoryCardsOnBoard(obj.victoryCardsOnBoard);
        //FIXCOUNTERSMETHOD
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}

function updateTreasureCardBoard() {
    var treasureCards = $('.treasurecards_on_table_print');
    treasureCards.empty();
    console.log("update victory werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateTreasureBoard'

        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.treasureCardsOnBoard);
        generateTreasureCardsOnBoard(obj.treasureCardsOnBoard);
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}
function updateCurrentlyPlaying() {
    console.log("update coins werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updatePlayer'

        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.activePlayer);
        updatePlayer(obj.activePlayer);
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}


function updateCoinsActionsBuys() {
    console.log("update coins werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateCoinsActionsBuys'

        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log(obj.coinsActionsBuys);
        updateCAB(obj.coinsActionsBuys);
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}

function updateCAB(array){
    $('#Amount_Of_Coins').html(array[0]);
    $('#Amount_Of_Actions').html(array[1]);
    $('#Amount_Of_Buys').html(array[2]);
}

function updatePlayer(player){
    $('#Current_Playing').html(player[0].name);
}

function update() {
    console.log("voor werkt");
    setBoard();
    console.log("na werkt");
    updateActionCardBoard();
    updateVictoryCardBoard();
    updateTreasureCardBoard();
    updateCoinsActionsBuys();
    updateCurrentlyPlaying();
    console.log("fml");
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
    $("#hand").empty();
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Big%20cards/' + array[i].toLowerCase() + '.jpg';
        html += '<img alt="' + array[i].toLowerCase() + '"  title="' + array[i].toLowerCase() + '" src="' + src + '" />';
        html += '</li>';
        $("#hand").append(html);
    }
}

$('#nextPlayerButton').on('click', function () {
    console.log("volgende speler werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType:"text",
        data: {action: 'endTurn'}
    });

    request.done(function (data) {
        //alert(data);
        update();

    });
    request.fail(function (jqXHR, textStatus) {
        alert("nie gelukt om volgende speler te starten");
        alert(jqXHR.status + ' ' + textStatus);
    });
});