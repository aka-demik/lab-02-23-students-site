<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="list" type="java.util.Collection<com.aka.services.SuperUserServiceImpl.Lecture>"--%>
<html>
<head>
    <title>Список лекций</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">Список лекций</div>
        <div class="panel-body">
            <table class="table table-condensed table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Время</th>
                    <th>Лекция</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td><c:out value="${item.schedule.id}"/></td>
                        <td><c:out value="${item.schedule.scheduledAt}"/></td>
                        <td><c:out value="${item.reason.name}"/></td>
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/lectures" method="post"
                                  class="form-inline">
                                <a class="btn btn-warning"
                                   href="${pageContext.servletContext.contextPath}/lectures/edit?id=${item.schedule.id}"
                                   role="button">Правка</a>
                                <input type="hidden" name="id" value="${item.schedule.id}">
                                <input type="hidden" name="type" value="delete">
                                <button type="submit" class="btn btn-danger">Удалить</button>
                            </form>
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
