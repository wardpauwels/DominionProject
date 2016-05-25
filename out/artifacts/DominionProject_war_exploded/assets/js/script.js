$(document).ready(function () {//var messages = ['adventurer', 'bureaucrat', 'cellar', 'chancellor', 'chapel', 'councilroom', 'feast', 'festival', 'gardens', 'laboratory', 'library', 'market', 'militia', 'mine', 'moat', 'moneylender', 'remodel', 'smithy', 'spy', 'thief', 'throneroom', 'village', 'witch', 'woodcutter', 'workshop'];
    console.log("Loaded!");
    var ammountOfPlayers = 2;
    disableCopyPaste();
    
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
        CardNames = JSON.parse(data.CardNames);
        console.log(CardNames);
    });
    request.fail(function (jqXHR, textStatus) {
        console.log("nie gelukt");
        console.log(jqXHR.status + ' ' + textStatus);
    });
});

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