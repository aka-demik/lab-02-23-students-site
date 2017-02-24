<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="list" type="java.util.Collection<models.pojo.User>"--%>
<html>
<head>
    <title>Список студентов</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">Список студентов</div>
        <div class="panel-body">
            <table class="table table-condensed table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Битрикс</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>email</th>
                    <th>Телефон</th>
                    <th>Дата рождения</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td><c:out value="${item.id}"/></td>
                        <td><c:out value="${item.bitrixId}"/></td>
                        <td><c:out value="${item.lastName}"/></td>
                        <td><c:out value="${item.firstName}"/></td>
                        <td><c:out value="${item.middleName}"/></td>
                        <td><c:out value="${item.email}"/></td>
                        <td><c:out value="${item.phone}"/></td>
                        <td><c:out value="${item.birthDate}"/></td>
                        <td>
                            <a class="btn btn-warning" href="${pageContext.servletContext.contextPath}/edit?id=${item.id}" role="button">Правка</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>
