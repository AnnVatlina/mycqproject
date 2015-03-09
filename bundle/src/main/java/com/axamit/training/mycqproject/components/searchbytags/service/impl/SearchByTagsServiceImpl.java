package com.axamit.training.mycqproject.components.searchbytags.service.impl;

import com.axamit.training.mycqproject.components.searchbytags.model.SearchResultModel;
import com.axamit.training.mycqproject.components.searchbytags.service.SearchByTagsService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.JcrTagManagerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 26.02.2015, 15:57
 */
@Component(metatype = true, immediate = true)
@Service(value = SearchByTagsService.class)
public class SearchByTagsServiceImpl implements SearchByTagsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchByTagsServiceImpl.class);

    @Reference
    private QueryBuilder builder;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    JcrTagManagerFactory jcrTagManagerFactory;

    @Override
    public List<SearchResultModel> search(String path, String tags) throws LoginException, RepositoryException {

        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(path)) {
            map.put("path", path);
        } else {
            map.put("path", "/content");
        }
        map.put("type", "cq:Page");
        map.put("group.p.or", "true"); // combine this group with OR
        map.put("tagid", "mycqproject:weather/" + tags);
        map.put("tagid.property", "jcr:content/cq:tags");
//        map.put("group.1_fulltext.relPath", "jcr:content/@cq:tags");

        // can be done in map or with Query methods
        map.put("p.offset", String.valueOf(0)); // same as query.setStart(0) below
        map.put("p.limit", String.valueOf(20)); // same as query.setHitsPerPage(20) below
        Query query = builder.createQuery(PredicateGroup.create(map), getSession(getResourceResolver()));

        //query.setStart(0);
        //query.setHitsPerPage(20);

        SearchResult result = query.getResult();

        // paging metadata
        int hitsPerPage = result.getHits().size(); // 20 (set above) or lower
        long totalMatches = result.getTotalMatches();
        long offset = result.getStartIndex();
        long numberOfPages = totalMatches / 20;

        // iterating over the results
        List<SearchResultModel> searchResultModels = new ArrayList<SearchResultModel>();
        for (Hit hit : result.getHits()) {
            SearchResultModel searchResultModel = new SearchResultModel();
            ValueMap props = hit.getProperties();
            searchResultModel.setTitle(String.valueOf(props.get("jcr:title")));
            searchResultModel.setPath(hit.getPath());
            searchResultModels.add(searchResultModel);
        }

        LOGGER.info("Some information");

        return searchResultModels;
    }

    @Override
    public List<SearchResultModel> searchByTags(String path, String conjunction, String[] tags) throws LoginException, RepositoryException {
        List<SearchResultModel> searchResults = new ArrayList<SearchResultModel>();

        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(path)) {
            map.put("path", path);
        } else {
            map.put("path", "/content");
        }

        map.put("type", "cq:Page");
//        map.put("group.p.or", "true"); // combine this group with OR

        map.put("tagid.and", conjunction);
        map = getPropertiMap(map, tags);

        map.put("p.offset", String.valueOf(0)); // same as query.setStart(0) below
        map.put("p.limit", String.valueOf(20)); // same as query.setHitsPerPage(20) below
        Query query = builder.createQuery(PredicateGroup.create(map), getSession(getResourceResolver()));

        //query.setStart(0);
        //query.setHitsPerPage(20);

        SearchResult result = query.getResult();

        // paging metadata
        List<Hit> results = result.getHits();
        long totalMatches = result.getTotalMatches();
        /*int hitsPerPage = results.size(); // 20 (set above) or lower
        long offset = result.getStartIndex();
        long numberOfPages = totalMatches / 20;*/

        // iterating over the results
        for (Hit hit : results) {
            SearchResultModel searchResultModel = new SearchResultModel();
            ValueMap props = hit.getProperties();
            searchResultModel.setTitle(String.valueOf(props.get("jcr:title")));
            searchResultModel.setPath(hit.getPath());
            searchResults.add(searchResultModel);
        }

        LOGGER.info("Some information");


        return searchResults;
    }

    private Session getSession(ResourceResolver resourceResolver) throws LoginException {
        return resourceResolver.adaptTo(Session.class);
    }

    private ResourceResolver getResourceResolver() throws LoginException {
        ResourceResolver resourceResolver;

        Map<String, Object> serviceParams = new HashMap<String, Object>();
        serviceParams.put(ResourceResolverFactory.SUBSERVICE, "admin");

        return resolverFactory.getServiceResourceResolver(serviceParams);
    }

    private Map<String, String> getPropertiMap(Map<String, String> searchMap, String[] tags) {
        searchMap.put("tagid.property", "jcr:content/cq:tags");
        if (tags.length == 1) {
            searchMap.put("tagid", tags[0]);
        } else {
            for (int i = 0; i < tags.length; i++) {
                searchMap.put( "tagid.property." + (i + 1) + "_value", tags[i]);
            }
        }
        return searchMap;
    }
}
