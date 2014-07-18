package com.mize.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mize.dto.Employee;

@SuppressWarnings("rawtypes")
public class EmployeeMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		  Employee employee = new Employee();    
		  employee.setId(rs.getInt("id"));    
		  employee.setName(rs.getString("name"));    
		  employee.setDepartment(rs.getString("department"));    
		  employee.setSalary(rs.getInt("salary"));    
		  return employee;
	}

}
