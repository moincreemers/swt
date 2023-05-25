package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.List;

public class Format extends MapStatement {
    final ValueStatement format;

    public Format(ValueStatement format) {
        super(JsType.STRING);
        this.format = format;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(%s,%s,_obj)=>{", ARGUMENT_SERVICE_RESPONSE, ARGUMENT_TARGET);

        js.append("if(_obj==undefined||_obj==null){return '';};");

        js.append("return ");
        V.Format(format, V.Reference("_obj")).renderJs(toolkit, widget, js);
        js.append(";");

        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }

    @Override
    public void getReferences(List<Statement> statements) {
        statements.add(format);
    }
}
