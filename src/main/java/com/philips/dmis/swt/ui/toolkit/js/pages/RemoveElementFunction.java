package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.EventHandlerRenderer;
import com.philips.dmis.swt.ui.toolkit.events.RemoveEvent;
import com.philips.dmis.swt.ui.toolkit.events.RemoveEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class RemoveElementFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "removeElement";
    private final Widget widget;
    private final WidgetType widgetType;

    public RemoveElementFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        js.append("(element)=>{");

        // remove a child element
        if (widgetType == WidgetType.PAGE) {
            js.append("const innerId='%s_inner';", widget.getId());
            js.append("var inner=document.getElementById(innerId);");
            js.append("if(inner==null){");
            js.throwError("element not found", "innerId");
            js.append("};");
            js.append("inner.remove(element);");

            // if page-header/footer then we need to add a class to the inner element
            js.append("var childWidgetId=element.getAttribute('id');");
            js.append("if(childWidgetId==null){");
            js.throwError("missing id attribute", "element");
            js.append("};");
            js.append("var childWidget=window[childWidgetId];");
            js.append("if(childWidget==undefined){");
            js.throwError("child widget not found", "childWidgetId");
            js.append("};");
            js.append("if(childWidget.widgetType=='%s'&&childWidget.panelType=='%s'){",
                    WidgetType.PANEL.getShortName(), PanelType.PAGE_HEADER);
            js.append("inner.classList.remove('tk-page-has-header');");
            js.append("};");
            js.append("if(childWidget.widgetType=='%s'&&childWidget.panelType=='%s'){",
                    WidgetType.PANEL.getShortName(), PanelType.PAGE_FOOTER);
            js.append("inner.classList.remove('tk-page-has-footer');");
            js.append("};");
        } else if (widgetType == WidgetType.GRID) {
            // todo: remove a child from the grid
        } else if (widgetType == WidgetType.SINGLE_ROW) {
            js.append("var singleRowElement=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("singleRowElement.remove(element.parentNode);");
        } else {
            js.append("%s().remove(element);",
                    JsPagesModule.getId(widget, GetElementFunction.class));
        }
        renderEvents(toolkit, js);
        renderUpdates(toolkit, js);

        js.append("}"); // end function
    }

    private void renderEvents(Toolkit toolkit, JsWriter js) {
        if (widget instanceof ContainerWidget<?>) {
            EventHandlerRenderer.renderHandlers(widget.getEventHandlers(), toolkit, widget, js, RemoveEventHandler.NAME, new RemoveEvent());
        }
    }

    private void renderUpdates(Toolkit toolkit, JsWriter js) {
        if (widget instanceof Page) {
            js.append("if(childWidget.level>0){");
            js.append("%s(childWidget.widgetType);", JsPagesModule.getId(widget, UpdateNumberingFunction.class));
            js.append("};");
        }
    }
}
