package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class FormatCodeFunction implements JsFunction {
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
        js.append("(s)=>{");
        js.append("if(s==null||s.length==0){return s;};");
        js.append("var a='';");
        js.append("var k=0;");
        js.append("const ind='  ';");
        js.append("var nl=false;");
        js.append("for(const i in s){");
        js.append("var c=s.charCodeAt(i);");
        js.append("var b=s.substring(i,i+1);");
        js.append("switch(c){"); // switch

        js.append("case 10:case 13:"); // lf, cr
        js.append("nl=true;");
        js.append("break;");

        js.append("case 32:"); // space
        js.append("if(a.length>0&&a.charAt(a.length-1)!=' '){a+=' ';};");
        js.append("break;");

        js.append("case 59:"); // semicolon
        js.append("a+=b;nl=true;");
        js.append("break;");

        js.append("case 91:"); // (
        js.append("if(a.length>0&&a.charAt(a.length-1)!=' '){a+=' ';};");
        js.append("a+=b;");
        js.append("break;");

        js.append("case 93:"); // )
        js.append("a+=b+' ';");
        js.append("break;");

        js.append("case 123:"); // {
        js.append("k++;nl=true;");
        js.append("break;");

        js.append("case 125:"); // }
        js.append("k--;nl=true;");
        js.append("break;");

        js.append("};"); // end switch
        js.append("if(nl){"); // if
        js.append("a+='\\n';");
        js.append("for(var l=0;l<k;l++){a+=ind;};"); // for
        js.append("nl=false;");
        js.append("};"); // end if
        js.append("};"); // end for

        js.append("return a;");
        js.append("}");
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("s", JsType.STRING));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
    }
}
