package com.me.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="useraccount", uniqueConstraints=@UniqueConstraint(columnNames={"username"}))
public class UserAccount {
	
	@Id 
	@GeneratedValue
	@Column(name="useraccountid", unique = true, nullable = false)
	private int userAccountID;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="patient")
	private Patient patient;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="doctor")
	private Doctor doctor;
	
	public UserAccount(){}
	
//	public static UserAccount createUserAccount(UserAccount userAccount)
//	{
//		UserAccount ua = new UserAccount();
//		ua.setUserName(userName);
//		return ua;
//	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getUserAccountID() {
		return userAccountID;
	}
	public void setUserAccountID(int userAccountID) {
		this.userAccountID = userAccountID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
