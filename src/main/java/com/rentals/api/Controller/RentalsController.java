package com.rentals.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.rentals.api.Service.RentalsService;
import com.rentals.api.model.Rentals;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;

    @GetMapping("rentals")
    public ResponseEntity<Iterable<Rentals>> getRentals() {
        Iterable<Rentals> rentals = rentalsService.getRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("rentals/{id}")
    public ResponseEntity<Rentals> getRentalById(@PathVariable Long id) {
        Optional<Rentals> rental = rentalsService.getRentalById(id);

        if (rental.isPresent()) {
            return new ResponseEntity<>(rental.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
