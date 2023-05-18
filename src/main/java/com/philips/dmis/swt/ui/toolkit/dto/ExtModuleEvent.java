package com.philips.dmis.swt.ui.toolkit.dto;

import java.io.Serializable;

public class ExtModuleEvent implements Serializable {
    public static final String READY = "ready";
    public static final String DISPLAY = "display";
    public static final String BEGIN_PROGRESS = "beginprogress";
    public static final String END_PROGRESS = "endprogress";

    public String name;
    public String pageId;
    public String widgetId;

    public ExtModuleEvent() {
        // for serialization
    }

    public ExtModuleEvent(String name, String pageId, String widgetId) {
        this.name = name;
        this.pageId = pageId;
        this.widgetId = widgetId;
    }
}
