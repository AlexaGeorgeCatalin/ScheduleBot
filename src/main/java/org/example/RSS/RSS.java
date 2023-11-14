// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.RSS;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RSS {
    /**
     * Functie ce ia primele 5 articole de la un link RSS si datele relevante acestuia
     * @param urlAddress String cu o adresa url de tip RSS
     * @return o lista cu articolele gasite
     */
    public static List<String> readRSS(String urlAddress){
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));


            String line;
            List<String> output = new ArrayList<String>();
            int i = 0;
            while ((line = in.readLine()) != null) {
                int titleEndIndex = 0;
                int linkEndIndex = 0;
                int descriptionEndIndex = 0;
                int titleStartIndex = 0;
                int linkStartIndex = 0;
                int descriptionStartIndex = 0;

                while (titleStartIndex >= 0 && i < 6) {
                    String sourceCode = "    Title: ";
                    titleStartIndex = line.indexOf("<title>", titleEndIndex);
                    if (titleStartIndex >= 0) {
                        titleEndIndex = line.indexOf("</title>", titleStartIndex);
                        sourceCode += line.substring(titleStartIndex + "<title>".length(), titleEndIndex) + "\n\n    Link: ";
                    }

                    linkStartIndex = line.indexOf("<link>", linkEndIndex);
                    if (linkStartIndex >= 0) {
                        linkEndIndex = line.indexOf("</link>", linkStartIndex);
                        sourceCode += line.substring(linkStartIndex + "<link>".length(), linkEndIndex) + "\n\n    Description: ";
                    }
                    descriptionStartIndex = line.indexOf("<description>", descriptionEndIndex);
                    if (descriptionStartIndex >= 0) {
                        descriptionEndIndex = line.indexOf("</description>", descriptionStartIndex);
                        sourceCode += line.substring(descriptionStartIndex + "<description>".length(), descriptionEndIndex);
                        output.add(sourceCode);
                        i++;
                    }

                }
            }
            in.close();
            return output;
        }catch(MalformedURLException ue){
            System.out.println("Malformed URL");
        }catch(IOException ioe){
            System.out.println("Something went wrong with the content");
        }
        return null;
    }
}
