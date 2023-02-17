package com.challenge.chatbotbahar.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@Configuration
public class DialogflowConfiguration {

    @Value("${dialogflow.projectId}")
    private String projectId;

    @Value("${dialogflow.sessionId}")
    private String sessionId;

    @Bean
    public DialogflowClient dialogflowClient() throws IOException {
        return new DialogflowClient(projectId, sessionId);
    }

}
