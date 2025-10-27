package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL  = "jdbc:mysql://localhost:3306/store_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // XAMPP por defecto
    private static final String PASS = "";     // tu contraseña (vacío si no pusiste)

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
