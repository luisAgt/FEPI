package com.equipo1.servlets;

import com.equipo1.logic.Controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvRegisterMaterial", urlPatterns = {"/SvRegisterMaterial"})
public class SvRegisterMaterial extends HttpServlet {

    Controller controller = new Controller();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            String name = request.getParameter("name");
            int stock = Integer.parseInt(request.getParameter("stock"));
            
            controller.createMaterial(name, stock);
            
            response.sendRedirect("Admin.jsp?status=success");
            
        } catch (Exception e) {
            System.err.println("Error al registrar material: " + e.getMessage());
            response.sendRedirect("RegisterMaterial.jsp?status=error");
        }
    }
}