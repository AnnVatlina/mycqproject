package com.axamit.training.mycqproject.service.impl;

import com.axamit.training.mycqproject.service.CreatePages;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 23.02.2015, 16:18
 */
@Service(value = CreatePages.class)
@Component(metatype = false)
public class CreatePagesImpl implements CreatePages {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePagesImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void generatePages(String path, List<String> names) {

        Page page=null;
        ResourceResolver resourceResolver=null;
        PageManager pageManager=null;
        try {
            if (path != null) {
                resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
                pageManager = resourceResolver.adaptTo(PageManager.class);
                page = pageManager.getContainingPage(resourceResolver.getResource(path));
                LOGGER.error("Page $$$$"+page);
                if (page == null) {
                    throw new IllegalArgumentException("Page does not exist: " + path);
                }
            }
        } catch (LoginException e) {
            LOGGER.error("Login Exception");
        }
//        return page.getTitle();
    }
}
