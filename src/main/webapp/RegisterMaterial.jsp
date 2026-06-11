<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Material</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2>Registrar Nuevo Material de Laboratorio</h2>
    
    <form action="SvRegisterMaterial" method="POST">
        <div class="mb-3">
            <label>Nombre del Material:</label>
            <input type="text" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Stock Inicial:</label>
            <input type="number" name="stock" class="form-control" required>
        </div>
        
        <button type="submit" class="btn btn-success">Guardar Material</button>
        <a href="Admin.jsp" class="btn btn-danger">Cancelar</a>
    </form>
</body>
</html>