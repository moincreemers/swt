package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.widgets.ContentType;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.List;

public class SendHttpRequestFunction implements JsFunction {
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
    public void renderJs(Toolkit toolkit, JsWriter js) {
        js.append("(method,contentType,responseType,url,headers,obj,success,failure,args)=>{"); // function

        js.append("var xhr=new XMLHttpRequest();");
        js.append("xhr.withCredentials=false;");
        js.append("var requestUrl=url;");
        js.append("xhr.responseType=responseType;");
        js.append("xhr.onreadystatechange=function(){"); // function
        js.append("if(xhr.readyState===XMLHttpRequest.DONE){"); // if
        js.append("success(%s(requestUrl,xhr,args));",
                // note: this is a common class we use to transfer the response
                //  we want to prevent xhr to be shared and we need a response that can be serialized
                JsGlobalModule.getQualifiedId(GetXhrResponseFunction.class));
        js.append("}"); // end if
        js.append("};"); // end function

        js.append("switch(method){"); // switch METHOD

        js.append("case 'GET':");
        js.append("case 'HEAD':");
        js.append("requestUrl=url+'?'+%s(obj);",
                JsGlobalModule.getId(ToQueryStringFunction.class));
        js.append("xhr.open(method,requestUrl,true);");
        js.append("%s(xhr,headers);",
                JsGlobalModule.getId(SetXhrRequestHeadersFunction.class));
        js.append("try{");
        js.append("xhr.send();");
        js.append("}catch(ex){");
        js.append("failure(xhr);");
        js.append("};");
        js.append("break;");

        js.append("case 'POST':");
        js.append("xhr.open('POST',url,true);");
        js.append("%s(xhr,headers);",
                JsGlobalModule.getId(SetXhrRequestHeadersFunction.class));
        js.append("xhr.setRequestHeader('Content-Type',contentType);");

        js.append("switch(contentType){"); // switch CONTENT-TYPE
        js.append("case '%s':", ContentType.NONE.getEncoding());
        js.append("case '%s':", ContentType.JSON.getEncoding());
        js.append("xhr.send(JSON.stringify(obj));");
        js.append("break;");

        js.append("case '%s':", ContentType.FORM_URLENCODED.getEncoding());
        js.append("var params=%s(obj);", JsGlobalModule.getQualifiedId(ToQueryStringFunction.class));
        js.debug("console.log('params',params);");
        js.append("xhr.send(params);");
        js.append("break;");

        js.append("case '%s':", ContentType.TEXT.getEncoding());
        js.append("var params=%s(obj);", JsGlobalModule.getQualifiedId(ToQueryStringFunction.class));
        js.append("xhr.send(params);");
        js.append("break;");

        js.append("case '%s':", ContentType.XML.getEncoding());
        // note: document element hardcoded to 'request'
        js.append("xhr.send(%s('request',obj));", JsGlobalModule.getQualifiedId(ToXmlFunction.class));
        js.append("break;");

        js.append("case '%s':", ContentType.FORM_MULTIPART.getEncoding());
        // note: assumption that browser supports XHR2
        js.append("if(!(obj instanceof FormData)){");
        js.throwError("expected FormData", "obj");
        js.append("};");
        js.append("xhr.send(obj);");
        js.append("break;");

        js.append("case '%s':", ContentType.OCTET_STREAM.getEncoding());
        js.append("xhr.send(new Blob(obj));");
        js.append("break;");

        js.append("};"); // end switch CONTENT-TYPE
        js.append("break;");

// todo:
//        js.append("case 'PUT':");
//        js.append("case 'PATCH':");
//        js.append("case 'DELETE':");
//        js.append("case 'OPTIONS':");
//        js.append("case 'TRACE':");

        js.append("default:");
        js.throwError("method not supported", "method");
        js.append("break;");

        js.append("};"); // end switch METHOD

        js.append("}"); // end function
    }

    @Override
    public void getParameters(List<JsParameter> parameters) {
        parameters.add(JsParameter.getInstance("method", JsType.STRING));
        parameters.add(JsParameter.getInstance("contentType", JsType.STRING));
        parameters.add(JsParameter.getInstance("responseType", JsType.STRING));
        parameters.add(JsParameter.getInstance("url", JsType.STRING));
        parameters.add(JsParameter.getInstance("obj", JsType.OBJECT));
        parameters.add(JsParameter.getInstance("success", JsType.FUNCTION));
        parameters.add(JsParameter.getInstance("failure", JsType.FUNCTION));
        parameters.add(JsParameter.getInstance("args", JsType.OBJECT));
    }

    @Override
    public void getDependencies(List<Class<? extends JsMember>> dependencies) {
        dependencies.add(ToQueryStringFunction.class);
        dependencies.add(ToStringSafeFunction.class);
        dependencies.add(ToXmlFunction.class);
    }
}
