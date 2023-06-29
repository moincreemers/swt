package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.ExtModuleInvoke;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.ExtModuleEvent;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.events.OpenEvent;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.js.state.EmptyTextVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.ImplementsVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.TableOrientationTypeVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.WidgetTypeVariable;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.TableOrientationType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateTableBodyFunction implements JsFunction {
    public static final String ID = "updateTableBody";

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
        js.append("const implements=widget.%s;", ImplementsVariable.ID);
        js.append("const element=document.getElementById(id);");

        js.append("const progressWidgetId=id+'-nodata';");
        ExtModuleInvoke.renderIndirectCall(ExtModuleEvent.END_PROGRESS, null, "progressWidgetId", js);

        js.append("element.textContent='';");
        js.append("const selectedView=%s(serviceResponse);", JsGlobalModule.getQualifiedId(GetSelectedViewFunction.class));
        js.append("if(selectedView==null){return};");
        js.append("const textWhenEmpty=widget.%s;", EmptyTextVariable.ID);
        js.append("const dataItems=serviceResponse.data.items;");

        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType=='%s'){", ViewType.SEQUENCE.name());
        js.append("if(view.appearance=='%s'&&textWhenEmpty==''){", ViewAppearance.DISPLAY_WHEN_DATA.name());
        js.append("element.style.display=(dataItems.length==0)?'none':'';");
        js.append("}else{");
        js.append("element.style.display='';");
        js.append("};");
        js.append("break;");
        js.append("};");
        js.append("};");

        js.append("if(textWhenEmpty!=''&&dataItems.length==0){");
        js.append("var tr=document.createElement('tr');");
        js.append("element.append(tr);");
        js.append("var td=document.createElement('td');");
        js.append("tr.append(td);");
        js.append("td.setAttribute('class','message-container');");
        js.append("const message=document.createElement('div');");
        js.append("message.setAttribute('class','message');");
        js.append("message.textContent=textWhenEmpty;");
        js.append("td.append(message);");
        js.append("};");

        js.append("if(widget.%s=='%s'){", TableOrientationTypeVariable.ID, TableOrientationType.ROW_HEADERS); // if
        renderRowHeadersJs(toolkit, js);
        js.append("}else{"); // else
        renderNormalJs(toolkit, js);
        js.append("};"); // end if

        js.append("}"); // end function
    }

    void renderNormalJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("for(const rowIndex in dataItems){"); // for
        js.append("const dataItem=dataItems[rowIndex];");
        js.append("var colIndex=0;");
        js.append("var evenRow=(rowIndex%2)!=0;");

        // render row:
        js.append("var tr=document.createElement('tr');");
        js.append("element.append(tr);");
        js.append("tr.setAttribute('id',id+'_row_'+rowIndex);");
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
        js.append("tr.setAttribute('id',id+'_row_'+rowIndex+'_column_'+colIndex);");
        js.append("td.setAttribute('tk-even',evenCol);");
        js.append("const cellValue=dataItem[view.source];");

        js.append("var nextElement=td;");

        js.append("if(view.appearance=='%s'){", ViewAppearance.OPEN.name()); // if
        js.append("var openLink=document.createElement('a');");
        js.append("openLink.setAttribute('href','javascript:void(0);');");
        js.append("openLink.setAttribute('class','tk-view-open');");
        js.append("openLink.textContent='(Link)';");
        js.append("openLink.onclick=()=>{");
        js.append("const event=%s;", DtoUtil.getDefault(OpenEvent.class, false));
        js.append("event.value=cellValue;");
        js.append("event.record=dataItem;");
        js.append("%s(id,%s,event);",
                JsWidgetModule.getQualifiedId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnOpenEventHandlerFunction.class));
        js.append("};");
        js.append("nextElement.append(openLink);");
        js.append("nextElement=openLink;");
        js.append("};"); // end if

        js.append("%s(nextElement,cellValue,view);",
                JsGlobalModule.getQualifiedId(CreateFormattedValue.class));
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
        js.append("element.append(tr);");
        js.append("tr.setAttribute('id',id+'_row_'+r);");
        js.append("var evenRow=(r%2)!=0;");
        js.append("tr.setAttribute('tk-even',evenRow);");
        js.append("tr.setAttribute('class','tk-row-header');");
        js.append("rows[view.source]={view:view,row:tr};");
        // render header cell
        js.append("var evenCol=(colIndex%2)!=0;");
        js.append("var td=document.createElement('td');");
        js.append("tr.append(td);");
        js.append("tr.setAttribute('id',id+'_row_'+r+'_column_'+colIndex);");
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
        js.append("tr.setAttribute('id',id+'_row_'+rowIndex+'_column_'+colIndex);");
        js.append("td.setAttribute('tk-even',evenCol);");
        js.append("const cellValue=dataItem[view.source];");

        js.append("var nextElement=td;");

        js.append("if(view.appearance=='%s'){", ViewAppearance.OPEN.name()); // if
        js.append("var openLink=document.createElement('a');");
        js.append("openLink.setAttribute('href','javascript:void(0);');");
        js.append("openLink.setAttribute('class','tk-view-open');");
        js.append("openLink.textContent='(Link)';");
        js.append("openLink.onclick=()=>{");
        js.append("const event=%s;", DtoUtil.getDefault(OpenEvent.class, false));
        js.append("event.value=cellValue;");
        js.append("event.record=dataItem;");
        js.append("%s(id,event);",
                JsWidgetModule.getId(EventHandlerFunction.OnOpenEventHandlerFunction.class));
        js.append("};");
        js.append("nextElement.append(openLink);");
        js.append("nextElement=openLink;");
        js.append("};"); // end if

        js.append("const formattedElement=%s(nextElement,cellValue,view);",
                JsGlobalModule.getQualifiedId(CreateFormattedValue.class));

        js.append("%s(id,formattedElement,cellValue,view);",
                JsWidgetModule.getId(BlobToElementFunction.class));

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
}
