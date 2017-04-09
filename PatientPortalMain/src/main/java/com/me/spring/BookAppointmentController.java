package com.me.spring;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.DoctorDAO;
import com.me.pojo.Appointment;
import com.me.pojo.Doctor;
import com.me.pojo.Patient;
import com.me.pojo.Session;

@Controller
public class BookAppointmentController {
	
	@Autowired
	@Qualifier("doctorDAO")
	DoctorDAO doctorDAO;
	
	@RequestMapping(value = "/bookAppointment.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
   
           ModelAndView mv = new ModelAndView();

           	int sID = Integer.parseInt( hsr.getParameter("sessionID"));
           	Session s = doctorDAO.fetchSingleSession(sID);
           	Appointment a = new Appointment();
           	a.setAppointmentType("WalkIn");
           	a.setStatus("Booked");
           	a.setSession(s);
           	a.setAppointmentID(s.getSessionID());
           	HttpSession sess = hsr.getSession();
           	Patient patient = (Patient) sess.getAttribute("patient");
           	a.setPatient(patient);
            mv.setViewName("welcomePatient");
           	doctorDAO.createAppointment(a);
            
           	return mv;
	}
	
	@RequestMapping(value = "/updateAppointment.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest1(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
   
           ModelAndView mv = new ModelAndView();

           	int apptID = Integer.parseInt( hsr.getParameter("apptID"));
           	doctorDAO.updateAppointment(apptID);
           	mv.setViewName("welcomeDoctor");
            
           	return mv;
	}
	
	

}
