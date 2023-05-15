package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.ExamplesPage;
import com.philips.dmis.swt.ui.toolkit.widgets.Page;

public class Constants {
    public static final String MAIN_MODULE_NAME = "Singular";
    public static final String ENTRY_POINT = "main";

    public static final String CSS_DEFAULT = "default.css";
    public static final String CSS_DARK = "dark.css";

    public static final boolean DEBUG = true;
    public static final JsLogLevel JS_LOG_LEVEL = JsLogLevel.INFO;

    // Uncomment this to get the Viewer demo pages:
    // public static final Class<? extends Page> DEBUG_ROOT_PAGE = LoginPage.class;

    // Uncomment this to get the general SWT demo pages:
    public static final Class<? extends Page> DEBUG_ROOT_PAGE = ExamplesPage.class;


    public static boolean isLogLevel(JsLogLevel logLevel) {
        return logLevel == JS_LOG_LEVEL;
    }
}
