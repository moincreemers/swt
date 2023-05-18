package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.AppendOptionFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOptions;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateOptionsFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "updateOptions";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateOptionsFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof HasOptions;
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
        js.append("(reason,cacheType,object,dataSourceId)=>{");

        js.debug("console.log('UpdateOptionsFunction',reason,cacheType,object,dataSourceId);");

        if (widgetType == WidgetType.MULTIPLE_CHOICE) {
            js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("var val=elem.getAttribute('value');");
            js.append("if(val==null||val==undefined){val='';};");
            js.append("const selectedValues=val.split(',');");
            js.append("%s(dataSourceId);", JsPagesModule.getId(widget, RemoveOptionsFunction.class));
            js.append("for(const i in object){");
            js.append("var item=document.createElement('li');");
            js.append("var cb=document.createElement('input');");
            js.append("var lbl=document.createElement('label');");
            js.append("item.setAttribute('tk-source',dataSourceId);");
            js.append("item.setAttribute('id','%s_item_'+i);", widget.getId());
            js.append("item.setAttribute('name','%s_item');", widget.getId());
            js.append("item.setAttribute('class','tk-mc-item');");
            js.append("cb.setAttribute('id','%s_input_'+i);", widget.getId());
            js.append("cb.setAttribute('tk-key-type','i');");
            js.append("cb.setAttribute('tk-key',''+i);");
            js.append("cb.setAttribute('class','tk-mc-checkbox');");
            js.append("lbl.setAttribute('for','%s_input_'+i);", widget.getId());
            js.append("lbl.setAttribute('class','tk-mc-label');");
            js.append("cb.setAttribute('type','checkbox');");
            js.append("cb.setAttribute('name','%s_input');", widget.getId());
            js.append("if(selectedValues.includes(object[i].key)){");
            js.append("cb.setAttribute('checked','');");
            js.append("item.classList.add('tk-selected');");
            js.append("};");
            js.append("cb.value=object[i].key;");
            js.append("lbl.textContent=object[i].value;");
            js.append("item.append(cb);");
            js.append("item.append(lbl);");
            js.append("%s().append(item);", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
        } else if (widgetType == WidgetType.SINGLE_CHOICE) {
            js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("var selectedValue=elem.getAttribute('value');");
            js.append("if(selectedValue==null||selectedValue==undefined){selectedValue='';};");
            js.append("%s(dataSourceId);", JsPagesModule.getId(widget, RemoveOptionsFunction.class));
            js.append("for(const i in object){");
            js.append("var item=document.createElement('li');");
            js.append("var rb=document.createElement('input');");
            js.append("var lbl=document.createElement('label');");
            js.append("item.setAttribute('tk-source',dataSourceId);");
            js.append("item.setAttribute('class','tk-sc-item');");
            js.append("item.setAttribute('id','%s_item_'+i);", widget.getId());
            js.append("item.setAttribute('name','%s_item');", widget.getId());
            js.append("rb.setAttribute('id','%s_input_'+i);", widget.getId());
            js.append("rb.setAttribute('tk-key-type','i');");
            js.append("rb.setAttribute('tk-key',''+i);");
            js.append("rb.setAttribute('class','tk-sc-radio');");
            js.append("lbl.setAttribute('for','%s_input_'+i);", widget.getId());
            js.append("lbl.setAttribute('class','tk-sc-label');");
            js.append("rb.setAttribute('type','radio');");
            js.append("rb.setAttribute('name','%s_input');", widget.getId());
            //js.info("console.log('selected value', selectedValue, object[i].key);");
            js.append("if(selectedValue==object[i].key){");
            js.append("rb.setAttribute('checked','');");
            js.append("item.classList.add('tk-selected');");
            js.append("};");
            js.append("rb.value=object[i].key;");
            js.append("lbl.textContent=object[i].value;");
            js.append("item.append(rb);");
            js.append("item.append(lbl);");
            js.append("%s().append(item);", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
        } else {
            js.append("%s(dataSourceId);", JsPagesModule.getId(widget, RemoveOptionsFunction.class));
            js.append("for(const i in object){");
            js.append("%s(%s(),object[i].key,object[i].value,dataSourceId);",
                    JsGlobalModule.getQualifiedId(AppendOptionFunction.class),
                    JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("};");
        }

        js.append("const previouslySelectedValue=%s().getAttribute('tk-value');",
                JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("if(previouslySelectedValue!=null){");
        js.append("%s(previouslySelectedValue);", JsPagesModule.getId(widget, SetValueFunction.class));
        js.append("};");

        js.append("}"); // end function
    }
}
