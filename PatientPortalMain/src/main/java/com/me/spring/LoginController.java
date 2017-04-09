package com.me.spring;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.ClinicsDAO;
import com.me.dao.DAO;
import com.me.dao.DoctorDAO;
import com.me.dao.ValidateDAO;
import com.me.exception.LoginException;
import com.me.pojo.Appointment;
import com.me.pojo.Facility;
import com.me.pojo.Patient;
import com.me.pojo.Session;
import com.me.pojo.UserAccount;

@Controller

public class LoginController {
	
	@Autowired
	@Qualifier("validateDAO")
	ValidateDAO validateDAO;
//	
//	@Autowired
//	@Qualifier("clinicDAO")
//	ClinicsDAO clinicDAO;
	
	@Autowired
	@Qualifier("doctorDAO")
	DoctorDAO doctorDAO;

	@RequestMapping(method=RequestMethod.POST,value="/validateUser.htm")
	
    protected String validateUser(HttpServletRequest request,HttpServletResponse response ){
		
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		userName = userName.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "+").trim();
		password = password.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "+").trim();
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ClinicsDAO clinicDAO = (ClinicsDAO) context.getBean("clinicDAO");
		HttpSession session = request.getSession(true);
		session.setAttribute("isLoggedIn", "yes");
		UserAccount user;
		try {
			user = (UserAccount)validateDAO.validateUser(userName, password);
			if (user != null)
			{
				if(user.getRole().equalsIgnoreCase("patient"))
				{
					session.setAttribute("patient", user.getPatient());
					ArrayList<Facility> clinicList = (ArrayList<Facility>) clinicDAO.fetchFacilities();
					
					session.setAttribute("cliniclist",clinicList);
					return "welcomePatient";
				}
				else if (user.getRole().equalsIgnoreCase("doctor"))
				{
					session.setAttribute("doctor", user.getDoctor());
					ArrayList<Appointment> appointmentList = (ArrayList<Appointment>) doctorDAO.fetchAppointment(user.getDoctor().getDoctorID());
					session.setAttribute("appointmentlist",appointmentList);
					
					return "welcomeDoctor";
				}
			}
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception: " + e.getMessage());
			
		}
		request.setAttribute("error","UserName or Password is incorrect.");
		return "home";
    }
}
