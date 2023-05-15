package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class ToXmlFunction implements JsFunction {
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
        return JsType.STRING;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(name,obj,list)=>{");
        js.append("if(obj==null){");
        js.append("return '<'+name+'/>';");
        js.append("};"); // end if
        js.append("if(typeof obj=='function'){");
        js.append("return '<'+name+'/>';");
        js.append("};"); // end if
        js.append("if(obj instanceof HTMLElement){");
        js.append("return '<'+name+'/>';");
        js.append("};"); // end if
        js.append("if(list==null){list=[];};");
        js.append("var xml='';");
        js.append("if(Array.isArray(obj)){");
        js.append("for(const i in obj){");
        js.append("xml+=%s(name,obj[i],list);", JsGlobalModule.getQualifiedId(ToXmlFunction.class));
        js.append("};"); // end for
        js.append("return xml;");
        js.append("};"); // end if
        js.append("xml='<'+name+'>';");
        js.append("if(typeof obj=='number'){");
        js.append("xml+=obj;");
        js.append("}"); // end if
        js.append("else if(typeof obj=='string'){");
        js.append("xml+=obj.toString();");
        js.append("}"); // end if
        js.append("else if(typeof obj=='boolean'){");
        js.append("xml+=obj.toString();");
        js.append("}"); // end if
        js.append("else if(%s(obj)){", JsGlobalModule.getQualifiedId(IsObjectFunction.class));
        js.append("if(list!=null&&list.includes(obj)){");
        js.throwError("repeating object reference", "obj");
        js.append("};");
        js.append("list.push(obj);");
        js.append("for(const f in obj){");
        js.append("xml+=%s(f,obj[f],list);", JsGlobalModule.getQualifiedId(ToXmlFunction.class));
        js.append("};"); // end for
        js.append("};"); // end if
        js.append("xml+='</'+name+'>';");
        js.append("return xml;");
        js.append("}"); // end function
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(IsObjectFunction.class);
        dependencies.add(ToXmlFunction.class);
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("name", JsType.STRING));
        parameters.add(JsParameter.getInstance("obj", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("list", JsType.ARRAY));
    }
}
