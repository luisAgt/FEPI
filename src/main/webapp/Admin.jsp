<%-- 
    Document   : Admin
    Created on : 23 may 2026, 5:39:18 p.m.
    Author     : XPxTBxLLX
--%>

<%
    response.setHeader("Cache-Control", "no-cache, no store, multi-revalidate");
    response.setHeader("Pragma", "no cache");
    response.setDateHeader("Expires", 0);
    
    String role =
        (String) session.getAttribute("role");

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
                    <link rel="stylesheet" href="css/style.css">
        <title>JSP Page ADMIN</title>
    </head>
    <body>

<div class="admin-container">

    <div class="admin-header">
        <h1>Panel de Administración</h1>
        <h3>Bienvenid@, <%=user.getFullname()%></h3>
    </div>

    <div class="admin-menu">

        <a href="url" class="admin-card">
            Registrar usuario
        </a>

        <a href="url" class="admin-card">
            Modificar usuario
        </a>

        <a href="url" class="admin-card">
            Registrar libro
        </a>

        <a href="url" class="admin-card">
            Modificar libro
        </a>

        <a href="url" class="admin-card">
            Registrar material
        </a>

        <a href="url" class="admin-card">
            Modificar material
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
