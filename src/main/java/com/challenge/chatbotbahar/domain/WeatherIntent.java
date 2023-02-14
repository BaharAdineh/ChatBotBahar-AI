package com.challenge.chatbotbahar.domain;

import com.challenge.chatbotbahar.ChatbotResponses;
import com.challenge.chatbotbahar.NlpUtils;
import com.challenge.chatbotbahar.controller.WeatherAPI;

import java.io.IOException;
import java.util.Map;
import ai.api.model.Intent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherIntent implements Intent {
    private static final String NAME = "WeatherIntent";
    private Map<String, String> responses;

    public WeatherIntent() {
        this.responses = ChatbotResponses.getWeatherResponses();
    }

    public String getName() {
        return NAME;
    }

    public String handle(String input) {
        // Extract the location and date from the user's input using regular expressions
        Pattern locationPattern = Pattern.compile("(?i)in\\s(\\w+)");
        Matcher locationMatcher = locationPattern.matcher(input);
        String location = locationMatcher.find() ? locationMatcher.group(1) : null;

        Pattern datePattern = Pattern.compile("(?i)on\\s(\\w+\\s\\d+)");
        Matcher dateMatcher = datePattern.matcher(input);
        String date = dateMatcher.find() ? dateMatcher.group(1) : null;

        if (location == null || date == null) {
            return "I'm sorry, I didn't understand your request.";
        }

        // Call an API to get the weather forecast for the location and date
        WeatherData weatherData;
        try {
            weatherData = WeatherAPI.getWeather(location, date);
        } catch (IOException e) {
            return "I'm sorry, there was an error getting the weather information.";
        }

        // Look up a response based on the weather
        String response = responses.get(weatherData.getTemperature());

        // If no response is found, return a default response
        if (response == null) {
            response = "I'm sorry, I don't know the weather for " + location + " on " + date + ".";
        }

        return response;
    }

}
