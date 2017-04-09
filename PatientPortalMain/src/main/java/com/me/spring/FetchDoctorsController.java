package com.me.spring;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.ClinicsDAO;
import com.me.dao.DoctorDAO;
import com.me.dao.PatientDAO;
import com.me.pojo.Doctor;
import com.me.pojo.Facility;
import com.me.pojo.Patient;
import com.me.pojo.Session;

import antlr.collections.List;

@Controller

public class FetchDoctorsController {
	
	@Autowired
	@Qualifier("doctorDAO")
	DoctorDAO doctorDAO;
	
	@Autowired
	@Qualifier("patientDAO")
	PatientDAO patientDAO;
	
	
	
	@RequestMapping(value = "/fetchDoctor.htm", method = RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        
           ArrayList<Doctor> doctorList ;

            int fac =  Integer.parseInt(hsr.getParameter("key"));
            doctorList = doctorDAO.fetchDoctors(fac);
            
            JSONArray jsonArray = new JSONArray();
            for (int i=0; i < doctorList.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("fn", doctorList.get(i).getFirstName());
                obj.put("ln", doctorList.get(i).getLastName());
                obj.put("sp", doctorList.get(i).getSpeciality());
                obj.put("qa", doctorList.get(i).getQualification());
                obj.put("id", doctorList.get(i).getDoctorID());
                jsonArray.put(obj);
            }
    
            JSONObject Obj = new JSONObject();
            Obj.put("doctor", jsonArray);
            
            
            PrintWriter out = hsr1.getWriter();
            out.println(Obj);
        

        return null;
	}
	
	@RequestMapping(value = "/fetchSession.htm", method = RequestMethod.POST)
	public ModelAndView handleRequest1(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
    
           ArrayList<Session> sessionList ;

            int doc =  Integer.parseInt(hsr.getParameter("key"));
            System.out.println("DoctorID :" + doc);
            sessionList = doctorDAO.fetchSessions(doc);
            JSONArray jsonArray = new JSONArray();
            for (int i=0; i < sessionList.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("sd", sessionList.get(i).getSessionDate());
                obj.put("sl", sessionList.get(i).getSlot());
                obj.put("dw", sessionList.get(i).getDayOfWeek());
                obj.put("fn", sessionList.get(i).getDoctor().getFirstName());
                obj.put("id", sessionList.get(i).getSessionID());
                jsonArray.put(obj);
            }
    
            JSONObject Obj = new JSONObject();
            Obj.put("session", jsonArray);
            
            
            PrintWriter out = hsr1.getWriter();
            out.println(Obj);
        

        return null;
	}
	
	
	@RequestMapping(value = "/displayPatient.htm", method = RequestMethod.POST)
	public ModelAndView handleRequest2(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
    
            int pid =  Integer.parseInt(hsr.getParameter("key"));
            Patient p = patientDAO.fetchPatient(pid);

            JSONObject obj = new JSONObject();
            obj.put("fn", p.getFirstName());
            obj.put("ln", p.getLastName());
            obj.put("ag", p.getAge());
            obj.put("bg", p.getBloodGroup());
            obj.put("ka", p.getKnownAllergies());
            obj.put("em", p.getEmailID());
    
            JSONObject Obj = new JSONObject();
            Obj.put("patient", obj);
            PrintWriter out = hsr1.getWriter();
            out.println(obj);
            
        

        return null;
	}
	
}
