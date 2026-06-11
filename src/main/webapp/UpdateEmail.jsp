<%-- 
    Document   : UpdateEmail
    Created on : 10 jun 2026, 1:42:07 p.m.
    Author     : XPxTBxLLX
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>Modificar Correo</title></head>
<body>
    <h2>Modificar Correo</h2>

    <% String mensaje = (String) request.getAttribute("mensaje");
       String tipoMensaje = (String) request.getAttribute("tipoMensaje");
       if (mensaje != null) { %>
        <div style="padding:10px; margin:10px 0; border-radius:5px;
             background-color: <%= "success".equals(tipoMensaje) ? "#d4edda" : "#f8d7da" %>;
             color: <%= "success".equals(tipoMensaje) ? "#155724" : "#721c24" %>;">
            <%= mensaje %>
        </div>
    <% } %>

    <form action="SvUpdateEmail" method="POST">
        <input type="email" name="newEmail" placeholder="Nuevo correo" required><br><br>
        <input type="password" name="password" placeholder="Confirma tu contraseña" required><br><br>
        <button type="submi
