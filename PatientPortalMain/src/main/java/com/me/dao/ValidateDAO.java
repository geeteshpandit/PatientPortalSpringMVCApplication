package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.LoginException;
import com.me.pojo.Patient;
import com.me.pojo.UserAccount;



public class ValidateDAO extends DAO{
	
	public ValidateDAO()
	{
		
	}

	public UserAccount validateUser(String userName,String password)
            throws LoginException {
        try {
            begin();
            System.out.println(userName);
            System.out.println(password);
            Query q = getSession().createQuery("from UserAccount where userName = :username and password = :password");
            q.setString("username", userName);
            q.setString("password", password);
            UserAccount user = (UserAccount) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new LoginException("Could not get user " + userName, e);
        } finally
        {
        	close();
        }
    }
	
	public Boolean isUserNameUsed(String userName)
            throws LoginException {
        try {
            begin();
            System.out.println(userName);
            Query q = getSession().createQuery("from UserAccount where userName = :username ");
            q.setString("username", userName);
            UserAccount user = (UserAccount) q.uniqueResult();
            commit();
            
            if (user!=null)
            {
            	System.out.println("true");
            	return true;
            }
            else
            {
            	System.out.println("false");
            	return false;
            }
        } catch (HibernateException e) {
        	System.out.println("false");
            rollback();
            return false;
        }finally
        {
        	
        }
        
        
    }
	
	public void insertPatient(Patient p) {
        try {
            begin();
            getSession().save(p);
            commit();
            
        } catch (HibernateException e) {
            rollback();
        } finally
        {
        	close();
        }
    }
}
