package com.equipo1.servlets;

import com.equipo1.logic.Controller;
import java.io.IOException;
import java.sql.Date; // Importante para la fecha
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvRegisterUser", urlPatterns = {"/SvRegisterUser"})
public class SvRegisterUserAdmin extends HttpServlet {

    Controller controller = new Controller();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            // Extraer los 7 datos del formulario
            int idUser = Integer.parseInt(request.getParameter("id_user"));
            String fullname = request.getParameter("fullname");
            String birthdateStr = request.getParameter("birthdate");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            
            // Convertir el texto de la fecha (YYYY-MM-DD) a java.sql.Date
            Date birthdate = Date.valueOf(birthdateStr);
            
            // Llamar al Controller
            controller.createUser(idUser, fullname, birthdate, email, username, password, role);
            
            // Redirigir de vuelta al panel con mensaje de éxito
            response.sendRedirect("Admin.jsp?status=success");
            
        } catch (Exception e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
            response.sendRedirect("RegisterUser.jsp?status=error");
        }
    }
}