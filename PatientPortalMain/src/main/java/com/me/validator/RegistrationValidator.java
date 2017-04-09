package com.me.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.dao.ValidateDAO;
import com.me.exception.LoginException;
import com.me.pojo.Patient;



public class RegistrationValidator implements Validator {
	
	
	String input_pattern = "^[^<>'\"/;`%]*$";
	String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	@Override
	public boolean supports(Class clazz) {
		
		return Patient.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
	   Pattern pattern = Pattern.compile(input_pattern);
	   Pattern emailPattern = Pattern.compile(email_pattern);
	   Matcher matcher;
	   
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailID","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bloodGroup","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "knownAllergies","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAccount.userName","Test", "Field cannot be empty");
	   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAccount.password","Test", "Field cannot be empty");
	   
	   Patient patient = (Patient) object;
	   
       if (patient.getPhone().length() != 10) {
           errors.rejectValue("phone", "phone.invalid", new Object[] {patient.getPhone()}, 
                   "Phone should be 10 character long.");
       }
       
       matcher = emailPattern.matcher(patient.getEmailID());
       if (!matcher.find()) {
           errors.rejectValue("emailID", "emailID.invalid", new Object[] {patient.getEmailID()}, 
                   "Email ID is not valid.");
       }
       
       if (patient.getUserAccount().getPassword().length() < 4) {
           errors.rejectValue("userAccount.password", "userAccount.password.invalid", new Object[] {patient.getUserAccount().getPassword()}, 
                   "Password should be atleast 4 characters long.");
       }
       
       
       matcher = pattern.matcher(patient.getFirstName());
       if (!matcher.find()){
    	   errors.rejectValue("firstName", "firstName.invalid", new Object[] {patient.getFirstName()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getLastName());
       if(!matcher.find()){
    	   errors.rejectValue("lastName", "lastName.invalid", new Object[] {patient.getLastName()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(String.valueOf(patient.getAge()));
       if(!matcher.find()){
    	   errors.rejectValue("age", "age.invalid", new Object[] {patient.getAge()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getEmailID());
       if(!matcher.find()){
    	   errors.rejectValue("emailID", "emailID.invalid", new Object[] {patient.getEmailID()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getPhone());
       if(!matcher.find()){
    	   errors.rejectValue("phone", "phone.invalid", new Object[] {patient.getPhone()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getAddress());
      
       if(!matcher.find()){
    	   errors.rejectValue("address", "address.invalid", new Object[] {patient.getAddress()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getBloodGroup());
       if(!matcher.find()){
    	   errors.rejectValue("bloodGroup", "bloodGroup.invalid", new Object[] {patient.getBloodGroup()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getKnownAllergies());
       if(!matcher.find()){
    	   errors.rejectValue("knownAllergies", "knownAllergies.invalid", new Object[] {patient.getKnownAllergies()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getUserAccount().getUserName());
       if(!matcher.find()){
    	   errors.rejectValue("userAccount.userName", "userAccount.userName.invalid", new Object[] {patient.getUserAccount().getUserName()}, 
                   "Special characters are not allowed.");
       }
       matcher = pattern.matcher(patient.getUserAccount().getPassword());
       if(!matcher.find()){
    	   errors.rejectValue("userAccount.password", "userAccount.password.invalid", new Object[] {patient.getUserAccount().getPassword()}, 
                   "Special characters are not allowed.");
       }
       
       
	    try {
	    	
	    	ValidateDAO validateDAO = new ValidateDAO();
	    	System.out.println(errors.hasErrors());
	    	
			if (errors.getFieldErrorCount("userName") == 0 && validateDAO.isUserNameUsed(patient.getUserAccount().getUserName()) == true) 
			{ 
			  errors.reject("patient", "Username already in use."); 
			  errors.rejectValue("userAccount.userName", "username.already.taken", null, "Username already in use."); 
			}
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(errors.hasErrors());
		} 
    	 
	}

}
