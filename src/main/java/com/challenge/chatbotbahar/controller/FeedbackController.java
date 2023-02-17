package com.challenge.chatbotbahar.controller;


import com.challenge.chatbotbahar.configuration.DialogflowClient;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.Struct;
import com.google.cloud.dialogflow.v2.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
public class FeedbackController {
    private final DialogflowClient dialogflowClient;
    private final String feedbackIntentId;

    public FeedbackController(DialogflowClient dialogflowClient, @Value("${feedback.intentId}") String feedbackIntentId) {
        this.dialogflowClient = dialogflowClient;
        this.feedbackIntentId = feedbackIntentId;
    }

    @PostMapping("/feedback")
    public String submitFeedback(@RequestBody String feedbackText) throws IOException, ApiException {
        DetectIntentResponse response = dialogflowClient.detectIntent(feedbackText);

        if (response.getQueryResult().getIntent().getDisplayName().equals(feedbackIntentId)) {
            Struct parameters = response.getQueryResult().getParameters();
            Value feedbackValue = parameters.getFieldsOrDefault("feedback", Value.getDefaultInstance());
            String feedbackMessage = feedbackValue.getStringValue();

            return "Thank you for your feedback: " + feedbackMessage;
        } else {
            return "I'm sorry, I didn't understand your feedback.";
        }
    }
}
