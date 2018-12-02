package com.ausecourse.dao;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ausecourse.model.User;



@Service
public class UserSecurityDao implements UserDetailsService {

	//private static final Logger LOG = LoggerFactory.getLogger(UserSecurityDao.class);
	
	@Autowired
	private IUserDao userDao ; 
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.findByUsername(username); 
		if(null==user){
			//LOG.warn("Username {} not found" , username);
			System.out.println("UserSecurity file : Usersername {} not found " + username);
			throw new UsernameNotFoundException("Username"+username+"notFound");
			
		}
		System.out.println("UserSecurity file : Usersername {} found " + user.getUsername());
		//LOG.warn("Username {} found " , user.getUsername());
		return user ; 
	}
}


