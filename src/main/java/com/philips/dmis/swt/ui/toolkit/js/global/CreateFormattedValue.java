package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.DataType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.HtmlTableBody;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class CreateFormattedValue implements JsFunction {
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
        return JsType.OBJECT;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(element,value,view)=>{");

        js.append("if(element==null){"); // if
        js.append("return null;");
        js.append("};"); // end if

        js.append("if(view==null){");
        js.append("element.textContent=value;");
        js.append("return element;");
        js.append("};"); // end if

        js.append("var newElement=element;");

        // note: all the other formatters subclass TextFormat
        js.append("if(view.formatType!='%s'){", DataType.STRING.name()); // if
        js.append("newElement=%s(element,value,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("};"); // end if

        js.append("switch(view.formatType){"); // switch 1

        js.append("case '%s':", DataType.BOOLEAN.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_BOOLEAN);
        js.append("newElement=%s(element,value,view.format);",
                JsGlobalModule.getQualifiedId(FormatBooleanFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.NUMBER.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_NUMBER);
        js.append("newElement=%s(element,value,view.format);",
                JsGlobalModule.getQualifiedId(FormatNumberFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.DATE.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_DATE);
        js.append("newElement=%s(element,value,view.format);",
                JsGlobalModule.getQualifiedId(FormatDateFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.STRING.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_STRING);
        js.append("%s(element,value,view.format);",
                JsGlobalModule.getQualifiedId(FormatTextFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.URL.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_URL);
        js.append("newElement=%s(element,value,view.format);",
                JsGlobalModule.getQualifiedId(FormatURLFunction.class));
        js.append("break;");

        js.append("case '%s':", DataType.ARRAY.name());
        js.append("element.classList.add('%s');", HtmlTableBody.CSS_CELL_ARRAY);
        js.append("newElement=%s(element,value,view.format);",
                JsGlobalModule.getQualifiedId(FormatArrayFunction.class));
        js.append("break;");

        js.append("};"); // end switch 1

        js.append("return newElement;");

        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("element", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("value", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("view", JsType.OBJECT));
    }
}
