package com.ssk.jdbc_template_demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ssk.jdbc_template_demo.model.Employee;

@Component
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

	// using query with ResultSetExtractor
	public List<Employee> getAllEmployeesUsingResultSetExtractor() {
		logger.info("getAllEmployeesUsingResultSetExtractor() called...");

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

	// using query with RowMapper
	public List<Employee> getAllEmployeesUsingRowMapper() {
		logger.info("getAllEmployeesUsingRowMapper() called...");

		String sql = "SELECT * FROM employee";

		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
				return employee;
			}
		};

		List<Employee> list = jdbcTemplate.query(sql, rowMapper);

		return list;
	}

	// using query with RowCallbackHandler
	public List<Employee> getAllEmployeesUsingRowCallbackHandler() {
		logger.info("getAllEmployeesUsingRowCallbackHandler() called...");

		String sql = "SELECT * FROM employee";

		List<Employee> list = new ArrayList<Employee>();

		RowCallbackHandler rch = new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
					list.add(employee);
				}
			}
		};

		jdbcTemplate.query(sql, rch);

		return list;
	}

	// using query with Parameter and ResultSetExtractor
	public List<Employee> getAllEmployeesByDeptIdUsingResultSetExtractor(int dept_id) {
		logger.info("getAllEmployeesByDeptIdUsingResultSetExtractor() called...");

		String sql = "SELECT * FROM employee WHERE dept_id = ?";

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

//		way1
//		List<Employee> list = jdbcTemplate.query(sql, rse, dept_id);

//		way2
//		Object[] args = new Object[] { dept_id };
//		int[] argTypes = new int[] { Types.INTEGER };
//		List<Employee> list = jdbcTemplate.query(sql, args, argTypes, rse);

//		way3
//		PreparedStatementSetter pss = new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, dept_id);
//			}
//		};
//		List<Employee> list = jdbcTemplate.query(sql, pss, rse);

//		way4
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, dept_id);
				return ps;
			}
		};
		List<Employee> list = jdbcTemplate.query(psc, rse);

		return list;
	}

	// using query with Parameter and RowMapper
	public List<Employee> getAllEmployeesByDeptUsingRowMapper(int dept_id) {
		logger.info("getAllEmployeesByDeptUsingRowMapper() called...");

		String sql = "SELECT * FROM employee WHERE dept_id = ?";

		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
				return employee;
			}
		};

//		way1
//		List<Employee> list = jdbcTemplate.query(sql, rowMapper);

//		way2
//		Object[] args = new Object[] { dept_id };
//		int[] argTypes = new int[] { Types.INTEGER };
//		List<Employee> list = jdbcTemplate.query(sql, args, argTypes, rowMapper);

//		way3
//		PreparedStatementSetter pss = new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, dept_id);
//			}
//		};
//		List<Employee> list = jdbcTemplate.query(sql, pss, rowMapper);

//		way4
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, dept_id);
				return ps;
			}
		};
		List<Employee> list = jdbcTemplate.query(psc, rowMapper);

		return list;
	}

	// using query with Parameter and RowCallbackHandler
	public List<Employee> getAllEmployeesByDeptIdUsingRowCallbackHandler(int dept_id) {
		logger.info("getAllEmployeesByDeptIdUsingRowCallbackHandler() called...");

		String sql = "SELECT * FROM employee WHERE dept_id = ?";

		List<Employee> list = new ArrayList<Employee>();

		RowCallbackHandler rch = new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
					list.add(employee);
				}
			}
		};

//		way1
//		jdbcTemplate.query(sql, rch, dept_id);

//		way2
//		Object[] args = new Object[] { dept_id };
//		int[] argTypes = new int[] { Types.INTEGER };
//		jdbcTemplate.query(sql, args, argTypes, rch);

//		way3
//		PreparedStatementSetter pss = new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, dept_id);
//			}
//		};
//		jdbcTemplate.query(sql, pss, rch);

