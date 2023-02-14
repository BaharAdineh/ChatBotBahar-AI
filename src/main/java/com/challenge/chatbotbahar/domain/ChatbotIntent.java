package com.challenge.chatbotbahar.domain;

import java.util.HashMap;
import java.util.Map;

public class ChatbotIntent {
    private final String name;
    private final Map<String, String> responses;

    public ChatbotIntent(String name) {
        this.name = name;
        this.responses = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addResponse(String input, String output) {
        responses.put(input, output);
    }

    public String getResponse(String input) {
        return responses.get(input);
    }

    public String getDefaultResponse() {
        return "I'm sorry, I didn't understand your question.";
    }
}
