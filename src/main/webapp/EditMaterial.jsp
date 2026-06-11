<%@page import="com.equipo1.entities.LabMaterial"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Material</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Modificar Material de Laboratorio</h2>
    
    <form action="SvEditMaterial" method="GET" class="mb-4">
        <label>Ingrese ID del Material a buscar:</label>
        <div class="input-group">
            <input type="number" name="id_material_search" class="form-control" required>
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
    </form>

    <hr>

    <%
        LabMaterial matToEdit = (LabMaterial) request.getAttribute("matToEdit");
        if (matToEdit != null) {
    %>
        <form action="SvEditMaterial" method="POST">
            <div class="mb-3">
                <label>ID Material:</label>
                <input type="number" name="id_material" value="<%= matToEdit.getIdLabMaterial() %>" class="form-control" readonly>
            </div>
            <div class="mb-3">
                <label>Nombre del Material:</label>
                <input type="text" name="name" value="<%= matToEdit.getName() %>" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Stock (Inventario):</label>
                <input type="number" name="stock" value="<%= matToEdit.getStock() %>" class="form-control" required>
            </div>
            
            <button type="submit" class="btn btn-warning">Actualizar Material</button>
            <a href="Admin.jsp" class="btn btn-danger">Cancelar</a>
        </form>
    <% } %>
</body>
</html>