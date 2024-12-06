package com.adp.employeeservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.employeeservice.dto.EmployeeDto;
import com.adp.employeeservice.service.EmployeeService;
import com.adp.employeeservice.utils.EmployeeException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = {"http://localhost:3000/","https://ephemeral-choux-4b34d5.netlify.app/"})
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService empservice;

	@GetMapping("/allDetails")
	@Operation
	@ApiResponse(responseCode = "200")
	public ResponseEntity<List<EmployeeDto>> getEmployees() throws IOException, EmployeeException{
		return empservice.getAllEmployees();

	}

	@Operation
	@ApiResponse(responseCode = "200")
	@GetMapping("/Details/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String id) throws Exception{
		return empservice.getEmpId(id);
	}

}
