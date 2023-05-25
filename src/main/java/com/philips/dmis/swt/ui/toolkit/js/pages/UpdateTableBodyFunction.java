package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.ExtModuleInvoke;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.ExtModuleEvent;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.events.OpenEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlTableBody;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateTableBodyFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "updateTableBody";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateTableBodyFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.TABLE_BODY;
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

        ExtModuleInvoke.renderCall(ExtModuleEvent.END_PROGRESS, null, widget.getId() + "-nodata", js);

        js.append("%s().textContent='';", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("const selectedView=%s(serviceResponse);", JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("if(selectedView==null){return};");
        js.append("const textWhenEmpty=%s;", JsPagesModule.getId(widget, EmptyTextVariable.class));
        js.append("const dataItems=serviceResponse.data.items;");

        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'){", ViewType.SEQUENCE.name());
        js.append("if(view.appearance=='%s'&&textWhenEmpty==''){", ViewAppearance.DISPLAY_WHEN_DATA.name());
        js.append("%s().style.display=(dataItems.length==0)?'none':'';",
                JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("}else{");
        js.append("%s().style.display='';",
                JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("};");
        js.append("break;");
        js.append("};");
        js.append("};");

        js.append("if(textWhenEmpty!=''&&dataItems.length==0){");
        js.append("var tr=document.createElement('tr');");
        js.append("%s().append(tr);", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("var td=document.createElement('td');");
        js.append("tr.append(td);");
        js.append("td.setAttribute('class','message-container');");
        js.append("const message=document.createElement('div');");
        js.append("message.setAttribute('class','message');");
        js.append("message.textContent=textWhenEmpty;");
        js.append("td.append(message);");
        js.append("};");

        switch (((HtmlTableBody) widget).getTableOrientationType()) {
            case ROW_HEADERS:
                renderRowHeadersJs(toolkit, js);
                break;

            case NORMAL:
            default:
                renderNormalJs(toolkit, js);
                break;
        }

        js.append("}"); // end function
    }

    void renderNormalJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("for(const rowIndex in dataItems){"); // for
        js.append("const dataItem=dataItems[rowIndex];");
        js.append("var colIndex=0;");
        js.append("var evenRow=(rowIndex%2)!=0;");

        // render row:
        js.append("var tr=document.createElement('tr');");
        js.append("%s().append(tr);", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("tr.setAttribute('id','%s_row_'+rowIndex);", widget.getId());
        js.append("tr.setAttribute('tk-even',evenRow);");

        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'&&view.appearance!='%s'){",
                ViewType.FIELD.name(),
                ViewAppearance.HIDDEN.name()); // if

        // render cell:
        js.append("var evenCol=(colIndex%2)!=0;");
        js.append("var td=document.createElement('td');");
        js.append("tr.append(td);");
        js.append("tr.setAttribute('id','%s_row_'+rowIndex+'_column_'+colIndex);", widget.getId());
        js.append("td.setAttribute('tk-even',evenCol);");
        js.append("const cellValue=dataItem[view.source];");

        js.append("var element=td;");
        js.append("if(view.appearance=='%s'){", ViewAppearance.OPEN.name()); // if
        js.append("var openLink=document.createElement('a');");
        js.append("openLink.setAttribute('href','javascript:void(0);');");
        js.append("openLink.setAttribute('class','tk-view-open');");
        js.append("openLink.textContent='(Link)';");
        js.append("openLink.onclick=()=>{");
        js.append("const event=%s;", DtoUtil.getDefault(OpenEvent.class, false));
        js.append("event[%s]=cellValue;", ValueStatement.valueOf(toolkit, OpenEvent.VALUE, widget));
        js.append("event[%s]=dataItem;", ValueStatement.valueOf(toolkit, OpenEvent.RECORD, widget));
        js.append("%s.%s(event);",
                widget.getId(),
                EventHandlerFunction.OnOpenEventHandlerFunction.ID);
        js.append("};");
        js.append("td.append(openLink);");
        js.append("element=openLink;");
        js.append("};"); // end if

        renderCell(toolkit, js);
        renderActions(toolkit, js);


        js.append("colIndex++;");
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("};"); // end for
    }


    void renderRowHeadersJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        // render row headers
        js.append("var colIndex=0;");
        js.append("const rows={};");
        js.append("var r=0;");
        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'&&view.appearance!='%s'){",
                ViewType.FIELD.name(),
                ViewAppearance.HIDDEN.name()); // if
        // render row:
        js.append("var tr=document.createElement('tr');");
        js.append("%s().append(tr);", JsPagesModule.getId(widget, GetElementFunction.class));
        js.append("tr.setAttribute('id','%s_row_'+r);", widget.getId());
        js.append("var evenRow=(r%2)!=0;");
        js.append("tr.setAttribute('tk-even',evenRow);");
        js.append("tr.setAttribute('class','tk-row-header');");
        js.append("rows[view.source]={view:view,row:tr};");
        // render header cell
        js.append("var evenCol=(colIndex%2)!=0;");
        js.append("var td=document.createElement('td');");
        js.append("tr.append(td);");
        js.append("tr.setAttribute('id','%s_row_'+r+'_column_'+colIndex);", widget.getId());
        js.append("td.setAttribute('tk-even',evenCol);");
        js.append("td.setAttribute('class','tk-row-header-cell');");
        js.append("td.textContent=view.name;");
        //
        js.append("r++;");
        js.append("};"); // end if
        js.append("};"); // end for
        js.append("colIndex++;");

        js.append("for(const rowIndex in dataItems){"); // for
        js.append("const dataItem=dataItems[rowIndex];");
        js.append("for(const r in rows){"); // for
        js.append("var view=rows[r].view;");
        js.append("var tr=rows[r].row;");

        // render cell:
        js.append("var evenCol=(colIndex%2)!=0;");
        js.append("var td=document.createElement('td');");
        js.append("tr.append(td);");
        js.append("tr.setAttribute('id','%s_row_'+rowIndex+'_column_'+colIndex);", widget.getId());
        js.append("td.setAttribute('tk-even',evenCol);");
        js.append("const cellValue=dataItem[view.source];");

        js.append("var element=td;");
        js.append("if(view.appearance=='%s'){", ViewAppearance.OPEN.name()); // if
        js.append("var openLink=document.createElement('a');");
        js.append("openLink.setAttribute('href','javascript:void(0);');");
        js.append("openLink.setAttribute('class','tk-view-open');");
        js.append("openLink.textContent='(Link)';");
        js.append("openLink.onclick=()=>{");
        js.append("const event=%s;", DtoUtil.getDefault(OpenEvent.class, false));
        js.append("event[%s]=cellValue;", ValueStatement.valueOf(toolkit, OpenEvent.VALUE, widget));
        js.append("event[%s]=dataItem;", ValueStatement.valueOf(toolkit, OpenEvent.RECORD, widget));
        js.append("%s.%s(event);",
                widget.getId(),
                EventHandlerFunction.OnOpenEventHandlerFunction.ID);
        js.append("};");
        js.append("td.append(openLink);");
        js.append("element=openLink;");
        js.append("};"); // end if

        renderCell(toolkit, js);

        js.append("};"); // end for
        js.append("};"); // end for
        js.append("colIndex++;");
    }

    void renderActions(Toolkit toolkit, JsWriter js) {
        js.append("for(const i in selectedView){"); // for
        js.append("var actionView=selectedView[i];");
        js.append("if(actionView.viewType=='%s'&&view.items.includes(actionView.id)){",
                ViewType.FIELD_ACTION.name()); // if

        js.append("td.append(document.createElement('br'));");

        js.append("var actionLink=document.createElement('a');");
        js.append("actionLink.setAttribute('href','javascript:void(0);');");
        js.append("actionLink.setAttribute('class','link tk-field-action');");
        js.append("actionLink.textContent=actionView.name;");
        js.append("td.append(actionLink);");

        js.append("};"); // end if
        js.append("};"); // end for
    }

    void renderCell(Toolkit toolkit, JsWriter js) {
        js.debug("console.log('UpdateTableBodyFunction',cellValue,view.formatType);");

        // note: all the other formatters subclass TextFormat
        js.append("if(view.formatType!='%s'){", DataType.STRING.name());
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("};");

        js.append("switch(view.formatType){"); // switch

        js.append("case '%s':", DataType.BOOLEAN.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_BOOLEAN);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatBooleanFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.NUMBER.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_NUMBER);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatNumberFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.DATE.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_DATE);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatDateFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.STRING.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_STRING);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.URL.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_URL);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatURLFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.ARRAY.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_ARRAY);
        js.append("%s(element,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatArrayFunction.class));
        js.append("break;");

        js.append("};"); // end switch
    }
}
