$(document).ready(function () {
    console.log("voor werkt");
    setBoard();
    console.log("na werkt");
    updateActionCardBoard();
    updateVictoryCardBoard();
    updateTreasureCardBoard();
    updateCoinsActionsBuys();

    

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
}
function generateActionCardsOnBoard(array) {
    for (var i = 0; i < array.length; i++) {
        var html = '<li>';
        var src = 'assets/images/Small%20Cards/' + array[i].name.toLowerCase() + '.jpg';
        html += '<p class="counteronactioncards">' + array[i].amount + '</p>';
        html += '<img alt="' + array[i].name.toLowerCase() + '"  title="' + array[i].name.toLowerCase() + '" src="' + src + '" />';
        html += '<img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyActionCard">'
        html += '</li>';
        $(".actioncards_on_table_print").append(html);
    }
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
        $("#actioncards_on_table ul li img:nth-of-type(2)").click(function () {
            console.log("kopen werkt");
            buyActionCard();})
        

    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}
function updateVictoryCardBoard(){
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
    });
        
    request.fail(function (jqXHR, textStatus) {

            alert(jqXHR.status + ' ' + textStatus);
        });

    }

function updateTreasureCardBoard(){
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
    });

    request.fail(function (jqXHR, textStatus) {

        alert(jqXHR.status + ' ' + textStatus);
    });

}

function buyActionCard(){
        console.log("kaart spelen werkt");
        var request = $.ajax({ cache: false,
            url: "/BoardServlet",
            type: "GET",
            data:{ action: 'buyActionCard',

                positionOnBoard: $(this).parent().index('li')
            }
        });
        request.done(function (data) {
            alert(success(data));
        });
        request.fail(function (jqXHR, textStatus) {
            console.log("nie gelukt");
            alert(jqXHR.status + ' ' + textStatus);
        });



}

function updateCoinsActionsBuys(){
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
        console.log(obj.CAB);
    });

    request.fail(function (jqXHR, textStatus) {

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































