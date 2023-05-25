package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.events.OpenEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlList;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlTableBody;
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

        js.append("for(const itemIndex in dataItems){"); // for R
        js.append("const dataItem=dataItems[itemIndex];");

        // render list item:
        js.append("var li=document.createElement('li');");
        js.append("%s().append(li);", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("li.setAttribute('id','%s_item_'+itemIndex);", widget.getId());

        js.append("for(const i in selectedView){"); // for V
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'&&view.appearance!='%s'){", // if 1
                ViewType.FIELD.name(),
                ViewAppearance.HIDDEN.name());

        js.append("const cellValue=dataItem[view.source];");

        js.append("var element=li;");
        js.append("if(view.appearance=='%s'){", ViewAppearance.OPEN.name()); // if 2
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

        // note: all the other formatters subclass TextFormat
        js.append("if(view.formatType!='%s'){", DataType.STRING.name()); // if 3
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("};"); // end if 3

        js.append("switch(view.formatType){"); // switch 1

        js.append("case '%s':", DataType.BOOLEAN.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_BOOLEAN);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatBooleanFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.NUMBER.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_NUMBER);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatNumberFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.DATE.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_DATE);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatDateFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.STRING.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_STRING);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.URL.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_URL);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatURLFunction.class));

        // todo: move to function and call from other places as well
        js.append("if(view.format.appearance=='%s'&&%s[cellValue]){",
                URLAppearanceType.XHR_IMAGE,
                JsPagesModule.getId(widget, SyncVariable.class));
        js.append("%s[cellValue].then((xhrResponse)=>{",
                JsPagesModule.getId(widget, SyncVariable.class)); // function
        js.debug("console.log('image callback',xhrResponse);");
        js.append("const dataURL=window.URL.createObjectURL(xhrResponse.data);");
        //js.info("console.log('referencing img',cellValue);");
        js.append("const elem=document.getElementById(cellValue);");
        js.append("elem.setAttribute('src',dataURL);");
        js.append("elem.style.visibility='';");
        js.append("delete %s[cellValue];",
                JsPagesModule.getId(widget, SyncVariable.class));
        js.append("});"); // end function
        js.append("%s[cellValue].fail((e)=>{",
                JsPagesModule.getId(widget, SyncVariable.class)); // function
        js.info("console.log('failed',e);");
        js.append("delete %s[cellValue];",
                JsPagesModule.getId(widget, SyncVariable.class));
        js.append("});"); // end function
        js.append("};");

        js.append("break;");

        js.append("case '%s':", DataType.ARRAY.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_ARRAY);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatArrayFunction.class));
        js.append("break;");

        js.append("};"); // end switch 1

        // note: this exits after rendering the first visible view field
        js.append("break;"); //

        js.append("};"); // end if 1

        js.append("};"); // end for V

        js.append("};"); // end for R

        js.append("}"); // end function
    }
}
