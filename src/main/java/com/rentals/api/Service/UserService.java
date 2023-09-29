package com.rentals.api.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentals.api.Repository.*;
import com.rentals.api.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	 public Iterable<User> getUsers() {
	        return userRepository.findAll();
	    }
	 
	 public Optional<User> GetUserById(Long id) {
		    return userRepository.findById(id);
	}

}


