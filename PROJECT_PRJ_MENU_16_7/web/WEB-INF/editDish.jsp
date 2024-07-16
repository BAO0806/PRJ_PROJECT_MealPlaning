<%@ page import="dto.Dishes" %>
<%@ page import="dao.DishDAO" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    DishDAO dishDAO = new DishDAO();
    Dish dish = dishDAO.getDishById(id);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Dish</title>
</head>
<body>
    <h2>Edit Dish</h2>
    <form action="DishServlet?action=update" method="post">
        <input type="hidden" name="id" value="<%= dish.getId() %>">
        <p>Name: <input type="text" name="name" value="<%= dish.getName() %>"></p>
        <p>Description: <input type="text" name="description" value="<%= dish.getDescription() %>"></p>
        <p>Calories: <input type="number" name="calories" value="<%= dish.getCalories() %>"></p>
        <p>Price: <input type="number" name="price" value="<%= dish.getEstimatedPrice() %>"></p>
        <p>Ingredients: <input type="text" name="ingredients" value="<%= dish.getIngredients() %>"></p>
        <p>Method: <input type="text" name="method" value="<%= dish.getMethod() %>"></p>
        <p>Image Path: <input type="text" name="imagePath" value="<%= dish.getImagePath() %>"></p>
        <p><input type="submit" value="Update Dish"></p>
    </form>
    <a href="listDishes.jsp">Back to List</a>
</body>
</html>
