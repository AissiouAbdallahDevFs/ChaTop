package com.rentals.api.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentals.api.Repository.*;
import com.rentals.api.model.Rentals;


@Service
public class RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;

    public Iterable<Rentals> getRentals() {
        return rentalsRepository.findAll();
    }

    public Optional<Rentals> getRentalById(Long id) {
        return rentalsRepository.findById(id);
    }
}
