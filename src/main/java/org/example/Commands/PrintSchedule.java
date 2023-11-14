// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.DatabaseDAO.DatabaseDAO;
import org.example.Utils.Database;
import java.sql.SQLException;
import java.util.List;

public class PrintSchedule {
    /**
     * Cand primeste comanda !printSchedule din chat-ul de discord verifica daca anumite conditii sunt intalnite, se conecteaza la baza de date si apeleaza o functie ce returneaza datele cerute
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args.length != 1){
            event.getTextChannel().sendMessage("```Prea multe argumente!```").queue();
        }else{
            String group = args[0];
            group = group.toUpperCase();

            boolean belong = false;
            String[] groups ={"3A5", "3B5"};
            for (String el : groups)
                if(el.equals(group))
                    belong = true;

            if(belong){
                try {
                    Database database = new Database();
                    database.createConnection();

                    List<String> mesaj = DatabaseDAO.getScheduleByGroup(group);

                    for (int i = 0; i < mesaj.size(); i++) {
                        if(i>0)
                            event.getTextChannel().sendMessage("```" + mesaj.get(i) +"```").queue();
                    }
                    database.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }else
                event.getTextChannel().sendMessage("```Grupa " + args[0] + " nu exista!```").queue();
        }
    }
}
