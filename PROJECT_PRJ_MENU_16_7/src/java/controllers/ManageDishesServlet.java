package controllers;

import dao.DishDAO;
import dto.Dishes;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageDishesServlet", urlPatterns = {"/ManageDishesServlet"})
public class ManageDishesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DishDAO dishDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        dishDAO = new DishDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "list":
                    listDishes(request, response);
                    break;
                case "create":
                    showCreateForm(request, response);
                    break;
                case "insert":
                    insertDish(request, response);
                    break;
                case "delete":
                    deleteDish(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateDish(request, response);
                    break;
                default:
                    listDishes(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listDishes(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Dishes> dishesList = dishDAO.getAllDishes();
        request.setAttribute("dishesList", dishesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/jsp/listDishes.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/jsp/addDish.jsp");
        dispatcher.forward(request, response);
    }

    private void insertDish(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        int price = Integer.parseInt(request.getParameter("price"));
        String ingredients = request.getParameter("ingredients");
        String method = request.getParameter("method");
        String imagePath = request.getParameter("imagePath");
        Dishes newDish = new Dishes(id,name,description, calories, price, ingredients, method, imagePath);
        try {
            dishDAO.insertDish(newDish);
        } catch (Exception ex) {
            Logger.getLogger(ManageDishesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("ManageDishesServlet?action=list");
    }

    private void deleteDish(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dishDAO.deleteDish(id);
        response.sendRedirect("ManageDishesServlet?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Dishes existingDish = dishDAO.getDishById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/jsp/editDish.jsp");
        request.setAttribute("dish", existingDish);
        dispatcher.forward(request, response);
    }

    private void updateDish(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        int price = Integer.parseInt(request.getParameter("price"));
        String ingredients = request.getParameter("ingredients");
        String method = request.getParameter("method");
        String imagePath = request.getParameter("imagePath");
        Dishes updatedDish = new Dishes(id, name, description,calories, price, ingredients, method, imagePath);
        dishDAO.updateDish(updatedDish);
        response.sendRedirect("ManageDishesServlet?action=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ManageDishesServlet";
    }
}
