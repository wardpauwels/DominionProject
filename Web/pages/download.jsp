<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../assets/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="../assets/css/screen.css" />
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
            <a href="../assets/client/Dominionwebclient.jnlp" onclick="javascript: var that=this; ga('send', 'pageview', {'page': '../assets/client/wurmclient.jnlp', 'title': 'Play'}); setTimeout(function(){location.href=that.href;},300); ga('send', 'event', 'play', 'stable'); return false;"></a></div>
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
            <h1>Download</h1>
            <p>Dominion can be played trough web browser with java or you can download it below for desktop</p>
        </div>

        <div id="body1">
            <div id="containerdownloadbuttons">
                <a href="../assets/client/Dominiondesktopclient.jnlp" download="Dominiondesktopclient.jnlp" id="downloadbutton"></a>
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