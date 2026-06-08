<%-- 
    Document   : LoanBook
    Created on : 20 may 2026, 10:14:37 p.m.
    Author     : XPxTBxLLX
--%>

<%@page import="com.equipo1.entities.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>BIBLIOTECA</title>
</head>
<body>
    <form action="SvLoanBook" method="POST" class="form">
    <input type="text" name="id_user" id="id_user" placeholder="ESCANEA TU CREDENCIAL" required><br><br>
    <input type="text" placeholder="Nombre Completo" readonly id="nombre_usuario"><br><br>
    
    <input type="text" name="status" placeholder="Estatus" value="Activo" required><br><br>
    
    <label>Fecha de devolución:</label>
    <input type="date" name="return_date" required><br><br>
    
    <select name="id_book" class="form-select">
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            if(books != null){
                for (Book b : books){
        %>
        <option value="<%= b.getIdBook()%>"><%= b.getTittle() %></option>
        <%  } } %>
    </select>
    
    <button type="submit" class="btn btn-primary">Enviar Préstamo</button>
</form>
</body>
</html>