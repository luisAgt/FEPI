<%-- 
    Document   : LoanMaterial
    Created on : 20 may 2026, 5:13:30 p.m.
    Author     : XPxTBxLLX
--%>

<%@page import="com.equipo1.entities.Lab_material"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
    <title>LABORATORIO</title>
</head>
<body>

<div class="container mt-4">

    <div class="admin-header">
        <h1>Préstamo de Material de Laboratorio</h1>
    </div>

    <div class="upload-card">

        <form action="SvLoanMaterial" method="POST">

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
                    name="date_loan"
                    class="form-control"
                    readonly>
            </div>

            <div class="mb-3">
                <label class="form-label">Fecha de devolución</label>
                <input
                    type="date"
                    name="date_return"
                    class="form-control">
            </div>

            <div class="mb-3">

                <label class="form-label">
                    Material de Laboratorio
                </label>

                <select name="id_material" class="form-select">

                    <%
                        List<Lab_material> materials =
                                (List<Lab_material>) request.getAttribute("materials");

                        if(materials != null){
                            for(Lab_material m : materials){
                    %>

                    <option value="<%= m.getIdLabMaterial()%>">
                        <%= m.getMaterialName() %>
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
