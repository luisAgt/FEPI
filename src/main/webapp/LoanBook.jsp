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
    <link rel="stylesheet" href="css/style.css">
    <title>BIBLIOTECA</title>
</head>
<body>

<div class="container mt-4">

    <div class="admin-header">
        <h1>Préstamo de Libros</h1>
    </div>

    <div class="upload-card">

        <form action="SvLoanBook" method="POST">

            <div class="mb-3">
                <label class="form-label">Credencial</label>
                <input
                    type="text"
                    name="id_user"
                    id="qrInput"
                    class="form-control"
                    placeholder="Escanea tu credencial"
                    readonly
                    autofocus>
            </div>

            <div class="mb-3">
                <label class="form-label">Nombre Completo</label>
                <input
                    type="text"
                    class="form-control"
                    placeholder="Nombre Completo"
                    readonly>
            </div>

            <div class="mb-3">
                <label class="form-label">Boleta</label>
                <input
                    type="text"
                    class="form-control"
                    placeholder="Boleta"
                    readonly>
            </div>

            <div class="mb-3">
                <label class="form-label">Estatus</label>
                <input
                    type="text"
                    name="status"
                    class="form-control"
                    placeholder="Estatus">
            </div>

            <div class="mb-3">
                <label class="form-label">Fecha de emisión</label>
                <input
                    type="text"
                    name="loan_date"
                    class="form-control"
                    readonly>
            </div>

            <div class="mb-3">
                <label class="form-label">Fecha de devolución</label>
                <input
                    type="date"
                    name="return_date"
                    class="form-control">
            </div>

            <div class="mb-3">
                <label class="form-label">Libro</label>

                <select name="id_book" class="form-select">

                    <%
                        List<Book> books =
                                (List<Book>) request.getAttribute("books");

                        if(books != null){
                            for(Book b : books){
                    %>

                    <option value="<%= b.getIdBook()%>">
                        <%= b.getTittle() %>
                    </option>

                    <%
                            }
                        }
                    %>

                </select>
            </div>

            <button type="submit" class="btn btn-primary w-100">
                Registrar Préstamo
            </button>

        </form>

    </div>

</div>

</body>
</html>