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
    <link rel="stylesheet" type="text/css" href="assets/css/Container.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/DominionSettings.css" />
    <meta name="author" content="Dylan Van Kerrebroeck"/>
    <title>Dominion</title>
</head>
<body>
<script>
    window.onload = function() {
        load();
    }
    function save(){
        var checkbox = document.getElementById('squaredThree');
        var slider = document.getElementById('slideThree');
        localStorage.setItem('squaredThree', checkbox.checked);
        localStorage.setItem('slideThree', slider.checked);
    }

    function load(){
        var checked = JSON.parse(localStorage.getItem('squaredThree'));
        var sliderChecked = JSON.parse(localStorage.getItem('slideThree'));
        document.getElementById("squaredThree").checked = checked;
        document.getElementById("slideThree").checked = sliderChecked;
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
            <form action="Settings.jsp" method="post">
                <table>
                    <tr>
                        <td>
                            <p>Fullscreen</p>
                        </td>
                        <td>
                            <div class="squaredThree">
                                <input type="checkbox" value="None" id="squaredThree" name="check" />
                                <label for="squaredThree" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p style="display:inline;">Music</p>
                        </td>
                        <td>
                            <div class="slideThree">
                                <input type="checkbox" value="None" id="slideThree" name="check" />
                                <label for="slideThree" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Save" onclick="save()">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="bottom">

        </div>
        <div id="foot">
            <form>
                <button id="Previous_Page" type="submit" formaction="Menu.jsp"></button>
            </form>
        </div>
    </div>
    <div id="footer"></div>
</div>
</body>
</html>