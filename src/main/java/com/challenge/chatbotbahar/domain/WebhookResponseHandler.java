package com.challenge.chatbotbahar.domain;


import com.google.cloud.dialogflow.v2.Intent.Message;
import com.google.cloud.dialogflow.v2.Intent.Message.Text;
import com.google.cloud.dialogflow.v2.WebhookResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

public class WebhookResponseHandler {
    @Setter
    private String fulfillmentText;

    public WebhookResponseHandler(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public WebhookResponseHandler() {
    }

    public WebhookResponse setTextResponse(String text) {
        Text messageText = Text.newBuilder()
                .addText(text)
                .build();

        Message message = Message.newBuilder()
                .setText(messageText)
                .build();

        List<Message> messages = new ArrayList<>();
        messages.add(message);

        WebhookResponse.Builder responseBuilder = WebhookResponse.newBuilder()
                .addAllFulfillmentMessages(messages)
                .setFulfillmentText(this.fulfillmentText);

        return responseBuilder.build();
    }

}
