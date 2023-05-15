package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetOsName implements JsFunction {
    public static final String MACOS = "macos";
    public static final String WINDOWS = "windows";
    public static final String LINUX = "linux";
    public static final String ANDROID = "android";
    public static final String IOS = "ios";
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
        js.append("if(ua.match(/\\(Macintosh/i)){return '%s'};", MACOS);
        js.append("if(ua.match(/\\(Windows NT/i)){return '%s'};", WINDOWS);
        js.append("if(ua.match(/\\(X11/i)){return '%s'};", LINUX);
        js.append("if(ua.match(/\\(Android|Linux; Android/i)){return '%s'};", ANDROID);
        js.append("if(ua.match(/\\(iPhone/i)){return '%s'};", IOS);
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
