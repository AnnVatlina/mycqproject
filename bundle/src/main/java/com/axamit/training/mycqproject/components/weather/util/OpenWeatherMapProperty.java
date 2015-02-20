package com.axamit.training.mycqproject.components.weather.util;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 18.02.2015, 13:16
 */
public class OpenWeatherMapProperty {

    //TODO соглашение об именовании static final переменных - uppercase с "_" между словами.
    public static final String sysParam = "sys";

    public static final String weatherArrayParam = "weather";

    public static final String nameParam = "name";

    public static final String countryParam = "country";

    public static final String descriptionParam = "description";

    public static final String mainParam = "main";

    public static final String iconParam = "icon";

    public static final String tempMinParam = "temp_min";

    public static final String tempMaxParam = "temp_max";

    public static final String humidityParam = "humidity";

    public static final String windParam = "wind";

    public static final String windSpeedParam = "speed";

    //TODO можно убрать методы ниже и забирать значения напрямую из JSONObject
    public static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    public static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    public static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
