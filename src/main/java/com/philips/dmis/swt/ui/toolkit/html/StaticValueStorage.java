package com.philips.dmis.swt.ui.toolkit.html;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.utils.DistinctMap;

import java.util.Map;

public class StaticValueStorage implements HasConstantStorage {
    private Map<String, String> constants = new DistinctMap();

    @Override
    public HasConstantStorage getConstantStorageImpl() {
        return this;
    }

    @Override
    public String registerConstant(String value) {
        String token = "c" + constants.size();
        return constants.put(token, value);
    }

    @Override
    public void renderConstantHtml(StringBuffer html) {
        System.out.println("render " + constants.size() + " tokens");
        html.append("\n<div style='display:none'>");
        for (String token : constants.keySet()) {
            html.append(String.format("<div id='%s'>%s</div>\n",
                    token, constants.get(token)));
        }
        html.append("</div>");
    }

    @Override
    public void renderConstantJs(JsWriter js) {
        js.append("var %s=(token,defaultValue)=>{", HasConstantStorage.JS_GET_FUNCTION);
        js.append("if(token==null){return (defaultValue|'');};");
        js.append("var constantElement=document.getElementById(token);");
        js.append("if(constantElement==null){return (defaultValue|'');};");
        js.append("return constantElement.innerHTML;");
        js.append("};"); // end function

        js.append("var %s=(widgetId)=>{", HasConstantStorage.JS_INIT_FUNCTION);
        js.append("var constantNodes=document.getElementsByClassName(widgetId + ' %s');",
                HasConstantStorage.CSS_CLASS_GLOBAL);
        js.append("for(var cx=0;cx<constantNodes.length;cx++){");
        js.debug("console.log('constant nodes', cx, constantNodes[cx]);");
        js.append("var token=constantNodes[cx].getAttribute('%s');",
                HasConstantStorage.HTML_ATTR_TOKEN);
        js.append("constantNodes[cx].setHTML(%s.%s(token,'unexpected error: constant not found'),{sanitizer:new Sanitizer()});",
                Constants.MAIN_MODULE_NAME, HasConstantStorage.JS_GET_FUNCTION);
        js.append("};"); // end for
        js.append("};"); // end function
    }
}
