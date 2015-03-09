package com.axamit.training.mycqproject.components.searchbytags.service;

import com.axamit.training.mycqproject.components.searchbytags.model.SearchResultModel;
import org.apache.sling.api.resource.LoginException;

import javax.jcr.RepositoryException;
import java.util.List;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 26.02.2015, 15:56
 */
public interface SearchByTagsService {

    List<SearchResultModel> search(String path, String tags) throws LoginException, RepositoryException;

    List<SearchResultModel> searchByTags(String path, String conjunction, String[] tags) throws LoginException, RepositoryException;
}
