package com.philips.dmis.swt.ui.toolkit.html;

import com.philips.dmis.swt.ui.toolkit.js.JsWriter;

public interface HasConstantStorage {
    String HTML_ATTR_TOKEN = "ctoken";
    String CSS_CLASS_GLOBAL = "constant-token";

    String JS_INIT_FUNCTION = "csInit";
    // a window function that allows direct retrieval of a constant value
    String JS_GET_FUNCTION = "gc";


    HasConstantStorage getConstantStorageImpl();

    String registerConstant(String value);

    void renderConstantHtml(StringBuffer html);

    void renderConstantJs(JsWriter js);
}
