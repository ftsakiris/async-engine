package tsakiris.fotis.async.engine.common;

import org.junit.Assert;
import org.junit.Test;
import tsakiris.fotis.async.engine.domain.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class ConverterTest {

    @Test
    public void urlConverter() throws Exception {
        Assert.assertEquals("http://www.google.com:80/search", Converter.urlConverter("http", "www.google.com", "80", "/search"));
    }

    @Test
    public void queryParamsConverter() throws Exception {
        List<KeyValue> queryParams = new ArrayList<>();
        queryParams.add(new KeyValue("name", "fotakos"));
        queryParams.add(new KeyValue("name", "Aleksakos"));
        queryParams.add(new KeyValue("filter", "pre"));
        Assert.assertEquals("?name=fotakos&name=Aleksakos&filter=pre", Converter.queryParamsConverter(queryParams));
    }

}