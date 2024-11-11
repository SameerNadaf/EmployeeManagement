package com.hibernate.EmployeeManagement;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ManageEmployee {
	
	public void addEmployee(SessionFactory factory) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    
	    Scanner scanner = new Scanner(System.in);
	    System.out.println();
	    System.out.println("Enter First Name : ");
	    String fname = scanner.next();
	    System.out.println("Enter Last Name : ");
	    String lname = scanner.next();
	    System.out.println("Enter Phone Number : ");
	    String phone = scanner.next();
	    System.out.println("Enter Department : ");
	    String department = scanner.next();
	    System.out.println("Enter Salary : ");
	    int salary = scanner.nextInt();
	    
	    try {
	         tx = session.beginTransaction();
	         EmployeeName employeeName = new EmployeeName();
	         employeeName.setFname(fname);
	         employeeName.setLname(lname);
	         Employee employee = new Employee();
	         employee.setName(employeeName);
	         employee.setNumber(phone);
	         employee.setDepartment(department);
	         employee.setSalary(salary);
	         
	         session.persist(employee);
	         tx.commit();
	         
	         System.out.println("Employee added successfully....");
	         
	      } catch (HibernateException e) {
	         if (tx!=null) {
	        	 tx.rollback();
	         }
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
	}

	public void updateEmployee(SessionFactory factory) {
		Session session = factory.openSession();
		Transaction tx = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println();
	    System.out.println("Enter Employee ID : ");
	    int id = scanner.nextInt();
	    System.out.println("Enter Salary : ");
	    int salary = scanner.nextInt();

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee)session.get(Employee.class, id);
			if (employee != null) { 
				employee.setSalary(salary);
				session.merge(employee); 
				tx.commit();
	    	    System.out.println("Employee with ID "+ id + " updated with new salary " + salary + " successfully....");
	    	} 
	    	else {
	    	    System.out.println("Employee with id " + id + " not found");
	    	    tx.rollback(); 
	    	}
			
		} catch (HibernateException e) {
			if (tx!=null) {
				tx.rollback();
			}
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}

	}

	public void deleteEmployee(SessionFactory factory) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    
	    Scanner scanner = new Scanner(System.in);
	    System.out.println();
	    System.out.println("Enter Employee ID : ");
	    int id = scanner.nextInt();
	    
	    try {
	    	tx = session.beginTransaction();
	    	Employee employee = (Employee)session.get(Employee.class, id);
	    	if (employee != null) { 
	    	    session.remove(employee);
	    	    tx.commit();
	    	    
	    	    System.out.println("Employee with ID "+ id + " deleted successfully....");
	    	} 
	    	else {
	    	    System.out.println("Employee with id " + id + " not found");
	    	    tx.rollback(); 
	    	}
	    } 
	    catch (HibernateException e) {
	         if (tx!=null) {
	        	 tx.rollback();
	         }
	         e.printStackTrace(); 
	    } 
	    finally {
	         session.close(); 
	    }
		
	}

	public void viewEmployees(SessionFactory factory) {
		Session session = factory.openSession();
	    
	    try {
	    	String hql = "FROM Employee";
	    	Query<Employee> query = session.createQuery(hql, Employee.class);
	    	List<Employee> employees = query.list();

	        System.out.println();
	        System.out.println("Employee List:");
	        for (Employee employee : employees) {
	        	System.out.println("------------------------------------------------------");
	            System.out.println("ID: " + employee.getId());
	            System.out.println("First Name: " + employee.getName().getFname());
	            System.out.println("Last Name: " + employee.getName().getLname());
	            System.out.println("Phone: " + employee.getNumber());
	            System.out.println("Department: " + employee.getDepartment());
	            System.out.println("Salary: " + employee.getSalary());
	            System.out.println("------------------------------------------------------");
	            System.out.println();
	            
	        }
	    } catch (HibernateException e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

}
