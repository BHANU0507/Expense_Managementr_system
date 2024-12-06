package com.adp.managerservice.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adp.managerservice.entity.ManagerEntity;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, String>
{
	 @Query(value = "SELECT * FROM manager_entity WHERE mgn_id =:id", nativeQuery = true)
	 public ManagerEntity getManager(@Param("id") String id);
}
