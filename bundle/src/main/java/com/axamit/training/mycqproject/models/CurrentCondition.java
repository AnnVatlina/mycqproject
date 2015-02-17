package com.axamit.training.mycqproject.models;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 17.02.2015, 14:34
 */
public class CurrentCondition {

    private int weatherId;
    private String city;
    private String country;
    private String description;
    private String condition;
    private String icon;

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
