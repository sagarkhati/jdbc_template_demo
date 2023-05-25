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
