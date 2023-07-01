package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.PageVariablesVariable;
import com.philips.dmis.swt.ui.toolkit.statement.method.Operator;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SetPageVariableFunction implements JsFunction {
    public static final String ID = "setPageVariable";

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
        parameters.add(JsParameter.getInstance("name", JsType.STRING));
        parameters.add(JsParameter.getInstance("value", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("operator", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,name,value,operator)=>{");
        js.trace(this);

        js.append("if(operator==undefined){operator='%s';};", Operator.ADD.name());

        js.append("const widget=window[id];");
        js.append("const pageVars=widget.%s;", PageVariablesVariable.ID);
        js.append("if(!pageVars.hasOwnProperty(name)){");
        js.throwError("undeclared page variable", "name");
        js.append("};");
        js.append("var changed=false;");
        js.append("if(!Array.isArray(pageVars[name])){pageVars[name]=[];};");

        js.append("if(operator=='%s'){", Operator.CLEAR.name());
        js.append("pageVars[name]=[];");
        js.append("changed=true;");
        js.append("};");

        js.append("if(operator=='%s'){", Operator.SET.name());
        js.append("pageVars[name]=value;");
        js.append("changed=true;");
        js.append("};");

        js.append("if(operator=='%s'){", Operator.ADD.name());
        js.append("if(!pageVars[name].includes(value)){");
        js.append("pageVars[name].push(value);");
        js.append("changed=true;");
        js.append("};");
        js.append("};");

        js.append("if(operator=='%s'){", Operator.XOR.name());
        js.append("const i=pageVars[name].indexOf(value);");
        js.append("if(i>-1){");
        js.append("pageVars[name].splice(i,1);");
        js.append("}else{");
        js.append("pageVars[name].push(value);");
        js.append("};");
        js.append("changed=true;");
        js.append("};");

        js.append("if(operator=='%s'){", Operator.REMOVE.name());
        js.append("const i=pageVars[name].indexOf(value);");
        js.append("if(i>-1){");
        js.append("pageVars[name].splice(i,1);");
        js.append("changed=true;");
        js.append("};");
        js.append("};");

        js.append("if(changed){");
        js.append("%s(id,%s,null,null,null,{name:name,value:pageVars[name]});",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnPageVariableChangeEventHandlerFunction.class));
        js.append("};");

        js.append("}"); // end function
    }
}
