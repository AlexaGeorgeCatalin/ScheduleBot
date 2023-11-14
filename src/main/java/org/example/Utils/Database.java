// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Utils;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "BOT";
    private static final String PASSWORD = "BOT";

    private static HikariDataSource dataSource;
    public Database(){}

    /**
     * Functie ce returneaza o conexiune
     * @return conexiunea ceruta daca aceasta exista
     */
    public static Connection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creeaza o conexiunea la o baza de date de tip poo folosin
     */
    public static void createConnection(){
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
    }

    /**
     * Inchide o conexiune la o baza de date
     */
    public static void closeConnection(){
        dataSource.close();
    }
}
