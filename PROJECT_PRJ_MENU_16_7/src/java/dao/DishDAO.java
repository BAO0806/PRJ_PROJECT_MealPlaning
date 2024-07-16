package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mylib.DButil;
import dto.Dishes;

public class DishDAO {

    // Get all dishes
    public List<Dishes> getAllDishes() {
        List<Dishes> dishes = new ArrayList<>();
        String query = "SELECT * FROM Dishes";

        try (Connection connection = DButil.makeConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Dishes dish = new Dishes();
                dish.setId(resultSet.getInt("id"));
                dish.setName(resultSet.getString("name"));
                dish.setDescription(resultSet.getString("description"));
                dish.setCalories(resultSet.getInt("calories"));
                dish.setEstimatedPrice(resultSet.getInt("estimatedPrice"));
                dish.setIngredients(resultSet.getString("ingredients"));
                dish.setMethod(resultSet.getString("method"));
                dish.setImagePath(resultSet.getString("imagePath"));

                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dishes;
    }

    // Get a dish by ID
    public Dishes getDishById(int id) {
        Dishes dish = null;
        String query = "SELECT * FROM Dishes WHERE id = ?";

        try (Connection connection = DButil.makeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dish = new Dishes();
                dish.setId(resultSet.getInt("id"));
                dish.setName(resultSet.getString("name"));
                dish.setDescription(resultSet.getString("description"));
                dish.setCalories(resultSet.getInt("calories"));
                dish.setEstimatedPrice(resultSet.getInt("estimatedPrice"));
                dish.setIngredients(resultSet.getString("ingredients"));
                dish.setMethod(resultSet.getString("method"));
                dish.setImagePath(resultSet.getString("imagePath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dish;
    }

    // Add a new dish
    public void addDish(Dishes dish) {
        String query = "INSERT INTO Dishes (name, description, calories, estimatedPrice, ingredients, method, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DButil.makeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, dish.getName());
            preparedStatement.setString(2, dish.getDescription());
            preparedStatement.setInt(3, dish.getCalories());
            preparedStatement.setInt(4, dish.getEstimatedPrice());
            preparedStatement.setString(5, dish.getIngredients());
            preparedStatement.setString(6, dish.getMethod());
            preparedStatement.setString(7, dish.getImagePath());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update an existing dish
    public void updateDish(Dishes dish) {
        String query = "UPDATE Dishes SET name = ?, description = ?, calories = ?, estimatedPrice = ?, ingredients = ?, method = ?, imagePath = ? WHERE id = ?";

        try (Connection connection = DButil.makeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, dish.getName());
            preparedStatement.setString(2, dish.getDescription());
            preparedStatement.setInt(3, dish.getCalories());
            preparedStatement.setInt(4, dish.getEstimatedPrice());
            preparedStatement.setString(5, dish.getIngredients());
            preparedStatement.setString(6, dish.getMethod());
            preparedStatement.setString(7, dish.getImagePath());
            preparedStatement.setInt(8, dish.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertDish(Dishes dish) throws Exception {
        try (Connection connection = DButil.makeConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Dishes (name, description, calories, estimatedPrice, ingredients, method, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, dish.getName());
         ps.setString(2, dish.getDescription());
            ps.setInt(3, dish.getCalories());
            ps.setInt(4, dish.getEstimatedPrice());
            ps.setString(5, dish.getIngredients());
         ps.setString(6, dish.getMethod());
            ps.setString(7, dish.getImagePath());

            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Delete a dish
    public void deleteDish(int id) {
        String query = "DELETE FROM Dishes WHERE id = ?";

        try (Connection connection = DButil.makeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
