package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RenderElementFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "renderElement";
    private final Widget widget;
    private final WidgetType widgetType;

    public RenderElementFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        dependencies.add(AppendElementFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(widgetSpec)=>{");
        //js.debug("console.log('RenderElementFunction',widgetSpec);");
        /*
         widgetSpec:{
             id:"",
             parentId:"",
             widgetType:"",
             htmlTag:'',
             htmlAttributes:{key:'value'},
             appendElement:(element)=>{...}
             renderInner:(element)=>{...}
         }
         */

        String widgetTypeClassNames = String.join(",",
                WidgetType.values().stream().map(wt -> wt.getShortName() + ":'" + wt.getClassName() + "'").toList());
        js.append("var widgetTypeClassNames={%s};", widgetTypeClassNames);

        js.append("var parentWidget=null;");
        js.append("if(widgetSpec.widgetType!='%s'){", WidgetType.PAGE.getShortName());
        js.append("parentWidget=window[widgetSpec.parentId];");
        js.append("if(parentWidget==null||parentWidget==undefined){");
        js.error("console.log(parentWidget);");
        js.throwError("parent widget not found", "widgetSpec");
        js.append("};");

        String allowedParentWidgetTypes = "'" + String.join("','",
                WidgetType.values().stream().filter(WidgetType::isContainer).map(WidgetType::getShortName).toList()) + "'";
        js.append("var allowedParentWidgetTypes=[%s];", allowedParentWidgetTypes);
        js.append("if(!allowedParentWidgetTypes.includes(parentWidget.%s())){",
                JsPagesModule.getId(widget, GetWidgetTypeFunction.class));
        js.throwError("parent widget type not allowed", "parentWidget");
        js.append("};");
        js.append("};");

        js.append("var htmlTag=widgetSpec.htmlTag;");
        js.append("var element=document.createElement(htmlTag);");

        js.append("element.setAttribute('id',widgetSpec.id);");
        js.append("for(const key in widgetSpec.htmlAttributes){");
        js.append("if(key=='id'){continue;};");
        js.append("var value=widgetSpec.htmlAttributes[key];");
        js.append("if(value==null||value==undefined||(typeof value!='string')){continue;};");
        js.append("element.setAttribute(key,value);");
        js.append("};");
        js.append("if(widgetTypeClassNames[widgetSpec.widgetType]!=''){");
        js.append("element.classList.add(widgetTypeClassNames[widgetSpec.widgetType]);");
        js.append("};");

        // NOTE: renderInner and appendElement are callback functions set in GetWidgetSpecFunction
        js.append("widgetSpec.renderInner(widgetSpec,element);");
        js.append("widgetSpec.appendElement(widgetSpec,element);");

        js.append("}"); // end function
    }


}
