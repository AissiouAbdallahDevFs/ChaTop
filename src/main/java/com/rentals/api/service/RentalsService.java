package com.rentals.api.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;

import com.rentals.api.model.Rentals;
import com.rentals.api.repository.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;



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
        Iterable<Rentals> rentals = rentalsRepository.findAll();
        return rentals;
    }

    public Optional<Rentals> getRentalById(Long id) {
        return rentalsRepository.findById(id);
    }
    
   public Rentals saveRentals(Rentals rentals, MultipartFile picture) {
        rentals.setCreatedAt(java.time.LocalDateTime.now());
        String pictureFileName = picture.getOriginalFilename();
        rentals.setPicture(pictureFileName);
        Rentals savedRentals = rentalsRepository.save(rentals);
        uploadFileToFolder(picture);
        return savedRentals;
    }

    private void uploadFileToFolder(MultipartFile file) {
        try {
            String folderPath = "file/";
            String originalFileName = file.getOriginalFilename();
            
            Path folder = Paths.get(folderPath);
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }
    
            Path targetPath = folder.resolve(originalFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
