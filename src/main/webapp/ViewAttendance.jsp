<%-- 
    Document   : ViewAttendance
    Created on : 8 jun 2026, 7:46:05 p.m.
    Author     : XPxTBxLLX
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>Mis Asistencias</title>
</head>
<body>
    <div class="admin-header">
        <h1>Mis Asistencias</h1>
    </div>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty attendances}">
            <p>No hay asistencias registradas.</p>
        </c:when>
        <c:otherwise>
            <table class="table table-striped table-hover custom-table">
                <tr>
                    <th>Fecha</th>
                    <th>Materia</th>
                    <th>Grupo</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="att" items="${attendances}">
                    <tr>
                        <td><fmt:formatDate value="${att.checkDate}" 
                                pattern="dd/MM/yyyy HH:mm"/></td>
                        <td>${att.idEnrollment.idSchedule.idSubject.name}</td>
                        <td>${att.idEnrollment.idSchedule.idGroup.aGroup}</td>
                        <td>${att.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-secondary">
            Volver
        </a>
    </div>
</body>
</html>
