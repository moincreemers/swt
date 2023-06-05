package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.SyncVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

/**
 * Converging method for all resource downloads.
 */
public class ProcessResourceResponseFunction implements JsFunction {
    public static final String ID = "processResourceResponse";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
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
        parameters.add(JsParameter.getInstance("xhrResponse", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,xhrResponse)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const uid=xhrResponse.arguments['uid'];");
        js.append("const sync=widget.%s[uid];", SyncVariable.ID);
        js.append("if(xhrResponse.status===200){");
        js.append("sync.resolve(xhrResponse);");
        js.append("}else{");
        // todo: error object?
        js.append("sync.reject(xhrResponse.status);");
        js.append("};");

        js.append("}"); // end function
    }
}
