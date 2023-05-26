package com.ssk.jdbc_template_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssk.jdbc_template_demo.dao.EmployeeDao;
import com.ssk.jdbc_template_demo.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	@GetMapping("/get-all-employees")
	public List<Employee> getAllEmployees() {
//		List<Employee> list = employeeDao.getAllEmployeesUsingResultSetExtractor();
//		List<Employee> list = employeeDao.getAllEmployeesUsingRowMapper();
		List<Employee> list = employeeDao.getAllEmployeesUsingRowCallbackHandler();

		return list;
	}

	@GetMapping("/get-all-employees/dept_id/{dept_id}")
	public List<Employee> getAllEmployeesByDeptId(@PathVariable int dept_id) {
//		List<Employee> list = employeeDao.getAllEmployeesByDeptIdUsingResultSetExtractor(dept_id);
		List<Employee> list = employeeDao.getAllEmployeesByDeptUsingRowMapper(dept_id);

		return list;
	}
}
