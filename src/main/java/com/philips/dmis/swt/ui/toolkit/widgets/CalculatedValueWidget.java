package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public class CalculatedValueWidget extends DataSourceWidget implements HasCalculatedValue {
    private final String name;
    private final ValueStatement valueStatement;

    public CalculatedValueWidget(String name, ValueStatement valueStatement) {
        super(WidgetType.CALCULATED, true);
        this.name = name;
        this.valueStatement = valueStatement;
    }

    public ValueStatement getValueStatement() {
        return valueStatement;
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
        valueStatement.renderJs(toolkit, this, js);
        js.append("}"); // end record
        js.append("]"); // end items
        js.append("}"); // end data
        js.append("}"); // end response
    }
}
