<%@page import="com.equipo1.entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Modificar Usuario Existente</h2>
    
    <form action="SvEditUser" method="GET" class="mb-4">
        <label>Ingrese ID (Boleta/Matrícula) a buscar:</label>
        <div class="input-group">
            <input type="number" name="id_user_search" class="form-control" required>
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
    </form>

    <hr>

    <%
        Users userToEdit = (Users) request.getAttribute("userToEdit");
        if (userToEdit != null) {
    %>
        <form action="SvEditUser" method="POST">
            <div class="mb-3">
                <label>ID Usuario:</label>
                <input type="number" name="id_user" value="<%= userToEdit.getIdUser() %>" class="form-control" readonly>
            </div>
            <div class="mb-3">
                <label>Nombre Completo:</label>
                <input type="text" name="fullname" value="<%= userToEdit.getFullname() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Fecha de Nacimiento:</label>
                <input type="date" name="birthdate" value="<%= userToEdit.getBirthdate() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Correo Electrónico:</label>
                <input type="email" name="email" value="<%= userToEdit.getEmail() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Nombre de Usuario:</label>
                <input type="text" name="username" value="<%= userToEdit.getUsername() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Contraseña:</label>
                <input type="text" name="password" value="<%= userToEdit.getPassword() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Rol en el Sistema (Actual: <b><%= userToEdit.getRole() %></b>):</label>
                <select name="role" class="form-select" required>
                    <option value="<%= userToEdit.getRole() %>">-- Mantener rol actual --</option>
                    <option value="ADMIN">Administrador (ADMIN)</option>
                    <option value="STUDENT">Estudiante (STUDENT)</option>
                    <option value="PROFESSOR">Profesor (PROFESSOR)</option>
                    <option value="EXECUTIVE">Ejecutivo (EXECUTIVE)</option>
                    <option value="LIBRARIAN">Bibliotecario (LIBRARIAN)</option>
                    <option value="ANALOGIC">Laboratorista (ANALOGIC)</option>
                </select>
            </div>
            
            <button type="submit" class="btn btn-warning">Actualizar Usuario</button>
            <a href="Admin.jsp" class="btn btn-danger">Cancelar</a>
        </form>
    <% } %>
</body>
</html>