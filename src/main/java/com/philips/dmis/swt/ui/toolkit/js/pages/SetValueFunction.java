package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ServiceResponse;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class SetValueFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "setValue";
    private final Widget widget;
    private final WidgetType widgetType;

    public SetValueFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasValue || widget instanceof DataSourceSupplier;
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
        js.append("(value)=>{");

        js.debug("console.log('SetValueFunction','%s',value);", widget.getId());

        if (widget != null && !(widget instanceof DataSourceSupplier
                || widget instanceof HasText)) {
            // prevent duplication of potentially large blobs of JSON
            js.append("%s().setAttribute('tk-value',value);", JsPagesModule.getId(widget, GetElementFunction.class));
        }

        if (widget instanceof DataProviderWidget) {
            js.append("%s(value);",
                    JsPagesModule.getId(widget, PostFunction.class));
        } else if (widget instanceof HasValue) {
            if (widgetType == WidgetType.CHECK) {
                js.append("%s().checked=(value=='true');",
                        JsPagesModule.getId(widget, GetElementFunction.class));
            } else if (widgetType == WidgetType.MULTIPLE_CHOICE) {
                // todo:
            } else if (widgetType == WidgetType.SINGLE_CHOICE) {
                // todo:
            } else if (widgetType == WidgetType.FILE) {
                // do nothing
            } else if (widgetType == WidgetType.FRAME
                    || widgetType == WidgetType.IMAGE_BUTTON
                    || widgetType == WidgetType.IMAGE) {
                js.append("%s().setAttribute('src',value);",
                        JsPagesModule.getId(widget, GetElementFunction.class));
            } else if (widgetType == WidgetType.DATA) {
                js.append("if(value==null){value={};}");
                js.append("const sr=%s;",
                        DtoUtil.getDefault(ServiceResponse.class, false));
                js.append("if(Array.isArray(value)){");
                js.append("sr.data.items=value;");
                js.append("}else{");
                js.append("sr.data.items.push(value);");
                js.append("};");
                js.append("%s=JSON.stringify(sr);",
                        JsPagesModule.getQualifiedId(widget, DataVariable.class));
                js.append("%s('%s');",
                        JsPagesModule.getId(widget, RefreshFunction.class),
                        JsPagesModule.REASON_USER);
            } else {
                js.append("%s().value=value;",
                        JsPagesModule.getId(widget, GetElementFunction.class));
            }
        } else if (widget instanceof DataSourceSupplier) {
            if (widget instanceof HasCalculatedValue) {
                js.append("%s=JSON.stringify(value);",
                        JsPagesModule.getQualifiedId(widget, DataVariable.class));
            } else if (widget instanceof StaticData) {
                js.append("%s=value;",
                        JsPagesModule.getQualifiedId(widget, DataVariable.class));
            } else {
                js.append("%s=JSON.stringify(value);",
                        JsPagesModule.getQualifiedId(widget, DataVariable.class));
            }
        }

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }
}
