package com.challenge.chatbotbahar.service;

import com.google.cloud.dialogflow.v2.*;

public class DialogflowService {

    private static final String PROJECT_ID = "<your-project-id>";
    private static final String LANGUAGE_CODE = "en-US";
    private static final String SESSION_ID = "<your-session-id>";

    public static String detectIntentTexts(String text) {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of(PROJECT_ID, SESSION_ID);
            TextInput.Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(LANGUAGE_CODE);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();
            return queryResult.getFulfillmentText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
