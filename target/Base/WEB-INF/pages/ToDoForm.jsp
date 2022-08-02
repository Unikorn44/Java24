<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create ToDo</title>
</head>
<body>
	
	<fieldset>
		<form method="GET" action="<c:url value="/todo/create" />" >
			<input type="text" name="tache">
			<input type="text" name="description">
			<input type="submit" value="creer" >
		</form>
	</fieldset>
	
	
</body>
</html>