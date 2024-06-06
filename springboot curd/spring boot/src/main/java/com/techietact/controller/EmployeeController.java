package com.techietact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techietact.service.EmployeeService;
import com.techitact.bo.EmployeeBO;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employees")
@Log4j2
public class EmployeeController {




	@Autowired
	EmployeeService employeeService;

	@PostMapping("/create-employee")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeBO employeeBO) {
		EmployeeBO employee = new EmployeeBO();
		try {
			employee = employeeService.saveEmployee(employeeBO);
		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		if (null == employee) {
			return new ResponseEntity<Integer>(employee.getEmployeeId(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<EmployeeBO>(employee, HttpStatus.OK);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<EmployeeBO>> listEmployee() {

		List<EmployeeBO> listEmployeeBO = null;

		try {
			listEmployeeBO = employeeService.listEmployee();
		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		if (listEmployeeBO.size() > 0 && !listEmployeeBO.isEmpty()) {
			return new ResponseEntity<List<EmployeeBO>>(listEmployeeBO, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<EmployeeBO>>(listEmployeeBO, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("get-employee/{id}")
	public ResponseEntity<EmployeeBO> getEmployeeId(@PathVariable int id) {

		EmployeeBO employeeBO = new EmployeeBO();
		try {
			employeeBO = employeeService.getEmployeeId(id);

		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		if (null != employeeBO) {
			return new ResponseEntity<EmployeeBO>(employeeBO, HttpStatus.OK);
		} else {
			return new ResponseEntity<EmployeeBO>(employeeBO, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("delete-employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

		String status = null;
		try {
			status = (String) employeeService.deleteEmployee(id);
		} catch (Exception e) {
			e.printStackTrace();

			if (log.isInfoEnabled()) {

				log.info(e.getMessage(), e);
			}
			if (log.isDebugEnabled()) {

				log.debug(e.getMessage(), e);
			}
		}
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@PutMapping("update-employee/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody EmployeeBO employee) {

		EmployeeBO employeeBO = new EmployeeBO();
		try {
			employeeBO = employeeService.getEmployeeId(id);
			if (null != employeeBO) {
				employeeBO.setName(employee.getName());
				employeeBO.setMobile(employee.getMobile());
				employeeBO.setAddress(employee.getAddress());
				employeeBO.setEmail(employee.getEmail());
				employeeService.saveEmployee(employeeBO);
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
		if (null == employeeBO) {
			return new ResponseEntity<Integer>(employeeBO.getEmployeeId(), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<EmployeeBO>(employee, HttpStatus.OK);
		}
	}

}
