<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Menu</title>
</head>
<body>
    <h1>Edit Menu</h1>
    <form action="MenuServlet" method="post">
        <input type="hidden" name="action" value="edit" />
        <input type="hidden" name="menuId" value="${menu.menuId}" />
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" value="${menu.name}" required /><br>
        <label for="description">Description:</label>
        <input type="text" name="description" id="description" value="${menu.description}" required /><br>
        <label for="weekNumber">Week Number:</label>
        <input type="number" name="weekNumber" id="weekNumber" value="${menu.weekNumber}" required /><br>
        <label for="userId">User ID:</label>
        <input type="number" name="userId" id="userId" value="${menu.userId}" required /><br>
        <input type="submit" value="Update Menu" />
    </form>
    <a href="MenuServlet?action=list">Back to List</a>
</body>
</html>
