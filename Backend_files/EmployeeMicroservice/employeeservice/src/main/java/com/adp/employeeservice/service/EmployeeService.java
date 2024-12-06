package com.adp.employeeservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adp.employeeservice.dto.EmployeeDto;
import com.adp.employeeservice.utils.EmployeeException;

@Service
public interface EmployeeService {

	public ResponseEntity<List<EmployeeDto>> getAllEmployees() throws IOException, EmployeeException;
	 public ResponseEntity<EmployeeDto> getEmpId(String id) throws Exception;

}
