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
    <title>LABORATORIO</title>
</head>
<body>
    <form action="SvLoanMaterial" method="POST">
        <div>
            <input type="text" name="id_user" id="qrInput" placeholder="ESCANEA TU CREDENCIAL" readonly autofocus><br><br>
            <input type="text"  placeholder="Nombre Completo" readonly autofocus><br><br>
            <input type="text" placeholder="Boleta" readonly autofocus id="qrInput"><br><br>
        </div>
        <div>
            <input type="text" name="status" placeholder="Estatus" ><br><br>
            <input type="text" name="date_loan" placeholder="Fecha de emision" readonly><br><br>
            <input type="date" name="date_return" placeholder="Fecha de devolución"><br><br>
            <select name = "id_material" class="form-select">
               <%
                     List<Lab_material> materials = (List<Lab_material>) request.getAttribute("materials");
                    if(materials != null ){
                    for (Lab_material m : materials){
                        System.out.println(m.getMaterialName());
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
        <div>
            <input type ="submit" value="Enviar">
        </div>
    </form>
</body>
</html>
