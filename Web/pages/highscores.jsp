<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../assets/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="../assets/css/screen.css" />
    <link rel="stylesheet" type="text/css" href="../assets/css/highscores.css" />
    <meta name="author" content="Dylan Van Kerrebroeck"/>
    <title>Dominion highscores</title>
    <style>

    </style>
</head>
<body>
<div id="container">
    <div class="header">
        <div class="logo">
            <a href="../index.jsp" class="dominion_logo"></a>
        </div>
        <div class="play-button" title="Play">
            <a href="../assets/client/Dominionwebclient.jnlp" onclick="javascript: var that=this; ga('send', 'pageview', {'page': 'assets/client/Dominionclient.jnlp', 'title': 'Play'}); setTimeout(function(){location.href=that.href;},300); ga('send', 'event', 'play', 'stable'); return false;"></a></div>
        <div class="menu_container menu_container_left">
            <div class="menu-left-menu-container">
                <ul id="menu-left-menu" class="menu">
                    <li>
                        <a href="../index.jsp">Home</a>
                    </li>
                    <li>
                        <a href="highscores.jsp">Highscores</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="menu_container menu_container_right">
            <div class="menu-right-menu-container">
                <ul id="menu-right-menu" class="menu">
                    <li>
                        <a href="guide.jsp">Guide</a>
                    </li>
                    <li>
                        <a href="download.jsp">Download</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div id="containerbodys">
        <div id="body">
            <h1>Highscores</h1>
            <p>Below you can find all highscores of players who have ever played Dominion</p>
        </div>
        <div id="body1">
            <table style="width:72%; margin-left:auto; margin-right:auto;">
                <tr style="font-size:1.5rem; vertical-align: top;" id="border">
                    <th style="width:12%">Rank</th>
                    <th style="width:25%">Name</th>
                    <th style="width:35%">Points</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>January</td>
                    <td>$100</td>
                </tr>
                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>
                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>

                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>
                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>
                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>
                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>
                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>
                <tr>
                    <td>February</td>
                    <td>February</td>
                    <td>$50</td>
                </tr>
            </table>
        </div>
        <div id="body2">
        </div>
        <div id="body3">
        </div>
    </div>
</div>




</body>
</html>