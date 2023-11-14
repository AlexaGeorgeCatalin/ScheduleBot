// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.DatabaseDAO;

import org.example.Utils.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO {
    /**
     * Adauga intr-o baza de date autorul si feedback acestuia
     * @param author string cu numele autorului
     * @param content string cu continutul de adaugat
     * @throws SQLException
     */
    public static void create(String author, String content[]) throws SQLException {
        String contentInput = "";
        for(String words : content)
            contentInput += words + " ";
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into feedback (author, content) values (?,?)")) {
            pstmt.setString(1, author);
            pstmt.setString(2, contentInput);
            pstmt.executeUpdate();
        }
    }

    /**
     * Ia din baza de date randurile in care coloana GROUP_NAME coincide cu groupName
     * @param groupName numele grupului
     * @return o lista cu randurile gasite
     * @throws SQLException
     */
    public static List<String> getScheduleByGroup(String groupName) throws SQLException {

        List<String> output = new ArrayList<String> ();

        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet result = stmt.executeQuery("SELECT *  FROM schedule WHERE GROUP_NAME  = '" + groupName + "'")){
            while(result.next()){
                output.add("Ziua: " + result.getString(2) + "; Materia: " + result.getString(3) +
                        "; Tipul materiei: " + result.getString(4) + "; Interval orar: " +
                        result.getString(5) + "\n");
            }
        }
        return output;
    }

    /**
     * Ia din baza de date randurile in care coloana CLASS_NAME coincide cu className
     * @param className numele materiei
     * @returno o lista cu randurile gasite
     * @throws SQLException
     */
    public static List<String> getClasses(String className) throws SQLException {

        List<String> output = new ArrayList<String> ();

        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet result = stmt.executeQuery("SELECT *  FROM schedule WHERE CLASS_NAME  = '" + className + "'")){
            while(result.next()){
                output.add("Grupa: " + result.getString(1) + " Ziua: " + result.getString(2) + "; Materia: " + result.getString(3) +
                        "; Tipul materiei: " + result.getString(4) + "; Interval orar: " +
                        result.getString(5) + "\n");
            }
        }
        return output;
    }

    /**
     * Functie ce returneaza toate datele din tabela feedback
     * @return datele gasite in tabela
     * @throws SQLException
     */
    public static String getList() throws SQLException {

        String output = "";

        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet result = stmt.executeQuery("SELECT * FROM feedback")){
            while(result.next()){
                output += "Autor: " + result.getString(1) + "; Continut: " + result.getString(2) + "\n";
            }
        }
        return output;
    }

}


