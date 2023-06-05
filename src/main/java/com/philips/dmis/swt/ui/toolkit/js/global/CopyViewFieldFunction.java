package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class CopyViewFieldFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse,viewTop,nameOrSource,name,source)=>{");

        js.append("const currentView=%s(serviceResponse);",
                JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));

        js.debug("console.log('CopyViewFieldFunction',viewTop,nameOrSource,name,source,currentView);");

        js.append("for(const v in currentView){"); // for
        js.append("var view=currentView[v];");
        js.append("if(view.viewType=='%s'&&(view.name==nameOrSource||view.source==nameOrSource)){", ViewType.FIELD); // if
        js.append("var newName=(name==undefined||name==null||name=='')?view.name:name;");
        js.append("var newSource=(source==undefined||source==null||source=='')?view.source:source;");
        js.append("%s(serviceResponse,viewTop,newName,newSource,view.formatType,view.format,view.dataType,view.appearance,false);",
                JsGlobalModule.getQualifiedId(AddViewFieldFunction.class));
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("serviceResponse", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("viewTop", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("nameOrSource", JsType.STRING));
        parameters.add(JsParameter.getInstance("name", JsType.STRING));
        parameters.add(JsParameter.getInstance("source", JsType.STRING));
    }
}
