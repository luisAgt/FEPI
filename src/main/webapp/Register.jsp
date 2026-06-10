<%-- 
    Document   : Register
    Created on : 28 may 2026, 4:55:42 a.m.
    Author     : XPxTBxLLX
--%>
<%
String error =
    (String) request.getAttribute("error");

if(error != null){
%>

<div class="alert alert-danger">
    <%= error %>
</div>

<%
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
                <link rel="stylesheet" href="css/style.css">
    <title name=" school_access">School Access</title>
</head>
<body class="login-body">

    <div class="login-container">

        <form action="SvRegister" method="POST" class="login-card">

            <h1 class="logo-title">ACCESCOM</h1>
            <h2 class="login-title">Registro de usuarios</h2>

            <input
                type="text"
                name="qr"
                id="qrInput"
                class="form-control"
                placeholder="Escanea tu credencial"
                autofocus
                autocomplete="off">

        </form>

    </div>

    <script>
        const qrInput = document.getElementById('qrInput');

        qrInput.addEventListener("keypress", function(event){
            if(event.key === "Enter"){
                event.preventDefault();
                this.form.submit();
            }
        });

        window.onload = function() {
            qrInput.focus();
        }
    </script>

</body>
</html>
