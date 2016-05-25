$(document).ready(function () {
    throne = false;
    setBoard();
    trPlayed = false;
    counterForLibrary = 2;
    update();
    $('#hand').on({
        mouseenter: function (e) {
            $('.popup').fadeIn(1000);
        },
        mouseleave: function (e) {
            $('.popup').fadeOut(1000);
        }
    });
    $('.actioncards_on_table_print').on({
        mouseenter: function (e) {
            $('.popupActioncard').fadeIn(1000);
        },
        mouseleave: function (e) {
            $('.popupActioncard').fadeOut(1000);
        }
    });
    $('#victory_cards').on({
        mouseenter: function (e) {
            $('.popupVictoryCards').fadeIn(1000);
        },
        mouseleave: function (e) {
            $('.popupVictoryCards').fadeOut(1000);
        }
    });
    $('#money_cards').on({
        mouseenter: function (e) {
            $('.popupTreasureCards').fadeIn(1000);
        },
        mouseleave: function (e) {
            $('.popupTreasureCards').fadeOut(1000);
        }
    });
});

function setBoard() {
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
        if (obj.amount == "2") {
            $("#header").html("<div id='two_players'><p id='player_one_name' class='player_one_name'>" + obj.name1 + "</p><p id='player_two_name' class='player_two_name'>" + obj.name2 + "</p></div>");
        } else if (obj.amount == "3") {
            $('#header').html("<div id='three_players'><div class='player_one_name'><p>" + obj.name1 + "</p></div><div class='player_two_name'><p>" + obj.name2 + "</p></div><div class='player_third_name'><p>" + obj.name3 + "</p></div>");
        } else if (obj.amount == "4") {
            $('#header').html("<div id='four_players'><div class='player_one_name'><p>" + obj.name1 + "</p></div><div class='player_two_name'><p>" + obj.name2 + "</p></div> <div class='player_third_name'><p>" + obj.name3 + "</p></div><div class='player_fourth_name'><p>" + obj.name4 + "</p></div></div>");
        }
    });
    requestNames.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + ' ' + textStatus);
    });
}
function generateActionCardsOnBoard(array) {
    for (var i = 0; i < array.length; i++) {
        var parent = $('<li class="test"></li>');
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        var html = "";
        html += '<p class="counteronactioncards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        //html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">';
        var plusbutton = $('<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">');
        plusbutton.data("cardNumber", array[i].number);
        parent.html(html);
        parent.append(plusbutton);
        $(".actioncards_on_table_print").append(parent);
    }
}

function generateVictoryCardsOnBoard(array) {
    for (var i = 0; i < array.length; i++) {
        var parent = $('<li></li>');
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        var html = "";
        html += '<p class="counteronsmallcards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        //html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">';
        var plusbutton = $('<img alt="Buy victory card" title="Buy victory card" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">');
        plusbutton.data("cardNumber", array[i].number);
        parent.html(html);
        parent.append(plusbutton);
        $(".victorycards_on_table_print").append(parent);
    }
}

function generateTreasureCardsOnBoard(array) {
    for (var i = 0; i < array.length; i++) {
        var parent = $('<li></li>');
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        var html = "";
        html += '<p class="counteronsmallcards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        //html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">';
        var plusbutton = $('<img alt="Buy treasure card" title="Buy treasure card" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">');
        plusbutton.data("cardNumber", array[i].number);
        parent.html(html);
        parent.append(plusbutton);
        $(".treasurecards_on_table_print").append(parent);
    }
}

$('#victory_cards').on('click', '.buyVictoryCardsandCoinCards', function () {
    console.log("kaart spelen werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'buyVictoryCard',
            positionOnBoard: $(this).data('cardNumber') //TODO dylan index van 'li' moet door gegeven worden als ik + druk, gwn achter deze positionOnBoard zetten

        }
    });

    request.done(function (data) {
        updateVictoryCardBoard();
        updateCoinsActionsBuys();
        updateDeckSize();
    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + ' ' + textStatus);
    });
});

$('#money_cards').on('click', '.buyVictoryCardsandCoinCards', function () {
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'buyTreasureCard',
            positionOnBoard: $(this).data('cardNumber') //TODO dylan index van 'li' moet door gegeven worden als ik + druk, gwn achter deze positionOnBoard zetten
        }
    });

    request.done(function (data) {
        updateTreasureCardBoard();
        updateCoinsActionsBuys();
        updateDeckSize();
    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + ' ' + textStatus);
    });


});

function playCard(array) {
    $('#hand li').on('click', function () {
        pos = $(this).index();
        console.log(array[pos]);
        if (array[pos] === "Throne Room" && trPlayed == false) {
            throne = true;
            trPlayed = true;
            alert("Throne Room played, select other card");
        }
        else if (array[pos] === "Library") {
            for (i = 0; i < 2; i++) {
                requestTopCard();
                console.log("request is al " + i + " keer gebeurd")
            }
            update();
        }

        else {
            console.log(pos + throne);
            playCardAjax(pos, throne);
        }
        console.log(pos);


    });
}

