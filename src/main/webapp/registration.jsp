<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>

<h1>Регистрация</h1>

<form action="/students/registration/" method="post">
    <label for="login">Login:</label>
    <input type="text" name="login" id="login" value="" placeholder="Input"><br>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="" placeholder="Input">

    <input type="submit" value="Submit">
</form>


</body>
</html>
