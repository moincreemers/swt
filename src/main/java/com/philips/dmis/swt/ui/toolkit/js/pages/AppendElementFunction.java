package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.AppendEvent;
import com.philips.dmis.swt.ui.toolkit.events.AppendEventHandler;
import com.philips.dmis.swt.ui.toolkit.events.EventHandlerRenderer;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

import java.util.List;

public class AppendElementFunction implements JsFunction, IsPageModuleMember {
    public static final String ID = "appendElement";
    private final Widget widget;
    private final WidgetType widgetType;

    public AppendElementFunction(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();
    }

    @Override
    public boolean isMemberOf(Widget widget, WidgetType widgetType) {
        return widget instanceof ContainerWidget<?>;
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
        parameters.add(JsParameter.getInstance("widgetSpec", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("element", JsType.OBJECT));
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(widgetSpec,element)=>{");

        // append a child element
        if (widgetType == WidgetType.PAGE) {
            js.append("const innerDivId='%s';", Page.getInnerDivId(widget.getId()));
            js.append("var inner=document.getElementById(innerDivId);");
            js.append("if(inner==null){");
            js.throwError("inner element not found", "innerDivId");
            js.append("};");
            js.append("inner.append(element);");

            // if page-header/footer or nav-left/right then we need to add a class to the inner element
            js.append("var childWidgetId=element.getAttribute('id');");
            js.append("if(childWidgetId==null){");
            js.throwError("missing id attribute", "element");
            js.append("};");
            js.append("var childWidget=window[childWidgetId];");
            js.append("if(childWidget==undefined){");
            js.throwError("child widget not found", "childWidgetId");
            js.append("};");
            js.append("if(widgetSpec.widgetType=='%s'&&widgetSpec.panelType=='%s'){",
                    WidgetType.PANEL.getShortName(), PanelType.PAGE_HEADER.getShortName());
            js.append("inner.classList.add('tk-page-has-header');");
            js.append("};");
            js.append("if(widgetSpec.widgetType=='%s'&&widgetSpec.panelType=='%s'){",
                    WidgetType.PANEL.getShortName(), PanelType.PAGE_FOOTER.getShortName());
            js.append("inner.classList.add('tk-page-has-footer');");
            js.append("};");
            js.append("if(widgetSpec.widgetType=='%s'&&widgetSpec.panelType=='%s'){",
                    WidgetType.PANEL.getShortName(), PanelType.NAV_LEFT.getShortName());
            js.append("inner.classList.add('tk-page-has-nav-left');");
            js.append("};");
            js.append("if(widgetSpec.widgetType=='%s'&&widgetSpec.panelType=='%s'){",
                    WidgetType.PANEL.getShortName(), PanelType.NAV_RIGHT.getShortName());
            js.append("inner.classList.add('tk-page-has-nav-right');");
            js.append("};");
        } else if (widgetType == WidgetType.GRID) {
                /* grid is a CSS-table:
                    <div class="grid">
                        <div class="grid-row">
                            <div class="grid-cell">
                */
            js.append("var gridElement=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("var numberOfRows=gridElement.childElementCount;");
            js.debug("console.log(numberOfRows);");
            js.append("var numberOfCells=0;");
            js.append("var rowElement=null;");

            // add to row
            js.append("if(numberOfRows!=0&&numberOfCells<%s){", JsPagesModule.getId(widget, ColumnsVariable.class));
            js.append("rowElement=gridElement.lastChild;");
            js.append("numberOfCells=rowElement.childElementCount;");
            js.append("};");

            // add new row
            js.append("if(numberOfRows==0||numberOfCells==columns){");
            js.append("rowElement=document.createElement('div');");
            js.append("numberOfRows+=1;");
            js.append("numberOfCells=0;");
            js.append("rowElement.setAttribute('id','%s_row_'+numberOfRows);", widget.getId());
            js.append("rowElement.setAttribute('class','%s');", Grid.CSS_CLASS_ROW);
            js.append("rowElement.setAttribute('tk-row',''+numberOfRows);");
            js.append("gridElement.append(rowElement);");
            js.append("};");

            // create cell
            js.append("var cellElement=document.createElement('div');");
            js.append("var cellIndex=(numberOfRows*columns)+numberOfCells;");
            js.append("var cellClassNames='%s %s-' + columns;",
                    Grid.CSS_CLASS_CELL, Grid.CSS_CLASS_CELL);
            js.append("cellElement.setAttribute('id','%s_cell_'+cellIndex);", widget.getId());
            js.append("cellElement.setAttribute('class',cellClassNames);");
            js.append("cellElement.setAttribute('tk-row',''+numberOfRows);");
            js.append("cellElement.setAttribute('tk-col',''+numberOfCells);");
            js.append("rowElement.append(cellElement);");
            js.append("cellElement.append(element);");
        } else if (widgetType == WidgetType.SINGLE_ROW) {
            js.append("var singleRowElement=%s();", JsPagesModule.getId(widget, GetElementFunction.class));
            js.append("var numberOfCells=singleRowElement.childElementCount;");
            js.append("var cellElement=document.createElement('div');");
            js.append("cellElement.setAttribute('id','%s_cell_'+numberOfCells);", widget.getId());
            js.append("cellElement.setAttribute('class','%s');", SingleRowPanel.CSS_CLASS_CELL);
            js.append("singleRowElement.append(cellElement);");
            js.append("cellElement.append(element);");
        } else if (widgetType == WidgetType.LIST_CONTAINER) {
            js.append("var itemElement=document.createElement('li');");
            js.append("itemElement.append(element);");
            js.append("%s().append(itemElement);", JsPagesModule.getId(widget, GetElementFunction.class));
        } else {
            js.append("%s().append(element);", JsPagesModule.getId(widget, GetElementFunction.class));
        }

        if (widget instanceof ContainerWidget<?>) {
            EventHandlerRenderer.renderHandlers(widget.getEventHandlers(), toolkit, widget, js, AppendEventHandler.NAME, new AppendEvent());
        }

        if (widget instanceof Page) {
            js.append("if(widgetSpec.isNumbered){");
            //js.debug("console.log('x',widgetSpec);");
            js.append("%s(widgetSpec.widgetType);",
                    JsPagesModule.getId(widget, UpdateNumberingFunction.class));
            js.append("};");
        }

        js.append("}"); // end function
    }
}
