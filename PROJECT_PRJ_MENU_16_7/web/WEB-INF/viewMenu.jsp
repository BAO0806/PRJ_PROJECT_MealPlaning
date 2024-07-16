<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Menu</title>
</head>
<body>
    <h1>Menu Details</h1>
    <c:if test="${not empty menu}">
        <p><strong>Name:</strong> ${menu.name}</p>
        <p><strong>Description:</strong> ${menu.description}</p>
        <p><strong>Week Number:</strong> ${menu.weekNumber}</p>
        <p><strong>User ID:</strong> ${menu.userId}</p>
    </c:if>
    <a href="menu">Back to Menu List</a>
</body>
</html>
