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

public class JsonDemo3 {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer XXXXXXXXXX") // replace XXXXXXs with your API Read Access Token
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
//        System.out.println(responseBody);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
//        JsonElement jsonElement = new JsonParser().parse(response.body().string());
//        String json = gson.toJson(jsonElement);
//        System.out.println(json);
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
            System.out.println(movie.title);
            System.out.println(movie.release_date);
            System.out.println(movie.vote_average);
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
