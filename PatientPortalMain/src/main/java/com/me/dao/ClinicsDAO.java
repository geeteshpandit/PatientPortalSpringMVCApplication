package com.me.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.me.pojo.Facility;

@Repository("cDAO")
public class ClinicsDAO extends DAO{

	
	@SuppressWarnings("unchecked")
	@Cacheable(value="facilityFindCache")
	public List fetchFacilities()  {
		
		ArrayList<Facility> clinicList = new ArrayList<Facility>();
        try {
            begin();
        
	            Query q = getSession().createQuery("From Facility");
	            clinicList = (ArrayList<Facility>) q.list();
	            commit();
	            return clinicList;

        } catch (HibernateException e) {
            rollback();
            System.out.println("From Catch Block");
        }
		return null;
    }
}
