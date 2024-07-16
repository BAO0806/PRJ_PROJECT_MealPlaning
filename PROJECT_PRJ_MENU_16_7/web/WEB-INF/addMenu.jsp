<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Menu</title>
</head>
<body>
    <h1>Add Menu</h1>
    <form action="MenuServlet" method="post">
        <input type="hidden" name="action" value="add" />
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required /><br>
        <label for="description">Description:</label>
        <input type="text" name="description" id="description" required /><br>
        <label for="weekNumber">Week Number:</label>
        <input type="number" name="weekNumber" id="weekNumber" required /><br>
        <label for="userId">User ID:</label>
        <input type="number" name="userId" id="userId" required /><br>
        <input type="submit" value="Add Menu" />
    </form>
    <a href="MenuServlet?action=list">Back to List</a>
</body>
</html>
