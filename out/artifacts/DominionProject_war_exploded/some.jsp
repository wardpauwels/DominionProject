<!DOCTYPE html>
<html lang="en">
<head>
    <title>SO question 4112686</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            $.get("someservlet", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
            });
        });
    </script>
</head>
<body>
<form>
    <input type="text" name="naam" id="naam"/>
    <input type="submit" name="somebutton" id="somebutton" value="Press here" />
</form>
<div id="somediv"></div>
</body>
</html>