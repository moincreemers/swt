package com.philips.dmis.swt.ui.toolkit.widgets;

public class DefaultWidgetConfigurator implements WidgetConfigurator {
    private static int idCounter = 0;
    private final String id;

    String generateId() {
        return "w" + idCounter++;
    }

    public DefaultWidgetConfigurator(Class<? extends Widget> widgetClass) {
        if (widgetClass == Page.class) {
            id = widgetClass.getSimpleName().substring(0, 1).toLowerCase()
                    + widgetClass.getSimpleName().substring(1);
        } else {
            id = generateId();
        }
    }

    public DefaultWidgetConfigurator(String id) {
        if (id == null) {
            this.id = generateId();
        } else {
            this.id = id + "_" + generateId();
        }
    }

    @Override
    public String getId() {
        return id;
    }
}
