package com.andile.Flyaway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/flyaway";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public FlightService() {
        // Initialize the database connection in the constructor
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Flight addFlight(Flight flight) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO flights (source, destination, airline, ticket_price) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, flight.getSource());
            preparedStatement.setString(2, flight.getDestination());
            preparedStatement.setString(3, flight.getAirline());
            preparedStatement.setDouble(4, flight.getTicketPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM flights")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String source = resultSet.getString("source");
                String destination = resultSet.getString("destination");
                String airline = resultSet.getString("airline");
                double ticketPrice = resultSet.getDouble("ticket_price");


                Flight flight = new Flight(id,source, destination, airline, ticketPrice);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void deleteFlight(int flightId) {
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable int id) throws FlightNotFoundException {
        FlightService flightService = new FlightService();
        Flight flight = flightService.getFlightById(id);

        if (flight != null) {
            return flight; // Return the flight as a JSON response
        } else {
            throw new FlightNotFoundException("Flight with ID " + id + " not found");
        }
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable int id, @RequestBody Flight updatedFlight) {
        FlightService flightService = new FlightService();
        Flight existingFlight = flightService.getFlightById(id);

        if (existingFlight != null) {
            // Ensure that the ID of the updatedFlight matches the ID in the path
            if (updatedFlight.getId() == id) {
                // Update the existing flight with the new data
                existingFlight.setSource(updatedFlight.getSource());
                existingFlight.setDestination(updatedFlight.getDestination());
                existingFlight.setAirline(updatedFlight.getAirline());
                existingFlight.setTicketPrice(updatedFlight.getTicketPrice());

                // Save the updated flight to the service
                Flight updated = flightService.updateFlight(updatedFlight.getId(),existingFlight);
                return updated;
            } else {
                throw new IllegalArgumentException("Flight ID in the request body does not match the URL path.");
            }
        } else {
            throw new FlightNotFoundException("Flight with ID " + id + " not found");
        }
    }

    // You can add more methods for updating and retrieving flight data
}
