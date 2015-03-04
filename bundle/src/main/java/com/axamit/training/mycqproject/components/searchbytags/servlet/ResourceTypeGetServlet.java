package com.axamit.training.mycqproject.components.searchbytags.servlet;

import com.axamit.training.mycqproject.components.searchbytags.model.SearchResultModel;
import com.axamit.training.mycqproject.components.searchbytags.service.SearchByTagsService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.servlets.ServletResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 27.02.2015, 10:52
 */
@SlingServlet(paths = "/bin/result.json", metatype = true)
public class ResourceTypeGetServlet extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceTypeGetServlet.class);

    private String url;

    @Reference
    private ServletResolver servletResolver;

    @Reference
    private SearchByTagsService searchByTagsService;


    @Activate
    protected void activate(Map<String, Object> properties) {
        this.url = (String) properties.get("url");
    }

    @Override
    public void doGet(SlingHttpServletRequest request,
                      SlingHttpServletResponse response) throws ServletException,
            IOException {
        List<RequestParameter> parameterList = request.getRequestParameterList();
        String query = request.getParameter("q");
        if (query == null || query.trim().length() == 0) {
            query = "*:*";
        }
        RequestPathInfo pathInfo = request.getRequestPathInfo();

        LOGGER.info("Test content");
        try {
            List<SearchResultModel> searchResults = searchByTagsService.search("", query);

            JSONArray optionsArray = new JSONArray();

            for (SearchResultModel searchResult : searchResults) {
                JSONObject eachOption = new JSONObject();
                eachOption.put("path", searchResult.getPath());
                eachOption.put("title", searchResult.getTitle());
                optionsArray.put(eachOption);
            }
            JSONObject finalJsonResponse = new JSONObject();
            finalJsonResponse.put("root",optionsArray);
            response.getWriter().println(finalJsonResponse.toString());

        } catch (LoginException e) {
            LOGGER.error("Login Exception: ", e);
        } catch (RepositoryException e) {
            LOGGER.error("Repository Exception: ", e);
        } catch (JSONException e) {
            LOGGER.error("Json Exception occured while adding data to JSON Object : ", e);
        }
    }
}
