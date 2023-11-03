package com.rentals.api.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.HashMap;
import java.util.Optional;
import com.rentals.api.Service.RentalsService;
import com.rentals.api.model.Rentals;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Api(tags = "Rentals", description = "Operations related to rentals")
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;

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

    @PostMapping(value = "/rentals", consumes = "application/json")
    @ApiOperation(value = "Create a new rental", notes = "Creates a new rental.")
    public Rentals createRentals(@RequestBody Rentals rentals) {
        return rentalsService.saveRentals(rentals);
    }
}
