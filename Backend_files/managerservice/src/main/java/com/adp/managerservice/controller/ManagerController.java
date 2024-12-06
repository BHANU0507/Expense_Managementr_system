package com.adp.managerservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.managerservice.dto.ManagerDto;
import com.adp.managerservice.service.ManagerService;
import com.adp.managerservice.utils.ManagerException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = {"http://localhost:3000/","https://ephemeral-choux-4b34d5.netlify.app/"})
@RestController
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	ManagerService managerservice;
	
	@GetMapping("/allDetails")
	@Operation
	@ApiResponse(responseCode = "200")
	public ResponseEntity<List<ManagerDto>> getEmployees() throws IOException, ManagerException{
		return managerservice.getAllManagers();
		
	}
	
	@Operation
	@ApiResponse(responseCode = "200")
	@GetMapping("/Details/{id}")
	public ResponseEntity<ManagerDto> getEmployee(@PathVariable String id) throws ManagerException{
		try {
			return managerservice.getManagerId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ManagerException("Failed to fetch manager "+e.getMessage());
			
		}
	}


}
