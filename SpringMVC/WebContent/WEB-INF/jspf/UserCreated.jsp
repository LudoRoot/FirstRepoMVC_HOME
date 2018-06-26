<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>"Utilisateur Créé / Modifié"</title>
<link href="<c:url value="/static/css/styles.css" />" rel="stylesheet">
</head>
<body>



	<h2>
		<c:out value="${message1}" />
		<br>
		<c:out value="${message2}" />
	</h2>
	
<p><a href="/SpringMVC/usercontroller/Start">Home</a></p>
	

	
</body>
</html>