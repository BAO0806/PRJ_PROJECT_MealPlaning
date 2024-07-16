package controllers;

import dao.MenuDAO;
import dto.Menu;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    private MenuDAO menuDAO;

    public MenuServlet() {
        this.menuDAO = new MenuDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "view":
                viewMenu(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteMenu(request, response);
                break;
            default:
                listMenus(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "add":
                addMenu(request, response);
                break;
            case "edit":
                editMenu(request, response);
                break;
            default:
                listMenus(request, response);
                break;
        }
    }

    private void listMenus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Menu> menus = menuDAO.getAllMenus();
        request.setAttribute("menus", menus);
        request.getRequestDispatcher("/view/jsp/listMenus.jsp").forward(request, response);
    }

    private void viewMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        Menu menu = menuDAO.getMenuById(menuId);
        request.setAttribute("menu", menu);
        request.getRequestDispatcher("/view/jsp/viewMenu.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/jsp/addMenu.jsp").forward(request, response);
    }

    private void addMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int weekNumber = Integer.parseInt(request.getParameter("weekNumber"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        Menu menu = new Menu(0, name, description, weekNumber, userId);
        menuDAO.addMenu(menu);
        response.sendRedirect("MenuServlet?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        Menu existingMenu = menuDAO.getMenuById(menuId);
        request.setAttribute("menu", existingMenu);
        request.getRequestDispatcher("/view/jsp/editMenu.jsp").forward(request, response);
    }

    private void editMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int weekNumber = Integer.parseInt(request.getParameter("weekNumber"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        Menu menu = new Menu(menuId, name, description, weekNumber, userId);
        menuDAO.updateMenu(menu);
        response.sendRedirect("MenuServlet?action=list");
    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        menuDAO.deleteMenu(menuId);
        response.sendRedirect("MenuServlet?action=list");
    }
}
