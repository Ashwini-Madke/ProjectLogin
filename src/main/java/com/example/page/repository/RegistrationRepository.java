package com.example.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.page.model.User;

@Repository
public interface RegistrationRepository extends JpaRepository<User,Integer>{
	@Query(value="Select DISTINCT(register.email) from tutorial.register Where id=?1 ",nativeQuery=true)
	public User fetchByEmail(String email);
	
	
    @Query(value=" select email,password from register where id=?1",nativeQuery=true)
	public User fetchByEmailAndPass(String email,String password);
//    @Query(value="Select register.email from tutorial.register Where id=?1 ",nativeQuery=true)
	public User findByEmail(String email);
	
	
}
