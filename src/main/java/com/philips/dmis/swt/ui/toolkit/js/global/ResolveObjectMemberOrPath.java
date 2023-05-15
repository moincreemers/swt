package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class ResolveObjectMemberOrPath implements JsFunction {
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
        return JsType.DATE;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(object,memberOrPath)=>{");

        js.debug("console.log('ResolveObjectMemberOrPath',object,memberOrPath);");

        js.append("if(object==undefined||object==null){return null;};");
        js.append("if(memberOrPath.startsWith('.')){memberOrPath=memberOrPath.substring(1);};");
        js.append("var fieldNames=memberOrPath.split('.');");
        js.append("var s=object;");
        js.append("for(const f in fieldNames){"); // for
        js.debug("console.log(fieldNames[f]);");
        js.append("if(typeof s=='object'&&s.hasOwnProperty(fieldNames[f])){"); // if
        js.append("s=s[fieldNames[f]];}else{s=null;break;");
        js.append("};");// end if
        js.append("};");// end for
        js.append("return s;");

        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("date", JsType.DATE));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
