package com.adp.employeeservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adp.employeeservice.dto.EmployeeDto;
import com.adp.employeeservice.entity.EmployeeEntity;
import com.adp.employeeservice.repository.EmployeeRepository;
import com.adp.employeeservice.utils.EmployeeException;





@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeRepository empRepo;


	@Override
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() throws EmployeeException, IOException {
		Iterable<EmployeeEntity> allemployees = empRepo.findAll();
		List<EmployeeDto> list = new ArrayList<>();

		allemployees.forEach(employee -> {

			EmployeeDto employeeDto = new EmployeeDto();

			employeeDto.setEmpId(employee.getEmpId());
			employeeDto.setEmpName(employee.getEmpName());
			employeeDto.setEmpEmail(employee.getEmpEmail());
			employeeDto.setEmpDOB(employee.getEmpDOB());
			employeeDto.setEmpCountry(employee.getEmpCountry());
			employeeDto.setEmpPosition(employee.getEmpPosition());
			employeeDto.setMgnId(employee.getMgnId());




			list.add(employeeDto);

		});


		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<EmployeeDto> getEmpId(String id) throws Exception, EmployeeException {

		EmployeeEntity employee = empRepo.getEmpId(id);

		EmployeeDto employeedto = new EmployeeDto();

		employeedto.setEmpId(employee.getEmpId());
		employeedto.setEmpName(employee.getEmpName());
		employeedto.setEmpEmail(employee.getEmpEmail());
		employeedto.setEmpDOB(employee.getEmpDOB());
		employeedto.setEmpCountry(employee.getEmpCountry());
		employeedto.setEmpPosition(employee.getEmpPosition());
		employeedto.setMgnId(employee.getMgnId());

		return ResponseEntity.ok(employeedto);

//		return new ResponseEntity<EmployeeEntity>(empRepo.getEmployee(id),HttpStatus.OK);
	}


}
