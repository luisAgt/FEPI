<%-- 
    Document   : Executive
    Created on : 24 may 2026, 3:39:37 a.m.
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

<div class="admin-container">

    <div class="admin-header">
        <h1>Dirección Ejecutiva</h1>
        <h2>Bienvenid@, <%=user.getFullname()%></h2>
    </div>

    <div class="admin-menu">

        <div class="admin-card">
            Gestión Institucional
        </div>

        <div class="admin-card">
            Reportes Generales
        </div>

        <div class="admin-card">
            Indicadores Académicos
        </div>

    </div>

    <div class="logout-section">
        <a href="SvLogout" class="btn btn-danger">
            Cerrar sesión
        </a>
    </div>

</div>

</body>
</html>
