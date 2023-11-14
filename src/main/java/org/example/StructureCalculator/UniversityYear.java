// Copyright (c) 2023 Alexa George-Catalin. All rights reserved.
package org.example.StructureCalculator;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UniversityYear {
    /**
     * Functie ce calculeaza cate zile sunt intre 2 date
     * @param startDate ziua de la care calculam
     * @param endDate ziua pana in care calculam
     * @return numar de zile dintre startDate si endDate
     */
    private static int getDaysBetween(Calendar startDate, Calendar endDate) {
        long startMillis = startDate.getTimeInMillis();
        long endMillis = endDate.getTimeInMillis();
        return (int) ((endMillis - startMillis) / (24 * 60 * 60 * 1000));
    }

    /**
     * Calculeaza in ce data pica Pastele Ortodox intr-un anumit an
     * @param year anul pentru care calculam
     * @return data in care este Pastele
     */
    private static Calendar calculateOrthodoxEaster(int year) {
        Calendar easter = Calendar.getInstance();

        int r1 = year % 4;
        int r2 = year % 7;
        int r3 = year % 19;
        int r4 = (19 * r3 + 15) % 30;
        int r5 = (2 * r1 + 4 * r2 + 6 * r4 + 6) % 7;
        int day = r5 + r4 + 13;

        if (day > 39) {
            day = day - 39;
            easter.set(year, 4, day);
        } else if (day > 9) {
            day = day - 9;
            easter.set(year, 3, day);
        } else {
            day = day + 22;
            easter.set(year, 2, day);
        }
        return easter;
    }

    /**
     * Genereaza un string in care este pusa sructura anului universitar pentru un anumit an
     * @param year anul pentru care vrem sa calculam
     * @return  string-ul generat
     */
    public static String generateStructure(int year) {

        String output="```Structura anului universitar " + String.valueOf(year) +"-"+String.valueOf(year+1) + "\n\n";
        // Formatul datei
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar semester1Start = Calendar.getInstance();
        semester1Start.set(Calendar.YEAR, year);
        semester1Start.set(Calendar.MONTH, Calendar.OCTOBER);
        semester1Start.set(Calendar.DAY_OF_MONTH, 1);
        while (semester1Start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            semester1Start.add(Calendar.DAY_OF_MONTH, -1);
        }

        Calendar winterBreakStart = Calendar.getInstance();
        winterBreakStart.set(Calendar.YEAR, year);
        winterBreakStart.set(Calendar.MONTH, Calendar.DECEMBER);
        winterBreakStart.set(Calendar.DAY_OF_MONTH, 24);

        Calendar semester1StudyEnd = (Calendar) winterBreakStart.clone();
        semester1StudyEnd.add(Calendar.DAY_OF_YEAR, -1);

        Calendar winterBreakEnd = (Calendar) winterBreakStart.clone();
        winterBreakEnd.add(Calendar.WEEK_OF_YEAR, 2);

        Calendar semester1StudyStart2 = (Calendar) winterBreakEnd.clone();
        semester1StudyStart2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar semester1StudyEnd2 = (Calendar) semester1StudyStart2.clone();
        semester1StudyEnd2.add(Calendar.DAY_OF_YEAR, 14*7 - getDaysBetween(semester1Start, semester1StudyEnd) - 1);


        Calendar exams1Start = (Calendar) semester1StudyEnd2.clone();
        exams1Start.add(Calendar.DAY_OF_YEAR,1);
        Calendar exams1End = (Calendar) exams1Start.clone();
        exams1End.add(Calendar.WEEK_OF_YEAR,3);

        Calendar intersemestrialBreakStart = (Calendar) exams1End.clone();
        intersemestrialBreakStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar intersemestrialBreakEnd = (Calendar) intersemestrialBreakStart.clone();
        intersemestrialBreakEnd.add(Calendar.WEEK_OF_YEAR, 2);

        Calendar orthodoxEaster = calculateOrthodoxEaster(year + 1);
        Calendar easterBreakStart = (Calendar) orthodoxEaster.clone();
        easterBreakStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar easterBreakEnd = (Calendar) easterBreakStart.clone();
        easterBreakEnd.add(Calendar.WEEK_OF_YEAR, 1);

        Calendar semester2StudyStart1 = (Calendar) intersemestrialBreakEnd.clone();
        semester2StudyStart1.add(Calendar.DAY_OF_YEAR, 1);
        Calendar semester2StudyEnd1 = (Calendar) easterBreakStart.clone();
        semester2StudyEnd1.add(Calendar.DAY_OF_YEAR, -1);


        Calendar semester2StudyStart2 = (Calendar) easterBreakEnd.clone();
        semester2StudyStart2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar semester2StudyEnd2 = (Calendar) semester2StudyStart2.clone();
        semester2StudyEnd2.add(Calendar.DAY_OF_YEAR, 14*7 - getDaysBetween(semester2StudyStart1, semester2StudyEnd1) - 1);

        Calendar exams2Start = (Calendar) semester2StudyEnd2.clone();
        exams2Start.add(Calendar.DAY_OF_YEAR,1);
        Calendar exams2End = (Calendar) exams2Start.clone();
        exams2End.add(Calendar.WEEK_OF_YEAR,3);

        // Intocmirea output-ului
        output+="Semestrul I \n\n";
        output+= dateFormat.format(semester1Start.getTime()) + " - " + dateFormat.format(semester1StudyEnd.getTime()) + " -->studii\n";
        output+=dateFormat.format(winterBreakStart.getTime()) + " - " + dateFormat.format(winterBreakEnd.getTime()) + " -->vacanta\n";
        output+=dateFormat.format(semester1StudyStart2.getTime()) + " - " + dateFormat.format(semester1StudyEnd2.getTime()) + " -->studii\n";
        output+=dateFormat.format(exams1Start.getTime()) + " - " + dateFormat.format(exams1End.getTime()) + " -->sesiune de examene\n";
        output+=dateFormat.format(intersemestrialBreakStart.getTime()) + " - " + dateFormat.format(intersemestrialBreakEnd.getTime()) + " -->vacanta\n\n";
        output+= "Semestrul II \n\n";
        output+=dateFormat.format(semester2StudyStart1.getTime()) + " - " + dateFormat.format(semester2StudyEnd1.getTime()) + " -->studii\n";
        output+=dateFormat.format(easterBreakStart.getTime()) + " - " + dateFormat.format(easterBreakEnd.getTime()) + " -->vacanta\n";
        output+=dateFormat.format(semester2StudyStart2.getTime()) + " - " + dateFormat.format(semester2StudyEnd2.getTime()) + " -->studii\n";
        output+=dateFormat.format(exams2Start.getTime()) + " - " + dateFormat.format(exams2End.getTime()) + " -->sesiune de examene```";

        return output;
        }
}
