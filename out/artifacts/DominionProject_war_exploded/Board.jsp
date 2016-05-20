<%--
  Created by IntelliJ IDEA.
  User: dylan
  Date: 12/05/2016
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
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
    <div id="header">
        <div id="two_players">
            <p id="player_one_name" class="player_one_name"></p>
            <p id="player_two_name" class="player_two_name"></p>
        </div>
        <!--<div id="three_players">
             <div class="player_one_name">
                 <p>Dylan</p>
             </div>

             <div class="player_two_name">
                 <p>Ward</p>
                 </div>
             <div class="player_third_name">
                 <p>Robert</p>
             </div>

         </div>-->

        <!--<div id="four_players">
            <div class="player_one_name">
                <p>Dylan</p>
            </div>
            <div class="player_two_name">
                <p>Ward</p>
            </div>
            <div class="player_third_name">
                <p>Robert</p>
            </div>
            <div class="player_fourth_name">
                <p>Jens</p>
            </div>
        </div>-->
    </div>

    <div id="content">
        <div id="actioncards_on_table">
            <ul class="actioncards_on_table_print">
            </ul>
        </div>
        <div id="cards_on_board">
            <div id="victory_cards">
                <ul>
                    <li>
                        <p class="counteronsmallcards">0</p>
                        <img src="assets/images/Small%20Cards/province.jpg" title="Province + 6 points" alt="Province"/>
                        <img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">
                    </li>
                    <li>
                        <p class="counteronsmallcards">0</p>
                        <img src="assets/images/Small%20Cards/duchy.jpg" title="Duchy + 3 points" alt="Duchy"/>
                        <img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">
                    </li>
                    <li>
                        <p class="counteronsmallcards">0</p>
                        <img src="assets/images/Small%20Cards/estate.jpg" title="Estate + 1 point" alt="Estate"/>
                        <img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">
                    </li>
                    <li>
                        <p class="counteronsmallcards">0</p>
                        <img src="assets/images/Small%20Cards/curse.jpg" title="Curse - 1 point" alt="Curse"/>
                        <img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">
                    </li>
                </ul>
            </div>
            <div id="money_cards">
                <ul>
                    <li>
                        <p class="counteronsmallcards">0</p>
                        <img src="assets/images/Small%20Cards/gold.jpg" alt="Gold" title="Gold + 3 coins"/>
                        <img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">
                    </li>
                    <li>
                        <p class="counteronsmallcards">0</p>
                        <img src="assets/images/Small%20Cards/silver.jpg" alt="Silver" title="Silver + 2 coins"/>
                        <img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">
                    </li>
                    <li>
                        <p class="counteronsmallcards">0</p>
                        <img src="assets/images/Small%20Cards/copper.jpg" alt="Copper" title="Copper + 1 coin"/>
                        <img alt="buyactioncard" title="buyactioncard" src="assets/images/buybutton.png" class="buyVictoryCardsandCoinCards">
                    </li>
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
                    <img src="assets/images/Big%20cards/bureaucrat.jpg" alt="" title=""/>
                </div>
                <div id="toplaycards_on_discard_pile">
                    <p id="backcardcounter">0</p>
                    <img src="assets/images/Big%20cards/back.jpg" alt="" title=""/>
                </div>

            </div>
            <div id="hand_on_table">
                <div class="baraja-demo">
                    <ul id="baraja-el" class="baraja-container">
                        <!--<li><img src="assets/images/Big%20cards/adventurer.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/bureaucrat.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/gold.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/silver.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>
                        <li><img src="assets/images/Big%20cards/copper.jpg"/> </li>-->
                    </ul>
                </div>
            </div>
            <div id="trash_pile_on_table">
                <!--<img src="assets/images/exit_button.jpg" title="exit the game" alt="exit"/>-->
                <img src="assets/images/trash_pile_filled.png" title="Trash Pile" alt="Trash Pile"/>

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
            <div id="center_side_footer">
                <button id="opencards">Open hand</button>
                <button id="closecards">Close hand</button>
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
<!--<script src="assets/js/scriptBoard.js"></script>-->
<script src="assets/css/Baraja/js/modernizr.custom.79639.js"></script>
<script src="assets/css/Baraja/js/jquery.baraja.js"></script>
</body>
</html>