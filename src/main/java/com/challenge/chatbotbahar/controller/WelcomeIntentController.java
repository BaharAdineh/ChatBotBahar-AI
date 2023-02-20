package com.challenge.chatbotbahar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.chatbotbahar.domain.WebhookResponseHandler;
import com.challenge.chatbotbahar.domain.WebhookRequestHandler;

@RestController
public class WelcomeIntentController {

    @PostMapping("/dialogflow/welcomeIntent")
    public WebhookResponseHandler handleWelcomeIntent(@RequestBody WebhookRequestHandler request) {
        String intentName = request.getQueryResult().getIntent().getDisplayName();
        if (intentName.equals("WelcomeIntent")) {
            String response = "Hello, how can I help you today?";
            return new WebhookResponseHandler(response);
        } else {
            return null;
        }
    }
}
