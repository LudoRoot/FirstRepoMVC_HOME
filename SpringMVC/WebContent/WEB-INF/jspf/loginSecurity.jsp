<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<title><spring:message code="login.title" /></title>

<link href="<c:url value="/static/css/styles.css" />" rel="stylesheet">
</head>
<body>
	<h2>
		<spring:message code="login.title" />
		<br>
		
		<c:if test="${param.error}">
		<h3> <spring:message code="login.unknown" /> </h3>
		</c:if>
		
		<c:if test="${param.logout}">
		<h3><spring:message code="logout.executed" /></h3>
		</c:if>
		
	</h2>
	<form method="POST" action="<c:url value="/login"/>">     
								<!-- 	ici il faut mettre la lien vers /login du securitycontroller, en renseignant le path depuis la racine, comme vu par l'utilisateur distant!! -->
		 * Login:<br> <input type="text" name="username" value="xxx">
		 <br>
		 * Mot de Passe:<br> <input type="password" name="password" value="xxx">
		 <br>
		
		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<!-- ce champ caché est requis par SPRING Security -->
				
		<br> <input type="submit" value="Submit">
	</form>
	<h3>
		<spring:message code="messagechampOblig" />
	</h3>
	
</body>
</html>