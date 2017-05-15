package tsakiris.fotis.async.engine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsakiris.fotis.async.engine.domain.KeyValue;

import java.util.List;

public class Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);

    private Converter() {
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
}
