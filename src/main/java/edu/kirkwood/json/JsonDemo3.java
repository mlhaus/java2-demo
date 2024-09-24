package edu.kirkwood.json;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDemo3 {
    public static void main(String[] args) throws IOException {
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZWI4OWFhZmUyNWExYWYxNDc1MzYxNGNhMDgzNDJlYyIsIm5iZiI6MTcyNzIxOTEzMy4wNzgxMywic3ViIjoiNWYxY2IyOTUwYmIwNzYwMDM0ZjEwNTM5Iiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.D-UUE3FuDRTAF_Zmytibo95kO5JbStUpwk372oeScf8";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + accessToken) // replace XXXXXXs with your API Read Access Token
                .build();

        Request request2 = new Request.Builder()
                .url("https://api.themoviedb.org/3/genre/movie/list?language=en")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
//        System.out.println(responseBody);

        Response response2 = client.newCall(request2).execute();
        String responseBody2 = response2.body().string();
//        System.out.println(responseBody2);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
//        JsonElement jsonElement = new JsonParser().parse(responseBody);
//        String json = gson.toJson(jsonElement);
//        System.out.println(json);

        MovieDBGenres movieDBGenres = null;
        try {
            movieDBGenres = gson.fromJson(responseBody2, MovieDBGenres.class);
        } catch(JsonSyntaxException | JsonIOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        Genre[] genres = movieDBGenres.genres;
        List<Genre> genreList = Arrays.asList(genres);
//        for(Genre genre: genres) {
//            System.out.println("ID: " + genre.id);
//            System.out.println("Name: " + genre.name);
//            System.out.println();
//        }
        
        MovieDBAPIResponse apiResponse = null;
        try {
            apiResponse = gson.fromJson(responseBody, MovieDBAPIResponse.class);
        } catch(JsonSyntaxException | JsonIOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        Movie[] movies = apiResponse.results;
        Arrays.sort(movies);
        for(Movie movie: movies) {
            System.out.println("ID: " + movie.id);
//            System.out.println("Genres: " + Arrays.toString(movie.genre_ids));
            System.out.print("Genres: ");
            for(int id: movie.genre_ids) {
                Genre genre = genreList.stream().filter(g -> g.id == id).collect(Collectors.toList()).get(0);
                System.out.print(genre.name + ", ");
            }
            System.out.println();
            System.out.println("Title: " + movie.title);
            System.out.println("Released: " + movie.release_date);
            System.out.println("Vote Average: " + movie.vote_average);
            System.out.println("Overview: " + movie.overview);
            System.out.println();
        }
    }
}

class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString());
    }
}
