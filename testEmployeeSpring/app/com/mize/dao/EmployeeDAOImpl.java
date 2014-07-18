package com.mize.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mize.dto.Employee;
import com.mize.mapper.EmployeeMapper;

@Component
public class EmployeeDAOImpl implements EmployeeDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;
	
	String getAllEmployees = null;
	String getEmployeeById = null;
	String insertEmployee = null;
	String deleteEmployee = null;
	String updateEmployee = null;
	
	public EmployeeDAOImpl() {
		loadProperties();
	}

	@Override
	public void loadProperties() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(
					"E:/suresh/play/EclipsePlayWorkspace/testEmployeeSpring/conf/queries.properties");
			Properties properties = new Properties();
			properties.load(fis);
			getEmployeeById = properties.getProperty("getEmployeeById");
			getAllEmployees = properties.getProperty("getAllEmployees");
			insertEmployee = properties.getProperty("insertEmployee");
			deleteEmployee = properties.getProperty("deleteEmployee");
			updateEmployee = properties.getProperty("updateEmployee");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		return jdbcTemplate.query(getAllEmployees, new EmployeeMapper());	
	}
	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployeeById(int id) throws EmptyResultDataAccessException {
		return (Employee) jdbcTemplate.queryForObject(getEmployeeById, new Object[]{id},new EmployeeMapper());
	}

	@Override
	public int insertEmployee(Employee employee) throws DuplicateKeyException {
		return jdbcTemplate.update(insertEmployee, new Object[]{employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()});
	}

	@Override
	public int deleteEmployee(Employee employee) {
		return jdbcTemplate.update(deleteEmployee, new Object[]{employee.getId()});
	}

	@Override
	public int updateEmployee(Employee employee) {
		return jdbcTemplate.update(updateEmployee, new Object[]{employee.getName(),employee.getDepartment(),employee.getSalary(),employee.getId()});
	}
}
