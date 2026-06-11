package com.equipo1.servlets;

import com.equipo1.logic.Controller;
import com.equipo1.entities.Book;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEditBook", urlPatterns = {"/SvEditBook"})
public class SvEditBook extends HttpServlet {

    Controller controller = new Controller();

    // Procesar la búsqueda del libro
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idSearch = Integer.parseInt(request.getParameter("id_book_search"));
            
            Book book = controller.findBook(idSearch);
            
            request.setAttribute("bookToEdit", book);
            request.getRequestDispatcher("EditBook.jsp").forward(request, response);
            
        } catch (Exception e) {
            response.sendRedirect("EditBook.jsp?status=notfound");
        }
    }

    // Procesar la actualización de los datos del libro
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        try {
            int idBook = Integer.parseInt(request.getParameter("id_book"));
            String tittle = request.getParameter("tittle");
            String editorial = request.getParameter("editorial");
            int edition = Integer.parseInt(request.getParameter("edition"));
            String author = request.getParameter("author");
            int stock = Integer.parseInt(request.getParameter("stock"));
            String status = request.getParameter("status");
            
            controller.editBook(idBook, tittle, editorial, edition, author, stock, status);
            
            response.sendRedirect("Admin.jsp?status=updated");
            
        } catch (Exception e) {
            System.err.println("Error al editar libro: " + e.getMessage());
            response.sendRedirect("EditBook.jsp?status=error");
        }
    }
}