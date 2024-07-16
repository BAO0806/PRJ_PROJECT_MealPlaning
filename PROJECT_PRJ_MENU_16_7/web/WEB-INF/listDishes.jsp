<%@ page import="java.util.List" %>
<%@ page import="dto.Dishes" %>
<%@ page import="dao.DishDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Dishes</title>
</head>
<body>
    <h2>List of Dishes</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Calories</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <%
            DishDAO dishDAO = new DishDAO();
            List<Dish> dishes = dishDAO.getAllDishes();
            for (Dish dish : dishes) {
        %>
        <tr>
            <td><%= dish.getName() %></td>
            <td><%= dish.getCalories() %></td>
            <td><%= dish.getEstimatedPrice() %></td>
            <td>
                <a href="DishServlet?action=view&id=<%= dish.getId() %>">View</a>
                <a href="DishServlet?action=edit&id=<%= dish.getId() %>">Edit</a>
                <a href="DishServlet?action=delete&id=<%= dish.getId() %>">Delete</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="addDish.jsp">Add New Dish</a>
</body>
</html>
