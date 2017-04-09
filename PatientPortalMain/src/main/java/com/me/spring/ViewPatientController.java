package com.me.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.PatientDAO;
import com.me.dao.ValidateDAO;
import com.me.pojo.Appointment;
import com.me.pojo.Patient;
import com.me.pojo.Session;
import com.me.pojo.UserAccount;
import com.me.validator.RegistrationValidator;

@Controller
public class ViewPatientController {
	
	@Autowired
	@Qualifier("regValidator")
	private RegistrationValidator registrationValidator;
   
	@Autowired
	@Qualifier("patientDAO")
	PatientDAO patientDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(registrationValidator);
	}
	
	
	@RequestMapping(value = "/viewPatient.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
           	
           	HttpSession sess = hsr.getSession();
           	Patient patnt = (Patient) sess.getAttribute("patient");
           	ModelAndView mv = new ModelAndView("editPatient","patient",patnt);
            hsr.setAttribute("patient",patnt);
           	return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/updatePatient.htm")
    protected String registerPatient(@ModelAttribute("patient")Patient patient,BindingResult result) throws Exception{

		registrationValidator.validate(patient, result);
		if(result.hasErrors())
		{
			System.out.println("Had error");
			return "Registration";
		}
		
		
		patientDAO.updatePatient(patient);
		
		return "home";
    }

}
