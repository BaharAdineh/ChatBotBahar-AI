package com.challenge.chatbotbahar;

import java.util.Scanner;

public class ChatbotUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Prompt the user for input
            System.out.print("You: ");
            String input = scanner.nextLine();

            // Get the chatbot's response
            String response = Chatbot.respondTo(input);

            // Display the chatbot's response
            System.out.println("Chatbot: " + response);
        }
    }
}

