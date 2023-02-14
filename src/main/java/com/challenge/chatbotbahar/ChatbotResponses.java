package com.challenge.chatbotbahar;

import java.util.HashMap;
import java.util.Map;

public class ChatbotResponses {
    public static Map<String, String> getResponses() {
        Map<String, String> responses = new HashMap<>();
        responses.put("What is the weather like today?", "The weather today is sunny and warm.");
        responses.put("What time does the store close?", "The store closes at 9 pm.");
        responses.put("How can I make an appointment?", "Please call our office to schedule an appointment.");
        return responses;
    }

    public static Map<String, String> getWeatherResponses() {
        Map<String, String> responses = new HashMap<>();
        responses.put("sunny", "It's going to be sunny!");
        responses.put("rainy", "It's going to rain!");
        responses.put("cloudy", "It's going to be cloudy!");
        return responses;
    }
}

