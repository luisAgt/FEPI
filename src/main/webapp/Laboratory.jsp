<%-- 
    Document   : Laboratory
    Created on : 25 may 2026, 1:04:44 p.m.
    Author     : XPxTBxLLX
--%>

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
            <link rel="stylesheet" href="css/style.css">
        <title>JSP Page</title>
    </head>
    <body>

    <div class="admin-container">

        <div class="admin-header">
            <h1>Laboratorio</h1>
            <h3>Bienvenid@, <%=user.getFullname()%></h3>
        </div>

        <div class="admin-menu">

            <a href="SvLogout" class="admin-card">
                Registrar préstamo
            </a>

            <a href="SvLogout" class="admin-card">
                Ver préstamos
            </a>

            <a href="SvLogout" class="admin-card">
                Consultar materiales
            </a>

        </div>

        <div class="logout-section">
            <a href="SvLogout" class="btn btn-danger">
                Cerrar sesión
            </a>
        </div>

    </div>

    </body>
</html>
