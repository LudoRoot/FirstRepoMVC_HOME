<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><c:out value="${pageTitle}" /></title>
<link href="<c:url value="/static/css/styles.css" />" rel="stylesheet">
</head>
<body>
	<h2>
		<c:out value="${message}" />
	</h2>

	<form action="DoDelete?"iduser>
		<c:out value="${messageId}" />
		<br> 
		<input type="text" name="iduser"	value="xxx"> 
		<br> <input type="submit" value="Effacer" />
		
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<!-- ce champ caché est requis par SPRING Security !!! attention a le placer à l'intérieur du formulaire-->     
	</form>
<p><a href="/SpringMVC/usercontroller/Start">Home</a></p>
</body>
</html>