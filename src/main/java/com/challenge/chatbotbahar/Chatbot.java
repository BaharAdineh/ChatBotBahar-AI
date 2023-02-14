package com.challenge.chatbotbahar;

import com.challenge.chatbotbahar.domain.ChatbotIntent;
import com.challenge.chatbotbahar.domain.WeatherIntent;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chatbot {

    private final Map<String, ChatbotIntent> intents;
    private final String defaultResponse;

    public Chatbot(Map<String, ChatbotIntent> intents, String defaultResponse) {
        this.intents = intents;
        this.defaultResponse = defaultResponse;
    }

    public String respondTo(String text) {
        // Process the text using the StanfordCoreNLP pipeline
        List<CoreMap> sentences = NlpUtils.processText(text);

        // Get the first sentence from the processed text
        CoreMap sentence = sentences.get(0);

        // Get the text of the sentence
        String sentenceText = sentence.get(CoreAnnotations.TextAnnotation.class);

        // Look up an intent for the sentence text in the intents map
        ChatbotIntent intent = getIntent(sentenceText);

        // If no intent is found, return the default response
        if (intent == null) {
            return defaultResponse;
        }

        // Get the response for the intent
        String response = intent.getResponse(sentence);

        // If no response is found, return the default response
        if (response == null) {
            return defaultResponse;
        }

        return response;
    }

    private ChatbotIntent getIntent(String sentenceText) {
        for (Map.Entry<String, ChatbotIntent> entry : intents.entrySet()) {
            String intentPattern = entry.getKey();
            ChatbotIntent intent = entry.getValue();
            if (sentenceText.matches(intentPattern)) {
                return intent;
            }
        }
        return null;
    }

    public static Chatbot getDefaultChatbot() {
        // Define some example intents
        Map<String, ChatbotIntent> intents = new HashMap<>();
        intents.put(".*weather.*", new WeatherIntent());
        intents.put(".*time.*close.*", new StoreHoursIntent());
        intents.put(".*make.*appointment.*", new AppointmentIntent());

        // Define a default response for when no intent is matched
        String defaultResponse = "I'm sorry, I didn't understand your question.";

        return new Chatbot(intents, defaultResponse);
    }

}
