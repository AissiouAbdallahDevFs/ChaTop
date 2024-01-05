package com.rentals.api.Service;

import java.io.Serializable;

import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.rentals.api.model.User;
import com.rentals.api.Repository.UserRepository;
import com.rentals.api.config.JwtConfig;
import com.rentals.api.config.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private SecurityConfig securityConfig;


    @Autowired
    private UserRepository userRepository;

    public class NotFoundException extends RuntimeException implements Serializable {
        private static final long serialVersionUID = 1L;

        public NotFoundException(String message) {
            super(message);
        }
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreatedAt(java.time.LocalDateTime.now());
        User savedUser = userRepository.save(user);
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
            existingUser.setUpdatedAt(java.time.LocalDateTime.now());
        }

        User updatedRecord = userRepository.save(existingUser);
        return updatedRecord;
    }

    public User updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            User updatedRecord = userRepository.save(existingUser);
            return updatedRecord;
        } else {
            throw new NotFoundException("Enregistrement introuvable");
        }
    }

   
   public String authenticate(String email, String password) {
    Optional<User> optionalUser = userRepository.findByEmail(email);

    if (optionalUser.isPresent()) {
        User user = optionalUser.get();

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            long expirationTimeInMillis = jwtConfig.getJwtExpirationMs();

            String token = Jwts.builder()
                    .setSubject(email)
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                    .signWith(SignatureAlgorithm.HS256, jwtConfig.getJwtSecret())
                    .compact();
            return token;
        } else {
            throw new NotFoundException("Mot de passe incorrect");
        }
    } else {
        throw new NotFoundException("Utilisateur introuvable");
    }
}

    
public String getEmailFromToken(String token) {

    String email = Jwts.parser()
            .setSigningKey(jwtConfig.getJwtSecret())
            .parseClaimsJws(token)
            .getBody()
            .getSubject();

    return email;
}

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByToken(String token) {
        String email = getEmailFromToken(token);
        return getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur introuvable"));
    }
    
}
