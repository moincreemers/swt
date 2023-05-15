package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RefreshPageFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "refreshPage";
    private final Widget widget;
    private final WidgetType widgetType;

    public RefreshPageFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.PAGE;
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
        dependencies.add(PageRefreshVariable.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason)=>{");

        if (widgetType == WidgetType.PAGE) {
            js.debug("console.log('%s','RefreshPage',reason);", widget.getId());

            js.append("const pr=%s;", JsPagesModule.getId(widget, PageRefreshVariable.class));
            js.append("if(pr=='%s'){return;}", PageRefreshType.MANUAL.getReason());

            js.append("if(reason==pr){");
            js.debug("console.log('page refresh because of: '+reason);");
            js.append("%s(reason);", JsPagesModule.getId(widget, RefreshFunction.class));
            js.append("};");
        }

        js.append("}"); // end function
    }
}
