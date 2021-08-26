package com.example.page.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.page.model.User;
import com.example.page.service.RegistrationServie;


@RestController
public class RegistrationController {
	@Autowired
	private RegistrationServie registrationServie;
	
	
	@PostMapping("/registration")
	@CrossOrigin(origins="http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmail=user.getEmail();
		if(tempEmail!=null && !"".equals(tempEmail)) {
			User userobj=registrationServie.fetchUserByEmailId(tempEmail);
			if(userobj!=null) {
				throw new Exception("User with this mailId="+tempEmail +" Already Exist!");
			}
		}
		User userObj=null;
		userObj=registrationServie.saveUser(user);
		return userObj;
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins="http://localhost:4200")
	public User userlogin(@RequestBody User user) throws Exception {
		return registrationServie.login(user);
		
	}
	
	@GetMapping("/AllUser")
	@CrossOrigin(origins="http://localhost:4200")
	public List<User> findAllUser(){
		return registrationServie.findUsers();
	}
	
	@PutMapping("/updatepassword/{email}")
	@CrossOrigin(origins="http://localhost:4200")
	public void resetPassword(@RequestBody User user,@PathVariable String email) {
		registrationServie.updateUser(user);
	}
	
//	@PostMapping("/login")
//	public User loginUser(@RequestBody User user) throws Exception{
//		User userobj=null;
//		userobj=registrationServie.fetchUserByEmailAndPass(user.getEmail(), user.getPassword());
//		if(userobj==null) {
//			throw new Exception("Bad Credentials");
//		}
//		return userobj;
//	}
//
//	@PostMapping("/logins")
//	public User loginsUser(@RequestBody User user) throws Exception{
//		String tempEmail=user.getEmail();
//		String tempass=user.getPassword();
//		User userobj=null;
//		if(tempEmail!=null && tempass!=null) {
//			userobj=registrationServie.fetchUserByEmailAndPass(tempEmail, tempass);
//		}
//		if(userobj==null) {
//			throw new Exception("Bad Credentials");
//		}
//		return userobj;
//	}
//	


}