package com.adp.identityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adp.identityservice.entity.UserInfo;

public interface userRepo extends JpaRepository<UserInfo, Integer>{
	Optional<UserInfo> findByUsername(String username);
}
