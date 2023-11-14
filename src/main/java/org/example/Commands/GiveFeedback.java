// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.DatabaseDAO.DatabaseDAO;
import org.example.Utils.Database;
import java.sql.SQLException;

public class GiveFeedback {
    /**
     * Cand primeste comanda !giveFeedback din chat-ul de discord verifica daca anumite conditii sunt intalnite, se conecteaza la baza de date si introduce in aceasta anumite date
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args[0].isEmpty())
            event.getTextChannel().sendMessage("```Te rog sa introduci si un feedback```").queue();
        else {
            Database database = new Database();
            database.createConnection();

            String author = String.valueOf(event.getAuthor());
            author = author.replaceFirst("U:", "");
            try {
                DatabaseDAO.create(author, args);
                event.getTextChannel().sendMessage("```Feedback adaugat```").queue();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            database.closeConnection();
        }
    }
}
