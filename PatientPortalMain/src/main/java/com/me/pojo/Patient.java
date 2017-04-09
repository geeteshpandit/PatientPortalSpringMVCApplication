package com.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="patient")
@PrimaryKeyJoinColumn(name="personid") 
public class Patient extends Person {
	
//	@Id 
//	@GeneratedValue
	@Column(name="patientid", unique = true, nullable = false)
	private int patientID;
	
	@Column(name="age")
	private int age;
	
	@Column(name="knownallergies")
	private String knownAllergies;
	
	@Column(name="bloodgroup")
	private String bloodGroup;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="patient")
	private Set<Appointment> appointment = new HashSet<Appointment>();
	

	@OneToOne(fetch=FetchType.LAZY, mappedBy="patient", cascade=CascadeType.ALL)
	private UserAccount userAccount;
	
	@Transient
	static int count = 0;
	public Patient()
	{
		UserAccount ua = new UserAccount();
		count++;
		this.setPatientID(count);
		
	}
//	public Patient(UserAccount ua)
//	{
////		super(firstName,lastName,gender,emailId,phone,address);
//		ua.setPatient(this);
//		this.setUserAccount(ua);
//		
//	}
//	
	
	public Set<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<Appointment> appointment) {
		this.appointment = appointment;
	}

	public String getKnownAllergies() {
		return knownAllergies;
	}
	public void setKnownAllergies(String knownAllergies) {
		this.knownAllergies = knownAllergies;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
}
