package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.DataVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class SetValueFunction implements JsFunction {
    public static final String ID = "setValue";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasValue
                || widget instanceof DataSourceSupplier;
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,value)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("if(!implements.includes('%s')&&!implements.includes('%s')){",
                DataSourceSupplier.class.getSimpleName(),
                HasText.class.getSimpleName()); // if
        // todo: is this still used?
        // prevent duplication of potentially large blobs of JSON
        js.append("element.setAttribute('tk-value',value);");
        js.append("};"); // end if

        js.append("if(implements.includes('%s')){", DataProviderWidget.class.getSimpleName()); // if
        js.append("%s(id,value);", JsWidgetModule.getId(PostFunction.class));

        js.append("}else if(implements.includes('%s')){", HasValue.class.getSimpleName());
        js.append("if(widgetType=='%s'){", WidgetType.CHECK.name());
        js.append("element.checked=(value=='true');");

        js.append("}else if(widgetType=='%s'){", WidgetType.MULTIPLE_CHOICE.name());
        // todo:

        js.append("}else if(widgetType=='%s'){", WidgetType.SINGLE_CHOICE.name());
        // todo:

        js.append("}else if(widgetType=='%s'){", WidgetType.FILE.name());
        // do nothing

        js.append("}else ");
        js.ifInArray("widgetType", WidgetType.FRAME.name(),
                WidgetType.IMAGE_BUTTON.name(), WidgetType.IMAGE.name());
        js.append("element.setAttribute('src',value);");

        js.append("}else if(widgetType=='%s'){", WidgetType.DATA.name());
        js.append("if(value==null){value={};}");
        js.append("const sr=%s;",
                DtoUtil.getDefault(ServiceResponse.class, false));
        js.append("if(Array.isArray(value)){");
        js.append("sr.data.items=value;");
        js.append("}else{");
        js.append("sr.data.items.push(value);");
        js.append("};");
        js.append("widget.%s=JSON.stringify(sr);", DataVariable.ID);
        js.append("%s(id,'%s');",
                JsWidgetModule.getId(RefreshFunction.class),
                JsStateModule.REASON_USER);

        js.append("}else{");
        js.append("element.value=value;");

        js.append("};");

        js.append("}else if(implements.includes('%s')){", DataSourceSupplier.class.getSimpleName());

        js.append("if(implements.includes('%s')){", HasCalculatedValue.class.getSimpleName()); // if
        js.append("widget.%s=JSON.stringify(value);", DataVariable.ID);

        js.append("}else if(implements.includes('%s')){", StaticData.class.getSimpleName()); // if
        js.append("widget.%s=value;", DataVariable.ID);

        js.append("}else{"); // if
        js.append("widget.%s=JSON.stringify(value);", DataVariable.ID);

        js.append("};"); // end if

        js.append("};"); // end if

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("value", JsType.OBJECT));
    }
}
