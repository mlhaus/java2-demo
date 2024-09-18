package edu.kirkwood.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonDemo1 {
    public static void main(String[] args) {
        String responseBody = "{\"role\":\"test\",\"content\":\"example\"}";
        Gson gson = new Gson();
        Message message = null;
        try {
            message = gson.fromJson(responseBody, Message.class);
        } catch(JsonSyntaxException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println(message.getRole());
        System.out.println(message.getContent());
    }
}