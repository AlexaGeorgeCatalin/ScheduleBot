// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.Utils;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.Commands.*;
import org.jetbrains.annotations.NotNull;

public class BotListeners extends ListenerAdapter {
    //prefixul comenzilor introduse de catre utilizator
    final String commandPrefix = "!";

    /**
     * Aceasta functie detecteaza cand se intampla un eveniment de tipul "mesaj primit" care sa nu fie de la un alt bot si sa nu fie un mesaj privat
     * De asemenea desparte mesajul in mai multe cuvinte, verifica daca are prefixul "!" si apeleaza comanda ceruta daca e cazul sau ne spune ca am introdus o comanda gresita
     * @param event  variabila din care putem extrage mai multe date ale unui eveniment
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if( (!event.getAuthor().isBot()) && event.getChannelType().equals(ChannelType.TEXT)) {

            //ia mesajul primit si il desparte in cuvinte
            String chatMessage = event.getMessage().getContentRaw();
            String[] splitCommand = chatMessage.split(" ");
            String[] firstWord = splitCommand[0].split("");

            //verifica daca se afla prefixul "!" in primul cuvant al mesajului
            if (firstWord[0].equals(commandPrefix)){
                String command = splitCommand[0].replaceFirst("[" + commandPrefix + "]", "");
                String commandArgs = event.getMessage().getContentRaw().replace(commandPrefix + command, "");
                commandArgs = commandArgs.replaceFirst(" ", "");
                String[] args = commandArgs.split(" ");


                //Switch ce executa comenzile date
                switch (command){
                    case "help":
                        Help.execute(args, event);
                        break;
                    case "about":
                        About.execute(args, event);
                        break;
                    case "getStructure":
                        GetStructure.execute(args, event);
                        break;
                    case "giveFeedback":
                        GiveFeedback.execute(args, event);
                        break;
                    case "printFeedback":
                        PrintFeedback.execute(args, event);
                        break;
                    case "rssHelp":
                        RSSHelp.execute(args, event);
                        break;
                    case "rssGet":
                        RSSGet.execute(args, event);
                        break;
                    case "printSchedule":
                        PrintSchedule.execute(args,event);
                        break;
                    case "whenClass":
                        WhenClass.execute(args,event);
                        break;
                    default:
                        event.getTextChannel().sendMessage("```Comanda invalida, scrie \"!help\" pentru a vedea lista de comenzi```").queue();
                }
            }
        }
    }
}
