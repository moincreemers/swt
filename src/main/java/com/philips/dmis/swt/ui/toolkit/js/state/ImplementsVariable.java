package com.philips.dmis.swt.ui.toolkit.js.state;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsVariable;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.HashSet;
import java.util.Set;

public class ImplementsVariable implements JsVariable {
    public static final String ID = "implements";
    private final Widget widget;
    private final WidgetType widgetType;

    public ImplementsVariable(Widget widget) {
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
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        Set<Class<?>> classes = new HashSet<>();
        Class<?> c = widget.getClass();
        while (c != null && c != Object.class) {
            if (!classes.contains(c)) {
                classes.add(c);
                resolveInterfaces(c, classes);
            }
            c = c.getSuperclass();
        }
        js.append("[");
        int i = 0;
        for (Class<?> cls : classes) {
            if (i > 0) {
                js.append(",");
            }
            js.append("'");
            js.append(cls.getSimpleName());
            js.append("'");
            i++;
        }
        js.append("]");
    }

    // note: recursive!
    private void resolveInterfaces(Class<?> c, Set<Class<?>> classes) {
        for (Class<?> f : c.getInterfaces()) {
            if (!classes.contains(f)) {
                classes.add(f);
                resolveInterfaces(f, classes);
            }
        }
    }
}
