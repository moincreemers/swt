package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlTable extends Panel {
    public HtmlTable(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.TABLE);
    }

    public HtmlTable() {
        this(NAMELESS);
    }

    public HtmlTable(String name) {
        super(name, WidgetType.TABLE);
    }

    @Override
    protected <T extends Widget> boolean allowChild(T widget) {
        return widget instanceof HtmlTableHeader
                || widget instanceof HtmlTableFooter
                || widget instanceof HtmlTableBody
                || widget instanceof HtmlTableCaption;
    }

    /**
     * Called when a table fixed column widget is clicked (button, link).
     *
     * @param eventHandler
     * @return
     */
    public HtmlTable onClick(ClickEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }
}
