package com.mendonca.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mendonca.carros.domain.User;
import com.mendonca.carros.domain.UserRepository;

@Service(value = "userDetailsService")
public class UserDatailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		User user = userRepository.findByLogin(username);
		
		if(user == null) {
			   throw new UsernameNotFoundException("User not found")	;
		}
		
		
	  //    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	  //    if(username.equals("user")) {
	//    	 return User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
	  //    }else {
	   // 	  if(username.equals("admin")) {
	  //  		  return User.withUsername(username).password(encoder.encode("admin")).roles("USER", "ADMIN").build();
	 //   	  }
	    	  
	//      }
	    
		// return User.withUsername(username).password(user.getSenha()).roles("USER").build();
		
	 
	  return user;
	}

}
