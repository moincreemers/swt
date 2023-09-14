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
        dependencies.add(GridColumnsVariable.class);
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
        js.append("const childImplements=childWidget.%s;", ImplementsVariable.ID);
        js.append("const isNumbered=childWidget.%s;", IsNumberedVariable.ID);
        js.append("const layout=childWidget.%s;", LayoutVariable.ID);

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
        js.append("const noResizeChildWidget=layout=='%s'||(layout=='%s'&&(" +
                        "childImplements.includes('%s')" +
                        "||childImplements.includes('%s')" +
                        "||childImplements.includes('%s')" +
                        "));",
                LayoutType.NO_RESIZE, LayoutType.AUTO,
                ValueWidget.class.getSimpleName(),
                HtmlCheckInput.class.getSimpleName(),
                HtmlButton.class.getSimpleName()
        );
        js.append("var cellElement=element;");
        js.append("if(noResizeChildWidget){");
        js.append("var outer=document.createElement('div');");
        js.append("outer.append(cellElement);");
        js.append("cellElement=outer;");
        js.append("};");
        js.append("cellElement.classList.add('%s');", Grid.CSS_CLASS_CELL);
        js.append("const gridRows=containerWidget.%s;", GridRowsVariable.ID);
        js.append("const gridColumns=containerWidget.%s;", GridColumnsVariable.ID);
        js.append("const numberOfChildren=containerElement.childElementCount;");
        js.append("if(gridColumns>0){");
        js.append("if(numberOfChildren==0||numberOfChildren%gridColumns==0){");
        js.append("cellElement.classList.add('grid-column-first');");
        js.append("}else if((numberOfChildren+1)%gridColumns==0){");
        js.append("cellElement.classList.add('grid-column-last');");
        js.append("};");
        js.append("};");
        js.append("if(gridRows>0){");
        js.append("if(numberOfChildren==0||numberOfChildren%gridRows==0){");
        js.append("cellElement.classList.add('grid-row-first');");
        js.append("}else if((numberOfChildren+1)%gridRows==0){");
        js.append("cellElement.classList.add('grid-row-last');");
        js.append("};");
        js.append("};");
        js.append("containerElement.append(cellElement);");

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

        js.append("if(isNumbered){"); // if
        js.append("%s(childWidget.%s,childWidgetType);",
                JsWidgetModule.getId(UpdateNumberingFunction.class),
                PageIdVariable.ID);
        js.append("};"); // end if

        js.append("}"); // end function
    }
}
