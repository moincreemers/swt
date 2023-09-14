package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

@PageXmlElement("name")
public class CalculatedValueWidget extends DataSourceWidget implements HasCalculatedValue {
    private final String name;
    private final ValueStatement value;

    public CalculatedValueWidget(WidgetConfigurator widgetConfigurator, String name, ValueStatement value) {
        super(widgetConfigurator, WidgetType.CALCULATED);
        this.name = name;
        this.value = value;
        setExpectServiceResponse(true);
    }

    public CalculatedValueWidget(String name, ValueStatement value) {
        super(WidgetType.CALCULATED);
        this.name = name;
        this.value = value;
        setExpectServiceResponse(true);
    }

    public String getName() {
        return name;
    }

    public ValueStatement getValue() {
        return value;
    }

    @Override
    public void renderValue(Toolkit toolkit, JsWriter js) {
        js.append("{"); // response
        js.append("error:false,warning:false,status:[],meta:{%s:'%s'},",
                ServiceResponse.META_DATASOURCE_ID, getId());
        js.append("data:{"); // data
        js.append("items:["); // items
        js.append("{"); // record
        js.append("%s:", name);
        value.renderJs(toolkit, this, js);
        js.append("}"); // end record
        js.append("]"); // end items
        js.append("}"); // end data
        js.append("}"); // end response
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.OBJECT;
    }
}
