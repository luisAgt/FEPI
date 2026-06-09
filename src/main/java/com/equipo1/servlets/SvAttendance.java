/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.equipo1.servlets;

import com.equipo1.entities.Attendance;
import com.equipo1.entities.Enrollment;
import com.equipo1.entities.Student;
import com.equipo1.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "SvAttendance", urlPatterns = {"/SvAttendance"})
public class SvAttendance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Controller controller = new Controller();
        List<Attendance> attendances = controller.findAttendanceByStudent(student);

        request.setAttribute("attendances", attendances);
        request.getRequestDispatcher("ViewAttendance.jsp").forward(request, response);

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("ViewAttendance.jsp").forward(request, response);
    }
}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Attendance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Attendance at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {        
        try {
            String boleta = request.getParameter("boleta");
            Controller controller = new Controller();

            // ── 1. Buscar estudiante ──────────────────────────────────────────
            Student student = controller.findStudentByBoleta(boleta);
            if (student == null) {
                throw new Exception("No existe alumno con boleta: " + boleta);
            }

            // ── 2. Hora y día actual ──────────────────────────────────────────
            //LocalDateTime now       = LocalDateTime.now();
            ZoneId mexicoZone   = ZoneId.of("America/Mexico_City");
            LocalDateTime now   = LocalDateTime.now(mexicoZone);
            LocalTime nowTime   = now.toLocalTime();
            //LocalTime     nowTime   = now.toLocalTime();
            String        todayDay  = now.getDayOfWeek().getDisplayName(
                                        TextStyle.FULL, Locale.forLanguageTag("es-MX"))
                                        .toUpperCase(); // "LUNES", "MARTES", etc.

            // Normalizar: quitar acentos para coincidir con BD
            // (MIÉRCOLES → MIERCOLES)
            todayDay = normalizeDay(todayDay);

            System.out.println("Día: " + todayDay + " Hora: " + nowTime);

            // ── 3. Buscar el Enrollment activo ahora ──────────────────────────
            // Obtener todos los enrollments del estudiante y encontrar
            // cuál tiene clase en este momento
            Enrollment activeEnrollment = controller.findActiveEnrollment(
                                            student, todayDay, nowTime);

            if (activeEnrollment == null) {
                
                throw new Exception("No hay clase en este momento para la boleta: " + boleta);
            }

            // ── 4. Determinar status: ON_TIME o LATE ──────────────────────────
            // Extraer startTime del horary
            Date startDate = activeEnrollment.getIdSchedule().getIdHorary().getStartTime();

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            int hour   = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            // Construir LocalTime para comparar con nowTime
            LocalTime classStart = LocalTime.of(hour, minute);
            System.out.println("NOW = " + now);
            System.out.println("ZONE = " + java.time.ZoneId.systemDefault());
            System.out.println("Timestamp = " + Timestamp.valueOf(now));
            String status;
            if (!nowTime.isAfter(classStart.plusMinutes(10))) {
                status = "ON_TIME";
            } else {
                status = "LATE";
            }
            System.out.println("Status: " + status + " (clase inicia: " + classStart + ")");

            // ── 5. Verificar que no se haya registrado ya hoy ─────────────────
            boolean yaRegistrado = controller.attendanceExistsToday(
                                        activeEnrollment, now.toLocalDate());
            if (yaRegistrado) {
                throw new Exception("Asistencia ya registrada para esta clase hoy.");
            }

            // ── 6. Registrar Attendance ───────────────────────────────────────
            controller.createAttendance(student, activeEnrollment,
                                        Timestamp.valueOf(now), status);
            System.out.println("Asistencia registrada: " + boleta + " " + status);

            request.setAttribute("mensaje", "Asistencia registrada: " + status);
            request.getRequestDispatcher("Attendance.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("Attendance.jsp").forward(request, response);
        }
    }

    // Quita acentos para normalizar día: MIÉRCOLES → MIERCOLES
    private String normalizeDay(String day) {
        return day.replace("É", "E").replace("é", "e")
                  .replace("Á", "A").replace("á", "a")
                  .replace("Í", "I").replace("Ó", "O")
                  .replace("Ú", "U");
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
