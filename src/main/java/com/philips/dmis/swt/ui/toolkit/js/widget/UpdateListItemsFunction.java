package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.events.OpenEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlList;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateListItemsFunction implements JsFunction {
    public static final String ID = "updateListItems";

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
        dependencies.add(ConvertHyperlinksFunction.class);
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
        js.append("(id,reason,cacheType,serviceResponse,dataSourceId)=>{");
        js.trace(this);

        js.append("const widget=window[id];");
        js.append("const widgetType=widget.%s;", WidgetTypeVariable.ID);
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("element.textContent='';");
        js.append("const selectedView=%s(serviceResponse);", JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("if(selectedView==null){return};");
        js.append("const dataItems=serviceResponse.data.items;");

        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'){", ViewType.SEQUENCE.name());
        js.append("if(view.appearance=='%s'){", ViewAppearance.DISPLAY_WHEN_DATA.name());
        js.append("element.style.display=(dataItems.length==0)?'none':'';");
        js.append("}else{");
        js.append("element.style.display='';");
        js.append("};");
        js.append("break;");
        js.append("};");
        js.append("};");

        js.append("var viewValueField=null;");
        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'&&view.source=='%s'){", // if
                ViewType.FIELD.name(),
                KeyValueListDataAdapter.DEFAULT_VALUE_FIELD);
        js.append("viewValueField=view;");
        js.append("break;");
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("for(const itemIndex in dataItems){"); // for R
        js.append("const dataItem=dataItems[itemIndex];");

        // render list item:
        js.append("var li=document.createElement('li');");
        js.append("element.append(li);");
        js.append("li.setAttribute('id',id+'_item_'+itemIndex);");

        js.append("const cellValue=dataItem[viewValueField.source];");

        js.append("element=li;");
        js.append("if(viewValueField.appearance=='%s'){", ViewAppearance.OPEN.name()); // if 2
        js.append("var openLink=document.createElement('a');");
        js.append("openLink.setAttribute('href','javascript:void(0);');");
        js.append("openLink.setAttribute('class','tk-view-open');");
        js.append("openLink.onclick=()=>{");
        js.append("const event=%s;", DtoUtil.getDefault(OpenEvent.class, false));
        js.append("event.value=cellValue;");
        js.append("event.record=dataItem;");
        js.append("%s(id,%s,event);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnOpenEventHandlerFunction.class));
        js.append("};");
        js.append("li.append(openLink);");
        js.append("element=openLink;");
        js.append("};"); // end if 2

        js.append("const formattedElement=%s(element,cellValue,viewValueField);",
                JsGlobalModule.getQualifiedId(CreateFormattedValue.class));

        js.append("%s(id,formattedElement,cellValue,viewValueField);",
                JsWidgetModule.getId(BlobToElementFunction.class));

        js.append("};"); // end for R

        js.append("}"); // end function
    }
}
