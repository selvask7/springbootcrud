package com.techietact.dao;

import java.util.List;

import com.techietact.vo.EmployeeVO;
import com.techitact.bo.EmployeeBO;

public interface EmployeeDao {

	EmployeeVO saveEmployee(EmployeeVO employeeVO);

	List<EmployeeVO> listEmployee();

	EmployeeVO getEmployeeId(int employeeId);

	boolean deleteEmployeeId(int employeeId);

	
}
