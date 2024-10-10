package edu.kirkwood.bpa;

import java.time.LocalDate;
import java.util.Comparator;

public class President implements Comparable<President>{
    private int id;
    private String firstName;
    private String lastName;
    private int heightInInches;
    private double weightInPounds;
    private LocalDate dateOfBirth;

    public President() {}

    public President(int id, String firstName, String lastName, int heightInInches, double weightInPounds,
            LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightInInches = heightInInches;
        this.weightInPounds = weightInPounds;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(int heightInInches) {
        this.heightInInches = heightInInches;
    }

    public double getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "President [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", heightInInches="
                + heightInInches + ", weightInPounds=" + weightInPounds + ", dateOfBirth=" + dateOfBirth + "]";
    }

    @Override
    public int compareTo(President o) {
        int result = Integer.valueOf(this.id).compareTo(Integer.valueOf(o.id));
        return result;
    }

    public static Comparator<President> compareHeight = Comparator.comparingInt(President::getHeightInInches);
    public static Comparator<President> compareWeight = Comparator.comparingDouble(President::getWeightInPounds);
    public static Comparator<President> compareAge = Comparator.comparing(President::getDateOfBirth);
    
}

