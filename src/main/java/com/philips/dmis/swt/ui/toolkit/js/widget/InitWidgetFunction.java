package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.html.HasConstantStorage;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.controller.JsPageControllerModule;
import com.philips.dmis.swt.ui.toolkit.js.controller.RegisterPage;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class InitWidgetFunction implements JsFunction {
    public static final String ID = "initWidget";

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
        dependencies.add(RenderElementFunction.class);
        dependencies.add(GetElementFunction.class);
        dependencies.add(RegisterPage.class);
        dependencies.add(InitWidgetFunction.class);
        dependencies.add(RefreshPageFunction.class);
        dependencies.add(EventHandlerFunction.class);
        dependencies.add(BeforeEventFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id)=>{");
        js.trace(this);

        // note: This is where the HTML for this element (not child elements) is rendered
        //js.append("%s();", JsPagesModule.getId(widget, RenderOuterHTMLFunction.class));
        js.append("%s(id);", JsWidgetModule.getId(RenderElementFunction.class));

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        // register page with the controller
        js.append("if(widgetType=='%s'){", WidgetType.PAGE.name()); // if
        js.append("%s(id);", JsPageControllerModule.getQualifiedId(RegisterPage.class));
        js.append("};"); // end if

        // replace constants
        js.append("%s.%s(id);", Constants.MAIN_MODULE_NAME, HasConstantStorage.JS_INIT_FUNCTION);

        // ensure widget is registered with parent
        js.append("if(widgetType!='%s'){", WidgetType.PAGE.name()); // if
        js.append("const parentId=widget.%s;", ParentWidgetIdVariable.ID);
        js.append("const containerWidget=window[parentId];");
        js.append("if(!containerWidget.%s.includes(id)){", ChildWidgetsVariable.ID); // if
        js.append("containerWidget.%s.push(id);", ChildWidgetsVariable.ID);
        js.append("};"); // end if
        js.append("};"); // end if

        // init child widgets (recursive)
        js.append("if(implements.includes('%s')){", ContainerWidget.class.getSimpleName()); // if
        js.append("for(const i in widget.%s){", ChildWidgetsVariable.ID); // for
        js.append("var childWidgetId=widget.%s[i];", ChildWidgetsVariable.ID);
        js.append("%s(childWidgetId);", JsWidgetModule.getId(InitWidgetFunction.class));
        js.append("};"); // end for
        js.append("};"); // end if

        js.append("if(widgetType=='%s'){", WidgetType.PAGE.name()); // if
        js.append("const viewType=widget.%s;", ViewTypeVariable.ID);
        js.ifInArray("viewType", ViewType.DIALOG.name(), ViewType.SIDEBAR_DIALOG.name()); // if
        js.append("element.onmousedown=(event)=>{"); // function
        js.append("if(event.srcElement!=null&&event.srcElement.id==id){"); // if
        js.append("%s(id,event);",
                JsWidgetModule.getQualifiedId(BeforeEventFunction.class));
        js.append("%s(id,%s,event);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getQualifiedId(EventHandlerFunction.OnClickOutsideDialogEventHandlerFunction.class));
        js.append("};"); // end if
        js.append("};"); // end function
        js.append("};"); // end if
        js.append("};"); // end if

        js.append("if(implements.includes('%s')){", HasKeyInput.class.getSimpleName()); // if
        js.append("element.oninput=(event)=>{"); // function
        js.append("%s(id,event);",
                JsWidgetModule.getQualifiedId(BeforeEventFunction.class));
        js.append("%s(id,%s,event);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getQualifiedId(EventHandlerFunction.OnInputEventHandlerFunction.class));
        js.append("};"); // end function
        js.append("};"); // end if

        js.append("if(implements.includes('%s')){", HasValue.class.getSimpleName()); // if
        js.append("element.onchange=(event)=>{"); // function
        js.append("%s(id,event);",
                JsWidgetModule.getQualifiedId(BeforeEventFunction.class));
        js.append("%s(id,%s,event);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getQualifiedId(EventHandlerFunction.OnChangeEventHandlerFunction.class));
        js.append("};"); // end function
        js.append("};"); // end if

        js.append("if(implements.includes('%s')){", IsClickable.class.getSimpleName()); // if
        js.append("element.onclick=(event)=>{"); // function
        js.append("if(element.hasAttribute('disabled')){return;};");
        js.append("%s(id,event);",
                JsWidgetModule.getQualifiedId(BeforeEventFunction.class));
        js.append("%s(id,%s,event);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getQualifiedId(EventHandlerFunction.OnClickEventHandlerFunction.class));
        js.append("};"); // end function
        js.append("};"); // end if

//        js.append("var tf=implements.includes('%s')?widget.%s:''",
//                HtmlPreformatted.class.getSimpleName(), TextFormatVariable.ID); // if

        // todo: this may not work as intended, timing-wise
        js.append("if(widgetType=='%s'){", WidgetType.CALCULATED.name()); // if
        js.append("%s(id,widget.%s)", JsWidgetModule.getId(SetValueFunction.class), CalculatedValueVariable.ID);
        js.append("};"); // end if

        js.append("if(widgetType=='%s'){", WidgetType.STATICDATA.name()); // if
        // note: see the StaticData.getUrlEncodedPlusBase64EncodedJson method for info
        js.append("%s(id,decodeURIComponent(atob(widget.%s)));", JsWidgetModule.getId(SetValueFunction.class), StaticDataVariable.ID);
        js.append("};"); // end if

//        js.append("if(widgetType=='%s'&&widget.%s=='%s'){",
//                WidgetType.IMAGE.name(),
//                ImageTypeVariable.ID,
//                ImageType.DRAG_TO_SCROLL.name()); // if
//        js.append("element.addEventListener('mousedown',(e)=>{});");
//        js.append("};"); // end if

        js.append("%s(id,%s);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnInitEventHandlerFunction.class));

        // if this is a page call refresh (recursive)
        // note: keep as last line in method
        js.append("if(widgetType=='%s'){", WidgetType.PAGE.name()); // if
        js.append("%s(id,'%s');", JsWidgetModule.getId(RefreshPageFunction.class), JsStateModule.REASON_INIT);
        js.append("};"); // end if
        js.append("}"); // end function
    }
}
