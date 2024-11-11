package com.hibernate.EmployeeManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeName {
	
	@Column(name = "first_name", length = 100)
	private String fname;
	@Column(name = "last_name", length = 100)
	private String lname;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}

}
