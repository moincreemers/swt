package com.philips.dmis.swt.ui.toolkit.js;

import com.philips.dmis.swt.ui.toolkit.Constants;
import com.philips.dmis.swt.ui.toolkit.JsLogLevel;
import com.philips.dmis.swt.ui.toolkit.JsLogTraceFilter;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.html.HasConstantStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class JsWriter {
    private static final Logger LOG = Logger.getLogger(JsWriter.class.getName());
    private static final String USE_STRICT = "\"use strict\";";
    private static int counter;
    private final StringBuffer js = new StringBuffer();
    private final HasConstantStorage constantStorage;
    private final boolean strict;
    private final JsLogTraceFilter filter;

    public JsWriter() {
        this(null);
    }

    public JsWriter(HasConstantStorage constantStorage) {
        this(constantStorage, false, null);
    }

    public JsWriter(HasConstantStorage constantStorage, boolean strict, JsLogTraceFilter filter) {
        this.constantStorage = constantStorage;
        this.strict = strict;
        this.filter = filter;
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

    public void trace(String format, Object... args) {
        if (Constants.isLogLevel(JsLogLevel.TRACE)) {
            append("if(traceEnabled!==undefined&&traceEnabled){");
            append(format, args);
            append("};");
        }
    }

    public void trace(JsFunction function) {
        if (!Constants.isLogLevel(JsLogLevel.TRACE)) {
            return;
        }
        List<JsParameter> parameters = new ArrayList<>();
        function.getParameters(parameters);
        List<String> functionArgs = parameters.stream().map(JsParameter::getName).toList();
        trace(
                getModuleName(function.getClass()),
                getClassName(function.getClass()),
                function.getType().name(),
                functionArgs,
                false);
    }

    public void trace(DataAdapter dataAdapter) {
        if (!Constants.isLogLevel(JsLogLevel.TRACE)) {
            return;
        }
        trace(
                getModuleName(dataAdapter.getClass()),
                getClassName(dataAdapter.getClass()),
                null,
                Arrays.asList("serviceResponse", "unmodifiedResponse"),
                true);
    }

    private void trace(String moduleName, String functionName, String returnType, List<String> functionArgs, boolean disableIdFilter) {
        if (returnType == null) {
            returnType = "";
        } else if (!returnType.isEmpty()) {
            returnType += " ";
        }

        append("const traceEnabled=%s;", filter.accept(moduleName, functionName) ? "true" : "false");
        append("const traceIdFilter=");
        if (!disableIdFilter && filter.hasIdFilter()) {
            append("(");
            filter.renderIdFilter(this);
            append(");");
        } else {
            append("true;");
        }
        append("const traceTime=window.performance.now();");
        append("const traceArgs={");
        int i = 0;
        for (String functionArg : functionArgs) {
            if (i > 0) {
                append(",");
            }
            append("%s:%s", functionArg, functionArg);
            i++;
        }
        append("};");
        append("if(traceEnabled){");
        append("console.log('['+traceTime+']','%s%s.%s',traceArgs);\n", returnType, moduleName, functionName);
        append("};");
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

    public void asArray(Object... values) {
        append("[");
        int i = 0;
        for (Object value : values) {
            if (i > 0) {
                append(",");
            }
            if (value == null) {
                append("null");
            } else if (value instanceof String) {
                append("'%s'", value);
            } else {
                append("%s", value);
            }
            i++;
        }
        append("]");
    }

    public void ifInArray(String var, Object... values) {
        append("if(");
        asArray(values);
        append(".includes(%s)){", var);
    }

    public void ifNotInArray(String var, Object... values) {
        append("if(!");
        asArray(values);
        append(".includes(%s)){", var);
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

    public static String getModuleName(Class<?> cls) {
        String name = cls.getName();
        String[] path = name.split("\\.");
        if (path.length < 2) {
            return "?";
        }
        return path[path.length - 2];
    }

    public static String getClassName(Class<?> cls) {
        return cls.getSimpleName();
    }
}
