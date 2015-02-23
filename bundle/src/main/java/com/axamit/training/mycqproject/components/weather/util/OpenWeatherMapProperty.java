package com.axamit.training.mycqproject.components.weather.util;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 18.02.2015, 13:16
 */
public class OpenWeatherMapProperty {

    public static final String sys_param = "sys";

    public static final String weather_array_param = "weather";

    public static final String name_param = "name";

    public static final String country_param = "country";

    public static final String description_param = "description";

    public static final String main_param = "main";

    public static final String icon_param = "icon";

    public static final String temp_min_param = "temp_min";

    public static final String temp_max_param = "temp_max";

    public static final String humidity_param = "humidity";

    public static final String wind_param = "wind";

    public static final String wind_speed_param = "speed";

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
