<%-- 
    Document   : Subjects
    Created on : 5 jun 2026, 2:55:24 a.m.
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
        <h2>Subir Comprobante de horario</h2>
        <form action="SvUploadSchedule" method="POST" enctype="multipart/form-data">
            <input type="file" name ="schedulePDF" accept=".pdf">
            <button type="submit">Subir</button>
        </form>
        
        
        <h2>Texto extraido</h2>
        
        <pre>
            ${textoPDF}
        </pre>
    </body>
</html>
