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
		<form method="POST" action="<c:url value="/todo?parametre=creation" />" >
			<input type="text" name="tache">
			<input type="text" name="description">
			<input type="submit" value="creer" >
		</form>
	</fieldset>
	
	<div id="list">
		<c:forEach var="element" items="${ todoListRecup }">
			<form method="POST" action="<c:url value="/todo?parametre=remove&id=${element.id}" />" >
				<input type="submit" value="remove" >
			</form>
			<c:out value="${element.tache}"/>
			<c:out value="${element.description}"/>
			<form method="POST" action="<c:url value="/todo?parametre=update&id=${element.id}&'description'" />" >
				<input type="submit" value="update" >
				<input type="text" name="description">
			</form> 
			<p>--</p>
		</c:forEach>
	</div>
	
	
	
	<!-- <script>
		fetch("/Base/todoRessources")
			 .then((response) => response.json())
			 .then((data)=>{
				 let liste = document.getElementById('list');
					console.table(data);
					console.log(data);
				 for(let i = 0; i < data.length; i++){
					let option = document.createElement('option');
					option.innerHTML = data[i].tache;
					option.setAttribute("value", i);
					liste.appendChild(option);
				} 
		 });
	</script>  -->	
	
	
</body>
</html>