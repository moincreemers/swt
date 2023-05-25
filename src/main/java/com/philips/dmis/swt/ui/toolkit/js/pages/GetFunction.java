package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class GetFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "get";
    private final Widget widget;
    private final WidgetType widgetType;

    public GetFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        return JsType.STRING;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(GetElementFunction.class);
        dependencies.add(IsObjectFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");

        js.debug("console.log('GetFunction');");
        js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));

        if (widget instanceof HasValue) {
            if (widgetType == WidgetType.CHECK) {
                js.append("return elem.checked.toString();");
            } else if (widgetType.isOneOf(WidgetType.MULTIPLE_CHOICE,
                    WidgetType.SINGLE_CHOICE)) {
                js.append("return elem.getAttribute('value');");
            } else if (widgetType == WidgetType.FILE) {
                js.append("return elem.files;");
            } else if (widgetType == WidgetType.FRAME || widgetType == WidgetType.IMAGE_BUTTON) {
                js.append("return elem.getAttribute('src');");
            } else if (widgetType == WidgetType.DATA) {
                js.append("return %s;", JsPagesModule.getQualifiedId(widget, DataVariable.class));
            } else {
                js.append("return elem.value;");
            }
        } else if (widget instanceof DataSourceSupplier) {
            js.append("return %s;",
                    JsPagesModule.getQualifiedId(widget, DataVariable.class));
        } else if (widget instanceof ContainerWidget<?> containerWidget) {
            js.append("let data={};");
            for (Widget child : containerWidget) {
                // note: this condition prevents addition of labels, buttons etc.
                if (child instanceof HasName) {
                    js.append("var element=%s();", JsPagesModule.getQualifiedId(child, GetElementFunction.class));
                    js.append("var name=element.getAttribute('name');");
                    js.append("if(name!=null&&name.length!=0){");
                    js.append("data[name]=%s();", JsPagesModule.getQualifiedId(child, GetFunction.class));
                    js.append("};");
                }
            }
            js.append("return data;");

        } else if (widget instanceof HasText) {
            // this is for labels, buttons, links, etc.
            js.append("return %s().textContent;", JsPagesModule.getId(widget, GetElementFunction.class));

        } else {
            js.append("return null;");
        }

        js.append("}"); // end function
    }
}
