package com.me.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Appointment;
import com.me.pojo.Doctor;
import com.me.pojo.Facility;
import com.me.pojo.Patient;
import com.me.pojo.Session;
import com.me.pojo.UserAccount;



public class DoctorDAO extends DAO {
	
	public ArrayList<Doctor> fetchDoctors(int facId)  {
        try {
        	ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
            begin();
            Query q = getSession().createQuery("from Doctor where facility = :facilityID ");
            q.setInteger("facilityID", facId);
            doctorList = (ArrayList<Doctor>) q.list();
            System.out.println("Size:" + doctorList.size());
            commit();
            return doctorList;
        } catch (HibernateException e) {
            rollback();
        }
		return null;
    }
	
	public ArrayList<Session> fetchSessions(int docId)  {
		
		try {
        	ArrayList<Session> sessionList = new ArrayList<Session>();
            begin();
            Query q = getSession().createQuery("from Session where doctor = :docID ");
            q.setInteger("docID", docId);
            sessionList = (ArrayList<Session>) q.list();
            System.out.println(sessionList.size());
            commit();
            return sessionList;
        } catch (HibernateException e) {
            rollback();
        }
		return null;
	}
	
public List<Appointment> fetchAppointment(int docId)  {
		
		try {
        	List<Appointment> apptList = new ArrayList<Appointment>();
        	List<Appointment> aList = new ArrayList<Appointment>();
            begin();
            Query q = getSession().createQuery("from Appointment a inner join a.session as s where doctor = :docID ");
            q.setInteger("docID", docId);
            aList = (List<Appointment>) q.list();
            
            Iterator itr = aList.iterator();
            int count = 0;
            while(itr.hasNext()){
            		
            	   Object[] obj = (Object[]) itr.next();
            	   Appointment a = (Appointment)obj[count];
            	   apptList.add(a);
            	   System.out.println("added success::" + a.getAppointmentID());
            	   count++;
            	   
            	   
            	   //now you have one array of Object for each row
//            	   String client = String.valueOf(obj[0]); // don't know the type of column CLIENT assuming String 
//            	   Integer service = Integer.parseInt(String.valueOf(obj[1])); //SERVICE assumed as int
            	   //same way for all obj[2], obj[3], obj[4]
        	}
            
            commit();
            return apptList;
        } catch (HibernateException e) {
            rollback();
        }
		return null;
	}
	
public Session fetchSingleSession(int sessId)  {
		
		try {
            begin();
            Query q = getSession().createQuery("from Session where sessionID = :sessID ");
            q.setInteger("sessID", sessId);
            Session session = (Session) q.uniqueResult();
            commit();
            return session;
        } catch (HibernateException e) {
            rollback();
        }
		return null;
	}

public void createAppointment(Appointment a) {
	// TODO Auto-generated method stub
	try {
        begin();
        getSession().save(a);
        commit();
        
    } catch (HibernateException e) {
        rollback();
    } finally
    {
    	close();
    }
}

public void updateAppointment(int id){
    try{
        begin();

         Query q = getSession().createQuery("Update Appointment set status = :status, "
                 + " outcome = :outcome "
                 + "WHERE appointmentID = :id");

         q.setInteger("id", id);
         q.setString("status", "Seen");
         q.setString("outcome", "Adviced");
         q.executeUpdate();
         System.out.println("Update Possible");
         commit();
    }
    catch (HibernateException e) {
          rollback();
      }
}

}
