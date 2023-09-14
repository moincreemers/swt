package com.philips.dmis.swt.ui.toolkit.html;

import com.philips.dmis.swt.ui.toolkit.js.JsWriter;

import java.io.IOException;
import java.io.InputStream;

public interface HasConstantStorage {
    String HTML_ATTR_TOKEN = "ctoken";
    String CSS_CLASS_GLOBAL = "constant-token";

    String JS_INIT_FUNCTION = "csInit";
    // a window function that allows direct retrieval of a constant value
    String JS_GET_FUNCTION = "gc";


    HasConstantStorage getConstantStorageImpl();

    String getDefaultLanguage();

    String registerConstant(String value);

    String registerTranslation(String valueInDefaultLanguage, String language, String value);

    void renderConstantHtml(StringBuffer html);

    void renderConstantJs(JsWriter js);

    String generateLanguageFileTemplate();

    void addLanguageResource(InputStream inputStream) throws IOException;
}
