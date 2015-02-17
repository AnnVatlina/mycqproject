package com.axamit.training.mycqproject.service.impl;

import com.axamit.training.mycqproject.models.CurrentCondition;
import com.axamit.training.mycqproject.models.WeatherModel;
import com.axamit.training.mycqproject.service.WeatherService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
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
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private static final String targetURL = "http://api.openweathermap.org/data/2.5/weather?q=London";
    private static String IMG_URL = "http://openweathermap.org/img/w/";

    @Override
    public void doSomething(WeatherModel weatherModel) {
        LOGGER.info("WeatherServiceImpl.doSomething");
        try {

            JSONObject jObj = getResource();
            JSONObject sysObj = getObject("sys", jObj);
            JSONObject weatherObj = jObj.getJSONArray("weather").getJSONObject(0);
            CurrentCondition currentCondition = new CurrentCondition();
            currentCondition.setCity(getString("name", jObj));
            currentCondition.setCountry(getString("country", sysObj));
            currentCondition.setDescription(getString("description", weatherObj));
            currentCondition.setCondition(getString("main", weatherObj));
            currentCondition.setIcon(IMG_URL + getString("icon", weatherObj));

            weatherModel.setCurrentCondition(currentCondition);

        } catch (JSONException e) {
            LOGGER.error("JSONException: {}", e);
        }
    }

    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

    private JSONObject getResource() {
        JSONObject jObj = null;
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

            while ((output = responseBuffer.readLine()) != null) {
                jObj = new JSONObject(output);
            }
            httpConnection.disconnect();
        } catch (MalformedURLException e) {
            LOGGER.error("error URL: {}", e);
        } catch (IOException e) {
            LOGGER.error("IOException: {}", e);
        } catch (JSONException e) {
            LOGGER.error("JSONException: {}", e);
        }

        if(jObj == null) {
            throw new NullPointerException();
        }

        return jObj;
    }

}
