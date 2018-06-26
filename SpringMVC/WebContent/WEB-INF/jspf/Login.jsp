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
	</h2>
	<form method="POST" action="checklogin">
		 * Login:<br> <input type="text" name="login" value="xxx">
		 <br>
		 * Mot de Passe:<br> <input type="password" name="mdp" value="xxx">
		 <br>
		<br> <input type="submit" value="Submit">
	</form>
	<h3>
		<spring:message code="messagechampOblig" />
	</h3>
	<p><a href="/SpringMVC/usercontroller/Start">Home</a></p>
</body>
</html>