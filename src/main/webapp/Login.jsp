<%-- 
    Document   : Login
    Created on : 27 may 2026, 9:47:19 p.m.
    Author     : XPxTBxLLX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>Inicio de sesion</title>
</head>
<body>
    <div>
        <form action="SvLogin" method="POST">
            <div>
                 <h1><---------A C C E S C O M---------></h1>
                  <h2>Inicio de sesion</h2>
                <input type="username" name="username" placeholder="Nombre de usuario"> <br><br>
                <input type="password" name="password" placeholder="Contraseña"> <br><br>
                <button type="submit">Entrar</button>
            </div>
        </form>
    </div>
</body>
<div></div>
</html>
