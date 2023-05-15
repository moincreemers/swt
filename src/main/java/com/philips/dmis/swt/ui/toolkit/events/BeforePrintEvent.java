package com.philips.dmis.swt.ui.toolkit.events;

public class BeforePrintEvent extends CustomEvent {
    public BeforePrintEvent() {
        super(BeforePrintEventHandler.EVENT_NAME);
    }
}
