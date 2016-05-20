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

$(document).ready(function () {
    var messages = ['adventurer', 'bureaucrat', 'cellar', 'chancellor', 'chapel', 'councilroom', 'feast', 'festival', 'gardens', 'laboratory', 'library', 'market', 'militia', 'mine', 'moat', 'moneylender', 'remodel', 'smithy', 'Spy', 'thief', 'throneroom', 'village', 'witch', 'woodcutter', 'workshop'];
    console.log("Loaded!");
    var messageArray = getMessage(messages);
    var actioncardOnTableName;
    var ammountOfPlayers = 2;
    messageArray.forEach(function (item) {
    });
    showCards(messageArray);
    disableCopyPaste();

    $("#baraja-el li").click(function(e){
        e.preventDefault();
        $(this).appendTo('#playedcards_on_table ul');
    });

    $("#actioncards_on_table ul li img").click(function () {
        $actioncardOnTableName = $(this).attr("title");
        var src = "<img src='assets/images/Big%20cards/" + $actioncardOnTableName + ".jpg' title = '" + $actioncardOnTableName + "' alt = '" + $actioncardOnTableName + "'/><br>";

        $('#bigCard').html(src).css('visibility', 'visible');
    });
    $('#bigCard').click(function () {
        $('#bigCard').css('visibility', 'hidden');
    });
    $("#addPlayer").click(function(){
        if(ammountOfPlayers < 4) {
            ammountOfPlayers++;
            var playerId = "player" + ammountOfPlayers;
            $('#playerNamesField').append('<input id="' + playerId + '" name = "' + playerId + '" type="text" placeholder="Playername" required/>');
        }
    });
    $('.deletePlayer').click(function(){
        if(ammountOfPlayers > 2) {
            $('#playerNamesField input').last().remove();
            ammountOfPlayers--;
        }
    });

});
$('#baraja-el li').click(function(){
    console.log("kaart spelen werkt");
    var request = $.ajax({ cache: false,
        url: "/BoardServlet",
        type: "GET",
        data:{ action: 'playCard',
            positionInHand: $('#baraja-el li').index(this)


        }
        ,
        success: function (data) {
            console.log(data);
            alert("SUCCES: " + data.status);
        },
        error: function (data) {
            console.log(data);
            alert("ERROR: " + data.status);
        }
    });

});
$('#startGame').click(function(){
    console.log("init werkt");
    var request = $.ajax({ cache: false,
        url: "/BoardServlet",
        dataType: "text",
        method: "GET",
        data:{ action: 'init',
            name1: $('#player1').val(),
            name2: $('#player2').val(),
            name3: $('#player3').val(),
            name4: $('#player4').val()
        }
    });
    request.done(function (data) {
        cardNames = JSON.parse(data.cards);
        alert(cardNames);
    });
    request.fail(function (jqXHR, textStatus) {
        console.log("nie gelukt");
        alert(jqXHR.status + ' ' + textStatus);
    });
});


function showCards(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li><p class="counteronactioncards">0</p>';
        var src = 'assets/images/Small%20Cards/' + array[i] + '.jpg';
        html += '<img alt="' + array[i] + '"  title="' + array[i] + '" src="' + src + '" />';
        html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">'
        html += '</li>';
        $(".actioncards_on_table_print").append(html);
    }
}





function importPlayerCards(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Big%20cards/' + array[i] + '.jpg';
        html += '<img alt="' + array[i] + '"  title="' + array[i] + '" src="' + src + '" />';
        html += '</li>';
        $("#baraja-el").append(html);
    }
}






function getMessage(messages) {
    var allCards = [];
    for (var i = 0; allCards.length < 10; i++) {
        var aCard = messages[Math.floor(Math.random() * messages.length)];
        if (checkRedundant(allCards, aCard)) {
            allCards.push(aCard);
        }

    }
    return allCards;
}

function checkRedundant(array, string) {
    for (var i = 0; array.length > i; i++) {
        if (string == array[i]) {
            return false
        }
    }
    return true;
}

function disableCopyPaste(){
    $('body').bind('copy paste',function(e) {
        e.preventDefault(); return false;
    });
}


document.getElementById('baraja-el').addEventListener("wheel", function (e) {
    if (e.wheelDelta < 0) {
        this.style.zIndex = 0;
        console.log("down");
    } else {
        this.style.zIndex = 1000000000;
        console.log("up");
    }
});