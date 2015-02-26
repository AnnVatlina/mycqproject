package com.axamit.training.mycqproject.workflow;

import com.axamit.training.mycqproject.service.CreatePages;
import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.apache.felix.scr.annotations.*;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Copyright (c) 2014 Axamit</p>
 * User: anna.vatlina on 24.02.2015, 10:44
 */
@Component(label = "Create Product pages", metatype = false, immediate = true)
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Custom Process Step Description"),
        @Property(name = Constants.SERVICE_VENDOR, value = "Anna Vatlina"),
        @Property(name = "process.label", value = "Custom process create page")}
)
@Service(WorkflowProcess.class)
public class CreatePagesStep implements WorkflowProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePagesStep.class);

    @Reference
    private CreatePages createPages;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        String path = (String) workItem.getWorkflowData().getPayload();
        String title = String.valueOf(metaDataMap.get("PROCESS_ARGS"));

        List<String> names = Arrays.asList(title.split(","));
        createPages.generatePages(path, names);
        try {
            workflowSession.getSession().save();
        } catch (RepositoryException e) {
            LOGGER.error("Repository Exception: {}", e);
        }
    }
}
