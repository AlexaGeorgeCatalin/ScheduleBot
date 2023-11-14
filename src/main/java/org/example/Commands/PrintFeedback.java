// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import org.example.DatabaseDAO.DatabaseDAO;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.Utils.Database;
import java.sql.SQLException;

public class PrintFeedback {
    /**
     * Cand primeste comanda !printFeedback din chat-ul de discord verifica daca anumite conditii sunt intalnite, se conecteaza la baza de date si apeleaza o functie ce returneaza datele cerute
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args[0].isEmpty()) {
            try {
                Database database = new Database();
                database.createConnection();

                String mesaj = DatabaseDAO.getList();
                event.getTextChannel().sendMessage("```" + mesaj + "```").queue();

                database.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        else
            event.getTextChannel().sendMessage("```Prea multe argumente!```").queue();
    }
}
