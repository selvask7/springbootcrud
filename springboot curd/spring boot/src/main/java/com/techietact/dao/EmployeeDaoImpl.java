package com.techietact.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techietact.repository.EmployeeRepository;
import com.techietact.vo.EmployeeVO;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public EmployeeVO saveEmployee(EmployeeVO employeeVO) {

		try {
			employeeRepository.save(employeeVO);

			if (0 < employeeVO.getEmployeeId()) {
				return employeeVO;
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
	public List<EmployeeVO> listEmployee() {

		List<EmployeeVO> employeeVOList = null;
		try {
			employeeVOList = employeeRepository.findAll();
			if (null != employeeVOList && 0 < employeeVOList.size() && !employeeVOList.isEmpty()) {
				return employeeVOList;
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
		return employeeVOList;
	}

	@Override
	public EmployeeVO getEmployeeId(int employeeId) {

		EmployeeVO employee = null;
		try {
			employee = employeeRepository.findById(employeeId).get();
			if (null != employee) {
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
	public boolean deleteEmployeeId(int employeeId) {

		EmployeeVO employee = null;
		try {
			employee = employeeRepository.findById(employeeId).orElse(null);
			if (null != employee) {
				employeeRepository.delete(employee);

				return true;
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
		return false;
	}

}
