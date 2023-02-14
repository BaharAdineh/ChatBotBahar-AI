package com.challenge.chatbotbahar.controller;

import com.challenge.chatbotbahar.domain.WeatherData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class WeatherAPI {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s";

    private final String city;

    public WeatherAPI(String city) {
        this.city = city;
    }

    public String getWeather() {
        try {
            // Create a URL object with the API endpoint and the city
            URL url = new URL(String.format(API_URL, this.city, API_KEY));

            // Open a connection to the API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Read the response from the API
            String responseBody = "";
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                responseBody += scanner.nextLine();
            }
            scanner.close();

            // Use Jackson library to parse the response as JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);

            // Extract the temperature from the response
            JsonNode main = root.path("main");
            double temp = main.path("temp").asDouble();

            return String.format("The current temperature in %s is %.1f°C.", this.city, temp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Unable to retrieve weather information at this time.";
        }
    }



    public static WeatherData getWeather(String location, String date) throws IOException {
        OkHttpClient client = new OkHttpClient();



        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        JSONObject json = new JSONObject(responseBody);

        WeatherData weatherData = new WeatherData();

        JSONObject locationJson = json.getJSONObject("location");
        weatherData.setLocation(locationJson.getString("name"));

        JSONObject currentJson = json.getJSONObject("current");
        weatherData.setTemperature(currentJson.getDouble("temp_c"));

        return weatherData;
    }
}


