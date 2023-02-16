package com.challenge.chatbotbahar.controller;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
public class FeedbackController {
    private static final String PROJECT_ID = "your-project-id";
    private static final String SESSION_ID = UUID.randomUUID().toString();
    private static final String LANGUAGE_CODE = "en-US";
    private static final String FEEDBACK_INTENT_ID = "your-feedback-intent-id";

    @PostMapping("/feedback")
    public String submitFeedback(@RequestBody String feedbackText) throws IOException, ApiException {
        SessionsClient sessionsClient = SessionsClient.create();
        SessionName session = SessionName.of(PROJECT_ID, SESSION_ID);

        // Build the query input
        TextInput.Builder textInput = TextInput.newBuilder().setText(feedbackText).setLanguageCode(LANGUAGE_CODE);
        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

        // Send the query to Dialogflow and get the response
        DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
        sessionsClient.close();

        // Check if the intent matches the feedback intent
        if (response.getQueryResult().getIntent().getDisplayName().equals(FEEDBACK_INTENT_ID)) {
            // Extract the feedback message from the intent parameters
            Struct parameters = response.getQueryResult().getParameters();
            Value feedbackValue = parameters.getFieldsOrDefault("feedback", Value.getDefaultInstance());
            String feedbackMessage = feedbackValue.getStringValue();

            // Process the feedback and return a response
            return "Thank you for your feedback: " + feedbackMessage;
        } else {
            return "I'm sorry, I didn't understand your feedback.";
        }
    }
}
