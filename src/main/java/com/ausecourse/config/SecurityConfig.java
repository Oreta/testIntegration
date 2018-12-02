package com.ausecourse.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import org.springframework.session.web.http.HttpSessionIdResolver;


import com.ausecourse.dao.UserSecurityDao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private UserSecurityDao userSecurityService;


	private static final String[] PUBLIC_MATCHES = { "/test/**", "/user/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().httpBasic().and()
				.authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll()
				.anyRequest().authenticated();
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(this.userSecurityService).passwordEncoder(SecurityConfig.passwordEncoder());
	}
	
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new HeaderHttpSessionIdResolver("x-auth-token");
    }

	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder(){
		//return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
		return new BCryptPasswordEncoder(11);
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    AppAuthProvider authProvider
	      = new AppAuthProvider();
	    authProvider.setUserDetailsService(userSecurityService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	

	
}
