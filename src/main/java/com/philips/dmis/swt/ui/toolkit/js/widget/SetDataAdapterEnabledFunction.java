package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.DisabledDataAdaptersVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.DataBoundWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.DataSourceSupplier;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetDataAdapterEnabledFunction implements JsFunction {
    public static final String ID = "setDataAdapterEnabled";

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
        return JsType.VOID;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("enabled", JsType.BOOLEAN));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,enabled)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const i=widget.%s.indexOf(id);", DisabledDataAdaptersVariable.ID);
        js.append("if(enabled===true&&i>-1){");
        js.append("widget.%s.splice(i,1);", DisabledDataAdaptersVariable.ID);
        js.append("}else if(enabled===false&&i==-1){");
        js.append("widget.%s.push(id);", DisabledDataAdaptersVariable.ID);
        js.append("};");

        js.append("}"); // end function
    }
}
