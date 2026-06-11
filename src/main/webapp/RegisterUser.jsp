<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Registrar Nuevo Usuario</h2>
    
    <form action="SvRegisterUser" method="POST">
        <div class="mb-3">
            <label>ID Usuario (Boleta/Matrícula):</label>
            <input type="text" name="id_user" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Nombre Completo:</label>
            <input type="text" name="fullname" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Fecha de Nacimiento:</label>
            <input type="date" name="birthdate" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Correo Electrónico:</label>
            <input type="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Nombre de Usuario (Username):</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Contraseña:</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Rol en el Sistema:</label>
            <select name="role" class="form-select" required>
                <option value="ADMIN">Administrador (ADMIN)</option>
                <option value="STUDENT">Estudiante (STUDENT)</option>
                <option value="PROFESSOR">Profesor (PROFESSOR)</option>
                <option value="EXECUTIVE">Ejecutivo (EXECUTIVE)</option>
                <option value="LIBRARIAN">Bibliotecario (LIBRARIAN)</option>
                <option value="ANALOGIC">Laboratorista (ANALOGIC)</option>
            </select>
        </div>
        
        <button type="submit" class="btn btn-success">Guardar Usuario</button>
        <a href="Admin.jsp" class="btn btn-danger">Cancelar</a>
    </form>
</body>
</html>