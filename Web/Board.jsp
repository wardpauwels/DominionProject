<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Layout.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Baraja/baraja.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Baraja/custom.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Baraja/demo.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/DominionBoard.css" />
    <meta name="author" content="Groep 4"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dominion</title>
</head>
<body>

<div id="wrapper">
    <div id="bigCard"></div>
    <div id="otherPlayerCardInfo">
        <ul>
            <h1>Trash one of the cards below</h1>
        </ul>
    </div>
    <div id="header">
        <div id="two_players">
            <p id="player_one_name" class="player_one_name"></p>
            <p id="player_two_name" class="player_two_name"></p>
        </div>
    </div>

    <div id="content">
        <div id="actioncards_on_table">
            <ul class="actioncards_on_table_print">
            </ul>
        </div>
        <div id="cards_on_board">
            <div id="victory_cards">
                <ul class="victorycards_on_table_print">
                </ul>
            </div>
            <div id="money_cards">
                <ul class="treasurecards_on_table_print">
                </ul>
            </div>
        </div>
        <div id="playedcards_on_table">
            <ul>

            </ul>
        </div>

        <div id="handcards_on_table">
            <div id="deck_on_table">
                </div>
            <div id="discard_pile_on_table">
                <div id="playedcards_on_discard_pile">
                    <img src="assets/images/Big%20cards/back.jpg" alt="Back" title="Back"/>
                </div>
                <div id="toplaycards_on_discard_pile">
                    <p id="backcardcounter">0</p>
                    <img src="assets/images/Big%20cards/back.jpg" alt="back" title="back"/>
                </div>

            </div>
            <div id="hand_on_table">
                    <ul id="hand">
                    </ul>
            </div>
            <div id="trash_pile_on_table">
                <img src="assets/images/trash_pile_filled.png" title="Trash Pile" alt="Trash Pile"/>
            </div>
            <div id="playButtons">
                <button id="nextPlayerButton"></button>
                <br>
                <button id="playVictoryButton" disabled></button>
                <br>
                <button id="playActionButton"></button>
            </div>
        </div>
    </div>
    <div id="footer">
        <div id="footer_container">
            <div id="left_side_footer">
                <div class="Actions">
                    <p>Actions</p>
                    <p id="Amount_Of_Actions" style="padding:2px 14px 2px 14px">0</p>
                </div>
                <div class="Buys">
                    <p>Buys</p>
                    <p id="Amount_Of_Buys" style="padding:2px 14px 2px 14px">0</p>
                </div>
                <div class="Coins">
                    <p>Coins</p>
                    <p id="Amount_Of_Coins" style="padding:2px 14px 2px 14px">0</p>
                </div>
            </div>
            <div id="right_side_footer">
                <p>Currently playing: </p>
                <p id="Current_Playing">Player Dominion</p>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/script.js"></script>
<script src="assets/js/scriptBoardFile.js"></script>

</body>
</html>