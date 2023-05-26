package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
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
        parameters.add(JsParameter.getInstance("serviceResponse", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason,cacheType,serviceResponse,dataSourceId)=>{");

        js.debug("console.log('UpdateOptionsFunction',reason,cacheType,serviceResponse,dataSourceId);");

        js.append("const dataItems=serviceResponse.data.items;");

        js.append("const elem=%s();", JsPagesModule.getId(widget, GetElementFunction.class));

        js.append("const selectedView=%s(serviceResponse);",
                JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("var viewValueField=null;");
        js.append("if(selectedView!=null){"); // if
        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'&&view.source=='%s'){", // if
                ViewType.FIELD.name(),
                KeyValueListDataAdapter.DEFAULT_VALUE_FIELD);
        js.append("viewValueField=view;");
        js.append("break;");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("};"); // end if

        if (widgetType == WidgetType.MULTIPLE_CHOICE) {
            js.append("var val=elem.getAttribute('value');");
            js.append("if(val==null||val==undefined){val='';};");
            js.append("const selectedValues=val.split(',');");
            js.append("%s(dataSourceId);", JsPagesModule.getId(widget, RemoveOptionsFunction.class));
            js.append("for(const i in dataItems){"); // for
            js.append("var item=document.createElement('li');");
            js.append("elem.append(item);");
            js.append("var cb=document.createElement('input');");
            js.append("item.setAttribute('tk-source',dataSourceId);");
            js.append("item.setAttribute('id','%s_item_'+i);", widget.getId());
            js.append("item.setAttribute('name','%s_item');", widget.getId());
            js.append("item.setAttribute('class','tk-mc-item');");
            js.append("cb.setAttribute('id','%s_input_'+i);", widget.getId());
            js.append("cb.setAttribute('tk-key-type','i');");
            js.append("cb.setAttribute('tk-key',''+i);");
            js.append("cb.setAttribute('class','tk-mc-checkbox');");
            js.append("cb.setAttribute('type','checkbox');");
            js.append("cb.setAttribute('name','%s_input');", widget.getId());
            js.append("if(selectedValues.includes(dataItems[i].key)){"); // if
            js.append("cb.setAttribute('checked','');");
            js.append("item.classList.add('tk-selected');");
            js.append("};"); // end if
            js.append("cb.value=dataItems[i].key;");
            js.append("var lbl=document.createElement('label');");
            js.append("lbl.setAttribute('for','%s_input_'+i);", widget.getId());
            js.append("lbl.setAttribute('class','tk-mc-label');");
            js.append("lbl.textContent=dataItems[i].value;");
            js.append("item.append(cb);");
            js.append("item.append(lbl);");

            js.append("const formattedElement=%s(lbl,dataItems[i].value,viewValueField);",
                    JsGlobalModule.getQualifiedId(CreateFormattedValue.class));

            js.append("%s(formattedElement,dataItems[i].value,viewValueField);",
                    JsPagesModule.getId(widget, BlobToElementFunction.class));

            js.append("if(formattedElement!=null&&lbl!=formattedElement){");
            js.append("item.classList.add('tk-hide-input');");
            js.append("};");

            js.append("};"); // end for
        } else if (widgetType == WidgetType.SINGLE_CHOICE) {
            js.append("var selectedValue=elem.getAttribute('value');");
            js.append("if(selectedValue==null||selectedValue==undefined){selectedValue='';};");
            js.append("%s(dataSourceId);", JsPagesModule.getId(widget, RemoveOptionsFunction.class));
            js.append("for(const i in dataItems){"); // for
            js.append("var item=document.createElement('li');");
            js.append("elem.append(item);");
            js.append("var rb=document.createElement('input');");
            js.append("item.setAttribute('tk-source',dataSourceId);");
            js.append("item.setAttribute('class','tk-sc-item');");
            js.append("item.setAttribute('id','%s_item_'+i);", widget.getId());
            js.append("item.setAttribute('name','%s_item');", widget.getId());
            js.append("rb.setAttribute('id','%s_input_'+i);", widget.getId());
            js.append("rb.setAttribute('tk-key-type','i');");
            js.append("rb.setAttribute('tk-key',''+i);");
            js.append("rb.setAttribute('class','tk-sc-radio');");
            js.append("rb.setAttribute('type','radio');");
            js.append("rb.setAttribute('name','%s_input');", widget.getId());
            js.append("if(selectedValue==dataItems[i].key){"); // if
            js.append("rb.setAttribute('checked','');");
            js.append("item.classList.add('tk-selected');");
            js.append("};"); // end if
            js.append("rb.value=dataItems[i].key;");
            js.append("var lbl=document.createElement('label');");
            js.append("lbl.setAttribute('for','%s_input_'+i);", widget.getId());
            js.append("lbl.setAttribute('class','tk-sc-label');");
            js.append("lbl.textContent=dataItems[i].value;");
            js.append("item.append(rb);");
            js.append("item.append(lbl);");

            js.append("const formattedElement=%s(lbl,dataItems[i].value,viewValueField);",
                    JsGlobalModule.getQualifiedId(CreateFormattedValue.class));

            js.append("%s(formattedElement,dataItems[i].value,viewValueField);",
                    JsPagesModule.getId(widget, BlobToElementFunction.class));

            js.append("if(formattedElement!=null&&lbl!=formattedElement){");
            js.append("item.classList.add('tk-hide-input');");
            js.append("};");

            js.append("};"); // end for

        } else {
            js.append("%s(dataSourceId);", JsPagesModule.getId(widget, RemoveOptionsFunction.class));
            js.append("for(const i in dataItems){"); // for
            js.append("%s(elem,dataItems[i].key,dataItems[i].value,dataSourceId);",
                    JsGlobalModule.getQualifiedId(AppendOptionFunction.class));
            js.append("};"); // end for
        }

        js.append("const previouslySelectedValue=elem.getAttribute('tk-value');");
        js.append("if(previouslySelectedValue!=null){");
        if (widgetType == WidgetType.SELECT) {
            js.append("if(elem.hasAttribute('tk-value')){");
            js.append("if(%s(elem,previouslySelectedValue)){", JsGlobalModule.getQualifiedId(ContainsOptionFunction.class));
            js.append("%s(previouslySelectedValue);", JsPagesModule.getId(widget, SetValueFunction.class));
            js.append("};");
            js.append("};");
        } else {
            js.append("%s(previouslySelectedValue);", JsPagesModule.getId(widget, SetValueFunction.class));
        }
        js.append("};");

        js.append("}"); // end function
    }
}
