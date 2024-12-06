package com.adp.managerservice.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adp.managerservice.dto.ManagerDto;
import com.adp.managerservice.entity.ManagerEntity;
import com.adp.managerservice.repository.ManagerRepository;
import com.adp.managerservice.utils.ManagerException;

@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	ManagerRepository managerRepo;
	

	public  ResponseEntity<List<ManagerDto>> getAllManagers() 
	{
		Iterable<ManagerEntity> allmanagers = managerRepo.findAll();
		List<ManagerDto> list = new ArrayList<>();

		allmanagers.forEach(manager -> {

			ManagerDto managerDto = new ManagerDto();
			
			managerDto.setMgnId(manager.getMgnId());
			managerDto.setMgnName(manager.getMgnName());
			managerDto.setEmpIds(Arrays.asList(manager.getEmpIds().split(",")));
			

			

			list.add(managerDto);

		});
		
		System.out.println(list);
		return ResponseEntity.ok(list); 
		
	}

	public ResponseEntity<ManagerDto> getManagerId(String id) 
	{
		ManagerEntity manager = managerRepo.getManager(id);
		
		ManagerDto managerdto = new ManagerDto();
		managerdto.setMgnId(manager.getMgnId());
		managerdto.setMgnName(manager.getMgnName());
		managerdto.setEmpIds(Arrays.asList(manager.getEmpIds().split(",")));
		
		return ResponseEntity.ok(managerdto);
	}
	

}
