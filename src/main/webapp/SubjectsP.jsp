<%-- 
    Document   : SubjectsP
    Created on : 10 jun 2026, 3:00:01 p.m.
    Author     : XPxTBxLLX
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mis Materias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" 
          rel="stylesheet">
</head>
<body class="p-4">
    <h2>Mis Materias Asignadas</h2>

    <% String mensaje = (String) request.getAttribute("mensaje");
       String tipoMensaje = (String) request.getAttribute("tipoMensaje");
       if (mensaje != null) { %>
        <div style="padding:10px; margin:10px 0; border-radius:5px;
             background-color: <%= "success".equals(tipoMensaje) ? "#d4edda" : "#f8d7da" %>;
             color: <%= "success".equals(tipoMensaje) ? "#155724" : "#721c24" %>;">
            <%= mensaje %>
        </div>
    <% } %>

    <c:choose>
        <c:when test="${empty schedules}">
            <p>No tienes materias asignadas.</p>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered table-striped">
                <tr>
                    <th>Código</th>
                    <th>Materia</th>
                    <th>Grupo</th>
                    <th>Carrera</th>
                    <th>Semestre</th>
                    <th>Día</th>
                    <th>Inicio</th>
                    <th>Fin</th>
                </tr>
                <c:forEach var="sc" items="${schedules}">
                    <tr>
                        <td>${sc.idSubject.code}</td>
                        <td>${sc.idSubject.name}</td>
                        <td>${sc.idGroup.AGroup}</td>
                        <td>${sc.idGroup.carrer}</td>
                        <td>${sc.idGroup.semester}</td>
                        <td>${sc.idHorary.weekDay}</td>
                        <td>${sc.idHorary.startTime}</td>
                        <td>${sc.idHorary.endTime}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <br><a href="Professor.jsp" class="btn btn-secondary">Volver</a>
</body>
</html>
