package com.andile.Flyaway;

import java.util.List;

public class FlightAdminController {
    private FlightService flightService;

    public FlightAdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> viewFlights() {
        return flightService.getAllFlights();
    }

    public void addFlight(Flight flight) {
        flightService.addFlight(flight);
    }

    public void deleteFlight(int flightId) {
        flightService.deleteFlight(flightId);
    }
}
