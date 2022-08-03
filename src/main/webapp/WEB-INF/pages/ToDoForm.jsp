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
		<form method="POST" action="<c:url value="/todo" />" >
			<input type="text" name="tache">
			<input type="text" name="description">
			<input type="submit" value="creer" >
		</form>
	</fieldset>
	
	<div id="list">
		
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