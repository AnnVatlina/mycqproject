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
//TODO @author anna.vatlina будет выглядеть эффектнее ;-)

@SlingServlet(paths = {"/bin/units"})
public class OptionsServlet extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionsServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        response.setContentType("application/json");
        try {
            JSONObject eachOption;
            JSONArray optionsArray = new JSONArray();

            //TODO создание JSONObject можно вынести в отдельный метод и / или создание конечного JSON файла в utility класс
            //TODO с методом наподобие getDialogOptions(List<String>)
            //This loop creates multiple objects under a array that will be returned.
            eachOption = new JSONObject();
            eachOption.put("text", "internal");
            eachOption.put("value", "internal");
            optionsArray.put(eachOption);

            eachOption = new JSONObject();
            eachOption.put("text", "metric");
            eachOption.put("value", "metric");
            optionsArray.put(eachOption);

            eachOption = new JSONObject();
            eachOption.put("text", "imperial");
            eachOption.put("value", "imperial");
            optionsArray.put(eachOption);

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

}
