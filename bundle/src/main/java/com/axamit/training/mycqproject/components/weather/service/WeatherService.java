package com.axamit.training.mycqproject.components.weather.service;

import com.axamit.training.mycqproject.components.weather.models.CurrentCondition;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 10:33
 */

public interface WeatherService {
    public CurrentCondition getWeather(final String city, final String unit);
}
