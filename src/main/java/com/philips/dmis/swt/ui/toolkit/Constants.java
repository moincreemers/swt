package com.philips.dmis.swt.ui.toolkit;

public class Constants {
    public static final String MAIN_MODULE_NAME = "Singular";
    public static final String ENTRY_POINT = "main";

    public static final String CSS_DEFAULT = "default.css";
    public static final String CSS_DARK = "dark.css";

    public static final boolean DEBUG = true;
    public static final JsLogLevel JS_LOG_LEVEL = JsLogLevel.INFO;

    public static boolean isLogLevel(JsLogLevel logLevel) {
        return logLevel == JS_LOG_LEVEL;
    }
}