//		way4
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, dept_id);
				return ps;
			}
		};
		jdbcTemplate.query(psc, rch);

		return list;
	}

	// using queryForList
	public List<Employee> getAllEmployeesUsingQueryForList() {
		logger.info("getAllEmployees() called...");

		String sql = "SELECT * FROM employee";

		List<Employee> list = new ArrayList<Employee>();

		List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);

		map.forEach(m -> {
			Employee employee = new Employee((int) m.get("emp_id"), (String) m.get("emp_fname"),
					(String) m.get("emp_lname"), (String) m.get("role"), Integer.valueOf((String) m.get("manager")),
					(Date) m.get("hire_date"), (int) m.get("salary"), (int) m.get("commission"),
					(int) m.get("dept_id"));
			list.add(employee);
		});

		return list;
	}

	// using queryForList
	public List<Integer> getAllEmployeesIdUsingQueryForList() {
		logger.info("getAllEmployeesIdUsingQueryForList() called...");

		String sql = "SELECT emp_id FROM employee";

		List<Integer> employeesId = jdbcTemplate.queryForList(sql, Integer.class);

		return employeesId;
	}

	// using queryForList
	public List<Integer> getAllEmployeesIdByDeptIdUsingQueryForList(int dept_id) {
		logger.info("getAllEmployeesIdByDeptIdUsingQueryForList() called..." + dept_id);

		String sql = "SELECT emp_id FROM employee WHERE dept_id = ?";

//		way 1
//		List<Integer> employeesId = jdbcTemplate.queryForList(sql, Integer.class, dept_id);

//		way 2
		Object[] args = new Object[] { dept_id };
		int[] argTypes = new int[] { Types.INTEGER };
		List<Integer> employeesId = jdbcTemplate.queryForList(sql, args, argTypes, Integer.class);

		return employeesId;
	}

	// using queryForList
	public List<Employee> getAllEmployeesByDeptIdUsingQueryForList(int dept_id) {
		logger.info("getAllEmployeesIdByDeptIdUsingQueryForList2() called..." + dept_id);

		String sql = "SELECT * FROM employee WHERE dept_id = ?";

		List<Employee> list = new ArrayList<Employee>();

		// way 1
//		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, dept_id);

		// way 2
		Object[] args = new Object[] { dept_id };
		int[] argTypes = new int[] { Types.INTEGER };
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, args, argTypes);

		result.forEach(m -> {
			Employee employee = new Employee((int) m.get("emp_id"), (String) m.get("emp_fname"),
					(String) m.get("emp_lname"), (String) m.get("role"), Integer.valueOf((String) m.get("manager")),
					(Date) m.get("hire_date"), (int) m.get("salary"), (int) m.get("commission"),
					(int) m.get("dept_id"));
			list.add(employee);
		});

		return list;
	}

	// using queryForMap
	public Object getTotalRecords() {
		String sql = "SELECT count(*) as totalRecords FROM employee";

		Map<String, Object> map = jdbcTemplate.queryForMap(sql);

		return map.get("totalRecords");
	}

	// using queryForMap
	public Object getTotalRecordsByDeptId(int dept_id) {
		String sql = "SELECT count(*) as totalRecords FROM employee WHERE dept_id = ?";

//		way 1
//		Map<String, Object> map = jdbcTemplate.queryForMap(sql, dept_id);

//		way 2
		Object[] args = new Object[] { dept_id };
		int[] argTypes = new int[] { Types.INTEGER };
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, args, argTypes);

		return map.get("totalRecords");
	}

	// using queryForObject to get single row and single col without Parameter
	public Object getTotalRecordsUsingQueryForObject() {
		logger.info("getTotalRecordsUsingQueryForObject() called...");

		String sql = "SELECT count(*) as totalRecords FROM employee";

		Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);

		return integer;
	}

	// using queryForObject to get single row and single col with Parameter
	public String getEmployeeNameByEmpId(int emp_id) {
		logger.info("getTotalRecordsUsingQueryForObject() called with emp_id: " + emp_id);

		String sql = "SELECT emp_fname FROM employee where emp_id = ?";

//		way 1
//		String string = jdbcTemplate.queryForObject(sql, String.class, emp_id);

//		way 2
		String string = jdbcTemplate.queryForObject(sql, new Object[] { emp_id }, new int[] { Types.INTEGER },
				String.class);

		return string;
	}

	// using queryForObject to get single row and multi col with and without
	// parameter
	public Employee getEmployeeDetails(int emp_id) {
		logger.info("getEmployeeDetails() called with emp_id: " + emp_id);

//		way 1
//		String sql = "SELECT * FROM employee where emp_id = " + emp_id;

//		way 2 and 3
		String sql = "SELECT * FROM employee where emp_id = ?";

		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getDate(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
				return employee;
			}
		};

//		way 1
//		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper);

//		way 2 
//		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, emp_id);

//		way 3
		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] { emp_id }, new int[] { Types.INTEGER },
				rowMapper);

		return employee;
	}

}
