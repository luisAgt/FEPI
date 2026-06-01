<%-- 
    Document   : SchoolAccess
    Created on : 20 may 2026, 5:14:17 p.m.
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
    <title name=" school_access">School Access</title>
</head>
<body >
    
    <h1><---------A C C E S C O M---------></h1>
                  <h2>PRINCIPAL</h2>
    <div>
        <form action="SvSchoolAccess" method="POST">
            <div>
                <input type="text" name="boleta" id="boletaInput" placeholder="Escanea tu Boleta"  value="${boleta}" autofocus autocomplete="off"> <br><br>
                <input type="text" name="status" placeholder="Estatus" readonly><br><br>
                <input type="text" name="fullname" placeholder="Nombre Completo" readonly value="${fullname}"> <br><br>
                <input type="date" name="birthdate" placeholder="Fecha de Nacimiento" readonly value="${birthdate}"><br><br>
                <input type="text" name="carrer" placeholder="Carrera" readonly value="${carrer}"><br><br>
                <input type="text" name="email" placeholder="Correo" readonly value="${email}" ><br><br>
            </div>       
            <div class="">                
                <input type="text" name="date_a" placeholder="Fecha de acceso" readonly value="${date_a}" ><br><br>
                <input type="text" name="access" placeholder="Tipo de acceso" readonly value="${access}"><br><br>
                <input type="text" name="gate" placeholder="Puerta de acceso" readonly value="${gate}"><br><br>
            </div>
        </form>
    </div>
    <script>
        const boletaInput = document.getElementById("boletaInput");

        boletaInput.addEventListener("keypress", function(e) {
            if (e.key === "Enter") {
                e.preventDefault();
                this.form.submit();
            }
        });

        window.onload = () => boletaInput.focus();
    </script>

</body>
</html>
