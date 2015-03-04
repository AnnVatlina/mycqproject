package com.axamit.training.mycqproject.components.weather.models;

import com.axamit.training.mycqproject.components.weather.service.WeatherService;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 12:52
 */

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WeatherModel {

    @Inject
    private SlingHttpServletRequest slingRequest;

    @Inject
    private WeatherService weatherService;

    @Inject
    private String titleWeatherComponent;

    @Inject
    private String textWeatherComponent;

    @Inject
    @Default(values = "524901")
    private String cityWeatherComponents;

    @Inject
    private String unitWeatherComponents;

    private CurrentCondition currentCondition;

    public String getCityWeatherComponents() {
        return cityWeatherComponents;
    }

    public String getTextWeatherComponent() {
        return textWeatherComponent;
    }

    public String getTitleWeatherComponent() {
        return titleWeatherComponent;
    }

    public String getUnitWeatherComponents() {
        return unitWeatherComponents;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    @PostConstruct
    protected void init() throws LoginException {
        if(slingRequest != null) {
            String newCity = getSelector();
            if (StringUtils.isNotBlank(newCity)) {
                cityWeatherComponents = newCity;
            }
        }
        setCurrentCondition(weatherService.getWeather(cityWeatherComponents, unitWeatherComponents));
    }

    private String getSelector() throws LoginException {
        String selectors = slingRequest.getRequestPathInfo().getSelectorString();
        if(StringUtils.isNotBlank(selectors)) {
            List<String> selectorsList = Arrays.asList(selectors.split("\\."));
            if(!selectorsList.contains("advertisement")) {
                return selectorsList.get(0);
            }
        }
        return null;
    }
}
