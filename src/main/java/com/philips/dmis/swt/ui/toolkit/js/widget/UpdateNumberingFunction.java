package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.NumberingLevelVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.PageClassNameVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasNumberedText;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateNumberingFunction implements JsFunction {
    public static final String ID = "updateNumbering";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.PAGE;
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
        dependencies.add(SetNumberFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("updatedWidgetType", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,updatedWidgetType)=>{");
        js.trace(this);

        js.append("const pageWidget=window[id];");
        js.append("const widgetType=pageWidget.%s;", WidgetTypeVariable.ID);
        js.append("if(widgetType!='%s'){", WidgetType.PAGE.name());
        js.append("return;");
        js.append("};");
        js.append("const widgetTypeClassName=%s[updatedWidgetType].className;", JsWidgetModule.getId(WidgetTypesVariable.class));
        // we want to get a nodelist of everything with the class HasLevel.CSS_CLASS_NUMBERED
        js.append("var nodes=document.getElementsByClassName('%s');", HasNumberedText.CSS_CLASS_NUMBERED);
        //js.debug("console.log('UpdateNumberingFunction: nodes',nodes);");
        js.append("if(nodes.length==0){return;}");
        // then we filter for same-type elements
        js.append("const pageClassName=pageWidget.%s;", PageClassNameVariable.ID);
        js.append("nodes=Array.from(nodes).filter(n=>n.classList.contains(pageClassName)&&n.classList.contains(widgetTypeClassName));");
        js.append("const state=[0,0,0,0,0,0];");
        js.append("for(const j in nodes){");
        js.append("var numberedWidgetId=nodes[j].id;");
        js.append("var numberedWidget=window[numberedWidgetId];");
        js.append("for(var l=numberedWidget.%s;l<state.length;l++){state[l]=0;};", NumberingLevelVariable.ID);
        js.append("state[numberedWidget.%s-1]+=1;", NumberingLevelVariable.ID);
        js.append("var number=state.filter(num=>num>0).join('.');");
        js.append("%s(numberedWidgetId,number);", JsWidgetModule.getId(SetNumberFunction.class));
        js.append("};");

        js.append("}"); // end function
    }
}
