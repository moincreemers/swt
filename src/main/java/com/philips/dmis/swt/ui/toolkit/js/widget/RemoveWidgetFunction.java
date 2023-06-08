package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ChildWidgetsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.TemplateIdVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.ContainerWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveWidgetFunction implements JsFunction {
    public static final String ID = "removeWidget";

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

        js.append("const widget=window[id];");
        js.append("const implements=widget.%s;", ImplementsVariable.ID);

        // Remove child widgets if container
        js.append("if(implements.includes('%s')){", ContainerWidget.class.getSimpleName()); // if
        js.append("const childWidgetIds=widget.%s.splice(0);", ChildWidgetsVariable.ID);
        js.append("for(const c in childWidgetIds){"); // for
        js.append("%s(childWidgetIds[c]);", JsWidgetModule.getId(RemoveWidgetFunction.class));
        js.append("};"); // end for
        js.append("};"); // end if

        // Remove the element
        js.append("%s(id);", JsWidgetModule.getId(RemoveElementFunction.class));

        js.append("if(widget.%s!=null){", TemplateIdVariable.ID);
        js.append("%s(widget.%s,id);", JsWidgetModule.getId(RemoveSlaveFunction.class),
                TemplateIdVariable.ID);
        js.append("};");
        js.append("delete window[id];");

        js.append("}");
    }
}
