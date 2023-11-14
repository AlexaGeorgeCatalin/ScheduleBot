// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.StructureCalculator.UniversityYear;

public class GetStructure {
    /**
     * Cand primeste comanda !getStructure din chat-ul de discord verifica daca anumite conditii sunt intalnite si returneaza un mesaj adecvat argumentelor
     * @param args un vector cu parametrii dati in chat-ul de discord
     * @param event o variabila din care putem extrage mai multe date ale unui eveniment
     */
    public static void execute(String[] args, MessageReceivedEvent event){
        if (args.length != 1){
            event.getTextChannel().sendMessage("```Prea multe argumente!```").queue();
        }else{
            try{
                int intYear = Integer.parseInt(args[0]);
                if (intYear > 0 ) {
                    String yearStructure = UniversityYear.generateStructure(intYear);
                    event.getTextChannel().sendMessage(yearStructure).queue();
                }else
                    event.getTextChannel().sendMessage("```Argument invalid!```").queue();
            }catch (NumberFormatException e){
                event.getTextChannel().sendMessage("```Argument invalid!```").queue();
            }
        }
    }
}
