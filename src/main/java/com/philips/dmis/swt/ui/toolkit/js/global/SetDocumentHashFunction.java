package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetDocumentHashFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(obj,isDlgOption)=>{");
        // isDlgOption=true when obj.p is a dialog page
        // in that case we want to push the id of the current page onto hash.o
        js.append("var h=%s();", JsGlobalModule.getQualifiedId(GetDocumentHashFunction.class));
        //js.append("if(h.p!=obj.p){"); // if
        js.append("if(isDlgOption!==undefined&&isDlgOption){"); // if
        js.append("h.o=h.o.concat(obj.o);");
        js.append("h.p=obj.p;");
        js.append("h.d=obj.d;");
        js.append("obj=h;");
        js.debug("console.log('sdh',obj);");
        js.append("};"); // end if
        js.append("document.location.hash='#'+%s(obj);",
                JsGlobalModule.getQualifiedId(ObjectToSearchFunction.class));
        //js.append("};"); // end if
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("obj", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("isDlgOption", JsType.BOOLEAN));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(GetDocumentHashFunction.class);
        dependencies.add(ObjectToSearchFunction.class);
    }
}