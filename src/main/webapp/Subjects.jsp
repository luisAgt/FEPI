<%-- 
    Document   : Subjects
    Created on : 5 jun 2026, 2:55:24 a.m.
    Author     : XPxTBxLLX
--%>
<%@page import="com.equipo1.entities.Student"%>
<%@page import = "com.equipo1.entities.Enrollment" %>
<%@page import = "java.util.List" %>
<%
    Student student = (Student) session.getAttribute("student");
    
    if(student == null){
        response.sendRedirect("Login.jsp");
        return;
    }
    
    List<Enrollment> enrolls = (List<Enrollment>) request.getAttribute("enrollments");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            if(enrolls == null || enrolls.isEmpty()){
            
        %>
        <h2>Subir Comprobante de horario</h2>
        <form action="SvUploadSchedule" method="POST" enctype="multipart/form-data">
            <input type="file" name ="schedulePDF" accept=".pdf">
            <button type="submit">Subir</button>
        </form>
        
        <%
            } else{
        %>      
        
        <h2>Materias inscritas</h2>
        
        <table border ="1">
            <tr>
                <th>Materia</th>
                <th>Grupo</th>
                <th>Dia</th>
                <th>Horario</th>
                <th>Creditos</th>
                <th>Docente</th>
            </tr>
            
            <%
                for(Enrollment e : enrolls){                                 
            %>
            <tr>
                <td>
                    <%=
                        e.getIdSchedule().getIdSubject().getName()
                    %>
                </td>
                <td>
                    <%=
                        e.getIdSchedule().getIdGroup().getAGroup()
                    %>
                </td>
                <td>
                    <%=
                        e.getIdSchedule().getIdHorary().getWeekDay()
                    %>
                </td>
                <td>
                    <%=
                        e.getIdSchedule().getIdHorary().getStartTime()
                    %>
                    -
                    <%=
                        e.getIdSchedule().getIdHorary().getEndTime()
                    %>
                </td>
                
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>
    </body>
</html>
