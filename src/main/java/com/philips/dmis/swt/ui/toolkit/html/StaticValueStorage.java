package com.philips.dmis.swt.ui.toolkit.html;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.utils.DistinctMap;
import org.springframework.security.core.parameters.P;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class StaticValueStorage implements HasConstantStorage {
    public static final String HTML_ID = StaticValueStorage.class.getSimpleName();
    private static final UUID NULL_TOKEN = new UUID(0, 0);
    private final String defaultLanguage;
    private Map<String, String> constants = new DistinctMap();

    public StaticValueStorage(String defaultLanguage) {
        this.defaultLanguage = getISOLanguageCode(defaultLanguage);
    }

    @Override
    public HasConstantStorage getConstantStorageImpl() {
        return this;
    }

    @Override
    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    @Override
    public String registerConstant(String value) {
        String token = createToken(value);
        String key = getDefaultLanguage() + "-" + createToken(value);
        if (!constants.containsKey(key)) {
            constants.put(key, value);
        }
        return token;
    }

    @Override
    public String registerTranslation(String valueInDefaultLanguage, String language, String value) {
        String tokenDefaultLanguage = createToken(valueInDefaultLanguage);
        String key = getISOLanguageCode(language) + "-" + tokenDefaultLanguage;
        if (!constants.containsKey(key)) {
            constants.put(key, value);
        }
        return tokenDefaultLanguage;
    }

    @Override
    public void renderConstantHtml(StringBuffer html) {
        System.out.println("render " + constants.size() + " tokens");
        html.append(String.format("\n<div id='%s' defaultLanguage='%s' style='display:none'>", HTML_ID, getDefaultLanguage()));
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
        js.append("const defaultLang=document.getElementById('%s').getAttribute('defaultLanguage');", HTML_ID);
        js.append("const lang=(navigator.language||navigator.userLanguage||defaultLang).split('-')[0].toLowerCase();");
        js.append("var constantElement=document.getElementById(lang + '-' + token);");
        js.append("if(constantElement==null){constantElement=document.getElementById(defaultLang + '-' + token);};");
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

    @Override
    public String generateLanguageFileTemplate() {
        String defaultLang = getDefaultLanguage() + "-";
        StringBuilder s = new StringBuilder();
        for (String token : this.constants.keySet()) {
            if (token.startsWith(defaultLang)) {
                String valueUsingDefaultLanguage = this.constants.get(token);
                s.append(String.format("%s=%s\n", token, valueUsingDefaultLanguage));
            }
        }
        return s.toString();
    }

    @Override
    public void addLanguageResourceFile(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            constants.put(key, value);
        }
        inputStream.close();
    }

    public String createToken(String constantUsingDefaultLanguage) {
        if (constantUsingDefaultLanguage == null || constantUsingDefaultLanguage.isEmpty()) {
            return NULL_TOKEN.toString();
        }
        return UUID.nameUUIDFromBytes(constantUsingDefaultLanguage.getBytes(StandardCharsets.UTF_16)).toString();
    }

    private static String getISOLanguageCode(String language) {
        if (language == null || language.isEmpty()) {
            throw new IllegalArgumentException("invalid language: \"" + language + "\"");
        }
        return language.split("-")[0].toLowerCase();
    }

}
