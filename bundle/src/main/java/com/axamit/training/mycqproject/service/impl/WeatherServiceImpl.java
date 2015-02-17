package com.axamit.training.mycqproject.service.impl;

import com.axamit.training.mycqproject.models.WeatherModel;
import com.axamit.training.mycqproject.service.WeatherService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 16.02.2015, 10:33
 */

@Component
@Service(value = WeatherService.class)
@Properties({
        @Property(name = "resourcePath", value = {"http://api.openweathermap.org/data/2.5/weather"})
})
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private static final String targetURL = "http://api.openweathermap.org/data/2.5/weather?q=London,uk";

    @Override
    public void doSomething(WeatherModel weatherModel) {
        LOGGER.info("WeatherServiceImpl.doSomething");
        try {

            URL restServiceURL = new URL(targetURL);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            String output;
            System.out.println("Output from Server:  \n");

            while ((output = responseBuffer.readLine()) != null) {
                LOGGER.info(output);
            }

            httpConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
