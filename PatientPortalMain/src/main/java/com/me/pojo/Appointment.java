package com.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="appointment")
public class Appointment {
	
	@Id 
	@GeneratedValue
	@Column(name="appointmentid", unique = true, nullable = false)
	private int appointmentID;
	
	@Column(name="appointmenttype")
	private String appointmentType;
	
	@Column(name="status")
	private String status;
	
	@Column(name="outcome")
	private String outcome;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient")
    private Patient patient;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="session")
	private Session session;
	
	public Appointment(){}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public int getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

}
