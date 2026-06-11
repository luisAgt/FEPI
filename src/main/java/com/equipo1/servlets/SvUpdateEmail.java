/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.equipo1.servlets;

import com.equipo1.entities.Student;
import com.equipo1.entities.Users;
import com.equipo1.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author XPxTBxLLX
 */
@WebServlet(name = "SvUpdateEmail", urlPatterns = {"/SvUpdateEmail"})
public class SvUpdateEmail extends HttpServlet {

    Controller controller = new Controller();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvUpdateEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvUpdateEmail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.getRequestDispatcher("UpdateEmail.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Student student = (Student) session.getAttribute("student");

            if (student == null) {
                response.sendRedirect("Login.jsp");
                return;
            }

            String newEmail = request.getParameter("newEmail");
            String password = request.getParameter("password");

            // Verificar contraseña
            Users user = student.getUsers();
            if (!user.getPassword().equals(password)) {
                request.setAttribute("mensaje", "Contraseña incorrecta.");
                request.setAttribute("tipoMensaje", "error");
                request.getRequestDispatcher("UpdateEmail.jsp").forward(request, response);
                return;
            }

            // Actualizar correo
            controller.updateEmail(user.getIdUser(), newEmail);

            // Actualizar objeto en sesión
            user.setEmail(newEmail);
            session.setAttribute("student", student);

            System.out.println("[SvUpdateEmail] OK | idUser=" + user.getIdUser() + " newEmail=" + newEmail);

            request.setAttribute("mensaje", "Correo actualizado correctamente.");
            request.setAttribute("tipoMensaje", "success");
            request.getRequestDispatcher("UpdateEmail.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("[SvUpdateEmail] ERROR | " + e.getMessage());
            request.setAttribute("mensaje", e.getMessage());
            request.setAttribute("tipoMensaje", "error");
            request.getRequestDispatcher("UpdateEmail.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
