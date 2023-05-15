package com.philips.dmis.swt.ui.toolkit.js;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.JsLogLevel;
import com.philips.dmis.swt.ui.toolkit.html.HasConstantStorage;

import java.util.logging.Logger;

public class JsWriter {
    private static final Logger LOG = Logger.getLogger(JsWriter.class.getName());
    private static final String USE_STRICT = "\"use strict\";";
    private final StringBuffer js = new StringBuffer();
    private final HasConstantStorage constantStorage;
    private final boolean strict;

    public JsWriter() {
        this(null);
    }

    public JsWriter(HasConstantStorage constantStorage) {
        this(constantStorage, false);
    }

    public JsWriter(HasConstantStorage constantStorage, boolean strict) {
        this.constantStorage = constantStorage;
        this.strict = strict;
    }

    public void info(String format, Object... args) {
        if (Constants.isLogLevel(JsLogLevel.INFO)) {
            append(format, args);
            cr();
        }
    }

    public void warning(String format, Object... args) {
        if (Constants.isLogLevel(JsLogLevel.WARNING)) {
            append(format, args);
            cr();
        }
    }

    public void error(String format, Object... args) {
        if (Constants.isLogLevel(JsLogLevel.ERROR)) {
            append(format, args);
            cr();
        }
    }

    public void debug(String format, Object... args) {
        if (Constants.isLogLevel(JsLogLevel.DEBUG)) {
            append(format, args);
            cr();
        }
    }

    /**
     * Renders a 'throw Error' Javascript line.
     * Any jsArgs parameters are logged to the console before the error is thrown.
     *
     * @param message
     * @param jsArgs
     */
    public void throwError(String message, String... jsArgs) {
        if (constantStorage == null) {
            append("throw new Error('%s');", message);
            return;
        }
        String token = constantStorage.registerConstant(message);
        if (jsArgs != null && jsArgs.length != 0) {
            String args = String.join(",", jsArgs);
            append("console.log('error info (args: %s)', %s);", args, args);
        }
        append("throw new Error(%s.%s('%s'));",
                Constants.MAIN_MODULE_NAME,
                HasConstantStorage.JS_GET_FUNCTION,
                token);
        cr();
    }

    public void append(String format, Object... args) {
        if (format == null) {
            return;
        }
        String s = format(format, args);
        js.append(s);
        if (s.endsWith("{") || s.endsWith(";")) {
            cr();
        }
    }

    String format(String format, Object[] args) {
        String s = "";
        if (args == null || args.length == 0) {
            s = format;

        } else {
            s = String.format(format, args);
        }
        return s;
    }

    @Override
    public String toString() {
        return (strict ? USE_STRICT : "") + js.toString();
    }

    public boolean isEmpty() {
        return js.isEmpty();
    }

    public void cr() {
        if (Constants.DEBUG) {
            js.append("\n");
        }
    }

    public void comment(String text) {
        if (Constants.DEBUG) {
            append("\n\n// %s\n\n", text);
        }
    }
}
