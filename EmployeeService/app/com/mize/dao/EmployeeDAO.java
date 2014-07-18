package com.mize.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.mize.dto.Employee;

public interface EmployeeDAO {

	public abstract void loadProperties();

	public abstract List<Employee> getAllEmployees() throws SQLException;

	public abstract Employee getEmployeeById(int id) throws SQLException;
	
	public abstract int insertEmployee(Employee employee) throws SQLException;
	
	public abstract int deleteEmployee(Employee employee) throws SQLException;
	
	public abstract int updateEmployee(Employee employee) throws SQLException;

}