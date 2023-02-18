package com.challenge.chatbotbahar.configuration;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;

import java.io.IOException;

public class DialogflowClient {
    private final SessionsClient sessionsClient;
    private final SessionName session;

    public DialogflowClient(String projectId, String sessionId) throws IOException {
        sessionsClient = SessionsClient.create();
        session = SessionName.of(projectId, sessionId);
    }

    public DetectIntentResponse detectIntent(String queryText) throws ApiException {
        TextInput.Builder textInput = TextInput.newBuilder().setText(queryText).setLanguageCode("en-US");
        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

        return sessionsClient.detectIntent(session, queryInput);
    }

    public void close() {
        sessionsClient.close();
    }
}
