package com.mize.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mize.dto.Employee;

public interface EmployeeService {

	public abstract Employee getEmployeeById(int id) throws EmptyResultDataAccessException;

	public abstract List<Employee> getAllEmployees();

	public abstract int insertEmployee(Employee employee) throws DuplicateKeyException;

	public abstract int deleteEmployee(Employee employee);

	public abstract int updateEmployee(Employee employee);

}