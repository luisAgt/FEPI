<%-- 
    Document   : Student.jsp
    Created on : 23 may 2026, 5:38:49 p.m.
    Author     : XPxTBxLLX
--%>

<%
    response.setHeader("Cache-Control", "no-cache, no store, multi-revalidate");
    response.setHeader("Pragma", "no cache");
    response.setDateHeader("Expires", 0);
    
    String role =
        (String) session.getAttribute("role");

    if(role == null || !role.equals("STUDENT")){

        response.sendRedirect("Login.jsp");

        return;
    }
%>

<%@page import="com.equipo1.entities.Users"%>
<%
    Users user = (Users) session.getAttribute("user");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <title>JSP Page Student</title>
    </head>
    <body>
        <h1><---------E S T U D I A N T E S--------></h1>
        <h2>Bienvenid@, <%=user.getFullname() %></h2>
        <a href="SvSubjects">Ver materias inscritas</a> <br><br>
        <a href="SvAttendance" >Ver asistencias</a> <br><br>
        <a href="url">Modificar datos personales</a> <br><br>
        <a href="SvLogout">Cerrar session</a>
    </body>
</html>
