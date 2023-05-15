package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetNavigatorName implements JsFunction {
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String SAFARI = "safari";
    public static final String OPERA = "opera";
    public static final String EDGE = "edge";
    public static final String OTHER = "other";

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
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("()=>{");
        js.append("const ua=navigator.userAgent;");
        js.append("if(ua.match(/chrome|chromium|crios/i)){return '%s'};",CHROME);
        js.append("if(ua.match(/firefox|fxios/i)){return '%s'};", FIREFOX);
        js.append("if(ua.match(/safari/i)){return '%s'};", SAFARI);
        js.append("if(ua.match(/opr/i)){return '%s'};", OPERA);
        js.append("if(ua.match(/edg/i)){return '%s'};", EDGE);
        js.append("return '%s';", OTHER);
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("s", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
