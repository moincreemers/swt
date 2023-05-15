package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.GetSelectedViewFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateTableHeaderFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "updateTableHeader";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateTableHeaderFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.TABLE_HEADER;
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
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason,cacheType,serviceResponse,dataSourceId)=>{");

        js.append("%s().textContent='';", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("const selectedView=%s(serviceResponse);", JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("if(selectedView==null){return};");
        js.append("const dataItems=serviceResponse.data.items;");

        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'){", ViewType.SEQUENCE.name());
        js.append("if(view.appearance=='%s'){", ViewAppearance.DISPLAY_WHEN_DATA.name());
        js.append("%s().style.display=(dataItems.length==0)?'none':'';",
                JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("}else{");
        js.append("%s().style.display='';",
                JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("};");
        js.append("break;");
        js.append("};");
        js.append("};");



        // todo: support for hierarchies?

        js.debug("console.log('selectedView',selectedView);");

        js.append("var orderingEnabled=serviceResponse.meta['%s'];", ServiceResponse.META_ORDERING_ENABLED);
        js.append("if(orderingEnabled==undefined||orderingEnabled==null){orderingEnabled=false;};");
        js.append("const g=(source)=>{"); // function
        String orderingVar = JsPagesModule.getId(widget, OrderingVariable.class);
        js.append("for(const i in %s){", orderingVar); // for
        js.append("if(%s[i].source==source){", orderingVar); // if
        js.append("return %s[i].order;", orderingVar);
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("return '%s';", Order.NONE.name());
        js.append("};"); // end function

        js.append("const tr=document.createElement('tr');");
        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'&&view.appearance!='%s'){",
                ViewType.FIELD.name(), ViewAppearance.HIDDEN.name()); // if

        js.append("var th=document.createElement('th');");
        js.append("if(orderingEnabled){"); // if

        js.append("var a=document.createElement('a');");
        js.append("a.textContent=view.name;");
        js.append("a.href='javascript:void(0);';");
        js.append("a.classList.add('tk-ordering-control');");
        js.append("var order=g(view.orderSource);");
        js.append("if(order=='%s'){", Order.ASCENDING.name()); // if
        js.append("a.classList.add('tk-ordering-control-ascending');");
        js.append("};"); // end if
        js.append("if(order=='%s'){", Order.DESCENDING.name()); // if
        js.append("a.classList.add('tk-ordering-control-descending');");
        js.append("};"); // end if
        js.append("const s=view.orderSource;");
        js.append("a.onclick=()=>{");
        js.append("%s(s);", JsPagesModule.getQualifiedId(widget, SetOrderFunction.class));
        js.append("};");
        js.append("th.append(a);");

        js.append("}else{"); // else
        js.append("th.textContent=view.name;");
        js.append("};"); // end if

        js.append("tr.append(th);");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("%s().append(tr);", JsPagesModule.getId(widget, GetElementFunction.class));

        js.append("}"); // end function
    }
}
