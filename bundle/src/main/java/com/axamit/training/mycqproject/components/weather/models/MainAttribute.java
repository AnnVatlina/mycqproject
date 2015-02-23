package com.axamit.training.mycqproject.components.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 23.02.2015, 11:22
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MainAttribute {

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("temp_min")
    private double temp_min;

    @JsonProperty("temp_max")
    private double temp_max;

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
