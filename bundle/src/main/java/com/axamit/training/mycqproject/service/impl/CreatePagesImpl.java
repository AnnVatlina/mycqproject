package com.axamit.training.mycqproject.service.impl;

import com.axamit.training.mycqproject.service.CreatePages;
import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.collections.IteratorUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 23.02.2015, 16:18
 */
@Service(value = CreatePages.class)
@Component(metatype = true, immediate = true)
public class CreatePagesImpl implements CreatePages {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePagesImpl.class);

    private static final String TEMPLATE_PATH = "mycqproject/components/contentpage/inner";

    private static final String MAIN_TAG_ID = "mycqproject:weather";

    private ResourceResolver resourceResolver = null;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void generatePages(String path, List<String> names) {

        Page page = null;
        PageManager pageManager = null;
        try {
            if (path != null) {
                Map<String, Object> serviceParams = new HashMap<String, Object>();
                serviceParams.put(ResourceResolverFactory.SUBSERVICE, "admin");

                resourceResolver = resolverFactory.getServiceResourceResolver(serviceParams);
                Session session = resourceResolver.adaptTo(Session.class);

                pageManager = resourceResolver.adaptTo(PageManager.class);
                page = pageManager.getContainingPage(resourceResolver.getResource(path));

                if (page == null) {
                    throw new IllegalArgumentException("Page does not exist: " + path);
                }
                for (String name : names) {
                    createActionNode(path, TEMPLATE_PATH, name, session);
                }
                if (resourceResolver.hasChanges()) {
                    resourceResolver.commit();
                }
            }
        } catch (LoginException e) {
            LOGGER.error("Login Exception: {}", e);
        } catch (PersistenceException e) {
            LOGGER.error("PersistenceException: {}", e);
        }
    }

    private void createActionNode(String relPath, String type, String name, Session session) {
        relPath = JcrUtil.escapeIllegalJcrChars(relPath + "/" + name.toLowerCase().trim().replace(" ", ""));

        try {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getContainingPage(resourceResolver.getResource(relPath));

            if (page == null) {
                Node newPage = JcrUtil.createPath(relPath, true, "sling:Folder", "cq:Page", session, false);
                Node content = newPage.addNode("jcr:content", "cq:PageContent");
                content.setProperty("sling:resourceType", type);
                content.setProperty("cq:distribute", false);
                session.save();
                content.setProperty("jcr:title", name);
                content.setProperty("cq:tags", (String[]) getRandomTags());
                content.setProperty("jcr:description", "Auto Create page " + name);
                content.setProperty("cq:lastModified", Calendar.getInstance());
                content.setProperty("cq:lastModifiedBy", session.getUserID());
                content.setProperty("cq:distribute", true);
            }
        } catch (RepositoryException e) {
            LOGGER.error("Repository Exception: {}", e);
        }
    }

    private Object[] getRandomTags() {
        Random randomGenerator = new Random();
        TagManager tagManager = resourceResolver.adaptTo(TagManager.class);

        Tag subgroupNameSpace = tagManager.resolve(MAIN_TAG_ID);
        List<Tag> allTags = IteratorUtils.toList(subgroupNameSpace.listAllSubTags());
        randomGenerator.nextInt(allTags.size());
        List<String> selectTags = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            int select = randomGenerator.nextInt(allTags.size());
            if (!selectTags.contains(allTags.get(select))) {
                selectTags.add(allTags.get(select).getPath());
            }
        }

        return selectTags.toArray(new String[selectTags.size()]);
    }
}
