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
    <title>Mis Asistencias</title>
</head>
<body>
    <h2>Mis Asistencias</h2>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty attendances}">
            <p>No hay asistencias registradas.</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="6">
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
                        <td>${att.idEnrollment.idSchedule.idGroup.AGroup}</td>
                        <td>${att.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <br><a href="Student.jsp">Volver</a>
</body>
</html>