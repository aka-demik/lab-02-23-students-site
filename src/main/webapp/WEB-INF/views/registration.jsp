<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация - список студентов</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3>Регистрация
                <small>список студентов</small>
            </h3>
        </div>
        <div class="panel-body">
            <form action="${pageContext.servletContext.contextPath}/registration" method="post">
                <div class="form-group">
                    <label for="login">Имя пользователя</label>
                    <input type="text" class="form-control" id="login" name="login" placeholder="Имя пользователя">
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Пароль">
                </div>
                <div class="pull-right">
                    <a href="${pageContext.servletContext.contextPath}/">Вход</a>
                </div>
                <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
            </form>
        </div>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>
