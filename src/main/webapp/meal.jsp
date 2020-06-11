<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<form method="POST" action='meals' name="frmAddMeal">
    <input type="hidden" name="mealId" value="${meal.id}">
    <p>
        <label>
            Описание : <input type="text" name="mealDescription" value="${meal.description}"/>
        </label>
    </p>
    <p>
        <label>
            Калории : <input type="text" name="mealCalories" value="${meal.calories}"/>
        </label>
    </p>
    <p>
        <label>
            <fmt:parseDate value="${meal.dateTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"/>
            <fmt:formatDate value="${parsedDate}" type="date" pattern="dd-MM-yyyy HH:mm" var="dateTime"/>
            Дата/Время : <input type="text" name="mealDateTime" value="${dateTime}"/>
        </label>
    </p>
    <input type="submit" value="Сохранить"/>
</form>
</body>
</html>