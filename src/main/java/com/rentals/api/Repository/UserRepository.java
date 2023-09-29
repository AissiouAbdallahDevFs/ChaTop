package com.rentals.api.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rentals.api.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

}

