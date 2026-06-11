/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.equipo1.servlets;

import com.equipo1.entities.AcademicGroup;
import com.equipo1.entities.Enrollment;
import com.equipo1.entities.Horary;
import com.equipo1.entities.Schedule;
import com.equipo1.entities.Student;
import com.equipo1.entities.Subject;
import com.equipo1.logic.Controller;
import com.equipo1.services.ScheduleExtractor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author XPxTBxLLX
 */
@WebServlet(name = "SvUploadSchedule", urlPatterns = {"/SvUploadSchedule"})
@MultipartConfig
public class SvUploadSchedule extends HttpServlet {

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
            out.println("<title>Servlet SvUploadSchedule</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvUploadSchedule at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        // ── 1. Leer PDF una sola vez en bytes para usarlo dos veces ──────
        Part pdfPart = request.getPart("schedulePDF");
        InputStream is = pdfPart.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] chunk = new byte[4096];
        int bytesRead;
        while ((bytesRead = is.read(chunk)) != -1) {
            buffer.write(chunk, 0, bytesRead);
        }
        byte[] pdfBytes = buffer.toByteArray();

        // Texto plano para boleta y bloques de materias
        PDDocument document = PDDocument.load(pdfBytes);
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);
        String texto = stripper.getText(document);

        // Tokens con coordenadas X para determinar días correctamente
        ScheduleExtractor extractor = new ScheduleExtractor();
        extractor.getText(document);
        
        document.close();

        List<Object[]> tokens = extractor.tokens;

        System.out.println("===== TEXTO EXTRAIDO =====");
        System.out.println(texto);
        System.out.println("==========================");

        // ── 2. Extraer boleta y buscar estudiante ────────────────────────
        Pattern boletaP = Pattern.compile("Boleta:\\s*(\\d+)");
        Matcher boletaM = boletaP.matcher(texto);
        if (!boletaM.find()) {
            throw new Exception("No se encontró boleta en el PDF");
        }
        String boleta = boletaM.group(1);
        System.out.println("Boleta extraída: " + boleta);

        Student student = controller.findStudentByBoleta(boleta);
        if (student == null) {
            throw new Exception("No existe alumno con boleta: " + boleta);
        }

        // ── 3. Dividir texto en bloques por materia ──────────────────────
        String[] lines = texto.split("\\n");
        List<String> blocks = new ArrayList<>();
        StringBuilder current = null;
        Pattern groupLineP = Pattern.compile("^\\d[A-Z]{2}\\d+\\s+C\\d+\\s+-");
        
        // Agrega esto temporalmente justo después de extractor.getText(document)
        // en el doPost, antes del for(blocks)
//        System.out.println("=== TODOS LOS TOKENS ===");
//        for (Object[] token : tokens) {
//            System.out.println("X=" + token[0] + " Y=" + token[1] + " texto='" + token[2] + "'");
//        }
//        System.out.println("========================");
        for (String line : lines) {
            if (groupLineP.matcher(line.trim()).find()) {
                if (current != null) blocks.add(current.toString());
                current = new StringBuilder(line).append("\n");
            } else if (current != null) {
                current.append(line).append("\n");
            }
        }
        if (current != null) blocks.add(current.toString());

        // ── 4. Procesar cada bloque ──────────────────────────────────────
        for (String block : blocks) {

            // Intentar extraer datos de la materia
            Pattern fallback = Pattern.compile(
                "(\\d[A-Z]{2}\\d+)\\s+(C\\d+)\\s+-\\s+(.+?)\\s+(\\d+\\.\\d+)");
            Matcher sm = fallback.matcher(block);
            if (!sm.find()) continue;

            String groupCode   = sm.group(1);        // "5CM2"
            String code        = sm.group(2);         // "C502"
            String subjectName = sm.group(3).trim();  // "PROCESAMIENTO DIGITAL DE SEÑALES"
            BigDecimal credits = new BigDecimal(sm.group(4));

            String semester = String.valueOf(groupCode.charAt(0));
            char carrerL    = groupCode.charAt(1);
            String carrer;
            switch (carrerL) {
                case 'A': carrer = "LCD"; break;
                case 'B': carrer = "IIA"; break;
                case 'C': carrer = "ISC"; break;
                default:  carrer = "UNKNOWN"; break;
            }
            String aGroupName = groupCode.substring(1); // "CM2"

            System.out.printf("Materia: %s | Código: %s | Créditos: %s | Semestre: %s | Carrera: %s | Grupo: %s%n",
                subjectName, code, credits, semester, carrer, aGroupName);

            // ── 4a. Buscar o crear Subject ────────────────────────────────
            Subject subject = controller.findSubjectByCode(code);
            if (subject == null) {
                controller.createSubject(code, subjectName, credits);
                subject = controller.findSubjectByCode(code); // recuperar con ID generado
                System.out.println("Subject creado: " + code);
            }

            // ── 4b. Buscar o crear AcademicGroup ──────────────────────────
            AcademicGroup agroup = controller.findAcademicGroup(semester, carrer, aGroupName);
            if (agroup == null) {
                controller.createGroup(semester, carrer, aGroupName);
                agroup = controller.findAcademicGroup(semester, carrer, aGroupName); // recuperar con ID
                System.out.println("AcademicGroup creado: " + aGroupName);
            }

            // ── 4c. Extraer sesiones con día correcto usando coordenadas X ─
            List<String[]> daySessions = extractDaySessionsByX(tokens, code);

            for (String[] session : daySessions) {
                String weekDay   = session[0]; // "LUNES"
                String startTime = session[1]; // "12:00"
                String endTime   = session[2]; // "13:30"

                Horary horary = controller.findHorary(weekDay, startTime, endTime);
                if (horary == null) {
                    System.out.println("ADVERTENCIA: Horary no encontrado → "
                        + weekDay + " " + startTime + "-" + endTime);
                    continue;
                }

                Schedule schedule = controller.findSchedule(agroup, horary, subject);
                if (schedule == null) {
                    controller.createSchedule(agroup, horary, subject);
                    schedule = controller.findSchedule(agroup, horary, subject); // recuperar con ID
                    System.out.println("Schedule creado: " + code + " " + weekDay + " " + startTime);
                }

                Enrollment enrollment = controller.findEnrollment(student, schedule);
                if (enrollment == null) {
                    controller.createEnrollment(student, schedule);
                    System.out.println("Enrollment creado: boleta=" + boleta
                        + " " + weekDay + " " + startTime);
                }
            }
        }
        System.out.println("[SvUploadSchedule] OK | boleta=" + boleta + " materias procesadas=" + blocks.size());
        request.setAttribute("mensaje", "Horario registrado correctamente");
        request.setAttribute("tipoMensaje", "success");
        request.getRequestDispatcher("SvSubjects.jsp").forward(request, response);


