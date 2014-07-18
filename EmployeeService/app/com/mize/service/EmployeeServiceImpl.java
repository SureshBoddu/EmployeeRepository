package com.mize.service;


import java.sql.SQLException;
import java.util.List;

import com.mize.dao.EmployeeDAO;
import com.mize.dao.EmployeeDAOImpl;
import com.mize.dto.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAO employeeDAO;
	public EmployeeServiceImpl()
	{
		employeeDAO=new EmployeeDAOImpl();
	}
	/* (non-Javadoc)
	 * @see com.mize.service.EmployeeService#getEmployeeById(int)
	 */
	@Override
	public Employee getEmployeeById(int id) throws SQLException
	{
		return employeeDAO.getEmployeeById(id);
	}
	/* (non-Javadoc)
	 * @see com.mize.service.EmployeeService#getAllEmployees()
	 */
	@Override
	public List<Employee> getAllEmployees() throws SQLException
	{
		return employeeDAO.getAllEmployees();
	}
	/* (non-Javadoc)
	 * @see com.mize.service.EmployeeService#insertEmployee(com.mize.dto.Employee)
	 */
	@Override
	public int insertEmployee(Employee employee) throws SQLException
	{
		return employeeDAO.insertEmployee(employee);
	}
	/* (non-Javadoc)
	 * @see com.mize.service.EmployeeService#deleteEmployee(com.mize.dto.Employee)
	 */
	@Override
	public int deleteEmployee(Employee employee) throws SQLException
	{
		return employeeDAO.deleteEmployee(employee);
	}
	/* (non-Javadoc)
	 * @see com.mize.service.EmployeeService#updateEmployee(com.mize.dto.Employee)
	 */
	@Override
	public int updateEmployee(Employee employee) throws SQLException
	{
		return employeeDAO.updateEmployee(employee);
	}
}
