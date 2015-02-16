package com.axamit.training.mycqproject.components.weather;

import com.axamit.training.mycqproject.service.WeatherService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 12:52
 */

@Model(adaptables=Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, adapters = WeatherService.class)
public class WeatherModel {
//
//    @OSGiService
//    private WeatherService weatherService;

    @Inject
    @Optional
    @Default(values="defaultValue")
    private String titleWeatherComponent;

    @Inject
    @Optional
    @Default(values="defaultValue")
    private String textWeatherComponent;

    @Inject
    @Optional
    @Default(values="defaultValue")
    private String cityWeatherComponents;

    @Inject
    @Optional
    @Default(values="defaultValue")
    private String unitWeatherComponents;

//    @Self
//    private Resource resource;

//    public WeatherModel(Resource resource) {
//        this.resource = resource;
//    }


    public String getTitleWeatherComponent() {
        return titleWeatherComponent;
    }

    public String getTextWeatherComponent() {
        return textWeatherComponent;
    }

    public String getCityWeatherComponents() {
        return cityWeatherComponents;
    }

    public String getUnitWeatherComponents() {
        return unitWeatherComponents;
    }

    @PostConstruct
    protected void init() {
        System.out.println("WeatherModel.init");
    }

}
