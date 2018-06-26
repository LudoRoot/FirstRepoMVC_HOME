<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>"Utilisateur Effacé"</title>
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