//       List<Enrollment> enrolls = controller.findEnrollmentByStudent(student.getIdStudent());
//       
//       request.setAttribute("enrollments", enrolls);
//        request.setAttribute("textoPDF", texto);
//        request.getRequestDispatcher("Subjects.jsp").forward(request, response);

    } catch (Exception e) {
         System.out.println("[SvUploadSchedule] ERROR | " + e.getMessage());
        e.printStackTrace();
        request.setAttribute("mensaje", e.getMessage());
        request.setAttribute("tipoMensaje", "error");
        request.getRequestDispatcher("SvSubjects.jsp").forward(request, response);
    }
}

// ── Método auxiliar: determina día real usando coordenadas Y del PDF ──────────
private List<String[]> extractDaySessionsByX(List<Object[]> tokens, String subjectCode) {

    List<String[]> result = new ArrayList<>();
    // El token viene completo: "12:00 - 13:30" → capturar start y end
    Pattern timeP = Pattern.compile("(\\d{2}:\\d{2})\\s*-\\s*(\\d{2}:\\d{2})");

    // A. Coordenadas X de los encabezados de día
    String[] dayNames = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
    String[] dayKeys  = {"LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES"};
    Map<String, Float> dayX = new LinkedHashMap<>();

    for (Object[] token : tokens) {
        String text = ((String) token[2]).trim();
        for (int i = 0; i < dayNames.length; i++) {
            if (text.equalsIgnoreCase(dayNames[i]) && !dayX.containsKey(dayKeys[i])) {
                dayX.put(dayKeys[i], (Float) token[0]);
            }
        }
    }
    System.out.println("Coordenadas X días: " + dayX);

    // B. Encontrar la Y del token que contiene el código de materia
    float subjectY = -1;
    for (Object[] token : tokens) {
        String text = ((String) token[2]).trim();
        if (text.contains(subjectCode)) {
            subjectY = (Float) token[1];
            System.out.println("Token materia: '" + text + "' Y=" + subjectY);
            break;
        }
    }
    if (subjectY < 0) {
        System.out.println("No se encontró código: " + subjectCode);
        return result;
    }

    // C. Buscar la fila de horarios INMEDIATAMENTE ANTES de subjectY
    //    Los horarios están ~8-10px encima de la línea de la materia.
    //    Buscamos todos los tokens de tiempo con Y < subjectY,
    //    luego nos quedamos solo con los de la Y más cercana (lastKey).
    TreeMap<Float, List<Object[]>> rowMap = new TreeMap<>();

    for (Object[] token : tokens) {
        float  y    = (Float) token[1];
        String text = ((String) token[2]).trim();

        // Solo tokens ENCIMA de la materia que sean horarios completos
        if (y < subjectY && timeP.matcher(text).find()) {
            // Redondear Y para agrupar tokens de la misma fila visual
            float roundedY = Math.round(y / 2f) * 2f;
            rowMap.computeIfAbsent(roundedY, k -> new ArrayList<>()).add(token);
        }
    }

    if (rowMap.isEmpty()) {
        System.out.println("Sin horarios para: " + subjectCode);
        return result;
    }

    // La última entrada del TreeMap = fila con Y más alta = más cercana a la materia
    List<Object[]> closestRow = rowMap.lastEntry().getValue();

    // D. Por cada token de horario en esa fila, extraer start/end y asignar día por X
    for (Object[] token : closestRow) {
        String text = ((String) token[2]).trim();
        float  xPos = (Float) token[0];

        Matcher m = timeP.matcher(text);
        if (!m.find()) continue;

        String start = m.group(1); // "12:00"
        String end   = m.group(2); // "13:30"

        // Día cuya X esté más cercana a la X de este token
        String bestDay  = null;
        float  bestDist = Float.MAX_VALUE;
        for (Map.Entry<String, Float> d : dayX.entrySet()) {
            float dist = Math.abs(d.getValue() - xPos);
            if (dist < bestDist) {
                bestDist = dist;
                bestDay  = d.getKey();
            }
        }

        if (bestDay != null) {
            result.add(new String[]{bestDay, start, end});
            System.out.println("  Sesión → " + bestDay
                + " " + start + "-" + end + " (X=" + xPos + " distancia=" + bestDist + ")");
        }
    }

    return result;
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
