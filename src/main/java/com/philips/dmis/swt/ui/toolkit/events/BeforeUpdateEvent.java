package com.philips.dmis.swt.ui.toolkit.events;

public class BeforeUpdateEvent extends CustomEvent {
    public BeforeUpdateEvent() {
        super(BeforeUpdateEventHandler.EVENT_NAME);
    }
}
