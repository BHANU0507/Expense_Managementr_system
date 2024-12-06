package com.adp.identityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adp.identityservice.dto.AuthRequest;
import com.adp.identityservice.entity.UserInfo;
import com.adp.identityservice.service.EmployeeService;
import com.adp.identityservice.service.JwtService;

@CrossOrigin(origins = {"http://localhost:3000/","https://ephemeral-choux-4b34d5.netlify.app/"})
@RestController
@RequestMapping("/auth")
public class Employee {
	@Autowired
	EmployeeService empService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private JwtService jwtService;
	
	
	public String addEmp(@RequestBody UserInfo userinfo) {
		return empService.addEmp(userinfo);
	}
	
	
	@PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
	
	
	 @PostMapping("/add")
 	public String addUser(@RequestBody UserInfo userInfo) {
 		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
 		System.out.println(userInfo.getPassword());
 		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
 		userInfo.setRoles("ROLE"+"_"+userInfo.getRoles());
 		return  empService.addEmp(userInfo);
 		
 	}
	
	
	@GetMapping("/home/h")
	public String home() {
		return "HOme";
	}
	
	@GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        empService.validateToken(token);
        return "Token is valid";
    }
	
	/*
	  @PostMapping("/login") public String loingUser(@RequestBody AuthDetailsOfUser
	  uDetails) {
	  
	  }
	 */
}
