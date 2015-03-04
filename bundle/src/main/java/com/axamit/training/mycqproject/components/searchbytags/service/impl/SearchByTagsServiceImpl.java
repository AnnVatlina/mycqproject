package com.axamit.training.mycqproject.components.searchbytags.service.impl;

import com.axamit.training.mycqproject.components.searchbytags.model.SearchResultModel;
import com.axamit.training.mycqproject.components.searchbytags.service.SearchByTagsService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
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

    @Override
    public List<SearchResultModel> search(String path, String tags) throws LoginException, RepositoryException {

        ResourceResolver resourceResolver;

        Map<String, Object> serviceParams = new HashMap<String, Object>();
        serviceParams.put(ResourceResolverFactory.SUBSERVICE, "admin");

        resourceResolver = resolverFactory.getServiceResourceResolver(serviceParams);
        Session session = resourceResolver.adaptTo(Session.class);

        Map<String, String> map = new HashMap<String, String>();
        if(StringUtils.isNotEmpty(path)) {
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
        Query query = builder.createQuery(PredicateGroup.create(map), session);

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
}
