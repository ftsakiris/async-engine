package tsakiris.fotis.async.engine.domain;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsakiris.fotis.async.engine.common.JsonHelper;

import java.util.Arrays;
import java.util.Map;

import static tsakiris.fotis.async.engine.common.Consts.*;
import static tsakiris.fotis.async.engine.common.UrlConverter.addHeaders;
import static tsakiris.fotis.async.engine.common.UrlConverter.urlConverter;

public class QuartzJob implements org.quartz.Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJob.class);

    public QuartzJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        // Extract data from job
        Map<String, String> properties = (Map<String, String>) context.getMergedJobDataMap().get(Map.class.getCanonicalName());

        final String url = urlConverter(
                DEFAULT_PROTOCOL,
                properties.get(SERVER_IP),
                properties.get(SERVER_PORT),
                properties.get(SERVER_CONTEXT_PATH)
        );

        try {
            final HttpResponse<String> httpResponse =
                    addHeaders(Unirest.post(url), Arrays.asList(new KeyValue("Content-Type", "application/json"))).body(JsonHelper.convert(new Task(
                            null,
                            null,
                            null,
                            "http",
                            "httpbin.org",
                            "/ip",
                            "80",
                            Method.GET,
                            null,
                            null
                    ))).asString();
        } catch (UnirestException e) {
            LOGGER.error("UnirestException", e);
        }

        System.err.println("Hello World!  QuartzJob is executing.");

    }
}
