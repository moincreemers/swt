package com.philips.dmis.swt.ui.toolkit.widgets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;


public class StaticData extends DataSourceWidget {
    private static final Logger LOG = Logger.getLogger(StaticData.class.getName());

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static StaticData unsafe(String json) {
        return new StaticData(json);
    }

    private Object object;
    private String json = "";
    private boolean serializationCompleted;

    private StaticData(String json) {
        super(WidgetType.STATICDATA, true);
        if (json == null) {
            setJson("");
            return;
        }
        setJson(json);
        setCacheType(CacheType.DISABLED);
    }

    public StaticData(Object object) throws IOException {
        this(object, true);
    }

    public StaticData(Object object, boolean autoRefresh) throws IOException {
        super(WidgetType.STATICDATA, true, autoRefresh);
        this.object = object;
    }

    void ensureSerialized() throws JsRenderException {
        if (serializationCompleted) {
            return;
        }
        serializationCompleted = true;
        if (object == null) {
            setJson("");
            return;
        }
        ServiceResponse serviceResponse = new ServiceResponse(ServiceResponse.wrap(object));
        serviceResponse.addMeta(ServiceResponse.META_DATASOURCE_ID, getId());
        try {
            setJson(OBJECT_MAPPER.writeValueAsString(serviceResponse));
        } catch (JsonProcessingException e) {
            throw new JsRenderException("data widget failed to serialize", e);
        }
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
        serializationCompleted = false;
        json = "";
    }

    public String getJson() throws JsRenderException {
        ensureSerialized();
        return json;
    }

    public void setJson(String json) {
        //LOG.info("Data, JSON: " + json);
        this.json = json;
        serializationCompleted = true;
    }

    /**
     * @return the JSON string is first URL encoded, then Base64 encoded.
     * This ensures reliable decoding is possible in JS.
     * <p>
     * Other solutions like escaping quotes is simply too finicky and
     * will cause the JS Client to fail if any mistake is made.
     * </p>
     * <p>
     * Note that this makes the Data widget probably unsuitable for transfer
     * of large amounts of data. How large is not defined but both encoding
     * methods enlarge the data considerably compared to a binary format.
     * Using a QueryService to get the data from a server is probably a better
     * choice if the data is large.
     * </p>
     * @throws JsRenderException
     */
    public String getUrlEncodedPlusBase64EncodedJson() throws JsRenderException {
        //System.out.println("encoding JSON data: " + getJson() + " for object: " + object);
        String escapedJson = URLEncoder.encode(getJson(), StandardCharsets.UTF_8).replace("+", " ");
        return new String(Base64.getEncoder().encode(escapedJson.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }
}
