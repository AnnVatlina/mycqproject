package com.axamit.training.mycqproject.components.weather;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 12:52
 */

@Model(adaptables=Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WeatherModel {
//
//    @OSGiService
//    private WeatherService weatherService;

    @Inject
    private String titleWeatherComponent;

    @Inject
    private String textWeatherComponent;

    @Inject
    private String cityWeatherComponents;

    @Inject
    private String unitWeatherComponents;

//    @Self
//    private Resource resource;

//    public WeatherModel(Resource resource) {
//        this.resource = resource;
//    }


    public String getTitleWeatherComponent() {
        return titleWeatherComponent;
    }

    public void setTitleWeatherComponent(String titleWeatherComponent) {
        this.titleWeatherComponent = titleWeatherComponent;
    }

    public String getTextWeatherComponent() {
        return textWeatherComponent;
    }

    public void setTextWeatherComponent(String textWeatherComponent) {
        this.textWeatherComponent = textWeatherComponent;
    }

    public String getCityWeatherComponents() {
        return cityWeatherComponents;
    }

    public void setCityWeatherComponents(String cityWeatherComponents) {
        this.cityWeatherComponents = cityWeatherComponents;
    }

    public String getUnitWeatherComponents() {
        return unitWeatherComponents;
    }

    public void setUnitWeatherComponents(String unitWeatherComponents) {
        this.unitWeatherComponents = unitWeatherComponents;
    }

    @PostConstruct
    protected void init() {
        System.out.println("WeatherModel.init");
    }

}
