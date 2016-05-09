<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../assets/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="../assets/css/screen.css" />
    <link rel="stylesheet" type="text/css" href="../assets/css/guide.css" />
    <meta name="author" content="Dylan Van Kerrebroeck"/>
    <title>Dominion</title>
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
            <a href="../assets/client/Dominionwebclient.jnlp" onclick="javascript: var that=this; ga('send', 'pageview', {'page': '/client/wurmclient.jnlp', 'title': 'Play'}); setTimeout(function(){location.href=that.href;},300); ga('send', 'event', 'play', 'stable'); return false;"></a></div>
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
            <h1>How to play Dominion</h1>
            <p>
                You are a monarch, like your parents before you, a ruler of a small pleasant kingdom of
                rivers and evergreens. Unlike your parents, however, you have hopes and dreams! You
                want a bigger and more pleasant kingdom, with more rivers and a wider variety of
                trees. You want a Dominion! In all directions lie fiefs, freeholds, and feodums. All are
                small bits of land, controlled by petty lords and verging on anarchy. You will bring
                civilization to these people, uniting them under your banner.
            </p>

        </div>
        <div id="body1" class="rules">

            <p>
                But wait! It must be something in the air; several other monarchs have had the exact
                same idea. You must race to get as much of the unclaimed land as possible, fending
                them off along the way. To do this you will hire minions, construct buildings, spruce
                up your castle, and fill your treasury. Your parents wouldn't be proud, but your
                grandparents, on your mother’s side, would be delighted.
            </p>
            <div id="containerdownloadbuttons">
                <a href="../assets/pdfs/Dominion_gamerules.pdf" download="Dominion_gamerules.pdf" id="downloadbuttonpdf"></a>
            </div>
        </div>
        <div id="body2">
        </div>
        <div id="body3">
        </div>
    </div>
    <div id="footer"></div>
</div>




</body>
</html>