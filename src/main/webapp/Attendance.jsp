<%-- 
    Document   : Attendance
    Created on : 20 may 2026, 5:13:50 p.m.
    Author     : XPxTBxLLX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
         <link rel="stylesheet" href="css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="login-container">
            <form method="POST" action="SvAttendance">
                
                <h1 class =" logo-tittle">ACCESCOM</h1>
                <h2 class =" logo-tittle">Registro de asistencias</h2>
                
                <input type="text" name="boleta" placeholder="Escanea tu boleta para verificar asistencias" autofocus autocomplete="off"
                                      onkeydown="if(event.key==='Enter'){ document.getElementById('formAttendance').submit(); }">            
             </form>
        </div>
    </body>
</html>
