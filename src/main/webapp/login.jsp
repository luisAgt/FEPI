<%-- 
    Document   : login
    Created on : 20 may 2026, 8:34:24 p.m.
    Author     : XPxTBxLLX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesion</title>
</head>
<body>
    <div>
        <form action="SvLogin">
            <div>
                <h1>INICIO DE SESION</h1> 
                <p>Sistema de Autenticacion</p> <br><br>
                <input type="username" name="username" placeholder="Nombre de usuario"> <br><br>
                <input type="password" name="password" placeholder="Contraseña"> <br><br>
                <button type="submit">Entrar</button>
            </div>
        </form>
    </div>
</body>
</html>
