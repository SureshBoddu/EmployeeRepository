package com.mize.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mize.connection.DataBaseConnection;
import com.mize.dto.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	private List<Employee> employeeList = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	String getAllEmployees = null;
	String getEmployeeById = null;
	String insertEmployee = null;
	String deleteEmployee = null;
	String updateEmployee = null;

	private Employee employeeDTO = null;

	public EmployeeDAOImpl() {
		employeeList = new ArrayList<Employee>();
		connection = DataBaseConnection.getConnection();
		loadProperties();
	}

	@Override
	public void loadProperties() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(
					"E:/PlayFrameWork/EmployeeService/app/queries.properties");
			Properties properties = new Properties();
			properties.load(fis);
			getEmployeeById = properties.getProperty("getEmployeeById");
			getAllEmployees = properties.getProperty("getAllEmployees");
			insertEmployee = properties.getProperty("insertEmployee");
			deleteEmployee = properties.getProperty("deleteEmployee");
			updateEmployee = properties.getProperty("updateEmployee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mize.dao.EmployeeDAO#getAllEmployees()
	 */
	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		preparedStatement = connection.prepareStatement(getAllEmployees);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			employeeDTO = new Employee();
			employeeDTO.setId(resultSet.getInt("id"));
			employeeDTO.setName(resultSet.getString("name"));
			employeeDTO.setDepartment(resultSet.getString("department"));
			employeeDTO.setSalary(resultSet.getInt("salary"));
			employeeList.add(employeeDTO);
		}
		return employeeList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mize.dao.EmployeeDAO#getEmployeeById(int)
	 */
	@Override
	public Employee getEmployeeById(int id) throws SQLException {
		employeeDTO = null;
		preparedStatement = connection.prepareStatement(getEmployeeById);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			employeeDTO = new Employee();
			employeeDTO.setId(Integer.parseInt(resultSet.getString(1)));
			employeeDTO.setName(resultSet.getString(2));
			employeeDTO.setDepartment(resultSet.getString(3));
			employeeDTO.setSalary(Integer.parseInt(resultSet.getString(4)));
			System.out.println(employeeDTO);
		}
		return employeeDTO;
	}

	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		int result = 0;
		preparedStatement = connection.prepareStatement(insertEmployee);
		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setString(3, employee.getDepartment());
		preparedStatement.setInt(4, employee.getSalary());
		result = preparedStatement.executeUpdate();
		return result;
	}

	@Override
	public int deleteEmployee(Employee employee) throws SQLException {
		int result = 0;
		preparedStatement = connection.prepareStatement(deleteEmployee);
		preparedStatement.setInt(1, employee.getId());
		result = preparedStatement.executeUpdate();
		return result;
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		int result = 0;
		preparedStatement = connection.prepareStatement(updateEmployee);
		preparedStatement.setString(1, employee.getName());
		preparedStatement.setString(2, employee.getDepartment());
		preparedStatement.setInt(3, employee.getSalary());
		preparedStatement.setInt(4, employee.getId());
		result = preparedStatement.executeUpdate();
		return result;
	}
}
