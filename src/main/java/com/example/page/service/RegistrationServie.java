package com.example.page.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.example.page.model.User;
import com.example.page.repository.RegistrationRepository;
@Transactional
@Service
public class RegistrationServie {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	     
	public User saveUser(User user) {
		return registrationRepository.save(user);
	}
	
	public User fetchUserByEmailId(String email) {
		return registrationRepository.findByEmail(email);
	}
	
	public User fetchById(Integer id) throws Exception {
		return registrationRepository.getOne(id);
	}
	
	public User login(User user) throws Exception{
		User userobj=registrationRepository.findByEmail(user.getEmail());
		if(userobj==null) {
			throw new Exception("Bad Credentials");
		}
		if(!userobj.getPassword().equals(user.getPassword())) {
			throw new Exception("password mismatch");
		}
		return userobj;
	}
	
	public List<User> findUsers(){
		return registrationRepository.findAll();
	}
	
	public User updateUser(User user) {
		Optional<User> obj=registrationRepository.findById(user.getId());
		if(obj.isPresent()) {
			User objt=obj.get();
			objt.setPassword(user.getPassword());
			return registrationRepository.save(objt);
		}
		return user;
	}
//		String tempEmail=user.getEmail();
//		if(tempEmail!=null && !"".equals(tempEmail)) {
//			User userobj=registrationRepository.fetchByEmail(email);
//			if(userobj!=null) {
//				throw new Excepion("User with "+tempEmail +"Already Exist");
//			}
//		}
//		User userObj=null;
//		userObj=registrationRepository.save(user);
//		return 
//	}
//

}
