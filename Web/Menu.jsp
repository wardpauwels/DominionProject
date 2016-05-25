<%--
  Created by IntelliJ IDEA.
  User: dylan
  Date: 12/05/2016
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Layout.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/Dominionmenu.css" />
    <meta name="author" content="Dylan Van Kerrebroeck"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dominion</title>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <img src="assets/images/logo.png" title="Dominion" alt="Dominion Logo"/>
    </div>
    <div id="content">
        <ul>
            <li>
                <form>
                    <button id="New_Game" type="submit" formaction="New_Game.jsp"></button>
                </form>
            </li>
            <li>
                <button id="Exit" type="submit" onClick="window.close();"></button>
            </li>
        </ul>

    </div>
</div>
</body>
</html>
