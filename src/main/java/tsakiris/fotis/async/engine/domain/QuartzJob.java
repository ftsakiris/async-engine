package tsakiris.fotis.async.engine.domain;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

import static tsakiris.fotis.async.engine.common.Consts.*;
import static tsakiris.fotis.async.engine.common.UrlConverter.urlConverter;
import static tsakiris.fotis.async.engine.controllers.TaskController.RETRY_PATH;

public class QuartzJob implements org.quartz.Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJob.class);

    public QuartzJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        // Extract data from job
        Map<String, String> properties = (Map<String, String>) context.getMergedJobDataMap().get(Map.class.getCanonicalName());
        Set<String> taskIds = (Set<String>) context.getMergedJobDataMap().get(Task.class.getCanonicalName());

        taskIds.forEach(taskId -> {
            final String url = urlConverter(
                    DEFAULT_PROTOCOL,
                    properties.get(SERVER_IP),
                    properties.get(SERVER_PORT),
                    properties.get(SERVER_CONTEXT_PATH) + RETRY_PATH + "/" + taskId
            );

            try {
                final HttpResponse<String> httpResponse =
                        Unirest.post(url).asString();
            } catch (UnirestException e) {
                LOGGER.error("UnirestException", e);
            }
        });

    }
}
