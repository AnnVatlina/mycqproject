package com.axamit.training.mycqproject.components.searchbytags.model;

import com.axamit.training.mycqproject.components.searchbytags.service.SearchByTagsService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import java.util.List;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 26.02.2015, 15:56
 */
@Model(adaptables = Resource.class)
public class SearchModel {

    @Inject
    private SearchByTagsService searchByTagsService;

    @Inject
    @Optional
    private String query;

    @Inject
    @Optional
    private List<SearchResultModel> searchResultModels;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SearchResultModel> getSearchResultModels() {
        return searchResultModels;
    }

    public void setSearchResultModels(List<SearchResultModel> searchResultModels) {
        this.searchResultModels = searchResultModels;
    }

    @PostConstruct
    protected SearchModel init() throws LoginException, RepositoryException {
        setSearchResultModels(searchByTagsService.search("", query));
        return this;
    }
}
