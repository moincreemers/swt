package com.philips.dmis.swt.ui.toolkit.statement.aggregate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class Minimum extends AggregateStatement {
    public Minimum() {
        super(JsType.NUMBER);
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(%s)=>{", ARGUMENT_DATA);
        js.append("if(data.length==0){return 0;}");
        js.append("var min=null;");
        js.append("for(i in data){");
        js.append("min=(min==null)?data[i]:Math.min(min,data[i]);");
        js.append("};");
        js.append("return min;");
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
