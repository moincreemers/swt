package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetSelectedViewFunction implements JsFunction {
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
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse)=>{");
        js.trace(this);

        js.append("const views=[];");
        js.debug("console.log('GetSelectedViewFunction',serviceResponse);");
        js.append("const selectedViewId=serviceResponse.meta['%s'];", ServiceResponse.META_SELECTED_VIEW_ID);
        js.append("const viewTop=serviceResponse.views[selectedViewId];");

        js.append("const fn=(serviceResponse,views,currentView)=>{"); // function
        js.append("if(currentView==undefined||currentView==null){return;};");
        js.append("views.push(currentView);");
        js.append("for(const i in currentView.items){"); // for
        js.append("var nextView=serviceResponse.views[currentView.items[i]];");
        js.append("fn(serviceResponse,views,nextView);");
        js.append("}"); // end for
        js.append("};");  // end function

        js.append("fn(serviceResponse,views,viewTop);");

        js.append("return views;");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("serviceResponse", JsType.OBJECT));
    }
}
