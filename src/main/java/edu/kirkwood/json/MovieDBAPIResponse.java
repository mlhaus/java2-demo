package edu.kirkwood.json;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class MovieDBAPIResponse {
    public Movie[] results;
}
class Movie implements Comparable<Movie> {
    public String title;
    public LocalDate release_date;
    public double vote_average;
    public int id;
    public String overview;
    public int[] genre_ids;
    
    @Override
    public int compareTo(@NotNull Movie o) {
        return -1 * Double.valueOf(vote_average).compareTo(Double.valueOf(o.vote_average));
    }
}

class MovieDBGenres {
    public Genre[] genres;
}
class Genre {
    public int id;
    public String name;
}






