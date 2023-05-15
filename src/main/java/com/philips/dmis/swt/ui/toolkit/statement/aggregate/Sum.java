package com.philips.dmis.swt.ui.toolkit.statement.aggregate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

public class Sum extends AggregateStatement {
    public Sum() {
        super(JsType.NUMBER);
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(data)=>{");
        js.append("if(data.length==0){return 0;}");
        js.append("var sum=0;");
        js.append("for(i in data){");
        js.append("sum+=data[i];");
        js.append("};");
        js.append("return sum;");
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
