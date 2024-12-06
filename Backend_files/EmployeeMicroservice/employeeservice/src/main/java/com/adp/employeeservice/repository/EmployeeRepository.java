package com.adp.employeeservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adp.employeeservice.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String>
{
	   @Query(value = "SELECT * FROM employee_entity WHERE emp_id = :id", nativeQuery = true)
	   public EmployeeEntity getEmpId(@Param("id") String id);
}