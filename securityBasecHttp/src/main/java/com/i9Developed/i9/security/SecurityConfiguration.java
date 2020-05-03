package com.i9Developed.i9.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("admin")
				.password(passwordEncoder().encode("admin123"))
				.roles("ADMIN").authorities("ACCESS_TES1","ACCESS_TES2","ROLE_ADMIN")
				.and()
				.withUser("nyl")
				.password(passwordEncoder().encode("nyl123"))
				.roles("USER")
				.and()
				.withUser("manager")
				.password(passwordEncoder().encode("manager123"))
				.authorities("ACCESS_TES1","ROLE_MANAGER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/index.html").permitAll()
				.antMatchers("/profile/**").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/manage/**").hasAnyRole("ADMIN","MANAGER")
				.antMatchers("/api/public/test1").hasAuthority("ACCESS_TES1")
				.antMatchers("/api/public/test2").hasAuthority("ACCESS_TES2")
				.antMatchers("/api/public/users").hasRole("ADMIN")
				.and()
				.httpBasic();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();

	}
}
