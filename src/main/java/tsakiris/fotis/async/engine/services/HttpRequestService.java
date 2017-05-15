package tsakiris.fotis.async.engine.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tsakiris.fotis.async.engine.common.CommonUtils;
import tsakiris.fotis.async.engine.common.JsonHelper;
import tsakiris.fotis.async.engine.domain.Method;
import tsakiris.fotis.async.engine.domain.KeyValue;
import tsakiris.fotis.async.engine.domain.Task;

import java.util.List;

import static tsakiris.fotis.async.engine.common.Converter.urlConverter;

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

    private HttpRequestWithBody addQueryParams(HttpRequestWithBody httpRequest, List<KeyValue> queryParams) {
        return (HttpRequestWithBody) addQueryParamsSuper(httpRequest, queryParams);
    }

    private GetRequest addQueryParams(GetRequest httpRequest, List<KeyValue> queryParams) {
        return (GetRequest) addQueryParamsSuper(httpRequest, queryParams);
    }

    private HttpRequest addQueryParamsSuper(HttpRequest httpRequest, List<KeyValue> queryParams) {
        if (CommonUtils.isNotEmpty(queryParams)) {
            queryParams.forEach(queryParam -> httpRequest.queryString(queryParam.getKey(), queryParam.getValue()));
        }
        return httpRequest;
    }

    private HttpRequestWithBody addHeaders(HttpRequestWithBody httpRequest, List<KeyValue> headers) {
        return (HttpRequestWithBody) addHeadersSuper(httpRequest, headers);
    }

    private GetRequest addHeaders(GetRequest httpRequest, List<KeyValue> headers) {
        return (GetRequest) addHeadersSuper(httpRequest, headers);
    }

    private HttpRequest addHeadersSuper(HttpRequest httpRequest, List<KeyValue> headers) {
        if (CommonUtils.isNotEmpty(headers)) {
            headers.forEach(header -> httpRequest.header(header.getKey(), header.getValue()));
        }
        return httpRequest;
    }
}
