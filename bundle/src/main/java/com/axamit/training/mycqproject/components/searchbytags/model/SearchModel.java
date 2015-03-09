package com.axamit.training.mycqproject.components.searchbytags.model;

import com.axamit.training.mycqproject.components.searchbytags.service.SearchByTagsService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import java.util.List;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 26.02.2015, 15:56
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SearchModel {

    @Inject
    private SearchByTagsService searchByTagsService;

    @Inject
    private String query;

    @Inject
    private String[] listTags;

    @Inject
    private String conjunction;

    @Inject
    private String searchPath;

    private List<SearchResultModel> searchResultModels;

    public String getQuery() {
        return query;
    }

    public String[] getListTags() {
        return listTags;
    }

    public List<SearchResultModel> getSearchResultModels() {
        return searchResultModels;
    }

    public void setSearchResultModels(List<SearchResultModel> searchResultModels) {
        this.searchResultModels = searchResultModels;
    }

    public String getConjunction() {
        return conjunction;
    }

    public String getSearchPath() {
        return searchPath;
    }

    @PostConstruct
    protected SearchModel init() throws LoginException, RepositoryException {
        if (listTags != null) {
            setSearchResultModels(searchByTagsService.searchByTags(searchPath, conjunction, listTags));
        } else {
            setSearchResultModels(searchByTagsService.search("", query));
        }
        return this;
    }
}
