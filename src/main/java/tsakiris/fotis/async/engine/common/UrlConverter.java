package tsakiris.fotis.async.engine.common;

import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsakiris.fotis.async.engine.domain.KeyValue;

import java.util.List;

// TODO: refactor this, code smell..
public class UrlConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlConverter.class);

    private UrlConverter() {
    }

    public static String urlConverter(String protocol, String domain, String port, String resourcePath) {
        final String url = protocol + "://" + domain + ":" + port + resourcePath;
        LOGGER.info(url);
        return url;
    }

    public static String queryParamsConverter(List<KeyValue> queryParams) {
        if (CommonUtils.isEmpty(queryParams)) {
            return "";
        }
        StringBuilder queryParamsUrl = new StringBuilder("?");
        for (KeyValue queryParam : queryParams) {
            queryParamsUrl.append(queryParam.getKey()).append("=").append(queryParam.getValue()).append("&");
        }
        queryParamsUrl.deleteCharAt(queryParamsUrl.length() - 1);
        LOGGER.info(queryParamsUrl.toString());
        return queryParamsUrl.toString();
    }

    public static HttpRequestWithBody addQueryParams(HttpRequestWithBody httpRequest, List<KeyValue> queryParams) {
        return (HttpRequestWithBody) addQueryParamsSuper(httpRequest, queryParams);
    }

    public static GetRequest addQueryParams(GetRequest httpRequest, List<KeyValue> queryParams) {
        return (GetRequest) addQueryParamsSuper(httpRequest, queryParams);
    }

    public static HttpRequest addQueryParamsSuper(HttpRequest httpRequest, List<KeyValue> queryParams) {
        if (CommonUtils.isNotEmpty(queryParams)) {
            queryParams.forEach(queryParam -> httpRequest.queryString(queryParam.getKey(), queryParam.getValue()));
        }
        return httpRequest;
    }

    public static HttpRequestWithBody addHeaders(HttpRequestWithBody httpRequest, List<KeyValue> headers) {
        return (HttpRequestWithBody) addHeadersSuper(httpRequest, headers);
    }

    public static GetRequest addHeaders(GetRequest httpRequest, List<KeyValue> headers) {
        return (GetRequest) addHeadersSuper(httpRequest, headers);
    }

    public static HttpRequest addHeadersSuper(HttpRequest httpRequest, List<KeyValue> headers) {
        if (CommonUtils.isNotEmpty(headers)) {
            headers.forEach(header -> httpRequest.header(header.getKey(), header.getValue()));
        }
        return httpRequest;
    }
}
