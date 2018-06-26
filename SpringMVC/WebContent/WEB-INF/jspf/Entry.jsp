<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<img alt="The Alfer" src="<c:url value="/static/images/ALF.jpeg" />" />
<h2>Choose action below</h2>
<br>
<br>
<p><a href="<c:url value="/usercontroller/goToCreer"/>">Creation d'un nouvel utilisateur</a></p>

<sec:authorize access="hasRole('ROLE_USER')">
	<p><a href="<c:url value="/usercontroller/goToModifier"/>">Modifier un utilisateur</a></p>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p><a href="<c:url value="/usercontroller/goToList"/>">Lister des utilisateurs</a></p>
		<p><a href="<c:url value="/usercontroller/goToDelete"/>">Supprimer un utilisateur</a></p>
	</sec:authorize>
</sec:authorize>
       <!-- Ici on définit si la ligne peut etre affichée ou non selon le role de l'utilisateur -->

<p><a href="<c:url value="/logout"/>">Log out</a></p>

<p><sec:authentication property="principal.authorities"/></p>
 
  

</body>
</html>