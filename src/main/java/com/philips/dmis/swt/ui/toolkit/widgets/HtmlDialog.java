package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.events.CloseDialogEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.OpenDialogEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlDialog extends ContainerWidget<HtmlDialog> {
    public HtmlDialog(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.DIALOG);
    }

    public HtmlDialog() {
        super(WidgetType.DIALOG);
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return !(widget instanceof HtmlDialog);
    }

    public HtmlDialog onOpen(OpenDialogEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public HtmlDialog onClose(CloseDialogEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }
}
