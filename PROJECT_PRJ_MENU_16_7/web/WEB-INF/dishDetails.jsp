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
    <title>Dish Details</title>
</head>
<body>
    <h2>Dish Details</h2>
    <p><strong>Name:</strong> <%= dish.getName() %></p>
    <p><strong>Description:</strong> <%= dish.getDescription() %></p>
    <p><strong>Calories:</strong> <%= dish.getCalories() %></p>
    <p><strong>Price:</strong> <%= dish.getEstimatedPrice() %></p>
    <p><strong>Ingredients:</strong> <%= dish.getIngredients() %></p>
    <p><strong>Method:</strong> <%= dish.getMethod() %></p>
    <p><strong>Image:</strong> <img src="<%= dish.getImagePath() %>" alt="Dish Image"></p>
    <a href="listDishes.jsp">Back to List</a>
</body>
</html>
