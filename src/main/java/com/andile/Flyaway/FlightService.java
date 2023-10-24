package com.andile.Flyaway;
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

    public void addFlight(Flight flight) {
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
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM flights")) {

            while (resultSet.next()) {
                String source = resultSet.getString("source");
                String destination = resultSet.getString("destination");
                String airline = resultSet.getString("airline");
                double ticketPrice = resultSet.getDouble("ticket_price");

                Flight flight = new Flight(source, destination, airline, ticketPrice);
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void deleteFlight(int flightId) {
    }

    // You can add more methods for updating and retrieving flight data
}
