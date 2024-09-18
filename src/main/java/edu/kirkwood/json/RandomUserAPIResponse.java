package edu.kirkwood.json;

import java.time.Instant;

public class RandomUserAPIResponse {
    public RandomUser[] results;
}
class RandomUser {
    public String gender;
    public Name name;
    public Location location;
    public String email;
    public String phone;
    public DateOfBirth dob;
}

class Name {
    public String title;
    public String first;
    public String last;
}

class Location {
    public Street street;
    public String city;
    public String state;
    public String postcode;
}

class Street {
    public String number;
    public String name;
}

class DateOfBirth {
    public Instant date;
}
