
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/screen.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/home.css" />
    <meta name="author" content="Dylan Van Kerrebroeck"/>
    <title>Dominion home</title>
    <style>

    </style>
</head>
<body>
<div id="container">
    <div class="header">
        <div class="logo">
            <a href="#" class="dominion_logo"></a>
        </div>
        <div class="play-button" title="Play">
            <a href="assets/client/Dominionwebclient.jnlp" onclick="javascript: var that=this; ga('send', 'pageview', {'page': 'assets/client/Dominionwebclient.jnlp', 'title': 'Play'}); setTimeout(function(){location.href=that.href;},300); ga('send', 'event', 'play', 'stable'); return false;"></a>
        </div>
        <div class="menu_container menu_container_left">
            <div class="menu-left-menu-container">
                <ul id="menu-left-menu" class="menu">
                    <li>
                        <a href="index.jsp">Home</a>
                    </li>
                    <li>
                        <a href="pages/highscores.jsp">Highscores</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="menu_container menu_container_right">
            <div class="menu-right-menu-container">
                <ul id="menu-right-menu" class="menu">
                    <li>
                        <a href="pages/guide.jsp">Guide</a>
                    </li>
                    <li>
                        <a href="pages/download.jsp">Download</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div id="containerbodys">
        <div id="body">
            <h1>Whats Dominion</h1>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.</p>
        </div>

        <div id="body1">
            <h1>How to play Dominion</h1>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.</p>

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
