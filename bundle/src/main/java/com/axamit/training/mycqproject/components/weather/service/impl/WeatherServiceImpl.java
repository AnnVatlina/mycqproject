package com.axamit.training.mycqproject.components.weather.service.impl;

import com.axamit.training.mycqproject.components.weather.models.CurrentCondition;
import com.axamit.training.mycqproject.components.weather.models.WeatherModel;
import com.axamit.training.mycqproject.components.weather.service.WeatherService;
import com.axamit.training.mycqproject.components.weather.util.OpenWeatherMapProperty;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.ComponentContext;
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

@Component(metatype = true, immediate = true)
@Service(value = WeatherService.class)
//TODO Так же можно размещать @Property внутри класса, привязав к static final переменной, которая будет хранить имя
//TODO свойства делая его доступным для других классов.

@Properties({
        @Property(name = "targetURL", description = "Link for get data"),
        @Property(name = "IMG_URL", description = "Path for image")
})
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private String targetURL;
    private String IMG_URL;

    @Activate
    public void activate(ComponentContext ctx) {
        targetURL = (String) ctx.getProperties().get("targetURL");
        IMG_URL = (String) ctx.getProperties().get("IMG_URL");
    }

    @Override
    public void doSomething(WeatherModel weatherModel) {
        LOGGER.info("WeatherServiceImpl.doSomething");
        String city = weatherModel.getCityWeatherComponents();
        String unit = weatherModel.getUnitWeatherComponents();
        if (city == null) {
            LOGGER.error("Not value for city.");
            //TODO Я бы предложил бросать на верх какой-нибудь кастомный exception, который можно было бы перехватить
            //TODO на уровне контроллера и уведомить пользователя о возникших проблемах.
            throw new NullPointerException();
        }
        try {
            JSONObject jObj = getResource(city, unit);
            JSONObject sysObj = OpenWeatherMapProperty.getObject(OpenWeatherMapProperty.sysParam, jObj);
            JSONObject weatherObj = jObj.getJSONArray(OpenWeatherMapProperty.weatherArrayParam).getJSONObject(0);
            //TODO Мне нравится подход к построению сущностей - static builder паттерн. Из плюсов - симпатичный читаемый код.
            //TODO Возможно, вам придется по вкусу.
            //TODO В Идее есть плагин, который генирирует необходимый код для сущности.
            //TODO Я бы вынес в отдельный метод конвертацию.
            CurrentCondition currentCondition = new CurrentCondition();
            currentCondition.setCity(OpenWeatherMapProperty.getString(OpenWeatherMapProperty.nameParam, jObj));
            currentCondition.setCountry(OpenWeatherMapProperty.getString(OpenWeatherMapProperty.countryParam, sysObj));
            currentCondition.setDescription(OpenWeatherMapProperty.getString(OpenWeatherMapProperty.descriptionParam, weatherObj));
            currentCondition.setCondition(OpenWeatherMapProperty.getString(OpenWeatherMapProperty.mainParam, weatherObj));
            currentCondition.setIcon(IMG_URL + OpenWeatherMapProperty.getString(OpenWeatherMapProperty.iconParam, weatherObj));

            JSONObject mainObj = OpenWeatherMapProperty.getObject(OpenWeatherMapProperty.mainParam, jObj);
            currentCondition.setHumidity(OpenWeatherMapProperty.getFloat(OpenWeatherMapProperty.humidityParam, mainObj));
            currentCondition.setTempMin(OpenWeatherMapProperty.getFloat(OpenWeatherMapProperty.tempMinParam, mainObj));
            currentCondition.setTempMax(OpenWeatherMapProperty.getFloat(OpenWeatherMapProperty.tempMaxParam, mainObj));

            JSONObject windObj = OpenWeatherMapProperty.getObject(OpenWeatherMapProperty.windParam, jObj);
            currentCondition.setWindSpeed(OpenWeatherMapProperty.getFloat(OpenWeatherMapProperty.windSpeedParam, windObj));

            weatherModel.setCurrentCondition(currentCondition);

        } catch (JSONException e) {
            LOGGER.error("JSONException: {}", e);
        }
    }

    private JSONObject getResource(String city, String unit) {
        JSONObject jObj = null;
        String currentPath = targetURL + "?id=" + city;
        //TODO StringUtils.isNotBlank() - альтернатива.
        if (unit != null) {
            currentPath = currentPath + "&units=" + unit;
        }
        try {
            //TODO функциональность получения файла можно вынести в отдельный utility class с методом наподобие getJSONFile(String url)
            URL restServiceURL = new URL(currentPath);

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

        if (jObj == null) {
            LOGGER.error("Get Null for request.");
            throw new NullPointerException();
        }

        return jObj;
    }

}
