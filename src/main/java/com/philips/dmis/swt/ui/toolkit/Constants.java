package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.demo.MainDemoPage;
import com.philips.dmis.swt.ui.forview.LoginPage;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;

public class Constants {
    public static final String MAIN_MODULE_NAME = "Singular";
    public static final String ENTRY_POINT = "main";
    public static final String EXTERNAL_MODULE_EVENT = "onExtModuleEvent";
    public static final String EXTERNAL_MODULE_EVENTHANDLER = "handleEvent";

    public static final String CSS_DEFAULT = "default.css";
    public static final String CSS_DARK = "dark.css";

    public static final boolean DEBUG = true;
    public static final JsLogLevel JS_LOG_LEVEL = JsLogLevel.TRACE;
    public static final JsLogTraceFilter JS_LOG_FILTER = new JsLogTraceFilter()
            .enable(true)
            .suppressModule("global", "controller", "widget", "data");
    public static final Class<? extends Page> DEMO = MainDemoPage.class;

    public static boolean isDemo(Class<? extends Page> page) {
        return (page == DEMO);
    }

    public static boolean isLogLevel(JsLogLevel logLevel) {
        return logLevel.getLevel() >= JS_LOG_LEVEL.getLevel();
    }
}
