<%-- 
    Document   : addDish
    Created on : 16-07-2024, 03:12:00
    Author     : phamg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Dish</title>
</head>
<body>
    <h2>Add New Dish</h2>
    <form action="DishServlet?action=add" method="post">
        <p>Name: <input type="text" name="name"></p>
        <p>Description: <input type="text" name="description"></p>
        <p>Calories: <input type="number" name="calories"></p>
        <p>Price: <input type="number" name="price"></p>
        <p>Ingredients: <input type="text" name="ingredients"></p>
        <p>Method: <input type="text" name="method"></p>
        <p>Image Path: <input type="text" name="imagePath"></p>
        <p><input type="submit" value="Add Dish"></p>
    </form>
    <a href="listDishes.jsp">Back to List</a>
</body>
</html>

