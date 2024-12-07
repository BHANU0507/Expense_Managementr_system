package com.adp.expenseservice.configurations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.adp.expenseservice.dto.EmployeeDto;



@FeignClient(name="employeeservice",url="http://localhost:8003/employee")
public interface EmployeeService {
	@GetMapping("/Details/{id}")
	ResponseEntity<EmployeeDto> getEmpId(@PathVariable String id);
}
