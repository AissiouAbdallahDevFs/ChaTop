package com.rentals.api.Service;
import org.springframework.stereotype.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;

import com.rentals.api.Repository.*;
import com.rentals.api.model.Rentals;



@Service
public class RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;
    
    
    public class NotFoundException extends RuntimeException implements Serializable {
        private static final long serialVersionUID = 1L;

        public NotFoundException(String message) {
            super(message);
        }
    }


    public Iterable<Rentals> getRentals() {
        return rentalsRepository.findAll();
    }

    public Optional<Rentals> getRentalById(Long id) {
        return rentalsRepository.findById(id);
    }
    
    public Rentals saveRentals(Rentals rentals) {
    	Rentals savedRentals = rentalsRepository.save(rentals);
    	return savedRentals;
    }
    
    public Rentals updateRentals(Rentals updatedRentals) {
       
        Rentals existingRentals = rentalsRepository.findById(updatedRentals.getId()).orElse(null);
        
        if (existingRentals != null) {
            existingRentals.setName(updatedRentals.getName());
            existingRentals.setSurface(updatedRentals.getSurface());
            existingRentals.setPrice(updatedRentals.getPrice());
            existingRentals.setPicture(updatedRentals.getPicture());
            existingRentals.setDescription(updatedRentals.getDescription());
            existingRentals.setOwner(updatedRentals.getOwner());
            existingRentals.setUpdatedAt(updatedRentals.getUpdatedAt());
            Rentals updatedRecord = rentalsRepository.save(existingRentals);
            return updatedRecord;
        } else {
            throw new NotFoundException("Enregistrement introuvable");
        }
    }
    

    public void deleteRentals(Long rentalsId) {
       
        Rentals existingRentals = rentalsRepository.findById(rentalsId).orElse(null);
        
        if (existingRentals != null) {
        
            rentalsRepository.delete(existingRentals);
        } else {
           
            throw new NotFoundException("Enregistrement introuvable");
        }
    }

    

}
