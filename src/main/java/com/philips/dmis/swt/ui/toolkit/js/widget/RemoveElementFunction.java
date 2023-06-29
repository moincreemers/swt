package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ChildWidgetsVariable;
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
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id)=>{");
        js.trace(this);

        js.append("const childWidget=window[id];");
        js.append("const childWidgetType=childWidget.%s;", WidgetTypeVariable.ID);
        js.append("const isNumbered=childWidget.%s;", IsNumberedVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("if(childWidgetType=='%s'){", WidgetType.PAGE.name());
        js.append("document.body.removeChild(element);");
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

        js.append("const innerDivId=%s(containerWidgetId);",
                JsWidgetModule.getId(InnerElementIdFunction.class));
        js.append("var inner=document.getElementById(innerDivId);");
        js.append("if(inner==null){");
        js.throwError("inner element not found", "innerDivId");
        js.append("};");
        js.append("inner.removeChild(element);");

        // if page-header/footer or nav-left/right then we need to remove a class from the inner element
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(), PanelType.PAGE_HEADER.name());
        js.append("inner.classList.remove('tk-page-has-header');");
        js.append("};");
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(),
                PanelType.PAGE_FOOTER.name());
        js.append("inner.classList.remove('tk-page-has-footer');");
        js.append("};");
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(),
                PanelType.NAV_LEFT.name());
        js.append("inner.classList.remove('tk-page-has-nav-left');");
        js.append("};");
        js.append("if(containerWidgetType=='%s'&&containerPanelType=='%s'){",
                WidgetType.PANEL.name(),
                PanelType.NAV_RIGHT.name());
        js.append("inner.classList.remove('tk-page-has-nav-right');");
        js.append("};");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.GRID.name());  // else
        // todo:
        //js.append("containerElement.removeChild(element);");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.SINGLE_ROW.name());  // else
        // todo:
        js.append("containerElement.removeChild(element);");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.LIST_CONTAINER.name()); // if
        js.append("var li=element.parentElement;");
        js.append("li.removeChild(element);");
        js.append("containerElement.removeChild(li);");

        js.append("}else{"); // else
        js.append("containerElement.removeChild(element);");

        js.append("};"); // end if

        js.append("var i=containerWidget.%s.indexOf(id);", ChildWidgetsVariable.ID);
        js.append("if(i!=-1){containerWidget.%s.splice(i,1);}", ChildWidgetsVariable.ID);

        js.append("%s(id,%s);",
                JsWidgetModule.getId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnRemoveEventHandlerFunction.class));

        js.append("if(containerWidgetType=='%s'&&isNumbered){",
                WidgetType.PAGE.name()); // if
        js.append("%s(containerWidgetId,childWidgetType);",
                JsWidgetModule.getId(UpdateNumberingFunction.class));
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
