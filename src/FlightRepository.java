package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {
    private Connection connection; // Set up the database connection

    public List<Flight> searchFlights(String source, String destination) {
        List<Flight> flights = new ArrayList<>();

        try {
            String query = "SELECT * FROM flights WHERE source = ? AND destination = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, source);
            preparedStatement.setString(2, destination);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getInt("id"));
                flight.setSource(resultSet.getString("source"));
                flight.setDestination(resultSet.getString("destination"));
                flight.setAirline(resultSet.getString("airline"));
                flight.setTicketPrice(resultSet.getDouble("ticket_price"));
                flights.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flights;
    }
}
