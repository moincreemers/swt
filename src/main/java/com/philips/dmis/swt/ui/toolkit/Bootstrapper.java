package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.js.HasJS;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;

public class Bootstrapper implements HasJS {
    private final String module;
    private final String method;

    public Bootstrapper(String module, String method) {
        this.module = module;
        this.method = method;
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        js.append("document.addEventListener('readystatechange',(e)=>{");
        js.debug("console.log('readyState',document.readyState);");
        js.append("if(document.readyState=='complete'){");
        js.debug("console.log('main', document.location.href);");
        js.append("%s.%s();", module, method);
        js.append("};");
        js.append("});");
    }
}
