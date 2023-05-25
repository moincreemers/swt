package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class ReplaceWithFieldIfEmpty extends MapStatement {
    public ReplaceWithFieldIfEmpty() {
        super(JsType.OBJECT);
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(%s,%s,obj,value,alternateValue)=>{", ARGUMENT_SERVICE_RESPONSE, ARGUMENT_TARGET);

        js.append("if(value==undefined||value==null||value==''){");
        js.append("return alternateValue;");
        js.append("};");
        js.append("return value;");

        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }

    @Override
    public void getReferences(List<Statement> statements) {
    }
}
