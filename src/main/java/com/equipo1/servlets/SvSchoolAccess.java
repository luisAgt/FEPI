/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.equipo1.servlets;

import com.equipo1.dto.CredentialData;
import com.equipo1.entities.Student;
import com.equipo1.entities.System_user;
import com.equipo1.logic.Controller;
import com.equipo1.services.DAEExtractor;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author XPxTBxLLX
 */
@WebServlet(name = "SvSchoolAccess", urlPatterns = {"/SvSchoolAccess"})
public class SvSchoolAccess extends HttpServlet {

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
//        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SvSchoolAccess</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SvSchoolAccess at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        //processRequest(request, response);
        
        request.getRequestDispatcher("SchoolAccess.jsp").forward(request, response);
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
           String url = request.getParameter("qr");
           
           url = url.replace(".ipn.mvcred", ".ipn.mx/vcred");
           
           DAEExtractor extractor = new DAEExtractor();
           CredentialData data = extractor.extractData(url);
           Controller controller = new Controller();
           
           controller.registerStudent(data);
           
           Student student = controller.findStudentByBoleta(data.getBoleta());
           
           System_user user = student.getSystemuser();
                      
           LocalDateTime date_now = LocalDateTime.now();
           int gate = 1;
           String AccessType = controller.determineAccessType(user.getIdUser());
           
           controller.createAccess(                   
                    user.getIdUser(),
                    date_now,
                    AccessType,
                    gate
            ); 
           
            request.setAttribute("carrer", student.getCarrer());
            request.setAttribute("fullname", user.getFullName());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("status", student.getStatus());
            request.setAttribute("access", AccessType);
            request.setAttribute("date_a", date_now);
            request.setAttribute("birthdate", user.getBirthdate());
            request.setAttribute("gate", gate);
            
            request.getRequestDispatcher("SchoolAccess.jsp").forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
            
            request.setAttribute("error", "No se pudo procesar");
            
            request.getRequestDispatcher("SchoolAccess.jsp").forward(request, response);
            
            
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
