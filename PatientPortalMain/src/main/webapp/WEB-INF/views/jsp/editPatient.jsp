<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edit Patient</title>
</head>
<body>
	
	<spring:form id="regForm" action="updatePatient.htm" method="POST" commandName="patient">
		<div id="logout" align = "right"><a href="logout.htm" margin-left = "auto" margin-right = "0";>Logout</a></div>
         First Name : <spring:input path="firstName" value="${patient.firstName}" />
         <spring:errors cssstyle = "color:red" path="firstName" /><br><br>

         Last Name : <spring:input path="lastName" value="${patient.lastName}"/>
		 <spring:errors cssstyle = "color:red" path="lastName" /><br><br>
         Gender:
         <spring:radiobutton path="gender" value="M" checked="true"/>Male
         <spring:radiobutton path="gender" value="F"/>Female
         <spring:errors cssstyle = "color:red" path="gender" />
         <br><br>
         
         Age : <spring:input path="age" type="text" Maxlength="3" value="${patient.age}"/>
         <spring:errors cssstyle = "color:red" path="age" /><br><br>
         
         Blood Group : <spring:input path="bloodGroup" type="text"/>
         <spring:errors cssstyle = "color:red" path="bloodGroup" value="${patient.bloodGroup}"/><br><br>
         
         Known Allergies: <spring:textarea path="knownAllergies"></spring:textarea> 
		 <spring:errors cssstyle = "color:red" path="knownAllergies" value="${patient.knownAllergies}" /><br><br>
		 
         Email : <spring:input path="emailID" type="text"/>
         <spring:errors cssstyle = "color:red" path="emailID" value="${patient.emailID}" /><br><br>
         
         Phone Number : <spring:input path="phone" type="text" Maxlength="10"/>
         <spring:errors cssstyle = "color:red" path="phone" value="${patient.phone}" /><br><br>

         Address: <spring:textarea path="address"></spring:textarea> 
         <spring:errors cssstyle = "color:red" path="address" value="${patient.address}"/><br><br>
         
         UserName : <spring:input path="userAccount.userName" type="text"/>
         <spring:errors cssstyle = "color:red" path="userAccount.userName" value="${patient.userAccount.userName}"/><br><br>
         
         Password : <spring:input path="userAccount.password" type="text"/>
         <spring:errors cssstyle = "color:red" path="userAccount.password" value="${patient.userAccount.password}"/><br><br>

         <input type="submit" name="Register Patient">

     </spring:form>
</body>
</html>