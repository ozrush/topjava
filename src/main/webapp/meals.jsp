<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table cellspacing="2" border="1" cellpadding="5" width="600">
    <tr>
        <td>Дата/время</td>
        <td>Описание</td>
        <td>Калории</td>
    </tr>
    <%--@elvariable id="mealsTo" type="java.util.List"--%>
    <c:forEach items="${mealsTo}" var="mealTo">
        <tr style="color: ${mealTo.excess ? 'red' : 'green' }">
            <fmt:parseDate value="${mealTo.dateTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"/>
            <fmt:formatDate value="${parsedDate}" type="date" pattern="dd-MM-yyyy HH:mm" var="dateTime"/>
            <td>${dateTime}</td>
            <td><c:out value="${mealTo.description}"/></td>
            <td><c:out value="${mealTo.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>