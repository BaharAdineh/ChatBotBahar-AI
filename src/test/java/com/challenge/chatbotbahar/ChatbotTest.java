package com.challenge.chatbotbahar;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChatbotTest {
    
    @Test
    public void testWeatherQuestion() {
        String message = "What is the weather like tomorrow in Boston?";
        String expectedResponse = "The weather in Boston on tomorrow is sunny.";
        String actualResponse = Chatbot.respondTo(message);
        assertEquals(expectedResponse, actualResponse);
    }
    
    @Test
    public void testAppointmentQuestion() {
        String message = "How can I schedule an appointment for Friday at 3 pm?";
        String expectedResponse = "Your appointment has been scheduled for Friday at 3 pm. Your confirmation number is 12345.";
        String actualResponse = Chatbot.respondTo(message);
        assertEquals(expectedResponse, actualResponse);
    }
    
    @Test
    public void testUnknownQuestion() {
        String message = "What is the meaning of life?";
        String expectedResponse = "I'm sorry, I don't understand. Can you please rephrase your question?";
        String actualResponse = Chatbot.respondTo(message);
        assertEquals(expectedResponse, actualResponse);
    }
}
