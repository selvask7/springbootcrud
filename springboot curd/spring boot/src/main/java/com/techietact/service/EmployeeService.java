package com.techietact.service;

import java.util.List;

import com.techitact.bo.EmployeeBO;

public interface EmployeeService {

	EmployeeBO saveEmployee(EmployeeBO employeeBO);

	List<EmployeeBO> listEmployee();

	EmployeeBO getEmployeeId(int employeeId);

	String deleteEmployee(int employeeId);

}
