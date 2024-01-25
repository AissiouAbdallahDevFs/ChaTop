package com.rentals.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rentals.api.model.Rentals;

public interface RentalsRepository extends JpaRepository<Rentals, Long> {
	
}

