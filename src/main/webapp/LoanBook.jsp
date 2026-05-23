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
        <div>
            <input type="text" placeholder="Nombre Completo" readonly autofocus id="qrInput"><br><br>
            <input type="text" placeholder="Boleta" readonly autofocus id="qrInput"><br><br>
            <input type="text" name="id_user" placeholder="Identificador" readonly autofocus id="qrInput"><br><br>
        </div>
        <div>
            <input type="text" name="status" placeholder="Estatus" ><br><br>
            <input type="text" name="loan_date" placeholder="Fecha de emision" readonly ><br><br>
            <input type="date" name="return_date" placeholder="Fecha de devolución"><br><br>
            <select name = "id_book">
                <%
                    List<Book> books = (List<Book>) request.getAttribute("books");
                    
                    for (Book b : books){
                %>
                <<option value="<%= b.getIdBook()%>">
                    <%= b.getTittle() %>
                </option>
                <% } %>
            </select>
        </div>
        
        <div>
            <input type ="submit" value="Enviar">
        </div>
    </form>
</body>
</html>