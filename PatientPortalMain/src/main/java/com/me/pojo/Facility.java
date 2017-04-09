package com.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="facility")
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "facility")

public class Facility {
	
	@Id 
	@GeneratedValue
	@Column(name="facilityid", unique = true, nullable = false)
	private int facilityID;
	
	@Column(name="facilityname")
	private String facilityName;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="phonenumber")
	private String phoneNumber;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="facility")
	private Set<Doctor> doctors = new HashSet<Doctor>();
	
	
	Facility(){}
	
	
	public Set<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}

	public int getFacilityID() {
		return facilityID;
	}
	public void setFacilityID(int facilityID) {
		this.facilityID = facilityID;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

}
