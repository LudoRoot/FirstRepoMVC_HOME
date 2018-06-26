<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
<title><spring:message code="create.modif" /></title>
<%-- <title><c:out value="${pageTitle}" /></title> --%>

<link href="<c:url value="/static/css/styles.css" />" rel="stylesheet">
</head>
<body>



	
	</h2>
	<form method="POST" action="ModifOK" modelAttribute="utilisateur">
		<!-- 	LUDO :on spécifie ici qu'on recoit l'attribut utilisateur de l'atape précédente. les informations qui sont rentrées -->
		<!-- 	dans le formulaire sont stockées dans l'attribut "utilisateur" le temps de passer de la page web au controleur pour la suite. -->

		<form:hidden path="utilisateur.id"/>
			<!-- 		LUDO: form hidden permet de récuperer l'ID du l'utilisateur concerné sans le monter sur le formuleaire.  -->
			<!--        cette information est alors transmise au controleur derrière lui qui va pouvoir sauver ou updater selon la valeur de l'ID.  -->

		<form:label path="utilisateur.civ">Civilité</form:label>
		<form:select path="utilisateur.civ.id">
			<form:options items="${listecivil}" itemValue="id"
				itemLabel="abbreviation" />
		</form:select>
		<br>
			<!-- 		LUDO: liste déroulante-- itemSSSS(signale au pluriel qu'on va jouer sur tous les elements d'une collection transmise) = (pour le model attribute transmis depuis le controller), -->
			<!-- 		itemValue (le choix sera fait et la valeur qui sera retournée sera l'attribut "id" ici) -->
			<!-- 		itemLabel (renseigne quel champ de l'objet sera affiché dans la liste déroulante (affichage seult)) -->


		<form:label path="utilisateur.prenom">* Prenom</form:label>
		<form:input path="utilisateur.prenom" />
		<form:errors path="utilisateur.prenom" />
		
		<br>
		<form:label path="utilisateur.nom">* Nom</form:label>
		<form:input path="utilisateur.nom" />
		<form:errors path="utilisateur.nom" />


		<br>
		<form:label path="utilisateur.email">Email</form:label>
		<form:input path="utilisateur.email" />
		
		<br>
		<form:label path="utilisateur.login">* Login</form:label>
		<form:input path="utilisateur.login" />
		<form:errors path="utilisateur.login" />
		
		<br>
		<form:label path="utilisateur.mdp">* Mot de Passe</form:label>
		<form:password path="utilisateur.mdp"  />
		<form:errors path="utilisateur.mdp" />
				
		<br>
		<form:label path="utilisateur.telfix">Telephone fixe</form:label>
		<form:input path="utilisateur.telfix" />
		<form:errors path="utilisateur.telfix" />
		
		<br>
		<form:label path="utilisateur.telmob">Telephone mobile</form:label>
		<form:input path="utilisateur.telmob" />
		<form:errors path="utilisateur.telmob" />
		
		
		<br> <br> <input type="submit" value="Enregistrer!" />
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<!-- ce champ caché est requis par SPRING Security !!! attention a le placer à l'intérieur du formulaire-->     
		
	</form>
	<h3>
		<spring:message code="messagechampOblig" />
	</h3>
	<p><a href="/SpringMVC/usercontroller/Start">Home</a></p>
	
	 		 
	
</body>
</html>