<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<h1>Вход</h1>
<div>

    <form action="/students/" method="post">
        <label for="login">Login:</label>
        <input type="text" name="login" id="login" value="" placeholder="Input"><br>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" value="" placeholder="Input">

        <input type="submit" value="Submit">
    </form>
</div>
<a href="/students/registration/">Registration</a>
</body>
</html>
