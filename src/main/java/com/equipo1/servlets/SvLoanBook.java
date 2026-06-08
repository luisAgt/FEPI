/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.equipo1.servlets;

import com.equipo1.entities.Book;
import com.equipo1.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author XPxTBxLLX
 */
@WebServlet(name = "SvLoanBook", urlPatterns = {"/SvLoanBook"})
public class SvLoanBook extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Controller controller = new Controller();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvLoanBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvLoanBook at " + request.getContextPath() + "</h1>");
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
       
        
        List<Book> books = controller.getAvailableBooks();
        System.out.println("Libros: " + books.size());
        request.setAttribute("books", books);
        
        request.getRequestDispatcher("/LoanBook.jsp").forward(request, response);
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
            // 1. Recibir datos del JSP
            int idUser = Integer.parseInt(request.getParameter("id_user"));
            int idBook = Integer.parseInt(request.getParameter("id_book"));
            String status = request.getParameter("status");
            
            // 2. Convertir la fecha que viene del input type="date"
            String returnDateStr = request.getParameter("return_date");
            LocalDate returnDate = LocalDate.parse(returnDateStr); // Convierte "2026-06-15" a objeto Date
            
            // Fecha actual de préstamo
            LocalDateTime loanDate = LocalDateTime.now();
            
            // 3. Llamar al controlador con los datos reales
            controller.createLoanBook(idBook, idUser, loanDate, returnDate, status);
            
            response.sendRedirect("LoanBook.jsp?status=success");
            
        } catch (Exception e) {
            System.err.println("Error en préstamo: " + e.getMessage());
            response.sendRedirect("LoanBook.jsp?status=error");
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
