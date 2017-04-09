package com.me.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Table(name="person")
@Inheritance(strategy=InheritanceType.JOINED)	
public class Person {
	
	@Id 
	@GeneratedValue
	@Column(name="personid", unique = true, nullable = false)
	private int personID;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="emailid")
	private String emailID;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="gender")
	private String gender;
	
	
	Person(){}
	
	
//	Person(String firstName,String lastName,String gender,String emailId,String phone,String address)
//	{
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.gender = gender;
//		this.emailID = emailId;
//		this.phone = phone;
//		this.address = address;
//	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
