package com.challenge.chatbotbahar.domain;

import com.google.cloud.dialogflow.v2.Intent.Message;
import com.google.cloud.dialogflow.v2.Intent.Message.Text;
import com.google.cloud.dialogflow.v2.WebhookResponse;
import com.google.cloud.dialogflow.v2.WebhookResponse.Builder;
import org.springframework.stereotype.Service;

@Service
public class PositiveFeedbackIntentHandler {

    public WebhookResponse handle() {
        // Create a response builder
        Builder builder = WebhookResponse.newBuilder();

        // Set the response text
        Text text = Text.newBuilder().addText("Thank you for your positive feedback!").build();
        Message message = Message.newBuilder().setText(text).build();
        builder.addFulfillmentMessages(message);

        // Return the response
        return builder.build();
    }

}

