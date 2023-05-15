package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasNumberedText;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetNumberedFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setNumbered";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetNumberedFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasNumberedText;
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
        js.append("(enabled,level)=>{");

        if (widgetType == WidgetType.HEADING) {
            js.append("if(level!=null){");
            js.append("%s=level;", JsPagesModule.getId(widget, NumberingLevelVariable.class));
            js.append("};");
            js.append("if(enabled===true){");
            js.append("%s('%s')", JsPagesModule.getId(widget, AddClassNameFunction.class),
                    HasNumberedText.CSS_CLASS_NUMBERED);
            js.append("};");
            js.append("if(enabled===false){");
            js.append("%s('%s')", JsPagesModule.getId(widget, RemoveClassNameFunction.class),
                    HasNumberedText.CSS_CLASS_NUMBERED);
            js.append("};");
        }

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }
}
