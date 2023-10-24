package com.andile.Flyaway;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
class AdminController {
    // For simplicity, using an in-memory list for storing flight data
    private final List<Flight> flights = new ArrayList<>();
    private int flightIdCounter = 1;

    // Route for admin authentication (replace with actual authentication logic)
    @PostMapping("/auth")
    public String adminAuth(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("password")) {
            return "Admin authenticated.";
        } else {
            return "Authentication failed.";
        }
    }

    // Route to get the list of flights
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return flights;
    }

    // Route to add a flight
    @PostMapping("/flights")
    public Flight addFlight(@RequestBody Flight newFlight) {
        newFlight.setId(flightIdCounter++);
        flights.add(newFlight);
        return newFlight;
    }

    // Route to delete a flight
    @DeleteMapping("/flights/{id}")
    public String deleteFlight(@PathVariable int id) {
        flights.removeIf(flight -> flight.getId() == id);
        return "Flight with ID " + id + " deleted.";
    }
}
