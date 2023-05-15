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

public class AddViewActionFunction implements JsFunction, IsPageModuleMember {
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
        js.append("(viewTop,viewField,name)=>{");

        // create view id
        js.info("console.log('AddViewActionFunction',viewTop,viewField,name);");
        js.append("var id=null;");
        js.append("var i=viewTop.items.length;");
        js.append("id='v'+i+'.'+viewTop.id;");
        js.append("while(viewTop.items.includes(id)){"); // while
        js.append("i++;");
        js.append("id='v'+i+'.'+viewTop.id;");
        js.append("};"); // end while

        // add view action
        js.append("const view=%s;", DtoUtil.getDefault(View.class, false));
        js.append("view.id=id;");
        js.append("view.name=name;");
        js.append("view.viewType='%s';", ViewType.FIELD_ACTION.name());
        js.append("viewField.items.push(id);");
        js.append("serviceResponse.views[id]=view;");

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("viewTop", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("viewField", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("name", JsType.STRING));
    }
}
