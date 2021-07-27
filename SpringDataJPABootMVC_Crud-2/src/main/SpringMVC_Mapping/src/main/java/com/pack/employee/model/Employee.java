package com.pack.employee.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="register3")
public class Employee {
	
	@Column(name = "name")
	private String  name;
	@Column(name = "email")
	private String email;
	@Column(name = "gender")
	private String gender;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	public Address getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(Address employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="employeeaddress",unique = true)
	private Address employeeAddress;

}
