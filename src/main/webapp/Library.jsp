<%-- 
    Document   : Library
    Created on : 25 may 2026, 1:05:07 p.m.
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
        <title>JSP Page</title>
    </head>
    <body>
        <h1><---------B I B L I O T E C A ---------></h1>
        <h2>Bienvenid@, <%=user.getFullname() %></h2>
        <a href="SvLoanBook">Registrar prestamo</a> <br><br>
        <a href="">Ver prestamos</a> <br><br>
        <a href="">Consultar libros</a> <br><br>
        <a href="SvLogout">Cerrar session</a>
              
    </body>
</html>
