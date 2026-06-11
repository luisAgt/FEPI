package com.equipo1.servlets;

import com.equipo1.logic.Controller;
import com.equipo1.entities.LabMaterial;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEditMaterial", urlPatterns = {"/SvEditMaterial"})
public class SvEditMaterial extends HttpServlet {

    Controller controller = new Controller();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idSearch = Integer.parseInt(request.getParameter("id_material_search"));
            LabMaterial mat = controller.findLabMaterial(idSearch);
            
            request.setAttribute("matToEdit", mat);
            request.getRequestDispatcher("EditMaterial.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("EditMaterial.jsp?status=notfound");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            int idMaterial = Integer.parseInt(request.getParameter("id_material"));
            String name = request.getParameter("name");
            int stock = Integer.parseInt(request.getParameter("stock"));
            
            controller.editMaterial(idMaterial, name, stock);
            
            response.sendRedirect("Admin.jsp?status=updated");
        } catch (Exception e) {
            System.err.println("Error al editar material: " + e.getMessage());
            response.sendRedirect("EditMaterial.jsp?status=error");
        }
    }
}