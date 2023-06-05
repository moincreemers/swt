package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class FormatURLFunction implements JsFunction {
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
        js.append("(element,value,urlFormat)=>{");

        js.append("if(value==undefined||urlFormat==undefined||value==null||value==''){");
        js.append("element.textContent=value;");
        js.append("return element;");
        js.append("};");

        js.append("var text=urlFormat.text;");
        js.append("if(text==undefined||text==null||text.length==0){text=value;};");
        js.append("var target=urlFormat.target;");
        js.append("if(target==undefined||target==null||target.length==0){target='_blank';};");

        js.append("var newElement=element;");

        js.append("switch(urlFormat.appearance){");
        js.append("case '%s':", URLAppearanceType.ANCHOR);
        js.append("element.textContent='';");
        js.append("var a=document.createElement('a');");
        js.append("a.setAttribute('href',value);");
        js.append("a.setAttribute('target',target);");
        js.append("a.textContent=text;");
        js.append("element.append(a);");
        js.append("newElement=a;");
        js.append("break;");

        js.append("case '%s':", URLAppearanceType.BUTTON);
        js.append("element.textContent='';");
        js.append("var a=document.createElement('a');");
        js.append("a.setAttribute('href',value);");
        js.append("a.setAttribute('target',target);");
        js.append("element.append(a);");
        js.append("var b=document.createElement('button');");
        js.append("b.classList.add('button');");
        js.append("b.setAttribute('tabindex',-1);");
        js.append("b.textContent=text;");
        js.append("a.append(b);");
        js.append("newElement=a;");
        js.append("break;");

        js.append("case '%s':", URLAppearanceType.IMAGE);
        js.append("element.textContent='';");
        js.append("var img=new Image();");
        js.append("img.src=value;");
        js.append("img.alt=value;");
        js.append("if(urlFormat.imageWidth!=null){img.style.width=urlFormat.imageWidth};");
        js.append("if(urlFormat.imageHeight!=null){img.style.height=urlFormat.imageHeight};");
        js.append("if(urlFormat.imageMaxWidth!=null){img.style.maxWidth=urlFormat.imageMaxWidth};");
        js.append("if(urlFormat.imageMaxHeight!=null){img.style.maxHeight=urlFormat.imageMaxHeight};");
        js.append("if(urlFormat.imageBorderRadius!=null){img.style.borderRadius=urlFormat.imageBorderRadius};");
        js.append("element.append(img);");
        js.append("newElement=img;");
        js.append("break;");

        js.append("case '%s':", URLAppearanceType.XHR_IMAGE);
        js.append("element.textContent='';");
        js.append("var img=new Image();");
        js.append("img.id=value;");
        js.append("img.alt=value;");
        js.append("if(urlFormat.imageWidth!=null){img.style.width=urlFormat.imageWidth};");
        js.append("if(urlFormat.imageHeight!=null){img.style.height=urlFormat.imageHeight};");
        js.append("if(urlFormat.imageMaxWidth!=null){img.style.maxWidth=urlFormat.imageMaxWidth};");
        js.append("if(urlFormat.imageMaxHeight!=null){img.style.maxHeight=urlFormat.imageMaxHeight};");
        js.append("if(urlFormat.imageBorderRadius!=null){img.style.borderRadius=urlFormat.imageBorderRadius};");
        js.append("img.style.visibility='hidden';");
        js.append("element.append(img);");
        js.append("newElement=img;");
        js.append("break;");

        js.append("default:");
        js.append("element.textContent=value;");
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
