<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="lecture" type="com.aka.services.SuperUserServiceImpl.Lecture"--%>
<%--@elvariable id="lectures" type="java.util.Collection<com.aka.models.CallReason>"--%>
<html>
<head>
    <title>Редактирование лекции</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">Редактирование лекции</div>
        <div class="panel-body">
            <form action="${pageContext.servletContext.contextPath}/lectures/edit" method="post">
                <div class="form-group">
                    <label>
                        Назначенное время
                        <input type="datetime-local" class="form-control" name="time"
                               value="${lecture.schedule.scheduledAt}">
                    </label>
                </div>
                <div class="form-group">
                    <label for="lecture">Лекция</label>
                    <select id="lecture" name="lecture" class="form-control">
                        <c:forEach items="${lectures}" var="item">
                            <c:choose>
                                <c:when test="${lecture.reason.id == item.id}">
                                    <option selected value="${item.id}">${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.id}">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
                <a class="btn btn-default"
                   href="${pageContext.servletContext.contextPath}/lectures"
                   role="button">Назад</a>
            </form>
        </div>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>
