<%-- 
    Document   : Admin
    Created on : 23 may 2026, 5:39:18 p.m.
    Author     : XPxTBxLLX
--%>

<%
    String role =
        (String) session.getAttribute("role");

    if(role == null || !role.equals("ADMIN")){

        response.sendRedirect("Login.jsp");

        return;
    }
%>

<%
    response.setHeader("Cache-Control",
            "no-cache, no-store, must-revalidate");

    response.setHeader("Pragma", "no-cache");

    response.setDateHeader("Expires", 0);

    if(role == null || !role.equals("ADMIN")){

        response.sendRedirect("Login.jsp");

        return;
    }
%>

<%@page import="com.equipo1.entities.System_user"%>
<%
    System_user user = (System_user) session.getAttribute("user");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page ADMIN</title>
    </head>
    <body>
        <h1><---------A D M I N I S T R A C I O N---------></h1>
        <h2>Bienvenid@, <%=user.getFullName() %></h2>
        <a href="url"> Registrar usuario</a> <br><br>
        <a href="url"> Modificar usuario</a> <br><br>
        <a href="url"> Registrar libro</a> <br><br>
        <a href="url"> Modificar libro</a> <br><br>
        <a href="url"> Registrar material de laboratorio</a> <br><br>
        <a href="url"> Modificar material de laboratorio</a> <br><br>
    </body>
</html>
