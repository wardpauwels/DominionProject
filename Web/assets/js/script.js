$(document).ready(function () {
    var messages = ['adventurer', 'bureaucrat', 'cellar', 'chancellor', 'chapel', 'councilroom', 'feast', 'festival', 'gardens', 'laboratory', 'library', 'market', 'militia', 'mine', 'moat', 'moneylender', 'remodel', 'smithy', 'Spy', 'thief', 'throneroom', 'village', 'witch', 'woodcutter', 'workshop'];
    console.log("Loaded!");
    var messageArray = getMessage(messages);
    var actioncardOnTableName;
    messageArray.forEach(function (item) {
    });
    showCards(messageArray);
    disableCopyPaste();

    $("#baraja-el li").click(function(){
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
});


function showCards(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li> <p class="counteronactioncards">0</p>';
        var src = 'assets/images/Small%20Cards/' + array[i] + '.jpg';
        html += '<img alt="' + array[i] + '"  title="' + array[i] + '" src="' + src + '" />';
        html += '</li>';
        $(".actioncards_on_table_print").append(html);
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

function moveCardToBoard() {
    //$(this).appendTo('#playedcards_on_table ul');
}

//var allCardsInHand = document.getElementById("baraja-el").children;
//var zindexArray = [];
//for (var i = 0; allCardsInHand.length > i; i++) {
//    zindexArray[i] = allCardsInHand.length - i + 1000 - 1;
//    console.log(allCardsInHand[i]);
//    allCardsInHand[i].addEventListener("wheel", function (e) {
//        this.id=zindexArray[i.toString()];
//        if (e.wheelDelta < 0) {
//            console.log(this);
//            this.style.zIndex = zindexArray[i];
//            console.log("down");
//        } else {
//            this.style.zIndex = 1000000000;
//            console.log("up");
//        }
//    })
//}


document.getElementById('baraja-el').addEventListener("wheel", function (e) {
    if (e.wheelDelta < 0) {
        this.style.zIndex = 0;
        console.log("down");
    } else {
        this.style.zIndex = 1000000000;
        console.log("up");
    }
});
