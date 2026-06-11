<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Libro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Registrar Nuevo Libro</h2>
    
    <form action="SvRegisterBook" method="POST">
        <div class="mb-3">
            <label>Título del Libro:</label>
            <input type="text" name="tittle" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Editorial:</label>
            <input type="text" name="editorial" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Edición (Número):</label>
            <input type="number" name="edition" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Autor:</label>
            <input type="text" name="author" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Stock (Cantidad disponible):</label>
            <input type="number" name="stock" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Estatus:</label>
            <select name="status" class="form-select" required>
                <option value="DISPONIBLE">Disponible</option>
                <option value="AGOTADO">Agotado</option>
                <option value="MANTENIMIENTO">En Mantenimiento</option>
            </select>
        </div>
        
        <button type="submit" class="btn btn-success">Guardar Libro</button>
        <a href="Admin.jsp" class="btn btn-danger">Cancelar</a>
    </form>
</body>
</html>