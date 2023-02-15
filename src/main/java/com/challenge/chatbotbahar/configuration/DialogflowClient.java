package com.challenge.chatbotbahar.configuration;
import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2beta1.DetectIntentResponse;
import com.google.cloud.dialogflow.v2beta1.QueryInput;
import com.google.cloud.dialogflow.v2beta1.QueryResult;
import com.google.cloud.dialogflow.v2beta1.SessionsClient;
import com.google.cloud.dialogflow.v2beta1.SessionsSettings;
import java.io.FileInputStream;
import java.io.IOException;

public class DialogflowClient {

    private SessionsClient sessionsClient;

    public DialogflowClient(String projectId, String credentialsPath) throws IOException {
        // Set up the credentials provider to authenticate with the Dialogflow API
        CredentialsProvider credentialsProvider = new CredentialsProvider() {
            @Override
            public Credentials getCredentials() throws IOException {
                return GoogleCredentials.fromStream(new FileInputStream(credentialsPath));
            }
        };

        // Set up the SessionsSettings with the credentials provider
        SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                .setCredentialsProvider(credentialsProvider)
                .build();

        // Create a new SessionsClient using the settings
        this.sessionsClient = SessionsClient.create(sessionsSettings);
    }

    public String sendTextQuery(String agentId, String sessionId, String text) {
        // Set up the session name with the project ID, agent ID, and session ID
        String sessionName = String.format("projects/%s/agent/sessions/%s", agentId, sessionId);

        // Build the QueryInput object with the text query
        QueryInput queryInput = QueryInput.newBuilder()
                .setText(com.google.cloud.dialogflow.v2beta1.TextInput.newBuilder()
                        .setText(text)
                        .setLanguageCode("en-US"))
                .build();

        // Call the detectIntent method to send the query and receive a response
        DetectIntentResponse response = this.sessionsClient.detectIntent(sessionName, queryInput);

        // Extract the QueryResult from the response
        QueryResult queryResult = response.getQueryResult();

        // Access the result of the query, such as the fulfillment text
        String fulfillmentText = queryResult.getFulfillmentText();

        return fulfillmentText;
    }

    public void close() throws Exception {
        this.sessionsClient.close();
    }
}