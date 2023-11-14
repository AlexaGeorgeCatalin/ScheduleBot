// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RSSHelp {
    /**
     * Cand primeste comanda !rssHelpe din chat-ul de discord acesta retuneaza niste instructiuni care ne ajuta sa facem rost de un link RSS
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args[0].isEmpty())
            event.getTextChannel().sendMessage(  "```Instructiuni pentru a gasi adresa RSS a site-ului dori: \n\n 1.Intrati pe site-ul dorit. \n 2.Faceti click dreapta pe pagina si apasati " +
                    "pe \"view source\". \n 3.Apasati Ctrl+F si introduceti \"rss\" in search bar. \n 4.Daca nu ati gasit nimic inseamna ca pagina nu are rss. \n 5.Daca ati gasit" +
                    " o linie ce contine rss copiati link-ul de acolo si dati-l ca argument in comanda \"!rssGet\". ```").queue();
        else
            event.getTextChannel().sendMessage("```Prea multe argumente!```").queue();

    }
}
