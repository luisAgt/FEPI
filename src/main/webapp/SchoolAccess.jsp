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
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title name=" school_access">School Access</title>
</head>
<body >
    <h1><---------A C C E S C O M---------></h1>
    <div>
        <form action="SvSchoolAccess" method="POST">
            <div>
                <input type="text" name="id_user" id="qrInput" placeholder="ESCANEA TU CREDENCIAL" readonly autofocus><br><br>
                <input type="text" name="status" placeholder="Estatus" readonly><br><br>
                <input type="text" name="fullname" placeholder="Nombre Completo" readonly autofocus> <br><br>
                <input type="date" name="birthdate" placeholder="Fecha de Nacimiento" readonly autofocus><br><br>
                <input type="text" name="carrer" placeholder="Carrera" readonly autofocus><br><br>
                <input type="text" name="email" placeholder="Correo" readonly autofocus><br><br>
            </div>       
            <div class="">                
                <input type="datetime-local" name="date_a" placeholder="Fecha de acceso"  autofocus><br><br>
                <input type="text" name="access" placeholder="Tipo de acceso" readonly autofocus><br><br>
                <input type="text" name="gate" placeholder="Puerta de acceso" readonly autofocus><br><br>
            </div>
        </form>
    </div>
</body>
</html>
