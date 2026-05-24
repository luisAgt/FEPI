<%-- 
    Document   : Student.jsp
    Created on : 23 may 2026, 5:38:49 p.m.
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
