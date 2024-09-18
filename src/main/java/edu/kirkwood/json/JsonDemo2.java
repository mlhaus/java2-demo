package edu.kirkwood.json;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;

public class JsonDemo2 {
    private static final String API_URL = "https://randomuser.me/api/?format=json&results=10&nat=us&seed=xyz";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantDeserializer())
                .create();
        RandomUserAPIResponse apiResponse = null;
        try {
            apiResponse = gson.fromJson(responseBody, RandomUserAPIResponse.class);
        } catch(JsonSyntaxException | JsonIOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        for(RandomUser randomUser: apiResponse.results ) {
            System.out.println(randomUser.name.title + " " + randomUser.name.first + " " + randomUser.name.last  + ", " + randomUser.gender);
            System.out.println(randomUser.location.street.number + " " + randomUser.location.street.name);
            System.out.println(randomUser.location.city + ", " + randomUser.location.state + ", " + randomUser.location.postcode);
            System.out.println(randomUser.email);
            System.out.println(randomUser.phone);
            System.out.println(randomUser.dob.date);
            System.out.println();
        }

    }
}

class InstantDeserializer implements JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Instant.parse(json.getAsString());
    }
}
