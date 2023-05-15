package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Order;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOrderingControls;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SetOrderFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setOrder";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetOrderFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasOrderingControls<?>;
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
        parameters.add(JsParameter.getInstance("source", JsType.STRING));
        parameters.add(JsParameter.getInstance("order", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        // note: order is optional
        js.append("(source,order)=>{"); // function

        js.append("if(source==null||source==undefined||source.length==0){return;};");
        js.debug("console.log('SetOrderFunction',source,order);");
        js.append("const toggle=(order==undefined||order==null);");
        js.append("const options=[%s];", Arrays.stream(Order.values()).map(o -> "'" + o.name() + "'").collect(Collectors.joining(",")));
        js.append("const newOrdering=[];");
        js.append("var fieldFound=false;");
        js.append("var orderingChanged=false;");

        js.append("const currentOrdering=Object.assign([],%s);", JsPagesModule.getId(widget, OrderingVariable.class));
        js.append("if(%s==false){", JsPagesModule.getId(widget, IsMultiLevelVariable.class));
        js.append("const temp=Object.assign([],%s);", JsPagesModule.getId(widget, OrderingVariable.class));
        js.append("for(const i in temp){");
        js.append("if(temp[i].source!=source){");
        js.append("currentOrdering.splice(i,1);");
        js.append("orderingChanged=true;");
        js.append("};");
        js.append("};");
        js.append("};");

        js.append("for(const i in currentOrdering){"); // for

        js.append("if(currentOrdering[i].source==source){"); // if
        js.append("fieldFound=true;");

        js.append("if(toggle){"); // if
        js.append("var o=currentOrdering[i].order;");
        js.append("var n=(options.indexOf(o)+1)%options.length;");
        js.append("currentOrdering[i].order=options[n];");
        // if new order is NONE, then do not copy the field
        js.append("if(options[n]!='%s'){", Order.NONE.name()); // if
        js.append("newOrdering.push(currentOrdering[i]);");
        js.append("};"); // end if
        js.append("orderingChanged=true;");
        js.append("}else{"); // else
        // if new order is NONE, then do not copy the field
        js.append("if(order!='%s'){", Order.NONE.name()); // if
        js.append("currentOrdering[i].order=order;");
        js.append("newOrdering.push(currentOrdering[i]);");
        js.append("};"); // end if
        js.append("orderingChanged=true;");
        js.append("};"); // end if

        js.append("}else{"); // else
        // copy existing ordered field
        js.append("newOrdering.push(currentOrdering[i]);");
        js.append("};"); // end if

        js.append("};"); // end for

        // add new ordered field
        js.append("if(!fieldFound){"); // if
        js.append("if(toggle){"); // if
        js.append("newOrdering.push({source:source,order:'%s'});", Order.ASCENDING.name());
        js.append("orderingChanged=true;");
        js.append("}else{"); // else
        js.append("newOrdering.push({source:source,order:order});");
        js.append("orderingChanged=true;");
        js.append("};"); // end if
        js.append("};"); // end if

        js.append("if(orderingChanged){"); //if
        js.append("%s=newOrdering;", JsPagesModule.getId(widget, OrderingVariable.class));
        js.info("console.log('SetOrderFunction',%s);", JsPagesModule.getId(widget, OrderingVariable.class));
        // todo: add event data
        js.append("%s();",
                EventHandlerFunction.OnOrderChangeEventHandlerFunction.ID);
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
