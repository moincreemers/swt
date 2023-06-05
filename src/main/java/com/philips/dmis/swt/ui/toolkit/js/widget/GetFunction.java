package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class GetFunction implements JsFunction {
    public static final String ID = "getValue";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasValue
                || widget instanceof HasText
                || widget instanceof DataSourceSupplier
                || widget instanceof ContainerWidget<?>;
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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(GetElementFunction.class);
        dependencies.add(IsObjectFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const elem=document.getElementById(id);");

        js.append("if(implements.includes('%s')){", HasValue.class.getSimpleName());
        js.append("if(widgetType=='%s'){", WidgetType.CHECK.name());
        js.append("return elem.checked.toString();");

        js.append("}else if(widgetType=='%s'){", WidgetType.MULTIPLE_CHOICE.name());
        js.append("var selectedValues=elem.getAttribute('value');");
        js.append("return selectedValues==null||selectedValues==''?[]:selectedValues.split(',');");

        js.append("}else if(widgetType=='%s'){", WidgetType.SINGLE_CHOICE.name());
        js.append("return elem.getAttribute('value');");

        js.append("}else if(widgetType=='%s'){", WidgetType.FILE.name());
        js.append("return elem.files;");

        js.append("}else if(widgetType=='%s'){", WidgetType.FRAME.name());
        js.append("return elem.getAttribute('src');");

        js.append("}else if(widgetType=='%s'){", WidgetType.IMAGE_BUTTON.name());
        js.append("return elem.getAttribute('src');");

        js.append("}else if(widgetType=='%s'){", WidgetType.DATA.name());
        js.append("return widget.%s;", DataVariable.ID);

        js.append("}else{");
        js.append("return elem.value;");

        js.append("};");

        js.append("} else if(implements.includes('%s')){", DataSourceSupplier.class.getSimpleName());
//        } else if (widget instanceof DataSourceSupplier) {
        js.append("return widget.%s;", DataVariable.ID);

        js.append("} else if(implements.includes('%s')){", ContainerWidget.class.getSimpleName());
        js.append("let data={};");

        js.append("for(const c in widget.%s){", ChildWidgetsVariable.ID);//for
        js.append("var childWidgetId=widget.%s[c];", ChildWidgetsVariable.ID);
        js.append("var childWidget=window[childWidgetId];");
        js.append("var childImplements=childWidget.%s;", ImplementsVariable.ID);
        // note: this condition prevents addition of labels, buttons etc.
        js.append("if(childImplements.includes('%s')){", HasName.class.getSimpleName());//if
        js.append("var childElement=document.getElementById(childWidgetId);");
        js.append("var name=childElement.getAttribute('name');");
        js.append("if(name!=null&&name.length!=0){");
        js.append("data[name]=%s(childWidgetId);", JsWidgetModule.getId(GetFunction.class));
        js.append("};");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("return data;");

        js.append("} else if(implements.includes('%s')){", HasText.class.getSimpleName());
        // this is for labels, buttons, links, etc.
        js.append("return elem.textContent;");

        js.append("}else{");
        js.append("return null;");
        js.append("};");

        js.append("}"); // end function
    }
}
