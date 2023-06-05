package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

public class DisabledDataAdaptersVariable implements JsVariable {
    public static final String ID = "disabledDataAdapters";
    private final Widget widget;
    private final WidgetType widgetType;

    public DisabledDataAdaptersVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier || widget instanceof DataBoundWidget<?>;
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public String getPublicName(String id) {
        return id;
    }

    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        if (widget instanceof DataSourceSupplier) {
            DataSourceSupplier dataSourceSupplier = (DataSourceSupplier) widget;
            if (dataSourceSupplier.getDisabledDataAdapters().isEmpty()) {
                js.append("[]");
            } else {
                js.append("['%s']",
                        String.join("','", dataSourceSupplier.getDisabledDataAdapters()));
            }
        } else {
            js.append("[]");
        }
    }
}
