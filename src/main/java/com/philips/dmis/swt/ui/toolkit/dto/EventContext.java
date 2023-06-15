package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;

public class EventContext implements Serializable {
    private String contextId;
    private String widgetId;
    private String slaveId;
    private Object dataKey;
    private Object domEvent;

    public EventContext() {
        // for serializable
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(String slaveId) {
        this.slaveId = slaveId;
    }

    public Object getDataKey() {
        return dataKey;
    }

    public void setDataKey(Object dataKey) {
        this.dataKey = dataKey;
    }

    public Object getDomEvent() {
        return domEvent;
    }

    public void setDomEvent(Object domEvent) {
        this.domEvent = domEvent;
    }
}
