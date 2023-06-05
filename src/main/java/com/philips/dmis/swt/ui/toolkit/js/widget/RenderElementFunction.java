package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.HtmlAttributesVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.HtmlTagVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ParentWidgetIdVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RenderElementFunction implements JsFunction {
    public static final String ID = "renderElement";

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
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id)=>{");
        js.trace(this);

        js.append("const childWidget=window[id];");
        js.append("const childWidgetType=childWidget.%s;", WidgetTypeVariable.ID);

        js.append("if(childWidgetType!='%s'){", WidgetType.PAGE.name()); // if
        js.append("const containerWidget=window[childWidget.%s];", ParentWidgetIdVariable.ID);
        js.append("const containerWidgetType=containerWidget.%s;", WidgetTypeVariable.ID);
        js.append("if(!%s(containerWidgetType)){", JsWidgetModule.getId(IsContainerFunction.class)); // if
        js.throwError("parent widget type not allowed", "containerWidgetType");
        js.append("};"); // end if
        js.append("};"); // end if

        // create new child element
        js.append("var element=document.createElement(childWidget.%s);", HtmlTagVariable.ID);

        // set id
        js.append("element.id=childWidget.id;");

        // set html attributes
        js.append("for(const key in childWidget.%s){", HtmlAttributesVariable.ID);
        js.append("if(key=='id'){continue;};");
        js.append("var value=childWidget.%s[key];", HtmlAttributesVariable.ID);
        js.append("if(value==null||value==undefined||(typeof value!='string')){continue;};");
        js.append("element.setAttribute(key,value);");
        js.append("};");

        // set css classes
        // todo: shorten this
        String widgetTypeClassNames = String.join(",",
                WidgetType.values().stream().map(wt -> wt.name() + ":'" + wt.getClassName() + "'").toList());
        js.append("var widgetTypeClassNames={%s};", widgetTypeClassNames);
        js.append("if(widgetTypeClassNames[childWidgetType]!=''){");
        js.append("element.classList.add(widgetTypeClassNames[childWidgetType]);");
        js.append("};");

        //js.append("childWidget.renderInner(id,element);");
        //js.append("widgetSpec.appendElement('%s',element);", widget.getParentId());
        js.append("%s(id,element);", JsWidgetModule.getId(RenderInnerHTMLFunction.class));
        js.append("%s(id,element);", JsWidgetModule.getId(AppendElementFunction.class));

        js.append("}"); // end function
    }


}
