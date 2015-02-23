package com.axamit.training.mycqproject.components.weather;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 12.02.2015, 15:08
 */

@SlingServlet(paths = {"/bin/units"})
public class OptionsServlet extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionsServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        response.setContentType("application/json");
        try {
            JSONArray optionsArray = new JSONArray();

            //This loop creates multiple objects under a array that will be returned.
            optionsArray.put(createObject("internal", "internal"));
            optionsArray.put(createObject("metric", "metric"));
            optionsArray.put(createObject("imperial", "imperial"));

            JSONObject finalJsonResponse = new JSONObject();
            //Adding this finalJsonResponse object to showcase optionsRoot property functionality
            finalJsonResponse.put("root", optionsArray);

            response.getWriter().println(finalJsonResponse.toString());
        } catch (JSONException e) {
            LOGGER.error("Json Exception occured while adding data to JSON Object : ", e);
        } catch (IOException e) {
            LOGGER.error("IOException occured while getting Print Writer from SlingServletResponse : ", e);
        }
    }

    private JSONObject createObject(String text, String value) {
        JSONObject eachOption = new JSONObject();
        try {
            eachOption.put("text", text);
            eachOption.put("value", value);
        } catch (JSONException e) {
            LOGGER.error("Json Exception occured while adding data to JSON Object : ", e);
        }
        return eachOption;
    }

}
