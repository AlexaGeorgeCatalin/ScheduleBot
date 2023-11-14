// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import org.example.Utils.BotListeners;
import javax.security.auth.login.LoginException;

public class ScheduleBot{
    /**
     * Functia main a aplicatiei in care se porneste botul de discord
     * @param args argumentul de la linia de comanda
     * @throws LoginException
     */
    public static void main(String[] args) throws LoginException {

        String botToken = "MTExMTY1MzU0MjM2OTk1MTc3NA.G61vLI.E2PqsQQvx9iqrMQWIsHMKE2dz2XJbytA3RmLk4";

        JDABuilder.createDefault(botToken)
                .addEventListeners(new BotListeners())
                .setActivity(Activity.playing("scrie !help"))
                .build();
    }
}