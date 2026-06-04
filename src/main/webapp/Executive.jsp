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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2>Bienvenid@ <%=user.getFullname() %></h2>
    </body>
</html>
