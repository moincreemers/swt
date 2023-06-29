package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HasDataTemplate;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.Map;

public class DataTemplateIdsVariable implements JsVariable {
    public static final String DEFAULT_TEMPLATE_ID = "__default__";
    public static final String ID = "dataTemplateIds";
    private final Widget widget;
    private final WidgetType widgetType;

    public DataTemplateIdsVariable(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasDataTemplate;
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
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        HasDataTemplate hasDataTemplate = (HasDataTemplate)widget;
        Widget defaultTemplateWidget = hasDataTemplate.getDefaultTemplateWidget();
        Map<Widget, ValueStatement> templateWidgets = hasDataTemplate.getTemplateWidgets();
        if (defaultTemplateWidget == null) {
            js.append("{}");
            return;
        }
        js.append("{");
        js.append("'%s'", DEFAULT_TEMPLATE_ID);
        js.append(":'%s'", defaultTemplateWidget.getId());
        for (Widget templateWidget : templateWidgets.keySet()) {
            ValueStatement valueStatement = templateWidgets.get(templateWidget);
            js.append(",");
            valueStatement.renderJs(toolkit, templateWidget, js);
            js.append(":'%s'", templateWidget.getId());
        }
        js.append("}");
    }
}
