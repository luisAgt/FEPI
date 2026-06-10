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
                <link rel="stylesheet" href="css/style.css">
    <title>Inicio de sesion</title>
</head>
<body class="login-body">

    <div class="login-container">

        <form action="SvLogin" method="POST" class="login-card">

            <h1 class="logo-title">ACCESCOM</h1>

            <h2 class="login-title">Inicio de Sesión</h2>

            <input

                type="text"

                name="username"

                placeholder="Nombre de usuario"

                class="form-control mb-3"

                autofocus>

            <input

                type="password"

                name="password"

                placeholder="Contraseña"

                class="form-control mb-4">

            <button type="submit" class="btn btn-primary w-100">

                Entrar

            </button>

        </form>

    </div>

</body>
<div></div>
</html>
