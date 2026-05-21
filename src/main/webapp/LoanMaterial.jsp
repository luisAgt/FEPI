<%-- 
    Document   : LoanMaterial
    Created on : 20 may 2026, 5:13:30 p.m.
    Author     : XPxTBxLLX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LABORATORIO</title>
</head>
<body>
    <form action="SvLoanMaterial" method="POST">
        <div>
            <input type="text" name="student_name" placeholder="Nombre Completo" disabled><br><br>
            <input type="text" name="student_boleta" placeholder="Boleta" disabled><br><br>
            <input type="text" name="correo" placeholder="Correo Electrónico"><br><br>
        </div>
        <div>
            <input type="text" name="status" placeholder="Estatus" disabled><br><br>
            <input type="text" name="date_loan" placeholder="Fecha de emision" disabled><br><br>
            <input type="date" name="date_return" placeholder="Fecha de devolución"><br><br>
        </div>
        <div>
            <input type ="submit" value="Enviar">
        </div>
    </form>
</body>
</html>
