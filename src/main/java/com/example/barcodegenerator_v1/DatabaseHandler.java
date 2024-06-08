package com.example.barcodegenerator_v1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:mydatabase.sqlite";

       // initialize method
    public static void initialize() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id integer PRIMARY KEY,
                    name text NOT NULL,
                    description text NOT NULL,
                    email text NOT NULL,
                    street text NOT NULL,
                    zip text NOT NULL,
                    caller text NOT NULL
                );""";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
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
                String description = rs.getString("description");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String zip = rs.getString("zip");
                String caller = rs.getString("caller");

                users.add(new User( name, description, email, street, zip, caller));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    // insertUser method
    public static void insertUser(String name, String description, String email, String street, String zip, String caller) {
        String sql = "INSERT INTO users(name, description, email, street, zip, caller) VALUES(?, ?, ?, ?, ?, ?)";
        executeUserUpdate(name, description, email, street, zip, caller, sql);
    }

    // updateUser method - updates the first user (only user) in the database
    public static void updateUser(String name, String description, String email, String street, String zip, String caller) {
        String sql = "UPDATE users SET name = ?, description = ?, email = ?, street = ?, zip = ?, caller = ? WHERE id = 1";
        executeUserUpdate(name, description, email, street, zip, caller, sql);
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
    public static void executeUserUpdate(String name, String description, String email, String street, String zip, String caller, String sqlStatement) {

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, email);
            pstmt.setString(4, street);
            pstmt.setString(5, zip);
            pstmt.setString(6, caller);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}