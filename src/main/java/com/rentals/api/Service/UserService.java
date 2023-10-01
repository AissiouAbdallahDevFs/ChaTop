package com.rentals.api.Service;
import java.io.Serializable;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rentals.api.Repository.*;
import com.rentals.api.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
    public class NotFoundException extends RuntimeException implements Serializable {
        private static final long serialVersionUID = 1L;

        public NotFoundException(String message) {
            super(message);
        }
    }
	
	 public Iterable<User> getUsers() {
	        return userRepository.findAll();
	    }
	 
	 public Optional<User> GetUserById(Long id) {
		    return userRepository.findById(id);
	}
    
	public User saveUser(User user) {
		User savedUser= userRepository.save(user);
	        return savedUser;
	    }
	
	 public void deleteUser(Long userId) {
	        User existingUser = userRepository.findById(userId).orElse(null);
	        if (existingUser != null) {
	        	userRepository.delete(existingUser);
	        } else {
	            throw new NotFoundException("Enregistrement introuvable");
	        }
	    }
	 
	 public User updatePassword(User updatePassword) {
		   User existingUser = userRepository.findById(updatePassword.getId()).orElse(null);
		   if (existingUser != null) {
			   existingUser.setPassword(updatePassword.getPassword());
		   }
		   User updatedRecord = userRepository.save(existingUser);
           return updatedRecord;
		 
	 }
	   public User updateUser(User updatedUser) {
		   User existingUser =  userRepository.findById(updatedUser.getId()).orElse(null);
	        if (existingUser != null) {
	        	existingUser.setName(updatedUser.getName());
	        	existingUser.setEmail(updatedUser.getEmail());
	        	User updatedRecord = userRepository.save(existingUser);
	            return updatedRecord;
	        } else {
	            throw new NotFoundException("Enregistrement introuvable");
	        }
	    }
}


