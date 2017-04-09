package com.me.pojo;

import javax.persistence.CascadeType;
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
@Table(name="session")
public class Session {
	
	@Id 
	@GeneratedValue
	@Column(name="sessionid", unique = true, nullable = false)
	private int sessionID;
	
	@Column(name="sessiondate")
	private String sessionDate;
	
	@Column(name="dayofweek")
	private String dayOfWeek;
	
	@Column(name="slot")
	private String slot;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="session", cascade=CascadeType.ALL)
	private Appointment appointment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor")
    private Doctor doctor;
	
	Session(){}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}
	public String getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	

}
