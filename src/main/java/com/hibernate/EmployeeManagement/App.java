package com.hibernate.EmployeeManagement;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App 
{
	private static SessionFactory factory;
	
    public static void main( String[] args )
    {
    	try {
       	 	Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            factory = con.buildSessionFactory(reg); 
            
            Scanner scanner = new Scanner(System.in);
            ManageEmployee ME = new ManageEmployee();
            
            while (true) {
           	 	System.out.println();
                System.out.println("Employee Management");
                System.out.println();
                System.out.println("1. Add Employee");
                System.out.println("2. Update Employee Salary");
                System.out.println("3. Delete Employee");
                System.out.println("4. View Employees");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                
                switch (choice) {
                	case 1 : ME.addEmployee(factory); break;
                	case 2 : ME.updateEmployee(factory); break;
                	case 3 : ME.deleteEmployee(factory); break;
                	case 4 : ME.viewEmployees(factory); break;
                	case 5 : System.out.println("Exiting...");
                			 System.out.println("Thank you."); 
                			 return;
                	default : System.out.println("Invalid choice! try agin.");
                }
            }
            
       }
       catch (Exception e) {
       	e.printStackTrace();
       }
    }
}
