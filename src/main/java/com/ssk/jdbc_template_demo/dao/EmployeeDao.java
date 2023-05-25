package com.ssk.jdbc_template_demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.ssk.jdbc_template_demo.model.Employee;

@Component
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmployees() {
		String sql = "SELECT * FROM employee";

		ResultSetExtractor<List<Employee>> rse = new ResultSetExtractor<List<Employee>>() {
			@Override
			public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Employee> employees = new ArrayList<Employee>();

				while (rs.next()) {
					Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
					employees.add(employee);
				}

				return employees;
			}
		};

		List<Employee> list = jdbcTemplate.query(sql, rse);

		return list;
	}
}
