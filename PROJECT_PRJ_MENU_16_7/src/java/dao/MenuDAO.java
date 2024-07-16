package dao;

import dto.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.DButil;

public class MenuDAO {
    private Connection connection;

    public MenuDAO() {
        try {
            connection = DButil.makeConnection(); // Establish database connection
        } catch (Exception ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to add a new menu to the database
    public void addMenu(Menu menu) {
        try {
            String query = "INSERT INTO Menu (name, description, weekNumber, userId) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, menu.getName());
            ps.setString(2, menu.getDescription());
            ps.setInt(3, menu.getWeekNumber());
            ps.setInt(4, menu.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing menu in the database
    public void updateMenu(Menu menu) {
        try {
            String query = "UPDATE Menu SET name=?, description=?, weekNumber=?, userId=? WHERE menuId=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, menu.getName());
            ps.setString(2, menu.getDescription());
            ps.setInt(3, menu.getWeekNumber());
            ps.setInt(4, menu.getUserId());
            ps.setInt(5, menu.getMenuId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a menu from the database by menuId
    public void deleteMenu(int menuId) {
        try {
            String query = "DELETE FROM Menu WHERE menuId=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, menuId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all menus from the database
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        try {
            String query = "SELECT * FROM Menu";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int menuId = rs.getInt("menuId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int weekNumber = rs.getInt("weekNumber");
                int userId = rs.getInt("userId");

                Menu menu = new Menu(menuId, name, description, weekNumber, userId);
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
    public List<Menu> getMenusByName(String name) {
    List<Menu> menus = new ArrayList<>();
    try {
        String query = "SELECT * FROM Menu WHERE name LIKE ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, "%" + name + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int menuId = rs.getInt("menuId");
            String menuName = rs.getString("name");
            String description = rs.getString("description");
            int weekNumber = rs.getInt("weekNumber");
            int userId = rs.getInt("userId");

            Menu menu = new Menu(menuId, menuName, description, weekNumber, userId);
            menus.add(menu);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return menus;
}


    // Method to retrieve a menu by menuId from the database
    public Menu getMenuById(int menuId) {
        Menu menu = null;
        try {
            String query = "SELECT * FROM Menu WHERE menuId=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                int weekNumber = rs.getInt("weekNumber");
                int userId = rs.getInt("userId");

                menu = new Menu(menuId, name, description, weekNumber, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    // Method to retrieve all menus for a specific user from the database
    public List<Menu> getMenusByUserId(int userId) {
        List<Menu> menus = new ArrayList<>();
        try {
            String query = "SELECT * FROM Menu WHERE userId=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int menuId = rs.getInt("menuId");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int weekNumber = rs.getInt("weekNumber");

                Menu menu = new Menu(menuId, name, description, weekNumber, userId);
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    // Close connection method
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
