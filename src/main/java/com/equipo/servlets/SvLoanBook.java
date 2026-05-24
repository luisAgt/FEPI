/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.equipo.servlets;

import com.equipo1.entities.Book;
import com.equipo1.logica.Controller;
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
        Controller controller = new Controller();
        
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
        //processRequest(request, response);
        try{
            int idBook = Integer.parseInt(request.getParameter("id_book"));
            int idUser = Integer.parseInt(request.getParameter("id_user"));
            LocalDate returnDate = LocalDate.parse(request.getParameter("return_date"));
            LocalDateTime loanDate = LocalDateTime.now();
            String status = "ACTIVO";

            Controller controller = new Controller();

            controller.createLoanBook(
                    idBook,
                    idUser,
                    loanDate,
                    returnDate,
                    status
            );

            response.sendRedirect("SvLoanBook?success=true");
            
        }catch(Exception e){
            e.printStackTrace();
            response.sendRedirect("SvLoanBook?error=true");
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