function showTopCard(card) {

}
function requestTopCard() {

    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'requestTopCard'


        }
    });
    request.done(function (data) {
        var obj = JSON.parse(data);
        console.log(obj.topCard);
        if (confirm("do you want to add " + obj.topCard + " to your hand?")) {
            moveCardToHand(pos);

        }
        else {
            moveCardToDiscard();
            requestTopCard();
        }


    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'PlayCardAjax' + textStatus);
    });


}
function moveCardToHand(position) {
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'confirmKeepCard',
            positionOfCard: position
        }
    });
    request.done(function (data) {
        updateHand();
    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'moveCardToHand' + textStatus);
    });
}
function moveCardToDiscard() {
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'denyKeepCard'


        }
    });
    request.done(function (data) {

    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'moveCardToDiscard' + textStatus);
    });
}
function playCardAjax(pos, tr) {
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'playCard',
            positionInHand: pos,
            throneRoom: tr


        }
    });
    request.done(function (data) {
        update();
        throne = false;
        trPlayed = false;

    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'PlayCardAjax' + textStatus);
        throne = false;
        trPlayed = false;
    });
}


$('#actioncards_on_table').on('click', '.buyActionCard', function () {
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'buyActionCard',
            positionOnBoard: $(this).data('cardNumber') //TODO dylan index van 'li' moet door gegeven worden als ik + druk, gwn achter deze positionOnBoard zetten
        }
    });

    request.done(function (data) {
        updateActionCardBoard();
        updateCoinsActionsBuys();
        updateDeckSize();
    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'buyActionCard' + textStatus);
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
    });

    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateActionCardBoard' + textStatus);
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
    });

    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateVictoryBoard' + textStatus);
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
        console.log(jqXHR.status + 'updateTreasureBoard' + textStatus);
    });

}

function getThiefOrSpyArray() {
    console.log("thief getter lukt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'playThiefOrSpy'
        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        console.log("thief succesfull");
        console.log(obj.top2Cards);
        console.log(obj.top2Cards[0]);
    });

    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateVictoryBoard' + textStatus);
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
        var obj = JSON.parse(data);
        updatePlayer(obj.activePlayer);
    });

    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateCurrentlyPlaying' + textStatus);
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
        console.log(jqXHR.status + 'updateCoinsActionsBuys' + textStatus);
    });

}

function updateCAB(array) {
    $('#Amount_Of_Coins').html(array[0]);
    $('#Amount_Of_Actions').html(array[1]);
    $('#Amount_Of_Buys').html(array[2]);
}

function updatePlayer(player) {
    $('#Current_Playing').html(player[0].name);
}

function checkIfMilitia() {
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'checkMilitia'
        }
    });

    request.done(function (data) {
        console.log(data);
        var obj = JSON.parse(data);
        if (obj.militiaCheck == true) {
            alert("MILITIA! Discard cards until you have 3 cards left!");
        }
    });

    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateCoinsActionsBuys' + textStatus);
    });
}
function update() {
    updateHand();
    updateActionCardBoard();
    updateVictoryCardBoard();
    updateTreasureCardBoard();
    updateCoinsActionsBuys();
    updateCurrentlyPlaying();
    updateDeckSize();
    //getThiefOrSpyArray();
    checkIfFinished();
    // playing with different origins and ranges
}

function checkIfFinished() {
    console.log("checkiffinished werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'checkIfFinished'
        }
    });

    request.done(function (data) {
        console.log(data);
        console.log(data.gameOver);
        var obj = JSON.parse(data);
        console.log("points "+obj);
        
    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateHand' + textStatus);
    });


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
        updateDeckSize();

    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateHand' + textStatus);
    });

}

function updateDeckSize() {
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {
            action: 'updateDeckSize'
        }
    });

    request.done(function (data) {
        console.log(data);
        console.log(data.deckSize);
        var obj = JSON.parse(data);
        console.log(obj.deckSize);
        $("#backcardcounter").html(obj.deckSize);
    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'updateHand' + textStatus);
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
    playCard(array);
}

$('#nextPlayerButton').on('click', function () {
    console.log("volgende speler werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {action: 'endTurn'}
    });

    request.done(function (data) {
        $("#playedcards_on_table").empty();
        updateCoinsActionsBuys();
        updateCurrentlyPlaying();
        updateHand();
        checkIfMilitia();
        checkIfFinished();
    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'nextPlayerButton' + textStatus);
    });
});

$('#playActionButton').on('click', function () {
    console.log("volgende speler werkt");
    var request = $.ajax({
        cache: false,
        url: "/BoardServlet",
        type: "GET",
        dataType: "text",
        data: {action: 'endPhase'}
    });

    request.done(function (data) {
        updateCoinsActionsBuys();

    });
    request.fail(function (jqXHR, textStatus) {
        console.log(jqXHR.status + 'PlayActionButton' + textStatus);
    });
});

function generateEnemyPlayerCard(array) {
    $("#hand").empty();
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Big%20cards/' + array[i].toLowerCase() + '.jpg';
        html += '<img alt="' + array[i].toLowerCase() + '"  title="' + array[i].toLowerCase() + '" src="' + src + '" />';
        html += '</li>';
        $("#otherPlayerCardInfo ul").append(html);
    }
    playCard();
}