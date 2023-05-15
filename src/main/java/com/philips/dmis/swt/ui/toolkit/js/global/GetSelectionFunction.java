package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class GetSelectionFunction implements JsFunction {
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
        return JsType.ARRAY;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(name,top)=>{");
        js.append("var nodeList=document.querySelectorAll('input[name='+name+']:checked');");
        js.append("var keys=[];");
        js.append("for(var i=0;i<nodeList.length;i++){");
        js.append("var keyType=nodeList.item(i).getAttribute('tk-key-type');");
        js.append("var key=nodeList.item(i).getAttribute('tk-key');");
        js.append("switch(keyType){");
        js.append("case 'i': keys.push(parseInt(key));break;");
        js.append("default: keys.push(key);break;");
        js.append("};");
        js.append("if(top==-1||keys.length==top){break;}");
        js.append("};");
        js.append("return keys;");
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("name", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("top", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
