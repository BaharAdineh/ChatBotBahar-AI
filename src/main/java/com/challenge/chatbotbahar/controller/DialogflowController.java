package com.challenge.chatbotbahar.controller;

import com.google.cloud.dialogflow.v2.*;
import org.springframework.web.bind.annotation.*;
import com.challenge.chatbotbahar.domain.WebhookResponse;
import com.challenge.chatbotbahar.domain.WebhookRequest;
@RestController
@RequestMapping(value = "/dialogflow", produces = "application/json")
public class DialogflowController {

    @PostMapping(value = "/webhook", consumes = "application/json")
    public WebhookResponse handleWebhook(@RequestBody WebhookRequest request) {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of("your-project-id", request.getSession());
            String languageCode = "en-US";
            QueryInput queryInput = QueryInput.newBuilder()
                    .setText(TextInput.newBuilder().setText(request.getQueryResult().getQueryText())
                            .setLanguageCode(languageCode))
                    .build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

            WebhookResponse webhookResponse = new WebhookResponse();
            webhookResponse.setFulfillmentText(response.getQueryResult().getFulfillmentText());

            return webhookResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}