<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
}

</style>
<title><c:out value="${pageTitle}" /></title>
<link href="<c:url value="/static/css/styles.css" />" rel="stylesheet">
</head>
<body>
<c:out value="${message}" />
	<table style="width:100%">
	<tr>
		
		<th> <spring:message code="form.Nom"/> </th>
		<th> <spring:message code="form.Prenom"/> </th>
		<th> <spring:message code="form.Email"/> </th>
		<th> <spring:message code="form.Telfix"/> </th>
		<th> <spring:message code="form.Telmob"/> </th>
		<th> <spring:message code="form.Admin"/> </th>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<th> <spring:message code="form.Admin"/> </th>
		</sec:authorize>

		
	</tr>
	<c:forEach items="${listeUser}" var="user" varStatus="status">
		<tr>
			<td>${user.nom}</td>
			<td>${user.prenom}</td>
			<td>${user.email}</td>
			<td>${user.telfix}</td>
			<td>${user.telmob}</td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td><a href="<c:url value="/usercontroller/DoModifier?iduser=${user.id}"/>">Modifier</a>
				<td><a href="<c:url value="/usercontroller/DoDelete?iduser=${user.id}"/>">Supprimer</a>
			</sec:authorize>
			
			
	  </tr>
	</c:forEach>
</table>	


<p><a href="/SpringMVC/usercontroller/Start">Home</a></p>	
	
</body>
</html>