package com.philips.dmis.swt.ui.toolkit.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CustomEvent extends Event {
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String valueOf(CustomEvent customEvent) {
        JsWriter js = new JsWriter();
        customEvent.renderJs(js);
        return js.toString();
    }

    private final String type;

    public CustomEvent() {
        this.type = getClass().getSimpleName();
    }

    public CustomEvent(String type) {
        this.type = type;
    }

    public void renderJs(JsWriter js) throws JsRenderException {
        Map<String, Object> properties = new LinkedHashMap<>();
        getProperties(properties);
        properties.put("type", type);
        try {
            js.append(OBJECT_MAPPER.writeValueAsString(properties));
        } catch (JsonProcessingException e) {
            throw new JsRenderException(e);
        }
    }

    public void getProperties(Map<String, Object> properties) {
        //
    }
}
