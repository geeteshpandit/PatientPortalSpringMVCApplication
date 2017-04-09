package com.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="doctor")
@PrimaryKeyJoinColumn(name="personid")
public class Doctor extends Person {
	
//	@Id 
//	@GeneratedValue
	@Column(name="doctorid", unique = true, nullable = false)
	private int doctorID;
	
	@Column(name="speciality")
	private String speciality;
	
	@Column(name="qualification")
	private String qualification;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="facility")
    private Facility facility;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="doctor")
	private Set<Session> session = new HashSet<Session>();
	

	@OneToOne(fetch=FetchType.LAZY, mappedBy="doctor", cascade=CascadeType.DETACH)
	private UserAccount userAccount;
	
	Doctor(){}
	
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Set<Session> getSession() {
		return session;
	}

	public void setSession(Set<Session> session) {
		this.session = session;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

}
