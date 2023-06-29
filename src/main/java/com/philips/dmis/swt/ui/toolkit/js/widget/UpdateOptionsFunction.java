package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.ContainsOptionFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.CreateFormattedValue;
import com.philips.dmis.swt.ui.toolkit.js.global.GetSelectedViewFunction;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.widgets.HasOptions;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateOptionsFunction implements JsFunction {
    public static final String ID = "updateOptions";

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
        //js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("const dataItems=serviceResponse.data.items;");

        // note: the viewValueField is only used for formatting
        // the key/value field mapping is based on a naming convention set in KeyValueDataAdapter
        js.append("const selectedView=%s(serviceResponse);",
                JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("var viewValueField=null;");
        js.append("if(selectedView!=null){"); // if
        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'&&view.source=='%s'){", ViewType.FIELD.name(),
                KeyValueListDataAdapter.OUTPUT_VALUE_FIELD); // if
        js.append("viewValueField=view;");
        js.append("break;");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("};"); // end if

        js.append("if(widgetType=='%s'){", WidgetType.MULTIPLE_CHOICE.name()); // if
        js.append("var val=element.getAttribute('value');");
        js.append("if(val==null||val==undefined){val='';};");
        js.append("const selectedValues=val.split(',');");
        js.append("%s(id,dataSourceId);", JsWidgetModule.getId(RemoveOptionsFunction.class));
        js.append("for(const i in dataItems){"); // for
        js.append("var item=document.createElement('li');");
        js.append("element.append(item);");
        js.append("var cb=document.createElement('input');");
        js.append("item.setAttribute('tk-source',dataSourceId);");
        js.append("item.setAttribute('id',id+'_item_'+i);");
        js.append("item.setAttribute('name',id+'_item');");
        js.append("item.setAttribute('class','tk-mc-item');");
        js.append("cb.setAttribute('id',id+'_input_'+i);");
        js.append("cb.setAttribute('tk-key-type','i');");
        js.append("cb.setAttribute('tk-key',''+i);");
        js.append("cb.setAttribute('class','tk-mc-checkbox');");
        js.append("cb.setAttribute('type','checkbox');");
        js.append("cb.setAttribute('name',id+'_input');");
        js.append("if(selectedValues.includes(dataItems[i]['%s'])){",
                KeyValueListDataAdapter.OUTPUT_KEY_FIELD); // if
        js.append("cb.setAttribute('checked','');");
        js.append("item.classList.add('tk-selected');");
        js.append("};"); // end if
        js.append("cb.value=dataItems[i]['%s'];", KeyValueListDataAdapter.OUTPUT_KEY_FIELD);
        js.append("var lbl=document.createElement('label');");
        js.append("lbl.setAttribute('for',id+'_input_'+i);");
        js.append("lbl.setAttribute('class','tk-mc-label');");
        js.append("lbl.textContent=dataItems[i]['%s'];", KeyValueListDataAdapter.OUTPUT_VALUE_FIELD);
        js.append("item.append(cb);");
        js.append("item.append(lbl);");

        js.append("const formattedElement=%s(lbl,dataItems[i]['%s'],viewValueField);",
                JsGlobalModule.getQualifiedId(CreateFormattedValue.class),
                KeyValueListDataAdapter.OUTPUT_VALUE_FIELD);

        js.append("%s(id,formattedElement,dataItems[i]['%s'],viewValueField);",
                JsWidgetModule.getId(BlobToElementFunction.class),
                KeyValueListDataAdapter.OUTPUT_VALUE_FIELD);

        js.append("if(formattedElement!=null&&lbl!=formattedElement){");
        js.append("item.classList.add('tk-hide-input');");
        js.append("};");

        js.append("};"); // end for

        js.append("}else if(widgetType=='%s'){", WidgetType.SINGLE_CHOICE.name()); // if
        js.append("var selectedValue=element.getAttribute('value');");
        js.append("if(selectedValue==null||selectedValue==undefined){selectedValue='';};");
        js.append("%s(id,dataSourceId);", JsWidgetModule.getId(RemoveOptionsFunction.class));
        js.append("for(const i in dataItems){"); // for
        js.append("var item=document.createElement('li');");
        js.append("element.append(item);");
        js.append("var rb=document.createElement('input');");
        js.append("item.setAttribute('tk-source',dataSourceId);");
        js.append("item.setAttribute('class','tk-sc-item');");
        js.append("item.setAttribute('id',id+'_item_'+i);");
        js.append("item.setAttribute('name',id+'_item');");
        js.append("rb.setAttribute('id',id+'_input_'+i);");
        js.append("rb.setAttribute('tk-key-type','i');");
        js.append("rb.setAttribute('tk-key',''+i);");
        js.append("rb.setAttribute('class','tk-sc-radio');");
        js.append("rb.setAttribute('type','radio');");
        js.append("rb.setAttribute('name',id+'_input');");
        js.append("if(selectedValue==dataItems[i]['%s']){",
                KeyValueListDataAdapter.OUTPUT_KEY_FIELD); // if
        js.append("rb.setAttribute('checked','');");
        js.append("item.classList.add('tk-selected');");
        js.append("};"); // end if
        js.append("rb.value=dataItems[i]['%s'];", KeyValueListDataAdapter.OUTPUT_KEY_FIELD);
        js.append("var lbl=document.createElement('label');");
        js.append("lbl.setAttribute('for',id+'_input_'+i);");
        js.append("lbl.setAttribute('class','tk-sc-label');");
        js.append("lbl.textContent=dataItems[i]['%s'];", KeyValueListDataAdapter.OUTPUT_VALUE_FIELD);
        js.append("item.append(rb);");
        js.append("item.append(lbl);");

        js.append("const formattedElement=%s(lbl,dataItems[i]['%s'],viewValueField);",
                JsGlobalModule.getQualifiedId(CreateFormattedValue.class),
                KeyValueListDataAdapter.OUTPUT_VALUE_FIELD);

        js.append("%s(id,formattedElement,dataItems[i]['%s'],viewValueField);",
                JsWidgetModule.getId(BlobToElementFunction.class),
                KeyValueListDataAdapter.OUTPUT_VALUE_FIELD);

        js.append("if(formattedElement!=null&&lbl!=formattedElement){");
        js.append("item.classList.add('tk-hide-input');");
        js.append("};");

        js.append("};"); // end for

        js.append("}else{"); // if
        js.append("%s(id,dataSourceId);", JsWidgetModule.getId(RemoveOptionsFunction.class));
        js.append("for(const i in dataItems){"); // for
        js.append("%s(element,dataItems[i]['%s'],dataItems[i]['%s'],dataSourceId);",
                JsWidgetModule.getQualifiedId(AppendOptionFunction.class),
                KeyValueListDataAdapter.OUTPUT_KEY_FIELD,
                KeyValueListDataAdapter.OUTPUT_VALUE_FIELD);
        js.append("};"); // end for

        js.append("};"); // end if

        js.append("const previouslySelectedValue=element.getAttribute('tk-value');");
        js.append("if(previouslySelectedValue!=null){"); // if
        js.append("if(widgetType=='%s'){", WidgetType.SELECT.name()); // if

        js.append("if(element.hasAttribute('tk-value')){"); // if
        js.append("if(%s(element,previouslySelectedValue)){", JsGlobalModule.getQualifiedId(ContainsOptionFunction.class)); // if
        js.append("%s(id,previouslySelectedValue);", JsWidgetModule.getId(SetValueFunction.class));
        js.append("};"); // end if
        js.append("};"); // end if
        js.append("}else{"); // if
        js.append("%s(id,previouslySelectedValue);", JsWidgetModule.getId(SetValueFunction.class));
        js.append("};"); // end if
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
