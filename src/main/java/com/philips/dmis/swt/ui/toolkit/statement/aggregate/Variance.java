package com.philips.dmis.swt.ui.toolkit.statement.aggregate;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class Variance extends AggregateStatement {
    public Variance() {
        super(JsType.NUMBER);
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(data)=>{");
        js.append("if(data.length==0){return 0;}");
        js.append("const avg=(array)=>{");
        js.append("var t=0;");
        js.append("for(i in array){");
        js.append("t+=array[i];");
        js.append("};");
        js.append("return t/data.length;");
        js.append("};");
        js.append("const mean=avg(data);");
        js.append("const a=[];");
        js.append("for(i in data){");
        js.append("a.push(Math.pow(data[i]-mean,2));");
        js.append("};");
        js.append("return avg(a);");
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
