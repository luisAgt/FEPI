package com.equipo1.servlets;

import com.equipo1.logic.Controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvRegisterBook", urlPatterns = {"/SvRegisterBook"})
public class SvRegisterBook extends HttpServlet {

    Controller controller = new Controller();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            // Extraer datos
            String tittle = request.getParameter("tittle");
            String editorial = request.getParameter("editorial");
            int edition = Integer.parseInt(request.getParameter("edition"));
            String author = request.getParameter("author");
            int stock = Integer.parseInt(request.getParameter("stock"));
            String status = request.getParameter("status");
            
            // Enviar al Controller
            controller.createBook(tittle, editorial, edition, author, stock, status);
            
            response.sendRedirect("Admin.jsp?status=success");
            
        } catch (Exception e) {
            System.err.println("Error al registrar libro: " + e.getMessage());
            response.sendRedirect("RegisterBook.jsp?status=error");
        }
    }
}