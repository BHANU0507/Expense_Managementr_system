package com.adp.managerservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;



import com.adp.managerservice.dto.ManagerDto;
import com.adp.managerservice.utils.ManagerException;

@Service
public interface ManagerService 
{
	ResponseEntity<List<ManagerDto>> getAllManagers() throws IOException, ManagerException ;
	ResponseEntity<ManagerDto> getManagerId(String id) throws Exception ;
}
