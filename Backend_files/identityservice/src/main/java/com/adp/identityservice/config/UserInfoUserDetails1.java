package com.adp.identityservice.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.adp.identityservice.entity.UserInfo;
import com.adp.identityservice.repository.userRepo;

@Component
public class UserInfoUserDetails1 implements UserDetailsService {
	@Autowired
	userRepo userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<UserInfo> userinfo = userrepo.findByUsername(username);
		return userinfo.map(UserDetailsServices1::new).orElseThrow(()-> new UsernameNotFoundException("user not found" + username));
	}
	
	
}
