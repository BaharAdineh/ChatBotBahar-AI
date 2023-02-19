package com.challenge.chatbotbahar.controller;

import com.google.cloud.dialogflow.v2.WebhookResponse;
import com.google.cloud.dialogflow.v2.WebhookRequest;
import com.google.cloud.dialogflow.v2.Intent.Message;
import com.google.cloud.dialogflow.v2.Intent.Message.Text;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackIntentController {

    @PostMapping("/dialogflow/feedbackIntent")
    public WebhookResponse handleFeedbackIntent(@RequestBody WebhookRequest request) {
        String intentName = request.getQueryResult().getIntent().getDisplayName();
        if (intentName.equals("FeedbackIntent")) {
            String response = "Thank you for your feedback. What would you like to provide feedback on?";
            Text text = Text.newBuilder().addText(response).build();
            Message message = Message.newBuilder().setText(text).build();
            return WebhookResponse.newBuilder().addFulfillmentMessages(message).build();
        } else if (intentName.equals("PositiveFeedbackIntent")) {
            String feedback = request.getQueryResult().getQueryText();
            // Process the positive feedback and store it in your system
            String response = "Thank you for your positive feedback.";
            Text text = Text.newBuilder().addText(response).build();
            Message message = Message.newBuilder().setText(text).build();
            return WebhookResponse.newBuilder().addFulfillmentMessages(message).build();
        } else if (intentName.equals("NegativeFeedbackIntent")) {
            String feedback = request.getQueryResult().getQueryText();
            // Process the negative feedback and store it in your system
            String response = "Thank you for your negative feedback. We will work to improve our service.";
            Text text = Text.newBuilder().addText(response).build();
            Message message = Message.newBuilder().setText(text).build();
            return WebhookResponse.newBuilder().addFulfillmentMessages(message).build();
        } else {
            return null;
        }
    }
}