package com.example.barcodegenerator_v1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:mydatabase.sqlite";

       // initialize method
       public static void initialize() {

           //Uz pomoć koda ispod dropaju se stare tablice u slučaju mijenjanja naziva ili dodavanja novih polja
           //String dropUsersTable = "DROP TABLE IF EXISTS users;";
           //String dropRecepeintsTable = "DROP TABLE IF EXISTS recepients;";

           String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id integer PRIMARY KEY,
                    name text NOT NULL,
                    IBAN text NOT NULL,
                    email text NOT NULL,
                    street text NOT NULL,
                    zip text NOT NULL,
                    caller text
                );""";

           String createRecepeintsTable = """
                CREATE TABLE IF NOT EXISTS recepients (
                    id integer PRIMARY KEY,
                    name text,
                    address text,
                    city text,
                    description text,
                    email text,
                    model text,
                    poziv_na_broj text,
                    amount text
                );""";

           try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
              // stmt.execute(dropUsersTable);
              // stmt.execute(dropRecepeintsTable);
               stmt.execute(createUsersTable);
               stmt.execute(createRecepeintsTable);
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }
    public static List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String IBAN = rs.getString("IBAN");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String zip = rs.getString("zip");
                String caller = rs.getString("caller");

                users.add(new User( name, IBAN, email, street, zip, caller));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    // insertUser method
    public static void insertUser(String name, String IBAN, String email, String street, String zip, String caller) {
        String sql = "INSERT INTO users(name, IBAN, email, street, zip, caller) VALUES(?, ?, ?, ?, ?, ?)";
        executeUserUpdate(name, IBAN, email, street, zip, caller, sql);
    }

    // updateUser method - updates the first user (only user) in the database
    public static void updateUser(String name, String IBAN, String email, String street, String zip, String caller) {
        String sql = "UPDATE users SET name = ?, IBAN = ?, email = ?, street = ?, zip = ?, caller = ? WHERE id = 1";
        executeUserUpdate(name, IBAN, email, street, zip, caller, sql);
    }

    // deleteUser method
    public static void deleteUser() {
        String sql = "DELETE FROM users WHERE id = 1";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // updateUserData method
    public static void executeUserUpdate(String name, String IBAN, String email, String street, String zip, String caller, String sqlStatement) {

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
            pstmt.setString(1, name);
            pstmt.setString(2, IBAN);
            pstmt.setString(3, email);
            pstmt.setString(4, street);
            pstmt.setString(5, zip);
            pstmt.setString(6, caller);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //insert recipient
    public static void insertRecepient(String name, String address, String city, String description, String email, String model, String pozivNaBroj, String amount) {
        String sql = "INSERT INTO recepients(name, address, city, description, email, model, poziv_na_broj, amount) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, city);
            pstmt.setString(4, description);
            pstmt.setString(5, email);
            pstmt.setString(6, model);
            pstmt.setString(7, pozivNaBroj);
            pstmt.setString(8, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve all recipients
    public static List<Recepient> getAllRecepients() {
        String sql = "SELECT * FROM recepients";
        List<Recepient> recepients = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String description = rs.getString("description");
                String email = rs.getString("email");
                String model = rs.getString("model");
                String pozivNaBroj = rs.getString("poziv_na_broj");
                String amount = rs.getString("amount");

                recepients.add(new Recepient(id, name, address, city, description, email, model, pozivNaBroj, amount));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return recepients;
    }

    // Method to delete a recipient by ID
    public static void deleteRecipient(int id) {
        String sql = "DELETE FROM recipients WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Recipient with ID " + id + " deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting recipient: " + e.getMessage());
        }
    }
}


