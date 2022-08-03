<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page User Log</title>
</head>
<body>
	<form method="POST" action="<c:url value="/userlog"/>">
		<input type="submit" value="Créer">
	</form>
</body>
</html>