<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Register as Patient</title>
</head>
<body>
	<spring:form id="regForm" action="registerPatient.htm" method="POST" commandName="patient">
         First Name : <spring:input path="firstName"/>
         <spring:errors cssstyle = "color:red" path="firstName" /><br><br>

         Last Name : <spring:input path="lastName"/>
		 <spring:errors cssstyle = "color:red" path="lastName" /><br><br>
         Gender:
         <spring:radiobutton path="gender" value="M" checked="true"/>Male
         <spring:radiobutton path="gender" value="F"/>Female
         <spring:errors cssstyle = "color:red" path="gender" />
         <br><br>
         
         Age : <spring:input path="age" type="text" Maxlength="3"/>
         <spring:errors cssstyle = "color:red" path="age" /><br><br>
         
         Blood Group : <spring:input path="bloodGroup" type="text"/>
         <spring:errors cssstyle = "color:red" path="bloodGroup" /><br><br>
         
         Known Allergies: <spring:textarea path="knownAllergies"></spring:textarea> 
		 <spring:errors cssstyle = "color:red" path="knownAllergies" /><br><br>
		 
         Email : <spring:input path="emailID" type="text"/>
         <spring:errors cssstyle = "color:red" path="emailID" /><br><br>
         
         Phone Number : <spring:input path="phone" type="text" Maxlength="10"/>
         <spring:errors cssstyle = "color:red" path="phone" /><br><br>

         Address: <spring:textarea path="address"></spring:textarea> 
         <spring:errors cssstyle = "color:red" path="address" /><br><br>
         
         UserName : <spring:input path="userAccount.userName" type="text"/>
         <spring:errors cssstyle = "color:red" path="userAccount.userName" /><br><br>
         
         Password : <spring:input path="userAccount.password" type="text"/>
         <spring:errors cssstyle = "color:red" path="userAccount.password" /><br><br>

         <input type="submit" name="Register Patient">

     </spring:form>
</body>
</html>