package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.state.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class AppendElementFunction implements JsFunction {
    public static final String ID = "appendElement";

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return true;
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
        dependencies.add(ColumnsVariable.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("id", JsType.STRING));
        parameters.add(JsParameter.getInstance("element", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(id,element)=>{");
        js.trace(this);

        js.append("const childWidget=window[id];");
        js.append("const childWidgetType=childWidget.%s;", WidgetTypeVariable.ID);
        js.append("const isNumbered=childWidget.%s;", IsNumberedVariable.ID);

        js.append("if(childWidgetType=='%s'){", WidgetType.PAGE.name());
        js.append("document.body.append(element);");
        js.append("return;");
        js.append("};");

        js.append("const containerWidgetId=childWidget.%s;", ParentWidgetIdVariable.ID);
        js.append("const containerWidget=window[containerWidgetId];");
        js.append("const containerWidgetType=containerWidget.%s;", WidgetTypeVariable.ID);
        js.append("const containerElement=document.getElementById(containerWidgetId);");

        js.append("if(containerElement==null){");
        js.throwError("container element not found", "containerWidgetId");
        js.append("};");

        // append a child element

        js.append("if(containerWidgetType=='%s'){", WidgetType.PAGE.name()); // if

        js.append("const innerDivId=%s(containerWidgetId);", JsWidgetModule.getId(InnerElementIdFunction.class));
        js.append("var inner=document.getElementById(innerDivId);");
        js.append("if(inner==null){");
        js.throwError("inner element not found", "innerDivId");
        js.append("};");

        js.append("inner.append(element);");

        // if page-header/footer or nav-left/right then we need to add a class to the inner element
        js.append("const childWidgetPanelType=childWidgetType=='%s'?childWidget.%s:null;",
                WidgetType.PANEL.name(), PanelTypeVariable.ID);
        js.append("if(childWidgetPanelType=='%s'){", PanelType.PAGE_HEADER.name());
        js.append("inner.classList.add('tk-page-has-header');");
        js.append("};");
        js.append("if(childWidgetPanelType=='%s'){", PanelType.PAGE_FOOTER.name());
        js.append("inner.classList.add('tk-page-has-footer');");
        js.append("};");
        js.append("if(childWidgetPanelType=='%s'){", PanelType.NAV_LEFT.name());
        js.append("inner.classList.add('tk-page-has-nav-left');");
        js.append("};");
        js.append("if(childWidgetPanelType=='%s'){", PanelType.NAV_RIGHT.name());
        js.append("inner.classList.add('tk-page-has-nav-right');");
        js.append("};");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.GRID.name());  // else

                /* grid is a CSS-table:
                    <div class="grid">
                        <div class="grid-row">
                            <div class="grid-cell">
                */

        js.append("const columns=containerWidget.%s;", ColumnsVariable.ID);
        js.append("containerElement.setAttribute('tk-columns',columns);");

        js.append("var numberOfRows=containerElement.childElementCount;");
        js.debug("console.log(numberOfRows);");
        js.append("var rowElement=containerElement.lastChild;");
        js.append("var numberOfCells=rowElement!=null?rowElement.childElementCount:0;");

        // add new row
        js.append("if(numberOfRows==0||numberOfCells==columns){"); // if
        js.append("rowElement=document.createElement('div');");
        js.append("rowElement.id=containerWidgetId+'_row_'+numberOfRows;");
        js.append("rowElement.setAttribute('class','%s');", Grid.CSS_CLASS_ROW);
        js.append("rowElement.setAttribute('tk-row',numberOfRows);");
        js.append("containerElement.append(rowElement);");
        js.append("containerElement.setAttribute('tk-rows',(numberOfRows+1));");
        js.append("numberOfCells=0;");
        js.append("};"); // end if

        // create cell
        js.append("var cellElement=document.createElement('div');");
        js.append("var cellIndex=(numberOfRows*columns)+numberOfCells;");
        js.append("var cellClassNames='%s %s-'+columns;", Grid.CSS_CLASS_CELL, Grid.CSS_CLASS_CELL);
        js.append("cellElement.id=containerWidgetId+'_cell_'+cellIndex;");
        js.append("cellElement.setAttribute('class',cellClassNames);");
        js.append("cellElement.setAttribute('tk-row',numberOfRows);");
        js.append("cellElement.setAttribute('tk-col',numberOfCells);");
        js.append("rowElement.append(cellElement);");
        js.append("cellElement.append(element);");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.SINGLE_ROW.name());  // else

        js.append("var numberOfCells=containerElement.childElementCount;");
        js.append("var cellElement=document.createElement('div');");
        js.append("cellElement.id=containerWidgetId+'_cell_'+numberOfCells;");
        js.append("cellElement.setAttribute('class','%s');", SingleRowPanel.CSS_CLASS_CELL);
        js.append("containerElement.append(cellElement);");
        js.append("cellElement.append(element);");

        js.append("}else if(containerWidgetType=='%s'){", WidgetType.LIST_CONTAINER.name()); // if

        js.append("var itemElement=document.createElement('li');");
        js.append("itemElement.append(element);");
        js.append("containerElement.append(itemElement);");

        js.append("}else{"); // else

        js.append("containerElement.append(element);");

        js.append("};"); // end if

        js.append("%s(id,%s);",
                JsWidgetModule.getId(RaiseEventFunction.class),
                JsWidgetModule.getId(EventHandlerFunction.OnAppendEventHandlerFunction.class));

        js.append("if(containerWidgetType=='%s'&&isNumbered){",
                WidgetType.PAGE.name()); // if
        js.append("%s(containerWidgetId,childWidgetType);",
                JsWidgetModule.getId(UpdateNumberingFunction.class));
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
