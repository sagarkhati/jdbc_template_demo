package com.ssk.jdbc_template_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssk.jdbc_template_demo.dao.EmployeeDao;
import com.ssk.jdbc_template_demo.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/get-all-employees")
	public List<Employee> getAllEmployees() {
		List<Employee> list = employeeDao.getAllEmployees();
		return list;
	}
	
	@GetMapping("/get-all-employees-rm")
	public List<Employee> getAllEmployeesRM() {
		List<Employee> list = employeeDao.getAllEmployeesRM();
		return list;
	}
}
