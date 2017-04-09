package com.me.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.me.dao.DAO;
import com.me.dao.ValidateDAO;
import com.me.pojo.Patient;
import com.me.pojo.UserAccount;
import com.me.validator.RegistrationValidator;

@Controller
public class RegistrationController {
	@Autowired
	@Qualifier("regValidator")
	private RegistrationValidator registrationValidator;
   
	@Autowired
	@Qualifier("validateDAO")
	ValidateDAO validateDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(registrationValidator);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/registerPatient.htm")
	public String getRegistrationPage(@ModelAttribute("patient")Patient patient){
		return "Registration";
	}
	@RequestMapping(method=RequestMethod.POST,value="/registerPatient.htm")
    protected String registerPatient(@ModelAttribute("patient")Patient patient,BindingResult result) throws Exception{

		registrationValidator.validate(patient, result);
		if(result.hasErrors())
		{
			return "Registration";
		}

		UserAccount ua = patient.getUserAccount();
		ua.setPatient(patient);
		ua.setRole("patient");
		validateDAO.insertPatient(patient);
		return "home";
    }


}
