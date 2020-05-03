package com.i9Developed.i9.db;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.i9Developed.i9.model.User;

@Service
public class DbInit implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public DbInit (UserRepository userRepository){
		this.userRepository = userRepository;
		
	}
	@Override
	public void run(String... args) throws Exception {
		
		//cria usuario
		User maria = new User("maria",passwordEncoder.encode("maria123"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");
        
        
        //salva no banco
        userRepository.saveAll(Arrays.asList(maria,admin,manager));
	}

}
