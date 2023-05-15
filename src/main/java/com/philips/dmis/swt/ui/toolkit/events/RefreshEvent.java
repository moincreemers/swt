package com.philips.dmis.swt.ui.toolkit.events;

import java.util.Map;

public class RefreshEvent extends CustomEvent {
    public RefreshEvent() {
        super(RefreshEventHandler.EVENT_NAME);
    }

    @Override
    public void getProperties(Map<String, Object> properties) {
        super.getProperties(properties);
        properties.put("reason", "");
    }
}
