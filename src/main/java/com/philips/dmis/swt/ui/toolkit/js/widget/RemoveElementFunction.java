package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.RemoveEvent;
import com.philips.dmis.swt.ui.toolkit.events.RemoveEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.InnerElementIdVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.IsNumberedVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ParentWidgetIdVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.ContainerWidget;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.PanelType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class RemoveElementFunction implements JsFunction {
    public static final String ID = "removeElement";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return !widgetType.isHidden() && widget instanceof ContainerWidget<?>;
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
        dependencies.add(GetElementFunction.class);
        dependencies.add(UpdateNumberingFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("element", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,element)=>{");
        js.trace(this);

        js.append("const childWidget=window[id];");
        js.append("const childWidgetType=childWidget.%s;", WidgetTypeVariable.ID);
        js.append("const isNumbered=childWidget.%s;", IsNumberedVariable.ID);

        js.append("if(childWidgetType=='%s'){", WidgetType.PAGE.name());
        js.append("document.body.remove(element);");
        js.append("return;");
        js.append("};");

        js.append("const containerWidgetId=childWidget.%s;", ParentWidgetIdVariable.ID);
        js.append("const containerWidget=window[containerWidgetId];");
        js.append("const containerWidgetType=containerWidget.%s;", WidgetTypeVariable.ID);
        js.append("const containerElement=document.getElementById(containerWidgetId);");

        js.append("if(containerElement==null){");
        js.throwError("container element not found", "containerWidgetId");
        js.append("};");

        // remove a child element
        js.append("if(containerWidgetType=='%s'){", WidgetType.PAGE.name()); // if

        js.append("const innerDivId=containerWidget.%s;", InnerElementIdVariable.ID);
        js.append("var inner=document.getElementById(innerDivId);");
        js.append("if(inner==null){");
        js.throwError("inner element not found", "innerDivId");
        js.append("};");
        js.append("inner.remove(element);");

        // if page-header/footer or nav-left/right then we need to remove a class from the inner element
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(), PanelType.PAGE_HEADER.name());
        js.append("inner.classList.remove('tk-page-has-header');");
        js.append("};");
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(),
                PanelType.PAGE_FOOTER.getShortName());
        js.append("inner.classList.remove('tk-page-has-footer');");
        js.append("};");
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(),
                PanelType.NAV_LEFT.getShortName());
        js.append("inner.classList.remove('tk-page-has-nav-left');");
        js.append("};");
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(),
                PanelType.NAV_RIGHT.getShortName());
        js.append("inner.classList.remove('tk-page-has-nav-right');");
        js.append("};");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.GRID.name());  // else
        // todo:
        //js.append("containerElement.remove(element);");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.SINGLE_ROW.name());  // else
        // todo:
        js.append("containerElement.remove(element);");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.LIST_CONTAINER.name()); // if
        // todo:
        js.append("containerElement.remove(element);");

        js.append("}else{"); // else
        js.append("containerElement.remove(element);");

        js.append("};"); // end if

        js.append("%s(id,'%s',%s);",
                JsWidgetModule.getId(RaiseEventFunction.class),
                RemoveEventHandler.NAME,
                CustomEvent.valueOf(new RemoveEvent()));

        js.append("if(containerWidgetType=='%s'&&isNumbered){",
                WidgetType.PAGE.name()); // if
        js.append("%s(containerWidgetId,childWidgetType);",
                JsWidgetModule.getId(UpdateNumberingFunction.class));
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
