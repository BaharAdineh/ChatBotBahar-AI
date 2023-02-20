package com.challenge.chatbotbahar.controller;

import com.google.cloud.dialogflow.v2.*;
import org.springframework.web.bind.annotation.*;
import com.challenge.chatbotbahar.domain.WebhookResponseHandler;
import com.challenge.chatbotbahar.domain.WebhookRequestHandler;
@RestController
@RequestMapping(value = "/dialogflow", produces = "application/json")
public class DialogflowController {

    @PostMapping(value = "/webhook", consumes = "application/json")
    public WebhookResponseHandler handleWebhook(@RequestBody WebhookRequestHandler request) {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of("your-project-id", request.getSession());
            String languageCode = "en-US";
            QueryInput queryInput = QueryInput.newBuilder()
                    .setText(TextInput.newBuilder().setText(request.getQueryResult().getQueryText())
                            .setLanguageCode(languageCode))
                    .build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

            WebhookResponseHandler webhookResponse = new WebhookResponseHandler();
            webhookResponse.setFulfillmentText(response.getQueryResult().getFulfillmentText());

            return webhookResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}