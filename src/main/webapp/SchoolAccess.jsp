<%-- 
    Document   : SchoolAccess
    Created on : 20 may 2026, 5:14:17 p.m.
    Author     : XPxTBxLLX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title name=" school_access">School Access</title>
</head>
<body >
    <div>
        <form action="SvSchoolAccess">
            <input type="text" name="status" placeholder="Estatus" readonly><br><br>
            <input type="text" name="name" placeholder="Nombre" readonly autofocus> <br><br>
            <input type="text" name="last_name" placeholder="Apellido Paterno" readonly autofocus><br><br>
            <input type="text" name="mother_last_name" placeholder="Apellido Materno" readonly autofocus><br><br>
            <input type="date" name="birthdate" placeholder="Fecha de Nacimiento" readonly autofocus><br><br>
            <input type="text" name="user_id" placeholder="Identificador" readonly autofocus><br><br>
            <input type="text" name="carrer" placeholder="Carrera" readonly autofocus><br><br>
            <input type="text" name="email" placeholder="Correo" readonly autofocus><br><br>
            <input type="text" name="status" placeholder="Tipo de acceso" readonly><br><br>
        </form>
    </div>
</body>
</html>
