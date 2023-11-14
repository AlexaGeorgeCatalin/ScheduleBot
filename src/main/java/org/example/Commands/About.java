// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class About {
    /**
     * Cand primeste comanda !about din chat-ul de discord raspunde in chat cu un anumit mesaj
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args[0].isEmpty())
            event.getTextChannel().sendMessage("```Sunt un bot creat de catre Alexa George-Catalin```").queue();
        else
            event.getTextChannel().sendMessage("```Prea multe argumente!```").queue();
    }
}
