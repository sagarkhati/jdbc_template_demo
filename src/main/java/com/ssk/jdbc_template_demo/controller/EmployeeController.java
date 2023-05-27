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
//		List<Employee> list = employeeDao.getAllEmployeesUsingRowCallbackHandler();
		List<Employee> list = employeeDao.getAllEmployeesUsingQueryForList();

		return list;
	}

	@GetMapping("/get-all-employees/dept_id/{dept_id}")
	public List<Employee> getAllEmployeesByDeptId(@PathVariable int dept_id) {
//		List<Employee> list = employeeDao.getAllEmployeesByDeptIdUsingResultSetExtractor(dept_id);
//		List<Employee> list = employeeDao.getAllEmployeesByDeptUsingRowMapper(dept_id);
//		List<Employee> list = employeeDao.getAllEmployeesByDeptIdUsingRowCallbackHandler(dept_id);
		List<Employee> list = employeeDao.getAllEmployeesByDeptIdUsingQueryForList(dept_id);

		return list;
	}

	@GetMapping("/get-all-employees-id")
	public List<Integer> getAllEmployeesId() {
		List<Integer> list = employeeDao.getAllEmployeesIdUsingQueryForList();

		return list;
	}

	@GetMapping("/get-all-employees-id/dept_id/{dept_id}")
	public List<Integer> getAllEmployeesIdByDeptId(@PathVariable int dept_id) {
		List<Integer> list = employeeDao.getAllEmployeesIdByDeptIdUsingQueryForList(dept_id);

		return list;
	}

	@GetMapping("/get-total-emp-records")
	public Object getTotalRecords() {
//		return employeeDao.getTotalRecords();
		return employeeDao.getTotalRecordsUsingQueryForObject();
	}

	@GetMapping("/get-total-emp-records/dept_id/{dept_id}")
	public Object getTotalRecordsByDeptId(@PathVariable int dept_id) {
		return employeeDao.getTotalRecordsByDeptId(dept_id);
	}

	@GetMapping("/get-emp-name/emp_id/{emp_id}")
	public String getEmployeeNameByEmpId(@PathVariable int emp_id) {
		return employeeDao.getEmployeeNameByEmpId(emp_id);
	}

	@GetMapping("/get-emp-details/emp_id/{emp_id}")
	public Employee getEmployeeDetails(@PathVariable int emp_id) {
		return employeeDao.getEmployeeDetails(emp_id);
	}
}
