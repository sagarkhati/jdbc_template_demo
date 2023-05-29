# jdbc_template_demo

**Notes:**

- query() – is used to get multiple rows results as list. (returns multi rows multi cols - with 3 diff mapper)
- queryForList() – is used to get selected columns data of multiple rows as List. (returns multi rows multi cols - without mapper)
- queryForMap() – is used to get selected columns data of single row as Map. (returns single row single col [OR] single row mutli col without mapper)
- queryForObject() – is used to get single value or single row results. (returns single row single col [OR] single row multi col with RowMapper)

---

**Step1:** Go to [Spring Initializr](https://start.spring.io/) and create the project with following dependencies included: 
- Spring Web
- Spring Data JDBC
- MySQL Driver (As per the database)
- Spring Boot DevTools (Optional)


**Step2:** Extract the downloaded code and import it into IDE (Eclipse).


**Step3:** In application.properties file, add the following properties: 

```
spring.datasource.url=jdbc:mysql://localhost:3306/jdbc_template_demo
spring.datasource.username=root
spring.datasource.password=root
```


**Step4:** Now launch the application as Java Application, It should successfully started.


**Step5:** Create the Employee model to match the corresponding db table

```
package com.ssk.jdbc_template_demo.model;

import java.util.Date;

public class Employee {

	private int empId;
	private String empFname;
	private String empLname;
	private String role;
	private int manager;
	private Date hireDate;
	private int salary;
	private int commission;
	private int deptId;

	public Employee() {
		super();
	}

	public Employee(int empId, String empFname, String empLname, String role, int manager, Date hireDate, int salary,
			int commission, int deptId) {
		super();
		this.empId = empId;
		this.empFname = empFname;
		this.empLname = empLname;
		this.role = role;
		this.manager = manager;
		this.hireDate = hireDate;
		this.salary = salary;
		this.commission = commission;
		this.deptId = deptId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpLname() {
		return empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFname=" + empFname + ", empLname=" + empLname + ", role=" + role
				+ ", manager=" + manager + ", hireDate=" + hireDate + ", salary=" + salary + ", commission="
				+ commission + ", deptId=" + deptId + "]";
	}

}
```


**Step6:** Create the EmployeeDao class to fetch the data using JdbcTemplate

```
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

import com.ssk.jdbc_template_demo.controllers.model.Employee;

@Component
public class EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Employee> getData() {
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
```


**Step7:** Create the EmployeeController class to define the rest endpoints, each endpoint will call a method from EmployeeDao class to return the response.

```
package com.ssk.jdbc_template_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssk.jdbc_template_demo.controllers.dao.EmployeeDao;
import com.ssk.jdbc_template_demo.controllers.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/data")
	public List<Employee> getData() {
		List<Employee> list = employeeDao.getData();
		return list;
	}
}
``` 