package com.mize.service;

import java.sql.SQLException;
import java.util.List;

import com.mize.dto.Employee;

public interface EmployeeService {

	public abstract Employee getEmployeeById(int id) throws SQLException;

	public abstract List<Employee> getAllEmployees() throws SQLException;

	public abstract int insertEmployee(Employee employee) throws SQLException;

	public abstract int deleteEmployee(Employee employee) throws SQLException;

	public abstract int updateEmployee(Employee employee) throws SQLException;

}