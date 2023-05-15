package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.controller.PageExistsFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class IsValidHashFunction implements JsFunction {
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
        return JsType.BOOLEAN;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(h)=>{");
        js.append("var valid=true;");
        js.append("var pids=Object.assign([],h.p);");
        js.append("pids=pids.concat(h.o);");
        js.append("for(const i in pids){");
        js.append("if(!%s(pids[i])){", JsPageControllerModule.getQualifiedId(PageExistsFunction.class));
        js.debug("console.log('invalid page id: '+pids[i]);");
        js.append("valid=false;break;");
        js.append("}");
        js.append("};");
        js.append("return valid;");
        js.append("}"); // end func
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("h", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
