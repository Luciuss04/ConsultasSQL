package com.luciuss4.tema02.boletin1.Ejercicio1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "root";
    private static final String PASSWORD = "test";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}