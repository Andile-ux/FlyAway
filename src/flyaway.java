package src;

import java.sql.*;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public class flyaway {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        initializeDatabaseConnection();
        Scanner scanner = new Scanner(System.in);


        statement.close();
        connection.close();
    }

    private static void initializeDatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/flyaway";
        String userName = "root";
        String password = "Gawoziwami@01";
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
    }





}
