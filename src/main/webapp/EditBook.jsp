<%@page import="com.equipo1.entities.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Libro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Modificar Libro Existente</h2>
    
    <form action="SvEditBook" method="GET" class="mb-4">
        <label>Ingrese ID del Libro a buscar:</label>
        <div class="input-group">
            <input type="number" name="id_book_search" class="form-control" required>
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
    </form>

    <hr>

    <%
        Book bookToEdit = (Book) request.getAttribute("bookToEdit");
        if (bookToEdit != null) {
    %>
        <form action="SvEditBook" method="POST">
            <div class="mb-3">
                <label>ID Libro:</label>
                <input type="number" name="id_book" value="<%= bookToEdit.getIdBook() %>" class="form-control" readonly>
            </div>
            <div class="mb-3">
                <label>Título:</label>
                <input type="text" name="tittle" value="<%= bookToEdit.getTittle() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Editorial:</label>
                <input type="text" name="editorial" value="<%= bookToEdit.getEditorial() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Edición:</label>
                <input type="number" name="edition" value="<%= bookToEdit.getEdition() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Autor:</label>
                <input type="text" name="author" value="<%= bookToEdit.getAuthor() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Stock (Inventario):</label>
                <input type="number" name="stock" value="<%= bookToEdit.getStock() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Estatus (Actual: <b><%= bookToEdit.getStatus() %></b>):</label>
                <select name="status" class="form-select" required>
                    <option value="<%= bookToEdit.getStatus() %>">-- Mantener estatus actual --</option>
                    <option value="DISPONIBLE">Disponible</option>
                    <option value="AGOTADO">Agotado</option>
                    <option value="MANTENIMIENTO">En Mantenimiento</option>
                </select>
            </div>
            
            <button type="submit" class="btn btn-warning">Actualizar Libro</button>
            <a href="Admin.jsp" class="btn btn-danger">Cancelar</a>
        </form>
    <% } %>
</body>
</html>