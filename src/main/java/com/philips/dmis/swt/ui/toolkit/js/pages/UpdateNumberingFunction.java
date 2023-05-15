package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HasNumberedText;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateNumberingFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "updateNumbering";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateNumberingFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.PAGE;
    }

    @Override
    public boolean isPublic() {
        return false;
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
        parameters.add(JsParameter.getInstance("updatedWidgetType", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(updatedWidgetType)=>{");

        //js.debug("console.log('UpdateNumberingFunction',updatedWidgetType);");
        String widgetTypeClassNames = String.join(",",
                WidgetType.values().stream()
                        .map(wt -> wt.getShortName() + ":'" + wt.getClassName() + "'")
                        .toList());
        js.append("var widgetTypeClassNames={%s};", widgetTypeClassNames);
        js.append("var widgetTypeClassName=widgetTypeClassNames[updatedWidgetType];");
        // we want to get a nodelist of everything with the class HasLevel.CSS_CLASS_NUMBERED
        js.append("var nodes=document.getElementsByClassName('%s');", HasNumberedText.CSS_CLASS_NUMBERED);
        //js.debug("console.log('UpdateNumberingFunction: nodes',nodes);");
        js.append("if(nodes.length==0){return;}");
        // then we filter for same-type elements
        js.append("var pageClassName='%s';", Widget.getPageClassName(widget.getPageId()));
        js.append("nodes=Array.from(nodes).filter(n=>n.classList.contains(pageClassName)&&n.classList.contains(widgetTypeClassName));");
        js.append("const state=[0,0,0,0,0,0];");
        js.append("for(const j in nodes){");
        js.append("var widget=window[nodes[j].getAttribute('id')];");
        js.append("for(var l=widget.%s;l<state.length;l++){state[l]=0;};", JsPagesModule.getId(widget, NumberingLevelVariable.class));
        js.append("state[widget.%s-1]+=1;", JsPagesModule.getId(widget, NumberingLevelVariable.class));
        js.append("var number=state.filter(num=>num>0).join('.');");
        js.append("widget.%s(number);",
                JsPagesModule.getId(widget, SetNumberFunction.class));
        js.append("};");

        js.append("}"); // end function
    }
}
