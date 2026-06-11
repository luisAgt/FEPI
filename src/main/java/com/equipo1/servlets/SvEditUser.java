package com.equipo1.servlets;

import com.equipo1.logic.Controller;
import com.equipo1.entities.Users;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEditUser", urlPatterns = {"/SvEditUser"})
public class SvEditUser extends HttpServlet {

    Controller controller = new Controller();

    // doGet: Sirve para BUSCAR al usuario cuando le dan al botón azul
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idSearch = Integer.parseInt(request.getParameter("id_user_search"));
            
            // Usamos el método que ya tenías en tu Controller para buscar
            Users user = controller.findUser(idSearch);
            
            // Se lo mandamos de regreso a la vista JSP
            request.setAttribute("userToEdit", user);
            request.getRequestDispatcher("EditUser.jsp").forward(request, response);
            
        } catch (Exception e) {
            response.sendRedirect("EditUser.jsp?status=notfound");
        }
    }

    // doPost: Sirve para GUARDAR LOS CAMBIOS cuando le dan al botón amarillo
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            int idUser = Integer.parseInt(request.getParameter("id_user"));
            String fullname = request.getParameter("fullname");
            Date birthdate = Date.valueOf(request.getParameter("birthdate"));
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            
            // Llamamos al nuevo método de edición en el Controller
            controller.editUser(idUser, fullname, birthdate, email, username, password, role);
            
            response.sendRedirect("Admin.jsp?status=updated");
            
        } catch (Exception e) {
            System.err.println("Error al editar: " + e.getMessage());
            response.sendRedirect("EditUser.jsp?status=error");
        }
    }
}