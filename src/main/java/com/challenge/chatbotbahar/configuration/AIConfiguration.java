package com.challenge.chatbotbahar.configuration;
import ai.api.AIConfiguration;
public class AIConfiguration {

    private final String CLIENT_ACCESS_TOKEN = "your_client_access_token_here";

    public AIConfiguration getConfiguration() {
        return new AIConfiguration(CLIENT_ACCESS_TOKEN);
    }
}
