package tsakiris.fotis.async.engine.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tsakiris.fotis.async.engine.common.JsonHelper;
import tsakiris.fotis.async.engine.domain.Method;
import tsakiris.fotis.async.engine.domain.Task;

import static tsakiris.fotis.async.engine.common.UrlConverter.addHeaders;
import static tsakiris.fotis.async.engine.common.UrlConverter.addQueryParams;
import static tsakiris.fotis.async.engine.common.UrlConverter.urlConverter;

@Service
public class HttpRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestService.class);

    public String startRequest(Task task) {

        final String url = urlConverter(
                task.getProtocol(),
                task.getDomain(),
                task.getPort(),
                task.getResourcePath()
        );

        final HttpResponse<String> httpResponse;

        try {
            if (task.getMethod().equals(Method.GET)) {
                httpResponse = addQueryParams(addHeaders(Unirest.get(url), task.getHeaders()), task.getQueryParams()).asString();
            } else if (task.getMethod().equals(Method.POST)) {
                httpResponse = addQueryParams(addHeaders(Unirest.post(url), task.getHeaders()), task.getQueryParams()).body(task.getBody()).asString();
            } else if (task.getMethod().equals(Method.PUT)) {
                httpResponse = addQueryParams(addHeaders(Unirest.put(url), task.getHeaders()), task.getQueryParams()).body(task.getBody()).asString();
            } else if (task.getMethod().equals(Method.DELETE)) {
                httpResponse = addQueryParams(addHeaders(Unirest.delete(url), task.getHeaders()), task.getQueryParams()).body(task.getBody()).asString();
            } else {
                return null;
            }

        } catch (UnirestException e) {
            LOGGER.error("UnirestException", e);
            return e.getMessage();
        }
        return JsonHelper.convert(httpResponse);
    }


}
