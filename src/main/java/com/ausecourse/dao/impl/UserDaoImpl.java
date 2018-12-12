package com.ausecourse.dao.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ausecourse.dao.IUserDao;
import com.ausecourse.model.User;
import com.ausecourse.model.security.UserRole;
import com.ausecourse.repository.RoleRepository;
import com.ausecourse.repository.UserRepository;


@Service
public class UserDaoImpl implements IUserDao {

	//private static final Logger LOG =  LoggerFactory.getLogger(UserDao.class);

	@Autowired
	private UserRepository userRepository ;

	@Autowired
	private RoleRepository roleRepository ;



	public User createUser(User user, Set<UserRole> userRoles) {
		// TODO Auto-generated method stub
		System.out.println("Is User null ? " + user.getNickname());
		User localUser = this.findByUsername(user.getNickname());
		System.out.println("Is User null ? 2 " + user.getEmail());
		if(localUser != null)
			System.out.println("UserDaoImpl User with username {} already exist.Nothing will be done. " +  user.getNickname() );
			//LOG.info("User with username {} already exist.Nothing will be done.",user.getNickname());
		else {
			for(UserRole ur : userRoles)
				roleRepository.save(ur.getRole());



			user.getUserRoles().addAll(userRoles);




			//user.setId("1");

			userRepository.save(user);


		}
		return localUser;
	}

	public User findByUsername(String username) {
		//return this.userRepository.findByName(username);
		List<User> users = this.findAll() ;
		//System.out.println("merde " + username + ".." + users.get(3).getNickname() + users.size());
		for(int i=0;i<users.size();i++) {
			if (users.get(i).getNickname().equals(username))
				return users.get(i) ;
		}
		return null ;
	}

	public User findByEmail(String email) {
		List<User> users = this.findAll() ;

		for(int i=0;i<users.size();i++) {
			if (users.get(i).getEmail().equals(email))
				return users.get(i) ;
		}
		return null ;

	}

	public User save(User user) {
		return userRepository.save(user) ;
	}


	public Optional<User> findById(String id) {
		return userRepository.findById(id) ;
	}



	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		this.userRepository.findAll().forEach(list::add);
		return list ;

	}


}
