package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

public class SubscribersVariable implements JsVariable {
    public static final String ID = "subscribers";
    private final Widget widget;
    private final WidgetType widgetType;

    public SubscribersVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof DataSourceSupplier;
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
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        DataSourceSupplier dataSourceSupplier = (DataSourceSupplier) widget;
        int i = 0;
        js.append("[");
        for (HasDataSource<?> subscriber : dataSourceSupplier.getSubscribers().keySet()) {
            DataSourceUsage dataSourceUsage = dataSourceSupplier.getSubscribers().get(subscriber);
            if (i > 0) {
                js.append(",");
            }
            js.append("{");
            js.append("dataSourceId:'%s',", widget.getId());
            js.append("subscriberId:'%s',", subscriber.asWidget().getId());
            js.append("dataSourceUsage:'%s',", dataSourceUsage.name());
            js.append("cacheType:'%s'", dataSourceSupplier.getCacheType().name());
            js.append("}");
            i++;
        }
        js.append("]");

//        js.append("%s('%s',reason,'%s','%s','%s');",
//                JsWidgetModule.getId(BeforeUpdateFunction.class),
//                subscriber.asWidget().getId(),
//                dataSourceSupplier.getCacheType().name(),
//                dataSourceUsage.name(),
//                widget.getId());
    }
}
