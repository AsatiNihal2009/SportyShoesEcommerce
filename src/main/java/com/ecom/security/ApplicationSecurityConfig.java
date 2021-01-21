package com.ecom.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	PasswordEncoder passworEndoder;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","index","/css/*","/js/*").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
		http.csrf().disable();
	}
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		UserDetails johnSmith = User.builder().username("johnsmith@gmail.com")
				.password(passworEndoder.encode("password"))
				.authorities(ApplicationUserRole.USER.getGrandtedAuthorities())
				.roles(ApplicationUserRole.USER.name())
				.build();
		
		UserDetails mikeSmith = User.builder().username("mikesmith@gmail.com")
				.password(passworEndoder.encode("password"))
				.authorities(ApplicationUserRole.ADMIN.getGrandtedAuthorities())
				.roles(ApplicationUserRole.ADMIN.name())
				.build();
		
		return new InMemoryUserDetailsManager(
				johnSmith , mikeSmith
				);
		
	}
	

}
