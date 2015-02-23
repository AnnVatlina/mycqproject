package com.axamit.training.mycqproject.components.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 17.02.2015, 14:34
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentCondition implements Serializable {

    private String name;
    private String country;
    /*private String main;
    private String icon;*/
    @JsonProperty("weather")
    private Weather[] weatherArray;

    /*private float temp_min;
    private float temp_max;
    private float humidity;*/
    @JsonProperty("main")
    private MainAttribute mainAttributes;
    private float speed;

    public MainAttribute getMainAttributes() {
        return mainAttributes;
    }

    public void setMainAttributes(MainAttribute mainAttributes) {
        this.mainAttributes = mainAttributes;
    }

    public Weather[] getWeatherArray() {
        return weatherArray;
    }

    public void setWeatherArray(Weather[] weatherArray) {
        this.weatherArray = weatherArray;
    }

    public CurrentCondition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
