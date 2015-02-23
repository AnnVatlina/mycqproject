package com.axamit.training.mycqproject.components.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 23.02.2015, 11:25
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonProperty("main")
    private String main;

    @JsonProperty("icon")
    private String iconWeather;

    public String getIconWeather() {
        return iconWeather;
    }

    public void setIconWeather(String iconWeather) {
        this.iconWeather = iconWeather;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
