package com.rentals.api.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.rentals.api.Service.RentalsService;
import com.rentals.api.model.Rentals;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Api(tags = "Rentals", description = "Operations related to rentals")
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;

    @GetMapping("rentals")
    @ApiOperation(value = "Get all rentals", notes = "Returns a list of all rentals.")
    public ResponseEntity<Iterable<Rentals>> getRentals() {
        Iterable<Rentals> rentals = rentalsService.getRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
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

    @PostMapping("/rentals")
    @ApiOperation(value = "Create a new rental", notes = "Creates a new rental.")
    public Rentals createRentals(@RequestBody Rentals rentals) {
        return rentalsService.saveRentals(rentals);
    }
}
