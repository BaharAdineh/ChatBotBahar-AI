package com.challenge.chatbotbahar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.chatbotbahar.domain.WebhookResponse;
import com.challenge.chatbotbahar.domain.WebhookRequest;

@RestController
public class WelcomeIntentController {

    @PostMapping("/dialogflow/welcomeIntent")
    public WebhookResponse handleWelcomeIntent(@RequestBody WebhookRequest request) {
        String intentName = request.getQueryResult().getIntent().getDisplayName();
        if (intentName.equals("WelcomeIntent")) {
            String response = "Hello, how can I help you today?";
            return new WebhookResponse(response);
        } else {
            return null;
        }
    }
}
