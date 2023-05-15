package com.philips.dmis.swt.ui.toolkit.widgets;

public class WidgetConfigurationException extends Exception {
    static String getThrownLocation() {
        // note: this method uses thread frames, be careful when refactoring
        if (Thread.currentThread().getStackTrace().length < 5) {
            return "unknown";
        }
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        return ste.getClassName();
    }

    public WidgetConfigurationException(String message) {
        super(message + " but was referenced in " + getThrownLocation());
    }

    public WidgetConfigurationException(String message, Throwable cause) {
        super(message + " but was referenced in " + getThrownLocation(), cause);
    }
}
