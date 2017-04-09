<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome to Patient Portal</title>
</head>
<body>
	<form action="validateUser.htm" method="post">
		<h1>
			Welcome to Patient Portal  
		</h1>
		
		<div id="loginframe">
			<h2>Please Login </h2>
			User Name:<input type="text" name="userName" required>
			<br/>
			<br/>
			Password:<input type="text" name="password" required>
			<br/>
			<br/>
			<input type='submit' name='submit'>
			<br/>
			<br/>
			<h2>${error}</h2>
			<br/>
			<a href="registerPatient.htm" >Register as Patient</a>
		</div>
	</form>
</body>
</html>
