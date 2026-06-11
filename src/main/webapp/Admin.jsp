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
        <title>JSP Page ADMIN</title>
    </head>
    <body>
        <h1><---------A D M I N I S T R A C I O N---------></h1>
        <h2>Bienvenid@, <%=user.getFullname() %></h2>
        <a href="RegisterUser.jsp" class="btn btn-primary mb-2">Registrar usuario</a> <br>
        <a href="EditUser.jsp" class="btn btn-secondary mb-2">Modificar usuario</a> <br>
        
        <a href="RegisterBook.jsp" class="btn btn-primary mb-2">Registrar libro</a> <br>
        <a href="EditBook.jsp" class="btn btn-secondary mb-2">Modificar libro</a> <br>
        
        <a href="RegisterMaterial.jsp" class="btn btn-primary mb-2">Registrar material de laboratorio</a> <br>
        <a href="EditMaterial.jsp" class="btn btn-secondary mb-2">Modificar material de laboratorio</a> <br>        
        <a href="SvLogout">Cerrar session</a>
    </body>
</html>
