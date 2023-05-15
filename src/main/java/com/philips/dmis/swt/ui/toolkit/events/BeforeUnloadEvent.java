package com.philips.dmis.swt.ui.toolkit.events;

public class BeforeUnloadEvent extends CustomEvent {
    public BeforeUnloadEvent() {
        super(BeforeUnloadEventHandler.EVENT_NAME);
    }
}
