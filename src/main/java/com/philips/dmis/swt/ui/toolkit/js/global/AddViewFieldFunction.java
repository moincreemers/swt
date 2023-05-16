package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.View;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.pages.IsPageModuleMember;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class AddViewFieldFunction implements JsFunction, IsPageModuleMember {
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
        js.append("(serviceResponse,viewTop,name,source,formatType,format,dataType,appearance,overwrite)=>{");

        // create view id
        js.append("var id=null;");
        js.append("if(overwrite===true){");
        js.debug("console.log('AddViewFieldFunction,overwrite',viewTop.items,serviceResponse.views);");
        js.append("for(const f in viewTop.items){"); // for
        js.append("if(serviceResponse.views[viewTop.items[f]].source==source){"); // if
        js.append("id=serviceResponse.views[viewTop.items[f]].id;");
        js.append("break;");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("};"); // end if
        js.append("if(!overwrite || id==null){");
        js.append("var i=viewTop.items.length;");
        js.append("id='v'+i+'.'+viewTop.id;");
        js.append("while(viewTop.items.includes(id)){");
        js.append("i++;");
        js.append("id='v'+i+'.'+viewTop.id;");
        js.append("};");
        js.append("};");

        // add view field
        js.append("const view=%s;", DtoUtil.getDefault(View.class, false));
        js.append("view.id=id;");
        js.append("view.name=name;");
        js.append("view.viewType='%s';", ViewType.FIELD.name());
        js.append("view.source=source;");
        js.append("view.orderSource=source;");
        js.append("view.dataType=dataType;");
        js.append("view.formatType=dataType;");
        js.append("if(format!=null){");
        js.append("view.formatType=formatType;");
        js.append("view.format=format;");
        js.append("view.appearance=appearance;");
        js.append("};");
        js.append("if(!viewTop.items.includes(id)){");
        js.append("viewTop.items.push(id);");
        js.append("};");
        js.append("serviceResponse.views[id]=view;");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("serviceResponse", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("viewTop", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("name", JsType.STRING));
        parameters.add(JsParameter.getInstance("source", JsType.STRING));
        parameters.add(JsParameter.getInstance("formatType", JsType.STRING));
        parameters.add(JsParameter.getInstance("format", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataType", JsType.STRING));
        parameters.add(JsParameter.getInstance("appearance", JsType.STRING));
        parameters.add(JsParameter.getInstance("overwrite", JsType.BOOLEAN));
    }
}
