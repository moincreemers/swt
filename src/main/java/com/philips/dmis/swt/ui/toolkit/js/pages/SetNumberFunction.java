package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasNumberedText;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetNumberFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setNumber";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetNumberFunction(Widget widget) {
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
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("text", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(text)=>{");
        if (widgetType == WidgetType.HEADING) {
            js.append("if(text==null||text==undefined||text.length==0){return;};");
            js.append("var numberElement=document.getElementById('%s');",
                    HasNumberedText.getNumberTextId(widget.getId()));
            js.append("if(numberElement==null){return;};");
            js.append("numberElement.textContent=text;");
        }
        js.append("}"); // end function
    }


}
