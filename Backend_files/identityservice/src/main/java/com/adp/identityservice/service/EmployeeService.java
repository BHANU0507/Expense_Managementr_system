package com.adp.identityservice.service;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adp.identityservice.entity.UserInfo;
import com.adp.identityservice.repository.userRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class EmployeeService {
	@Autowired
	userRepo userrepo;
	
	 public static final String SECRET = "FB75543D83A083C0C285D0D2286C2629A4E45E2380BE93D35AE60022A0BE853C";
	
	public String addEmp(UserInfo userinfo) {
		System.out.println(userinfo.getPassword());
//		userinfo.setUsername(userinfo.getUsername());
		userinfo.setPassword(userinfo.getPassword());
		userrepo.saveAndFlush(userinfo);
		return "Added User successfully";
	}
	
	public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }
	
	private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
	

