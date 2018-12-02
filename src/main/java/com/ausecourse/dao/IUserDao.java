package com.ausecourse.dao;

import java.util.List;

import java.util.Optional;
import java.util.Set;

import com.ausecourse.model.User;
import com.ausecourse.model.security.UserRole;




public interface IUserDao {

	
	User createUser(User user, Set<UserRole> userRoles);
	
	User findByUsername(String username) ; 
	
	User findByEmail(String email) ; 
	
	List<User> findAll();
	
	User save(User user) ; 
	
	Optional<User> findById(String id) ; 
	
}
