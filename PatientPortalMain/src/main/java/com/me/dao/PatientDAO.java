package com.me.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.LoginException;
import com.me.pojo.Doctor;
import com.me.pojo.Patient;
import com.me.pojo.UserAccount;

public class PatientDAO extends DAO {
	
	public void updatePatient(Patient p)  {
        try {
            begin();
            getSession().update(p);
//            Query q = getSession().createQuery("from Doctor where facility = :facilityID ");
//            q.setInteger("facilityID", facId);
//            doctorList = (ArrayList<Doctor>) q.list();
//            System.out.println("Size:" + doctorList.size());
            commit();

        } catch (HibernateException e) {
            rollback();
        }
    }
	
	public Patient fetchPatient(int pid)  {
		Patient p = null;
		 try {
			 	
	            begin();
	            Query q = getSession().createQuery("from Patient where patientID = :patntID ");
	            q.setInteger("patntID", pid);
	            
	            p = (Patient) q.uniqueResult();
	            System.out.println(p);
	            commit();
	            return p;
	        } catch (HibernateException e) {
	            rollback();
	            return null;
	        } finally
	        {
	        	close();
	        }
    }

}
