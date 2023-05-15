package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.js.pages.IsPageModuleMember;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class FormatURLFunction implements JsFunction, IsPageModuleMember {
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
        js.append("return;");
        js.append("};");

        js.append("var text=urlFormat.text;");
        js.append("if(text==undefined||text==null||text.length==0){text=value;};");
        js.append("var target=urlFormat.target;");
        js.append("if(target==undefined||target==null||target.length==0){target='_blank';};");

        js.append("switch(urlFormat.appearance){");
        js.append("case '%s':", URLAppearanceType.ANCHOR);
        js.append("element.innerHTML='<a href=\\''+value+'\\' target=\\''+target+'\\'>'+text+'</a>';");
        js.append("break;");
        js.append("case '%s':", URLAppearanceType.BUTTON);
        js.append("element.innerHTML='<a href=\\''+value+'\\' target=\\''+target+'\\'><button class=\\'button\\' tabindex=\\'-1\\'>'+text+'</button></a>';");
        js.append("break;");
        js.append("case '%s':", URLAppearanceType.IMAGE);
        js.append("var style=[];");
        js.append("if(urlFormat.imageWidth!=null){style.push('width:'+urlFormat.imageWidth);};");
        js.append("if(urlFormat.imageHeight!=null){style.push('height:'+urlFormat.imageHeight);};");
        js.append("if(urlFormat.imageMaxWidth!=null){style.push('max-width:'+urlFormat.imageMaxWidth);};");
        js.append("if(urlFormat.imageMaxHeight!=null){style.push('max-height:'+urlFormat.imageMaxHeight);};");
        js.append("if(urlFormat.imageBorderRadius!=null){style.push('border-radius:'+urlFormat.imageBorderRadius);};");
        js.append("element.innerHTML='<img src=\\''+value+'\\' alt=\\''+value+'\\' style=\\''+style.join(';')+'\\'>';");
        js.append("break;");
        js.append("default:");
        js.append("element.textContent=value;");
        js.append("break;");
        js.append("};");

        js.append("}");
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {

    }

    @Override
    public void getParameters(List<JsParameter> parameters) {

    }
}
