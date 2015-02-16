package com.axamit.training.mycqproject.service.impl;

import com.axamit.training.mycqproject.service.WeatherService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 10:33
 */

@Component
@Service (value = WeatherService.class)
@Properties({
        @Property(name = "adaptables", value = {"Resource" }),
        @Property(name = "adapters", value = {"WeatherModel" })
})
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);



}
