package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.IsObjectFunction;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlList;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateTemplateListItemsFunction implements JsFunction {
    public static final String ID = "updateTemplateListItems";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HtmlList;
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
        dependencies.add(IsObjectFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("serviceResponse", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason,cacheType,serviceResponse,dataSourceId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");
        js.append("const dataTemplateId=widget.%s;", DataTemplateIdVariable.ID);
        js.append("const dataKeyField=widget.%s;", DataKeyFieldVariable.ID);
        js.append("const templateTargetWidgetId=widget.%s;", TemplateTargetWidgetIdVariable.ID);
        js.append("if(dataTemplateId==undefined||dataTemplateId==null||dataTemplateId==''||dataKeyField==null||templateTargetWidgetId==null){return;};");

        js.append("const dataItems=serviceResponse.data.items;");
        js.append("for(const itemIndex in dataItems){"); // for R
        js.append("var dataItem=dataItems[itemIndex];");
        js.append("var dataKey=dataItem[dataKeyField];");

        // render list item:
        js.append("%s(dataTemplateId,templateTargetWidgetId,dataKeyField,dataKey);",
                JsWidgetModule.getQualifiedId(CloneFunction.class));

        js.append("};"); // end for R

        js.append("}"); // end function
    }
}
