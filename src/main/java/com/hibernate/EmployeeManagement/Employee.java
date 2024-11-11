package com.hibernate.EmployeeManagement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
	
	@Id 
	@GeneratedValue
	@Column(name = "employee_id")
	private int id;
	private EmployeeName name;
	@Column(length = 10, unique = true)
	private String number;
	private String department;
	private int salary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public EmployeeName getName() {
		return name;
	}
	public void setName(EmployeeName name) {
		this.name = name;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}
