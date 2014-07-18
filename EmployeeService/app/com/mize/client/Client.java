package com.mize.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mize.dao.EmployeeDAO;
import com.mize.dao.EmployeeDAOImpl;
import com.mize.dto.Employee;

public class Client {

	EmployeeDAO employeeDAO = null;
	static Employee employeeDTO = null;
	static List<Employee> employeeList = new ArrayList<Employee>();

	public static void displayEmployees(List<Employee> employeeList) {
		Iterator<Employee> iterator = employeeList.iterator();
		while (iterator.hasNext()) {
			employeeDTO = iterator.next();
			displayEmployee(employeeDTO);
		}
	}

	public static void displayEmployee(Employee employeeDTO) {
		System.out.println(employeeDTO.getId() + " " + employeeDTO.getName()
				+ " " + employeeDTO.getDepartment() + " "
				+ employeeDTO.getSalary());
	}

	public static void main(String[] args) {
		try {
			EmployeeDAO employeeDAO = new EmployeeDAOImpl();
			System.out.println(employeeDAO.getEmployeeById(3));
			System.out.println(employeeDAO.getAllEmployees());
			Employee employee = new Employee();
			employee.setId(235);
			employee.setName("Swamy");
			employee.setDepartment("Develop");
			employee.setSalary(20000);
			System.out.println(employeeDAO.insertEmployee(employee));
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * try { employeeList=employeeDAO.getAllEmployees();
		 * System.out.println("****All Employees*****");
		 * displayEmployees(employeeList);
		 * employeeList=employeeDAO.getDepartmentWiseEmployees();
		 * System.out.println("****All Department Wise Employees*****");
		 * displayEmployees(employeeList);
		 * employeeList=employeeDAO.getEmployeesOrderBySalary();
		 * System.out.println("****All Employees Order By Salary*****");
		 * displayEmployees(employeeList);
		 * employeeDTO=employeeDAO.getEmployeeById(333);
		 * System.out.println("****Searched Employee*****");
		 * displayEmployee(employeeDTO); } catch (ClassNotFoundException |
		 * SQLException | IOException e) { e.printStackTrace(); }
		 */
	}

}
