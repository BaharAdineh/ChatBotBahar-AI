package com.challenge.chatbotbahar.domain;

import com.google.cloud.dialogflow.v2.Intent.Message;
import com.google.cloud.dialogflow.v2.Intent.Message.Text;
import com.google.cloud.dialogflow.v2.WebhookResponse;

import lombok.Setter;


public class WebhookResponse {
    @Setter
    private String fulfillmentText;

    public WebhookResponse(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public WebhookResponse() {
    }

    public WebhookResponse setTextResponse(String text) {
        Text messageText = Text.newBuilder()
                .setText(text)
                .build();

        Message message = Message.newBuilder()
                .setText(messageText)
                .build();

        WebhookResponse response = WebhookResponse.newBuilder()
                .addFulfillmentMessages(message)
                .build();

        return response;
    }
}
