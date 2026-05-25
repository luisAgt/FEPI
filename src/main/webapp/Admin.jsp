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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page ADMIN</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <a href="SvLogout">Cerrar session</a>
    </body>
</html>
