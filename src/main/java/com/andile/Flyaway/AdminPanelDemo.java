package com.andile.Flyaway;

import java.util.List;

public class AdminPanelDemo {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        FlightService flightService = new FlightService();
        FlightAdminController flightAdminController = new FlightAdminController(flightService);

        boolean isAdminAuthenticated = adminService.authenticateAdmin("admin", "admin@Flyaway");

        if (isAdminAuthenticated) {
            // View flights
            List<Flight> flights = flightAdminController.viewFlights();
            System.out.println("Flight list:");
            for (Flight flight : flights) {
                System.out.println(flight);
            }

            // Add a flight
            Flight newFlight = new Flight(1,"SourceCity", "DestinationCity", "AirlineName", 250.0);
            flightAdminController.addFlight(newFlight);

            // Delete a flight (replace 'flightId' with the actual flight ID)
            int flightIdToDelete = 1;
            flightAdminController.deleteFlight(flightIdToDelete);
        } else {
            System.out.println("Admin authentication failed.");
        }
    }
}

