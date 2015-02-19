package com.axamit.training.mycqproject.components.weather.models;

import com.axamit.training.mycqproject.components.weather.service.WeatherService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 12:52
 */

@Model(adaptables = Resource.class)
public class WeatherModel {

    @Inject
    @Optional
    private WeatherService weatherService;

    @Inject
    @Optional
    private String titleWeatherComponent;

    @Inject
    @Optional
    private String textWeatherComponent;

    @Inject
    @Default(values = "524901")
    private String cityWeatherComponents;

    @Inject
    @Optional
    private String unitWeatherComponents;

    private CurrentCondition currentCondition;

    public String getCityWeatherComponents() {
        return cityWeatherComponents;
    }

    public void setCityWeatherComponents(String cityWeatherComponents) {
        this.cityWeatherComponents = cityWeatherComponents;
    }

    public String getTextWeatherComponent() {
        return textWeatherComponent;
    }

    public void setTextWeatherComponent(String textWeatherComponent) {
        this.textWeatherComponent = textWeatherComponent;
    }

    public String getTitleWeatherComponent() {
        return titleWeatherComponent;
    }

    public void setTitleWeatherComponent(String titleWeatherComponent) {
        this.titleWeatherComponent = titleWeatherComponent;
    }

    public String getUnitWeatherComponents() {
        return unitWeatherComponents;
    }

    public void setUnitWeatherComponents(String unitWeatherComponents) {
        this.unitWeatherComponents = unitWeatherComponents;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    @PostConstruct
    protected void init() {
        weatherService.doSomething(this);
    }
}
