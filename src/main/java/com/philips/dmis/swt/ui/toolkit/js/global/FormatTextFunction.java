package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.TextAlignmentType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class FormatTextFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("(element,value,textFormat)=>{");

        js.debug("console.log('FormatTextFunction',value);");

        js.append("if(value==undefined||value==null||value==''){");
        js.append("return element;");
        js.append("};");

        js.append("element.textContent=value;");

        js.append("if(textFormat==undefined||textFormat==null){");
        js.append("return element;");
        js.append("};");

        js.append("var newElement=element;");

        js.append("if(textFormat.cellMaxWidth!=null){");
        js.append("element.style.maxWidth=textFormat.cellMaxWidth;");
        js.append("};");
        js.append("if(textFormat.cellMaxHeight!=null){");
        js.append("element.style.maxHeight=textFormat.cellMaxHeight;");
        js.append("};");
        js.append("if(textFormat.cellColor!=null){");
        js.append("element.style.color=textFormat.cellColor;");
        js.append("};");
        js.append("if(textFormat.cellBackgroundColor!=null){");
        js.append("element.style.backgroundColor=textFormat.cellBackgroundColor;");
        js.append("};");

        js.append("switch(textFormat.cellAlignment){");
        js.append("case '%s':", TextAlignmentType.TOP_LEFT);
        js.append("element.classList.add('tk-align-top');");
        js.append("element.classList.add('tk-align-left');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.TOP_CENTER);
        js.append("element.classList.add('tk-align-top');");
        js.append("element.classList.add('tk-align-center');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.TOP_RIGHT);
        js.append("element.classList.add('tk-align-top');");
        js.append("element.classList.add('tk-align-right');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.MIDDLE_LEFT);
        js.append("element.classList.add('tk-align-middle');");
        js.append("element.classList.add('tk-align-left');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.MIDDLE_CENTER);
        js.append("element.classList.add('tk-align-middle');");
        js.append("element.classList.add('tk-align-center');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.MIDDLE_RIGHT);
        js.append("element.classList.add('tk-align-middle');");
        js.append("element.classList.add('tk-align-right');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.BOTTOM_LEFT);
        js.append("element.classList.add('tk-align-bottom');");
        js.append("element.classList.add('tk-align-left');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.BOTTOM_CENTER);
        js.append("element.classList.add('tk-align-bottom');");
        js.append("element.classList.add('tk-align-center');");
        js.append("break;");
        js.append("case '%s':", TextAlignmentType.BOTTOM_RIGHT);
        js.append("element.classList.add('tk-align-bottom');");
        js.append("element.classList.add('tk-align-right');");
        js.append("break;");
        js.append("};");

        js.append("return newElement;");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }
}
