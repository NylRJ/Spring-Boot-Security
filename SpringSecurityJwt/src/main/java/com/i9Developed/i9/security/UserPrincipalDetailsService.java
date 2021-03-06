 package com.i9Developed.i9.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.i9Developed.i9.db.UserRepository;
import com.i9Developed.i9.model.User;

@Service
public class UserPrincipalDetailsService implements UserDetailsService{
	//retirar o contrutor e colocar um anotation autowared
	private UserRepository userRepository;

public UserPrincipalDetailsService(UserRepository userRepository) {
	
	this.userRepository = userRepository;
}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal(user);
		
		return userPrincipal;
	}

}
