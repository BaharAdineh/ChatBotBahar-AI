package com.challenge.chatbotbahar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WeatherData {
    private double temperature;
    private String description;
    private String location;

    public WeatherData(double temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }
}

