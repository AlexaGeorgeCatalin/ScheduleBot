// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.RSS.RSS;
import java.util.List;

public class RSSGet {
    /**
     * Cand primeste comanda !rssGet din chat-ul de discord verifica daca anumite conditii sunt intalnite si apeleaza o functie primeste un feed RSS
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args.length != 1){
            event.getTextChannel().sendMessage("```Prea multe argumente!```").queue();
        }else{
            String URL = args[0];
            List<String> feed = RSS.readRSS(URL);

            for (int i = 0; i < feed.size(); i++) {
                if(i>0)
                    event.getTextChannel().sendMessage("```" + feed.get(i) +"```").queue();
            }

        }
    }
}