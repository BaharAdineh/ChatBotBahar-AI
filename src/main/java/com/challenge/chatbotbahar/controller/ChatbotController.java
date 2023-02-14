package com.challenge.chatbotbahar.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatbotController {
    private static Map<String, String> responses = new HashMap<>();

    static {
        // populate responses map with some example responses
        responses.put("What is the weather like today?", "The weather today is sunny and warm.");
        responses.put("What time does the store close?", "The store closes at 9 pm.");
        responses.put("How can I make an appointment?", "Please call our office to schedule an appointment.");
    }

    @GetMapping("/chatbot")
    public ResponseEntity<String> respondTo(@RequestParam("message") String message) {
        String response = "I'm sorry, I don't understand. Can you please rephrase your question?";
        if (message.contains("weather")) {
            String location = extractLocation(message);
            String date = extractDate(message);
            String weather = getWeather(location, date);
            response = "The weather in " + location + " on " + date + " is " + weather + ".";
        } else if (message.contains("appointment")) {
            String appointmentDate = extractDate(message);
            String appointmentTime = extractTime(message);
            String confirmationNumber = scheduleAppointment(appointmentDate, appointmentTime);
            response = "Your appointment has been scheduled for " + appointmentDate + " at " + appointmentTime + ". Your confirmation number is " + confirmationNumber + ".";
        }

        return ResponseEntity.ok("{ \"response\": \"" + response + "\" }");
    }

    private static String extractLocation(String message) {
        // extract location from message
        return "Boston";
    }

    private static String extractDate(String message) {
        // extract date from message
        return "tomorrow";
    }

    private static String getWeather(String location, String date) {
        // use an API to get the weather for the given location and date
        return "sunny and warm";
    }

    private static String extractTime(String message) {
        // extract time from message
        return "3 pm";
    }

    private static String scheduleAppointment(String date, String time) {
        // schedule appointment and return confirmation number
        return "123456";
    }
}
