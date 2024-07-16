package controllers;

import dao.DishDAO;
import dto.Dishes;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DishServlet")
public class DishServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DishDAO dishDAO;

    public void init() {
        dishDAO = new DishDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "view":
                    showDishDetails(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteDish(request, response);
                    break;
                default:
                    listDishes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "add":
                    addDish(request, response);
                    break;
                case "update":
                    updateDish(request, response);
                    break;
                default:
                    listDishes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listDishes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dishes> listDishes = dishDAO.getAllDishes();
        request.setAttribute("listDishes", listDishes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listDishes.jsp");
        dispatcher.forward(request, response);
    }

    private void showDishDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Dishes dish = dishDAO.getDishById(id);
        request.setAttribute("dish", dish);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dishDetails.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Dishes existingDish = dishDAO.getDishById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editDish.jsp");
        request.setAttribute("dish", existingDish);
        dispatcher.forward(request, response);
    }

    private void addDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        int price = Integer.parseInt(request.getParameter("estimatedPrice"));
        String ingredients = request.getParameter("ingredients");
        String method = request.getParameter("method");
        String imagePath = request.getParameter("imagePath");

        Dishes newDish = new Dishes(id,name, description, calories, price, ingredients, method, imagePath);
        dishDAO.addDish(newDish);
        response.sendRedirect("DishServlet");
    }

    private void updateDish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        int price = Integer.parseInt(request.getParameter("price"));
        String ingredients = request.getParameter("ingredients");
        String method = request.getParameter("method");
        String imagePath = request.getParameter("imagePath");

        Dishes updatedDish = new Dishes(id, name, description, calories, price, ingredients, method, imagePath);
        dishDAO.updateDish(updatedDish);
        response.sendRedirect("DishServlet");
    }

    private void deleteDish(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dishDAO.deleteDish(id);
        response.sendRedirect("DishServlet");
    }
}
