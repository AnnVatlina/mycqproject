package com.axamit.training.mycqproject.components.weather.service.impl;

import com.axamit.training.mycqproject.components.weather.models.CurrentCondition;
import com.axamit.training.mycqproject.components.weather.service.WeatherService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.*;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Dictionary;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 10:33
 */

@Component(metatype = true, immediate = true)
@Service(value = WeatherService.class)
@Properties({
        @Property(name = "targetURL", description = "Link for get data"),
        @Property(name = "IMG_URL", description = "Path for image")
})
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private String targetURL;
    private String IMG_URL;

    @Reference
    private ConfigurationAdmin configAdmin;

    @Activate
    public void activate(ComponentContext ctx) {
        try {
            Configuration config = configAdmin.getConfiguration(this.getClass().getCanonicalName());
            Dictionary<?, ?> configProperties = config.getProperties();
            targetURL = (String) configProperties.get("targetURL");
            IMG_URL = (String) configProperties.get("IMG_URL");
        } catch (IOException e) {
            LOGGER.error("IOException: {}", e);
        }
    }

    @Override
    public CurrentCondition getWeather(String city, String unit) {
        LOGGER.info("WeatherServiceImpl.getWeather");
        CurrentCondition currentCondition = new CurrentCondition();

        if (city == null) {
            LOGGER.error("Not value for city.");
            throw new NullPointerException();
        }
        try {
            String currentPath;
            Pattern p = Pattern.compile(".*\\d.*");
            Matcher m = p.matcher(city);
            if(m.matches()){
                currentPath = targetURL + "?id=" + city;
            } else{
                currentPath = targetURL + "?q=" + city;
            }
            if (StringUtils.isNotBlank(unit)) {
                currentPath = currentPath + "&units=" + unit;
            }
            URL restServiceURL = new URL(currentPath);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            ObjectMapper mapper = new ObjectMapper();
            // to prevent exception when encountering unknown property:
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
            mapper.disable(DeserializationFeature.EAGER_DESERIALIZER_FETCH);
            mapper.disable(DeserializationFeature.WRAP_EXCEPTIONS);
            currentCondition = mapper.readValue(responseBuffer, CurrentCondition.class);
            httpConnection.disconnect();
        } catch (JsonMappingException e) {
            LOGGER.error("JsonMappingException: {}", e);
        } catch (JsonParseException e) {
            LOGGER.error("JsonParseException: {}", e);
        } catch (IOException e) {
            LOGGER.error("IOException: {}", e);
        }

        return currentCondition;
    }

}
