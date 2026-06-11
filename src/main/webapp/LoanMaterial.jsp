<%-- 
    Document   : LoanMaterial
    Created on : 20 may 2026, 5:13:30 p.m.
    Author     : XPxTBxLLX
--%>

<%@page import="com.equipo1.entities.LabMaterial"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>LABORATORIO</title>
</head>
<body>
    <form action="SvLoanMaterial" method="POST" class="form">
        <input type="text" name="boleta" id="boleta" placeholder="ESCANEA TU CREDENCIAL" required><br><br>
        <input type="text" placeholder="Nombre Completo" readonly id="fullname" name="fullname"><br><br>
        <input type="text" placeholder="Correo electronico" readonly id="email" name="email"><br><br>
        <input type="text" name="status" placeholder="Estatus" value="Activo" required><br><br>

        <label>Fecha de devolución:</label>
        <input type="date" name="return_date" placeholder="Fecha de devolución"><br><br>

        <select name="id_lab_material" class="form-select">
            <%
                List<LabMaterial> materials = (List<LabMaterial>) request.getAttribute("materials");
                if (materials != null) {
                    for (LabMaterial m : materials) {
            %>
            <option value="<%= m.getIdLabMaterial()%>"><%= m.getMaterialName()%></option>
            <%  } } %>
        </select>

        <button type="submit" class="btn btn-primary">Enviar Préstamo</button>
    </form>
</body>
</html>
