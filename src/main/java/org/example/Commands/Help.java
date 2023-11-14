// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help {
    /**
     * Cand primeste comanda !help din chat-ul de discord returneaza o lista cu comenzile adaugate
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args[0].isEmpty()) {
            String output = "``` Lista de comenzi: \n";
            output += "!help --> afiseaza aceasta lista; \n";
            output += "!about --> informatii cu privire la scopul acestui bot; \n";
            output += "!getStructure [an]--> urmat de un numar intreg pozitiv, genereaza structura anului universitar pentru acel an ; \n";
            output += "!giveFeedback [text] --> urmat de un text,  adauga in baza de date un feedback; \n";
            output += "!printFeedback --> afiseaza toate intrarile din baza de date mentionata mai sus.\n";
            output += "!rssHelp --> indicatii asupra rularii comenzii \"!rssGet\" \n";
            output += "!rssGet [rss link] --> afiseaza ultimele 5 articole de la RSS-ul specificat.\n";
            output += "!printSchedule [group_name] --> Ofiseaza orarul grupei date ca argument.\n";
            output += "!whenClass [class_name] --> afiseaza orarul pentru materia data ca argument.";
            event.getTextChannel().sendMessage( output + "```").queue();
        }
        else
            event.getTextChannel().sendMessage("```Prea multe argumente!```").queue();

    }
}