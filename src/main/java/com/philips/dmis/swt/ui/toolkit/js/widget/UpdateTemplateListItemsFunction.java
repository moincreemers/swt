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
        js.append("const dataTemplateIds=widget.%s;", DataTemplateIdsVariable.ID);
        js.append("const dataKeyField=widget.%s;", DataKeyFieldVariable.ID);
        js.append("const templateTargetWidgetId=widget.%s;", TemplateTargetWidgetIdVariable.ID);
        js.append("const templateSelectorField=widget.%s;", TemplateSelectorFieldVariable.ID);
        js.append("if(dataTemplateIds==undefined||dataTemplateIds==null||templateTargetWidgetId==null){return;};");
        js.append("const hasTemplateSelectorField=(templateSelectorField!=undefined&&templateSelectorField!=null&&templateSelectorField!='');");

        js.append("const dataItems=serviceResponse.data.items;");
        js.append("for(const itemIndex in dataItems){"); // for R
        js.append("var dataItem=dataItems[itemIndex];");
        js.append("var dataKey=dataItem[dataKeyField];");
        js.append("var dataTemplateId=dataTemplateIds['%s'];", DataTemplateIdsVariable.DEFAULT_TEMPLATE_ID);
        js.append("var templateSelectorKey=null;");
        js.append("if(hasTemplateSelectorField){");
        js.append("templateSelectorKey=dataItem[templateSelectorField];");
        js.append("if(dataTemplateIds.hasOwnProperty(templateSelectorKey)&&dataTemplateIds[templateSelectorKey]!=null&&dataTemplateIds[templateSelectorKey]!=''){");
        js.append("dataTemplateId=dataTemplateIds[templateSelectorKey];");
        js.append("};");
        js.append("};");

        // render list item:
        js.append("%s(dataTemplateId,templateTargetWidgetId,dataKeyField,dataKey);",
                JsWidgetModule.getQualifiedId(CloneFunction.class));

        js.append("};"); // end for R

        js.append("}"); // end function
    }
}
