package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.ValueDataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.DataKeyVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.TemplateIdVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasValue;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateValueFunction implements JsFunction {
    public static final String ID = "updateValue";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasValue;
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
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,reason,cacheType,object,dataSourceId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const templateId=widget.%s;", TemplateIdVariable.ID);
        js.append("const isSlave=(templateId!=null&&templateId!='');");

        js.append("const items=object.data.items;");
        js.append("if(items.length==0){"); // if
        js.throwError("no items found", "object");
        js.append("};"); // end if
        js.append("var record=items[0];");

        // note: if this is a slave then filter on dataKey
        js.append("if(isSlave){"); // if
        js.append("var dataKey=widget.%s;", DataKeyVariable.ID);
        js.append("record=null;");
        js.append("for(const i in items){"); // for
        js.append("var r=items[i];");
        js.append("var recordId=r[dataKey.field];");
        js.append("if(dataKey.value===recordId){"); // if
        js.append("record=r;");
        js.append("break;");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("if(record==null){"); // if
        js.throwError("dataKey not found", "items");
        js.append("};"); // end if
        js.append("};"); // end if

        js.append("const value=record['%s'];", ValueDataAdapter.OUTPUT_VALUE_FIELD_NAME);
        js.append("%s(id,value);", JsWidgetModule.getId(SetValueFunction.class));

        js.append("}"); // end function
    }
}
