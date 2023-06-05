package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetViewFieldFunction implements JsFunction {
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
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse,nameOrSource)=>{");

        js.debug("console.log('GetViewFieldFunction',nameOrSource);");

        js.append("const selectedView=%s(serviceResponse);", JsGlobalModule.getId(GetSelectedViewFunction.class));
        js.debug("console.log(selectedView);");

        js.append("for(const f in selectedView){"); // for
        js.append("var view=selectedView[f];");
        js.append("if(view.viewType=='%s'&&(view.name==nameOrSource||view.source==nameOrSource)){", ViewType.FIELD); // if
        js.debug("console.log('found view',view);");
        js.append("return view;");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("return null;");

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("serviceResponse", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("nameOrSource", JsType.STRING));
    }
}
