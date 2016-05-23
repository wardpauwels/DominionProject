<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Layout.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Container.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/DominionNewGame.css" />
    <meta name="author" content="Groep 4"/>
    <title>Dominion</title>
</head>
<body>
<script>
    window.onload = function() {
        load();
    }
    function save(){
        var checkbox = document.getElementById('slideThree');
        localStorage.setItem('slideThree', checkbox.checked);
    }

    function load(){
        var checked = JSON.parse(localStorage.getItem('slideThree'));
        document.getElementById("slideThree").checked = checked;
    }
</script>
<div id="wrapper">
    <div id="header">
        <img src="assets/images/logo.png" title="Dominion" alt="Dominion Logo"/>
    </div>
    <div id="content">
        <div id="head">
            <div id="headername"></div>
        </div>
        <div id="top">

        </div>
        <div id="tile">
            <div id="users">
                <form action="Board.jsp" method="get" id="playerNameForm">
                    <div id="playerNamesField">
                        <input id="player1" name = "player1" type="text" placeholder="Playername" required/>
                        <input id="player2" name = "player2" type="text" placeholder="Playername" required/>
                    </div>
                    <input type="submit" id="startGame" value="Start" action="Board.jsp">
                    <input type="button" id="addPlayer" value="Add" />
                    <input type="button" class = "deletePlayer" value="Delete">
                </form>
            </div>
        </div>
        <div id="bottom">

        </div>
        <div id="foot">
            <form>

                <button id="Previous_Page" type="submit" formaction="Menu.jsp"></button>

            </form>
        </div>
    </div>
    <div id="footer">
    </div>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/script.js"></script>
</body>
</html>