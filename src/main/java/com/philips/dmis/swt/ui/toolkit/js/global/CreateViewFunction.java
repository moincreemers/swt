package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.View;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.pages.IsPageModuleMember;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class CreateViewFunction implements JsFunction, IsPageModuleMember {
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
        js.append("(serviceResponse,id,name,inheritSelectedView)=>{");


        // create view
        js.append("const viewTop=%s;",
                DtoUtil.valueOf(new View("", "", ViewType.SEQUENCE, DataType.VIEW)));
        js.append("viewTop.id=id;");
        js.append("viewTop.name=name;");
        js.append("serviceResponse.views[id]=viewTop;");


        // inherit?
        js.append("if(inheritSelectedView===true){"); // if
        js.append("const currentView=%s(serviceResponse);",
                JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("for(const v in currentView){"); // for
        js.append("var view=currentView[v];");
        js.append("if(view.viewType=='%s'){", ViewType.FIELD); // if
        js.append("%s(serviceResponse,viewTop,view.name,view.source,view.formatType,view.format,view.dataType,view.appearance,false);",
                JsGlobalModule.getQualifiedId(AddViewFieldFunction.class));
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("};"); // end if


        js.append("serviceResponse.meta['%s']=id;",
                ServiceResponse.META_SELECTED_VIEW_ID);
        js.append("return viewTop;");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("parent", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("key", JsType.STRING));
        parameters.add(JsParameter.getInstance("value", JsType.STRING));
    }
}
