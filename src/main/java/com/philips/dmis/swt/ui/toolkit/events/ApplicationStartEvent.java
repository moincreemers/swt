package com.philips.dmis.swt.ui.toolkit.events;

public class ApplicationStartEvent extends CustomEvent {
    public ApplicationStartEvent() {
        super(ApplicationStartEventHandler.EVENT_NAME);
    }
}
