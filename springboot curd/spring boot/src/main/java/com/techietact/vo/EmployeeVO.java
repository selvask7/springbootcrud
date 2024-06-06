package com.techietact.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class EmployeeVO {

	@Id
	@Column(name = "employee_id",length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column(name = "name",length = 10)
	private String name;
	
	@Column(name = "email",length = 20)
	private String email;
	
	@Column(name = "mobile",length = 10)
	private long mobile;
	
	@Column(name = "address",length = 10)
	private String address;
}
