package com.techietact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techietact.dao.EmployeeDao;
import com.techietact.vo.EmployeeVO;
import com.techitact.bo.EmployeeBO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public EmployeeBO saveEmployee(EmployeeBO employeeBO) {

		EmployeeBO employeebo = new EmployeeBO();
		EmployeeVO employeeVO = new EmployeeVO();
		try {
			BeanUtils.copyProperties(employeeBO, employeeVO);
			employeeVO = employeeDao.saveEmployee(employeeVO);
			BeanUtils.copyProperties(employeeVO, employeebo);
		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		return employeebo;
	}

	@Override
	public List<EmployeeBO> listEmployee() {

		List<EmployeeBO> employeeBOlist = new ArrayList<>();
		try {
			List<EmployeeVO> employeeVOList = employeeDao.listEmployee();

			if (employeeVOList.size() > 0 && !employeeVOList.isEmpty()) {
				for (EmployeeVO employee : employeeVOList) {
					EmployeeBO employeebo = new EmployeeBO();
					BeanUtils.copyProperties(employee, employeebo);
					employeeBOlist.add(employeebo);
				}
			}
			if (employeeBOlist.size() > 0 && !employeeBOlist.isEmpty()) {
				return employeeBOlist;
			}
		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		return null;
	}

	@Override
	public EmployeeBO getEmployeeId(int employeeId) {

		try {
			EmployeeBO employee = new EmployeeBO();
			EmployeeVO employeevo = employeeDao.getEmployeeId(employeeId);
			if (null != employeevo) {
				BeanUtils.copyProperties(employeevo, employee);
				return employee;
			}
		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		return null;
	}

	@Override
	public String deleteEmployee(int employeeId) {

	
		try {
			boolean status = employeeDao.deleteEmployeeId(employeeId);
			if (status) {
				return "Employee with ID " + employeeId + " deleted successfully";
			}

		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		return "Employee with ID " + employeeId + " not found. Deletion failed";
	}

}
