package com.mize.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.mize.dao.EmployeeDAO;
import com.mize.dto.Employee;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	@Override
	public Employee getEmployeeById(int id) throws EmptyResultDataAccessException
	{
		return employeeDAO.getEmployeeById(id);
	}
	@Override
	public List<Employee> getAllEmployees()
	{
		
		return employeeDAO.getAllEmployees();
	}
	/* (non-Javadoc)
	 * @see com.mize.service.EmployeeService#insertEmployee(com.mize.dto.Employee)
	 */
	@Override
	public int insertEmployee(Employee employee) throws DuplicateKeyException
	{
		return employeeDAO.insertEmployee(employee);
	}
	@Override
	public int deleteEmployee(Employee employee)
	{
		return employeeDAO.deleteEmployee(employee);
	}
	@Override
	public int updateEmployee(Employee employee)
	{
		return employeeDAO.updateEmployee(employee);
	}
}
