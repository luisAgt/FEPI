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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="POST" action="SvAttendance">
            <input type="text" name="boleta" placeholder="Escanea tu boleta para verificar asistencias" autofocus autocomplete="off"
                              onkeydown="if(event.key==='Enter'){ document.getElementById('formAttendance').submit(); }">
            <br><br>
            
        </form>
        
        
    </body>
</html>
