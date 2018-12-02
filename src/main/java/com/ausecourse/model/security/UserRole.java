package com.ausecourse.model.security;

import java.io.Serializable;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.ausecourse.model.User;

@RedisHash("Role")
public class UserRole implements Serializable{

	private static final long serialVersionUID = 890345L ;
	

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}


	private User user ; 
	
	
	private Role role ; 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Id private String userRoleId ; 
	
	public UserRole(User user , Role role){
		this.user = user ; 
		this.role = role ; 
	}
	
}
