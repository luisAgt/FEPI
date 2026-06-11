/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.equipo1.servlets;

import com.equipo1.dto.CredentialData;
import com.equipo1.entities.Student;
import com.equipo1.entities.Users;
import com.equipo1.logic.Controller;
import com.equipo1.services.DAEExtractor;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author XPxTBxLLX
 */
@WebServlet(name = "SvRegister", urlPatterns = {"/SvRegister"})
public class SvRegister extends HttpServlet {

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
        
        request.getRequestDispatcher("Register.jsp").forward(request, response);
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
            
//            deshabilitarSSL();
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession)->true);
           String url = request.getParameter("qr");
           
           url = url.replace(".ipn.mvcred", ".ipn.mx/vcred");
           
           DAEExtractor extractor = new DAEExtractor();
           CredentialData data = extractor.extractData(url);
           
           
           Student existing = controller.findStudentByBoleta(data.getBoleta());
           
           if(existing != null){
               request.setAttribute("error", "Usuario ya existente");
               request.getRequestDispatcher("Register.jsp").forward(request, response);
               return;
           }
           
           Student student = controller.registerStudent(data);
           Users user = student.getUsers();
           
                      
            request.setAttribute("carrer", student.getCarrer());
            request.setAttribute("fullname", user.getFullname());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("status", student.getStatus());
            request.setAttribute("birthdate", user.getBirthdate());
            
            System.out.println("[SvRegister] OK | boleta=" + data.getBoleta() + " carrer=" + data.getCarrer());

            request.setAttribute("mensaje", "Registro realizado correctamente");
            request.setAttribute("tipoMensaje", "success");
            request.getRequestDispatcher("LoanBook.jsp").forward(request, response);


        }catch(Exception e){            
            System.out.println("[SvRegister] ERROR | " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("mensaje", e.getMessage());
            request.setAttribute("tipoMensaje", "error");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
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
