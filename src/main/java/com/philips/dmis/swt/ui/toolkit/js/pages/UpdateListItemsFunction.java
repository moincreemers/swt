package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.KeyValueListDataAdapter;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.events.OpenEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlList;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateListItemsFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "updateListItems";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateListItemsFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

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
        parameters.add(JsParameter.getInstance("reason", JsType.STRING));
        parameters.add(JsParameter.getInstance("cacheType", JsType.STRING));
        parameters.add(JsParameter.getInstance("object", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("dataSourceId", JsType.STRING));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(reason,cacheType,serviceResponse,dataSourceId)=>{");

        js.append("%s().textContent='';", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("const selectedView=%s(serviceResponse);", JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("if(selectedView==null){return};");
        js.append("const dataItems=serviceResponse.data.items;");

        js.debug("console.log('UpdateListItemsFunction',serviceResponse);");

        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'){", ViewType.SEQUENCE.name());
        js.append("if(view.appearance=='%s'){", ViewAppearance.DISPLAY_WHEN_DATA.name());
        js.append("%s().style.display=(dataItems.length==0)?'none':'';",
                JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("}else{");
        js.append("%s().style.display='';",
                JsPagesModule.getId(widget, GetElementFunction.class));
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
        js.append("%s().append(li);", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("li.setAttribute('id','%s_item_'+itemIndex);", widget.getId());

        js.append("const cellValue=dataItem[viewValueField.source];");

        js.append("var element=li;");
        js.append("if(viewValueField.appearance=='%s'){", ViewAppearance.OPEN.name()); // if 2
        js.append("var openLink=document.createElement('a');");
        js.append("openLink.setAttribute('href','javascript:void(0);');");
        js.append("openLink.setAttribute('class','tk-view-open');");
        js.append("openLink.onclick=()=>{");
        js.append("const event=%s;", DtoUtil.getDefault(OpenEvent.class, false));
        js.append("event[%s]=cellValue;", ValueStatement.valueOf(toolkit, OpenEvent.VALUE, widget));
        js.append("event[%s]=dataItem;", ValueStatement.valueOf(toolkit, OpenEvent.RECORD, widget));
        js.append("%s.%s(event);",
                widget.getId(),
                EventHandlerFunction.OnOpenEventHandlerFunction.ID);
        js.append("};");
        js.append("li.append(openLink);");
        js.append("element=openLink;");
        js.append("};"); // end if 2

        js.append("const formattedElement=%s(element,cellValue,viewValueField);",
                JsGlobalModule.getQualifiedId(CreateFormattedValue.class));

        js.append("%s(formattedElement,cellValue,viewValueField);",
                JsPagesModule.getId(widget, BlobToElementFunction.class));

        js.append("};"); // end for R

        js.append("}"); // end function
    }
}
