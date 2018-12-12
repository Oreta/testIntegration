package com.ausecourse.model;


import com.ausecourse.model.security.Authority;
import com.ausecourse.model.security.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import jdk.internal.jline.internal.Log;


@RedisHash("User")
public class User implements UserDetails,Serializable {

	//private static final Logger LOG = LoggerFactory.getLogger(User.class);

	@Id
	private String id;
	private String name;
	@Indexed private String username;
	private String password;

	private String email;
	private int tel;
	private int numeroRoad;
	private String Road;
	private String city;
	private String country; // tout en maj

	private boolean client;
	private boolean deliverer;
	
	//se rempli avec les identifiants des liste de courses dont il est notifie quand il est en mode livreur
	private List<String> courses = new ArrayList<String>(); 
	
	
	
	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public boolean isDeliverer() {
		return deliverer;
	}

	public void setDeliverer(boolean deliverer) {
		this.deliverer = deliverer;
	}

	public void setClient(boolean client) {
		this.client = client;
	}


	private @Reference Set<UserRole> userRoles = new HashSet<UserRole>();

	public User() {
		super();
	}

	public User(String id) {
		super();
		this.id = id;
	}

	public User(String id, String name) {
		super();
		this.id = id;
		this.name=name;
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		System.out.println("userrr " + this.username);
		return username;
	}

	public void setNickname(String nickname) {
		this.username = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getNumeroRoad() {
		return numeroRoad;
	}

	public void setNumeroRoad(int numeroRoad) {
		this.numeroRoad = numeroRoad;
	}

	public String getRoad() {
		return Road;
	}

	public void setRoad(String road) {
		Road = road;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public User(String id, String name, String nickname, String email, int tel, int numeroRoad, String road, String city,
			String country) {
		super();
		this.id = id;
		this.name = name;
		this.username = nickname;
		this.email = email;
		this.tel = tel;
		this.numeroRoad = numeroRoad;
		Road = road;
		this.city = city;
		this.country = country;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + ", tel=" + tel + ", numeroRoad=" + numeroRoad + ", Road=" + Road + ", city=" + city
				+ ", country=" + country + ", client=" + client + ", deliverer=" + deliverer + ", userRoles="
				+ userRoles + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(UserRole ur : this.userRoles) {
			authorities.add(new Authority(ur.getRole().getName()));
			System.out.println("User file : " + "user authorities : " + ur.getRole().getName());
			//Log.warn("user authorities {}" , ur.getRole().getName());
		}
		return authorities;
	}



	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password ;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {

		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public User(String id, String name, String username, String password, String email, int tel, int numeroRoad,
			String road, String city, String country, boolean client, boolean deliverer, Set<UserRole> userRoles) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.tel = tel;
		this.numeroRoad = numeroRoad;
		Road = road;
		this.city = city;
		this.country = country;
		this.client = client;
		this.deliverer = deliverer;
		this.userRoles = userRoles;
	}

	public boolean isClient() {
		return client;
	}
}
