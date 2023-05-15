package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.dto.ViewAppearance;
import com.philips.dmis.swt.ui.toolkit.dto.ViewType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlTableBody;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class UpdateTableFooterFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "updateTableFooter";
    private final Widget widget;
    private final WidgetType widgetType;

    public UpdateTableFooterFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widgetType == WidgetType.TABLE_FOOTER;
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

        js.append("const tr=document.createElement('tr');");
        js.append("for(const rowIndex in dataItems){"); // for
        js.append("var dataItem=dataItems[rowIndex];");
        js.append("for(const i in selectedView){"); // for
        js.append("var view=selectedView[i];");
        js.append("if(view.viewType!='%s'){continue;};", ViewType.FIELD.name());
        js.append("if(view.appearance=='%s'){continue;};", ViewAppearance.HIDDEN.name());
        js.append("var td=document.createElement('td');");
        js.append("var cellValue=dataItem[view.source];");

        // note: all the other formatters subclass TextFormat
        js.append("if(view.formatType!='%s'){", DataType.STRING.name());
        js.append("%s(td,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("};");

        js.append("switch(view.formatType){"); // switch

        js.append("case '%s':", DataType.BOOLEAN.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_BOOLEAN);
        js.append("%s(td,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatBooleanFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.NUMBER.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_NUMBER);
        js.append("%s(td,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatNumberFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.DATE.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_DATE);
        js.append("%s(td,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatDateFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.STRING.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_STRING);
        js.append("%s(td,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.URL.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_URL);
        js.append("%s(td,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatURLFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.ARRAY.name());
        js.append("td.classList.add('%s');", HtmlTableBody.CSS_CELL_ARRAY);
        js.append("%s(td,cellValue,view.format);",
                JsGlobalModule.getQualifiedId(FormatArrayFunction.class));
        js.append("break;");

        js.append("};"); // end switch
        js.append("tr.append(td);");
        js.append("};"); // end for
        js.append("};"); // end for

        js.append("%s().append(tr);", JsPagesModule.getId(widget, GetElementFunction.class));

        js.append("}"); // end function
    }
}
