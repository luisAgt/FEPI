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
    <title name=" school_access">School Access</title>
</head>
<body >
    
    <h1><---------A C C E S C O M---------></h1>
                  <h2>Registro de usuarios</h2>
    <div>
        <form action="SvRegister" method="POST">
            <div>
                <input type="text" name="qr" id="qrInput" placeholder="ESCANEA TU CREDENCIAL" autofocus  value ="" autocomplete="off"><br><br>               
            </div>       
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
