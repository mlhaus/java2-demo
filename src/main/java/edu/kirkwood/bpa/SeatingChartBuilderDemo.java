package edu.kirkwood.bpa;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SeatingChartBuilderDemo {
    public static void main(String[] args) {
        List<President> presidents = PresidentDAO.getPresidents();
        // Collections.sort(presidents); // sort by id low to high
        // Collections.reverse(presidents); // sort by id high to low
        // Collections.sort(presidents, President.compareHeight); // shortest to tallest
        // Collections.sort(presidents, President.compareHeight.reversed()); // tallest to shortest
        // Collections.sort(presidents, President.compareWeight); // light to heavy
        // Collections.sort(presidents, President.compareWeight.reversed()); // heavy to light
        // Collections.sort(presidents, President.compareAge); // old to young
        Collections.sort(presidents, President.compareAge.reversed()); // young to old
        presidents.forEach(System.out::println);
    }
}



class PresidentDAO {
    private static List<President> presidents = new ArrayList<>();

    public static void getFromCSV() {
        List<String> lines = FileInput.readAllLines("presidents.csv");
        for(String line: lines) {
            String[] president = line.split(",");
            try {
                int id = Integer.parseInt(president[0].trim());
                String firstName = president[1].trim();
                String lastName = president[2].trim();
                int heightInInches = Integer.parseInt(president[3].trim());
                double weightInPounds = Double.parseDouble(president[4].trim());
                LocalDate dateOfBirth = LocalDate.parse(president[5].trim());
                presidents.add(new President(id, firstName, lastName, heightInInches, weightInPounds, dateOfBirth));
            } catch(IndexOutOfBoundsException | NumberFormatException | DateTimeParseException e) {
                continue;
            } 
        }
    }

    public static List<President> getPresidents() {
        if(presidents.size() == 0) {
            getFromCSV();
        }
        return presidents;
    }
}