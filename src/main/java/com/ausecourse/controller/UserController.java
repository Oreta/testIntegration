package com.ausecourse.controller;



import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ausecourse.config.SecurityConfig;
import com.ausecourse.config.SecurityUtility;
import com.ausecourse.dao.IUserDao;
import com.ausecourse.model.User;
import com.ausecourse.model.security.Role;
import com.ausecourse.model.security.UserRole;
import com.ausecourse.utility.MailConstructor;


@RestController
@RequestMapping("/user")
public class UserController {
//routes


	@Autowired
	private IUserDao userDao ;
	
	@Autowired
	private MailConstructor mailConstructor ; 
	
	//@Autowired//import jdk.internal.jline.internal.Log;
	//private JavaMailSender mailSender ; 

	
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public ResponseEntity newUserPost(HttpServletRequest request,
										@RequestBody HashMap<String,String> mapper) throws Exception {
		
		String username = mapper.get("username") ;
		String userEmail = mapper.get("email");
		String password = mapper.get("password") ;
		
		
		
		if(userDao.findByUsername(username) != null){
			return new ResponseEntity("usernameExists" , HttpStatus.BAD_REQUEST);
		}
		
		if(userDao.findByEmail(userEmail) != null){
			return new ResponseEntity("emailExists" , HttpStatus.BAD_REQUEST);
		}
		
		User user = new User(); 
		user.setNickname(username);
		user.setEmail(userEmail);
		String encryptedPassword = SecurityConfig.passwordEncoder().encode(password) ; 
		user.setPassword(encryptedPassword);
		
		System.out.println("credential received username : " + user.getUsername() + " and password : " + user.getPassword());
		
		Role role = new Role(); 
		role.setRoleId("1");
		role.setName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, role)) ; 
		userDao.createUser(user, userRoles);
		
		SimpleMailMessage email = mailConstructor.construcNewUserEmail(user,password) ;
		//mailSender.send(email);
		return new ResponseEntity("User Added Successfully", HttpStatus.OK);
	}
	

	
	@GetMapping
	public ResponseEntity<List<User>> getAll(){
		return ResponseEntity.ok(userDao.findAll());
	}
	@RequestMapping(value="/forgetPassword", method=RequestMethod.POST)
	public ResponseEntity forgetPassword(HttpServletRequest request,
										@RequestBody HashMap<String,String> mapper) throws Exception {
		User user = userDao.findByEmail(mapper.get("email")) ; 
		
		if(user == null){
			return new ResponseEntity("Email not found" , HttpStatus.BAD_REQUEST);
		}
		
		String password = SecurityUtility.randomPassword() ; 
		String encryptedPassword = SecurityConfig.passwordEncoder().encode(password) ; 
		user.setPassword(encryptedPassword);
		userDao.save(user);
		
		SimpleMailMessage newEmail = mailConstructor.construcNewUserEmail(user,password) ; 
		//mailSender.send(newEmail);
		
		return new ResponseEntity("Email sent!", HttpStatus.OK);
		
	}

	
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	public ResponseEntity profileInfo(
				@RequestBody HashMap<String, Object> mapper
			) throws Exception{
		
		String id = (String) mapper.get("id");
		String email = (String) mapper.get("email");
		String username = (String) mapper.get("username");
		String newPassword = (String) mapper.get("newPassword");
		String currentPassword = (String) mapper.get("currentPassword");
		
		Optional<User> currentUser = userDao.findById(id);
		
		if(currentUser == null) {
			throw new Exception ("User not found");
		}
		
		/*if(userService.findByEmail(email) != null) {
			if(userService.findByEmail(email).getId() != currentUser.getId()) {
				return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
			}
		}*/
		
		/*if(userService.findByUsername(username) != null) {
			if(userService.findByUsername(username).getId() != currentUser.getId()) {
				return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
			}
		}*/
		
		SecurityConfig securityConfig = new SecurityConfig();
		
		
			BCryptPasswordEncoder passwordEncoder = SecurityConfig.passwordEncoder();
			String dbPassword = currentUser.get().getPassword();
			
			if(null != currentPassword)
			if(passwordEncoder.matches(currentPassword, dbPassword)) {
				if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
					currentUser.get().setPassword(passwordEncoder.encode(newPassword));
				}
			} else {
				return new ResponseEntity("Incorrect current password!", HttpStatus.BAD_REQUEST);
			}
		
		
		//currentUser.get().setFirstName(firstName);
		
		//currentUser.get().setLastName(lastName);

		currentUser.get().setNickname(username);

		currentUser.get().setEmail(email);
		
		
		
		userDao.save(currentUser.get());
		
		return new ResponseEntity("Update Success", HttpStatus.OK);
	}

	@RequestMapping(value ="/getCurrentUser" , method = RequestMethod.GET)
	@ResponseBody
	public User getCurrentUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal(); 
		User user = new User(); 
		System.out.println("test " + (principal == null));
		if(null != principal) {
			System.out.println("principal nullllll ");
			user = this.userDao.findByUsername(principal.getName()) ; 
		}
		
		return user ; 
		
	}
	


}
