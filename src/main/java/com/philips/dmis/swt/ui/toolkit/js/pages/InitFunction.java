package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.GlobalEvents;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.CustomEvent;
import com.philips.dmis.swt.ui.toolkit.events.EventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InitEvent;
import com.philips.dmis.swt.ui.toolkit.html.HasConstantStorage;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.controller.RegisterPage;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class InitFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "init";
    private final Widget widget;
    private final WidgetType widgetType;

    public InitFunction(Widget widget) {
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
        return JsType.VOID;
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(RenderOuterHTMLFunction.class);
        dependencies.add(GetElementFunction.class);
        dependencies.add(RegisterPage.class);
        dependencies.add(CreateDataAdaptersFunction.class);
        dependencies.add(InitFunction.class);
        dependencies.add(RefreshPageFunction.class);
        dependencies.add(EventHandlerFunction.class);
        dependencies.add(BeforeEventFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("()=>{");
        js.debug("console.log('%s','%s','init');", widget.getId(), widget.getWidgetType().name());

        // note: This is where the HTML for this element (not child elements) is rendered
        js.append("%s();", JsPagesModule.getId(widget, RenderOuterHTMLFunction.class));

        if (!(widget instanceof IconsWidget)) {
            js.append("if(%s()==null){", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("const widgetId='%s';", widget.getId());
            js.append("const widgetType='%s';", widget.getWidgetType());
            js.append("const widgetPageId='%s';", widget.getPageId());
            js.throwError("element not found", "widgetId", "widgetType", "widgetPageId");
            js.append("};");
        }

        // register page with the controller
        if (widget instanceof Page) {
            js.append("%s('%s');", JsPageControllerModule.getQualifiedId(RegisterPage.class), widget.getId());
        }

        // create data adapters
        if (widget instanceof DataSourceSupplier || widget instanceof DataBoundWidget<?>) {
            js.append("%s();", JsPagesModule.getId(widget, CreateDataAdaptersFunction.class));
        }

        // replace constants
        js.append("%s.%s('%s');",
                Constants.MAIN_MODULE_NAME, HasConstantStorage.JS_INIT_FUNCTION, widget.getId());

        // init child widgets
        if (widget instanceof ContainerWidget<?> containerWidget) {
            for (Widget childWidget : containerWidget) {
                js.append("%s();",
                        JsPagesModule.getQualifiedId(childWidget, InitFunction.class));
            }
        }

        if (widget instanceof HasURL hasURL) {
            for (Parameter parameter : hasURL.getParameters()) {
                String a = (parameter.getDefaultValue() == null || parameter.getDefaultValue().isEmpty())
                        ? "null" : "'" + parameter.getDefaultValue() + "'";
                js.append("%s['%s']='%s';", ParametersVariable.ID, parameter.getName(), a);
            }
        }

        // create event handlers
        for (EventHandler eventHandler : widget.getEventHandlers()) {
            if (eventHandler.getStatements().isEmpty()) {
                continue;
            }
            js.append("var c=%s['%s'];",
                    EventHandlersVariable.ID, eventHandler.getName().toLowerCase());
            js.append("if(c==undefined){"); // if
            js.append("c=[];");
            js.append("%s['%s']=c;",
                    EventHandlersVariable.ID, eventHandler.getName().toLowerCase());
            js.append("};"); // end if
            js.append("var handler=(%s)=>{", GlobalEvents.EVENT_PARAMETER_NAME); // function
            for (Statement statement : eventHandler.getStatements()) {
                statement.renderJs(toolkit, widget, js);
            }
            js.append("};"); // end function
            js.append("c.push(handler);");
        }

        if (widget instanceof Page page) {
            if (page.getViewType() == ViewType.DIALOG || page.getViewType() == ViewType.SIDEBAR_DIALOG) {
                js.append("%s().onmousedown=(event)=>{", JsPagesModule.getId(widget, GetElementFunction.class));
                js.append("if(event.srcElement!=null&&event.srcElement.id=='%s'){", page.getId());
                js.append("%s(event);", JsPagesModule.getId(widget, BeforeEventFunction.class));
                js.append("%s(event);", JsPagesModule.getId(widget, EventHandlerFunction.OnClickOutsideDialogEventHandlerFunction.class));
                js.append("};");
                js.append("};");
            }
        }

        if (widget instanceof HasKeyInput<?>) {
            js.append("%s().oninput=(event)=>{", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("%s(event);", JsPagesModule.getId(widget, BeforeEventFunction.class));
            js.append("%s(event);", JsPagesModule.getId(widget, EventHandlerFunction.OnInputEventHandlerFunction.class));
            js.append("};");
        }
        if (widget instanceof HasValue<?>) {
            js.append("%s().onchange=(event)=>{", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("%s(event);", JsPagesModule.getId(widget, BeforeEventFunction.class));
            js.append("%s(event);", JsPagesModule.getId(widget, EventHandlerFunction.OnChangeEventHandlerFunction.class));
            js.append("};");
        }
        if (widget instanceof IsClickable<?>) {
            js.append("%s().onclick=(e)=>{", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("if(%s().hasAttribute('disabled')){return;};", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("%s(e);", JsPagesModule.getId(widget, BeforeEventFunction.class));
            js.append("%s(e);", JsPagesModule.getId(widget, EventHandlerFunction.OnClickEventHandlerFunction.class));
            js.append("};");
        }

        if (widget instanceof HtmlPreformatted htmlPreformatted) {
            js.append("tf='%s';", htmlPreformatted.getTextFormat().name());
        }

        if (widget instanceof CalculatedValueWidget) {
            js.append("%s(", JsPagesModule.getId(widget, SetValueFunction.class));
            ((CalculatedValueWidget) widget).renderValue(toolkit, js);
            js.append(");");
        }

        if (widget instanceof StaticData) {
            // note: see the getUrlEncodedPlusBase64EncodedJson method for info
            js.append("%s(decodeURIComponent(atob('%s')));",
                    JsPagesModule.getId(widget, SetValueFunction.class),
                    ((StaticData) widget).getUrlEncodedPlusBase64EncodedJson());
        }

        js.append("%s(%s);",
                JsPagesModule.getId(widget, EventHandlerFunction.OnInitEventHandlerFunction.class),
                CustomEvent.valueOf(new InitEvent()));

        if (widget instanceof Page) {
            // note: keep as last line in method
            js.append("%s('%s');",
                    JsPagesModule.getId(widget, RefreshPageFunction.class),
                    JsPagesModule.REASON_INIT);
        }
        js.append("}"); // end function
    }
}
