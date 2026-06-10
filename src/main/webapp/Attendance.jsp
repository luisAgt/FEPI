<%-- 
    Document   : Attendance
    Created on : 20 may 2026, 5:13:50 p.m.
    Author     : XPxTBxLLX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="login-body">

    <div class="login-container">

        <form id="formAttendance" method="POST" action="SvAttendance" class="login-card">

            <h1 class="logo-title">ACCESCOM</h1>
            <h2 class="login-title">Registro de Asistencia</h2>

            <input
                type="text"
                name="boleta"
                class="form-control"
                placeholder="Escanea tu boleta"
                autofocus
                autocomplete="off"
                onkeydown="if(event.key==='Enter'){ document.getElementById('formAttendance').submit(); }">

        </form>

    </div>

</body>
</html>
