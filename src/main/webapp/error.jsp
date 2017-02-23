<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="error" type="java.lang.String"--%>
<html>
<head>
    <title>Внезапно, ошибка</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading bg-warning">
            <h3>Извините, ошибки случаются
                <small>вот и сейчас</small>
            </h3>
        </div>
        <div class="panel-body">
            Если вас интерсуют подробности, то у нас
            <c:if test="${error != null}">
                <c:out value="${error}"/>.
            </c:if>
            <c:if test="${error == null}">
                случилась какая-то неведомая ошибка.
            </c:if>
        </div>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>
