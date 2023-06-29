package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.dto.URLAppearanceType;
import com.philips.dmis.swt.ui.toolkit.dto.URLFormat;
import com.philips.dmis.swt.ui.toolkit.js.JsParameter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.global.*;
import com.philips.dmis.swt.ui.toolkit.js.state.HttpHeadersVariable;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.js.state.PromisesVariable;
import com.philips.dmis.swt.ui.toolkit.js.widget.JsWidgetModule;
import com.philips.dmis.swt.ui.toolkit.js.widget.ProcessResourceResponseFunction;
import com.philips.dmis.swt.ui.toolkit.js.widget.SendHttpRequestFunction;
import com.philips.dmis.swt.ui.toolkit.reflect.DtoUtil;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.*;
import org.springframework.http.HttpMethod;

import java.util.*;

public class DownloadStatement extends MethodStatement implements HasAbstractURL {
    private HttpMethod httpMethod;
    private ContentType contentType;
    private ResponseType responseType;
    private AuthenticationType authenticationType;
    private final Map<String, List<ValueStatement>> httpHeaders = new LinkedHashMap<>();

    public DownloadStatement() {
        this(HttpMethod.GET, ContentType.NONE, ResponseType.BLOB, AuthenticationType.NONE);
    }

    public DownloadStatement(HttpMethod httpMethod, ContentType contentType, ResponseType responseType, AuthenticationType authenticationType) {
        this.httpMethod = httpMethod;
        this.contentType = contentType;
        this.responseType = responseType;
        this.authenticationType = authenticationType;
    }

    @Override
    public JsType getType() {
        return JsType.STRING;
    }

    @Override
    public List<JsParameter> getParameters() {
        return NO_PARAMETERS;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    @Override
    public Map<String, List<ValueStatement>> getHttpHeaders() {
        return httpHeaders;
    }

    @Override
    public List<ValueStatement> getHttpHeader(String name) {
        return httpHeaders.get(name);
    }

    @Override
    public void setHttpHeader(String name, ValueStatement value) {
        httpHeaders.put(name, Arrays.asList(value));
    }

    @Override
    public void addHttpHeader(String name, ValueStatement value) {
        List<ValueStatement> headers = httpHeaders.computeIfAbsent(name, k -> new ArrayList<>());
        headers.add(value);
    }

    @Override
    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    @Override
    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    @Override
    public void renderJs(Toolkit toolkit, Widget widget, JsWriter js) throws JsRenderException {
        js.append("(serviceResponse,nameOrSource,obj,value)=>{");
        js.append("if(value==undefined||value==null||value==''){return null;};");

        // note: downloads a resource (most likely an image)
        //  assumption is that the value contains the URL of the resource
        //  the main reason for using XHR is the ability to add an access token
        //  in case of an image, we can simply set the @src attribute of the image tag, the idea is that the URL formatter
        //  generates the IMG tag with a temporary image (or no image at all) and sets the @id attribute so the code will
        //  be able to update the IMG tag when the XHR request completes.

        // Strategy:
        //  Before anything else, we change the data type of the view field to XHR_IMAGE
        //
        //  1. we generate a unique id
        //  2. in widget, we set a callback object (e.g., like a Promise) using the id
        //  3. we use an asynchronous request to download the resource
        //  4. when the request is complete, we update the promise object
        //
        //  When the client of a promise object needs to retrieve the data, they can attach a callback to the
        //  promise. The callback is called as soon as the request completes or immediately when the callback is
        //  attached to the promise and the request has completed before that.

        js.append("const viewField=%s(serviceResponse,nameOrSource);",
                JsGlobalModule.getQualifiedId(GetViewFieldFunction.class)
        );
        URLFormat urlFormat = new URLFormat();
        js.append("if(viewField!=null){");
        js.append("var format=Object.assign(%s,viewField.format);", DtoUtil.valueOf(urlFormat));
        js.append("format.appearance='%s';", URLAppearanceType.XHR_IMAGE.name());
        js.append("viewField.format=format;");
        js.append("viewField.formatType='%s';", urlFormat.getFormatType().name());
        js.append("};");

        js.append("const uid=String(Date.now().toString(32)+Math.random().toString(16)).replace(/\\./g,'');");
        js.append("%s[uid]=%s();",
                JsStateModule.getQualifiedId(widget, PromisesVariable.class),
                JsGlobalModule.getQualifiedId(CreatePromiseFunction.class));

        // method,contentType,url,headers,obj,success,failure,arguments
        js.append("var headers=");
        HttpHeadersVariable.writeHttpHeaders(toolkit, widget, this, js);
        js.append(";");
        js.append("var arguments={uid:uid};");
        js.append("var url=%s(value);", JsGlobalModule.getQualifiedId(GetURLFunction.class));
        js.append("var obj=%s(%s(value));",
                JsGlobalModule.getQualifiedId(SearchToObjectFunction.class),
                JsGlobalModule.getQualifiedId(GetSearchString.class));

        js.debug("console.log('download resource',uid,obj);");

        // todo: failure function is undefined
        js.append("%s('%s','%s','%s','%s',url,headers,'%s',obj,%s,()=>{console.log('resource request failed');},arguments);",
                JsWidgetModule.getQualifiedId(SendHttpRequestFunction.class),
                widget.getId(),
                httpMethod.name(),
                contentType.getEncoding(),
                responseType.getValue(),
                authenticationType.name(),
                JsWidgetModule.getQualifiedId(ProcessResourceResponseFunction.class)
        );

        js.append("return uid;");
        js.append("}");
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        for (List<ValueStatement> headers : httpHeaders.values()) {
            for (ValueStatement header : headers) {
                header.validate(toolkit);
            }
        }
    }

    @Override
    public void getReferences(List<Statement> statements) {
        for (List<ValueStatement> headers : httpHeaders.values()) {
            statements.addAll(headers);
        }
    }
}
