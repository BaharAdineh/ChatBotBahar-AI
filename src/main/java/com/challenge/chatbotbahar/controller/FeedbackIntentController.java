package com.challenge.chatbotbahar.controller;


import com.google.cloud.dialogflow.v2.WebhookRequest;
import com.google.cloud.dialogflow.v2.WebhookResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackIntentController {

    @PostMapping("/webhook")
    public WebhookResponse handleWebhook(@RequestBody WebhookRequest request) {
        String intentName = request.getQueryResult().getIntent().getDisplayName();
        switch (intentName) {
            case "Welcome Intent":
                return handleWelcome(request);
            case "Feedback Intent":
                return handleFeedback(request);
            case "Positive Feedback Intent":
                return handlePositiveFeedback(request);
            case "Negative Feedback Intent":
                return handleNegativeFeedback(request);
            case "Confirmation Intent":
                return handleConfirmation(request);
            case "Repeat Intent":
                return handleRepeat(request);
            case "Help Intent":
                return handleHelp(request);
            default:
                return handleUnknown(request);
        }
    }

    private WebhookResponse handleWelcome(WebhookRequest request) {
        return WebhookResponse.newBuilder()
                .setFulfillmentText("Hello! How can I help you today?")
                .build();
    }

    private WebhookResponse handleFeedback(WebhookRequest request) {
        String feedbackTopic = request.getQueryResult().getParameters().getFieldsOrThrow("feedback-topic").toString();
        return WebhookResponse.newBuilder()
                .setFulfillmentText("Thank you for your feedback on " + feedbackTopic + ". Please provide more details.")
                .build();
    }

    private WebhookResponse handlePositiveFeedback(WebhookRequest request) {
        String feedbackDetails = request.getQueryResult().getParameters().getFieldsOrThrow("feedback-details").toString();
        // Save positive feedback to database or send to relevant team for action
        return WebhookResponse.newBuilder()
                .setFulfillmentText("Thank you for your positive feedback! We appreciate your support.")
                .build();
    }

    private WebhookResponse handleNegativeFeedback(WebhookRequest request) {
        String feedbackDetails = request.getQueryResult().getParameters().getFieldsOrThrow("feedback-details").toString();
        // Save negative feedback to database or send to relevant team for action
        return WebhookResponse.newBuilder()
                .setFulfillmentText("We're sorry to hear that. Please provide more details so we can address the issue.")
                .build();
    }

    private WebhookResponse handleConfirmation(WebhookRequest request) {
        return WebhookResponse.newBuilder()
                .setFulfillmentText("Thank you for your feedback! We will forward it to the relevant team for action.")
                .build();
    }

    private WebhookResponse handleRepeat(WebhookRequest request) {
        // Get the previous response and repeat it back to the user
        String previousResponse = // Code to retrieve previous response
        return WebhookResponse.newBuilder()
                .setFulfillmentText(previousResponse)
                .build();
    }

    private WebhookResponse handleHelp(WebhookRequest request) {
        return WebhookResponse.newBuilder()
                .setFulfillmentText("I can help you with feedback. Just provide your feedback and I'll take care of the rest.")
                .build();
    }

    private WebhookResponse handleUnknown(WebhookRequest request) {
        return WebhookResponse.newBuilder()
                .setFulfillmentText("I'm sorry, I didn't understand what you said. Can you please rephrase?")
                .build();
    }
}
