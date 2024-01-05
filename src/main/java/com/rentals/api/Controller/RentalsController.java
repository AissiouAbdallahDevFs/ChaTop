package com.rentals.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;
import com.rentals.api.Service.RentalsService;
import com.rentals.api.Service.UserService;
import com.rentals.api.model.Rentals;
import com.rentals.api.model.User;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Api(tags = "Rentals", description = "Operations related to rentals")
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/rentals")
    @ApiOperation(value = "Get all rentals", notes = "Returns a list of all rentals.")
    public Map<String, List<Rentals>> getRentals() {
        List<Rentals> rentalList = (List<Rentals>) rentalsService.getRentals();
        Map<String, List<Rentals>> response = new HashMap<String, List<Rentals>>();
        response.put("rentals", rentalList);
        return response;
    }
    

    @GetMapping("rentals/{id}")
    @ApiOperation(value = "Get rental by ID", notes = "Returns a rental by its ID.")
    public ResponseEntity<Rentals> getRentalById(@PathVariable Long id) {
        Optional<Rentals> rental = rentalsService.getRentalById(id);

        if (rental.isPresent()) {
            return new ResponseEntity<>(rental.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/rentals", consumes = {"multipart/form-data"})
    @ApiOperation(value = "Create a new rental", notes = "Creates a new rental.")
    public Rentals saveRentals(@RequestHeader("Authorization") String authorizationHeader,
                               @RequestParam("picture") MultipartFile picture,
                               @RequestParam("surface") BigDecimal surface,
                               @RequestParam("price") BigDecimal price,
                               @RequestParam("description") String description,
                               @RequestParam("name") String name) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        User user = userService.getUserByToken(token);

        Rentals rentals = new Rentals();
        rentals.setName(name);
        rentals.setOwner(user);
        rentals.setSurface(surface);
        rentals.setPrice(price);
        rentals.setDescription(description);
        rentals.setPicture(picture.getOriginalFilename());
        rentals.setCreatedAt(java.time.LocalDateTime.now());
        Rentals savedRentals = rentalsService.saveRentals(rentals, picture);

        return savedRentals;
    }
}

