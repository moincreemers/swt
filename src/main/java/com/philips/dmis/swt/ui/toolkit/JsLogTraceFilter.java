package com.philips.dmis.swt.ui.toolkit;

import com.philips.dmis.swt.ui.toolkit.js.JsWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JsLogTraceFilter {
    record FunctionInfo(String moduleName, String functionName) {
    }

    private final Set<String> moduleIndex = new HashSet<>();
    private final Set<FunctionInfo> functionIndex = new HashSet<>();
    private final Set<String> idFilter = new HashSet<>();
    private boolean enable = false;

    public boolean accept(String moduleName, String functionName) {
        if (!enable) {
            return true;
        }
        if (functionIndex.contains(new FunctionInfo(moduleName, functionName))) {
            return true;
        }
        return moduleName == null || moduleName.isEmpty() || !moduleIndex.contains(moduleName);
    }

    public JsLogTraceFilter suppressModule(String... moduleNames) {
        for (String moduleName : moduleNames) {
            if (moduleName != null && !moduleName.isEmpty()) {
                moduleIndex.add(moduleName);
            }
        }
        return this;
    }

    public JsLogTraceFilter filterFunction(Class<?>... functions) {
        for (Class<?> function : functions) {
            filterFunction(JsWriter.getModuleName(function), JsWriter.getClassName(function));
        }
        return this;
    }

    public JsLogTraceFilter filterFunction(String moduleName, String... functionNames) {
        for (String functionName : functionNames) {
            if (moduleName != null && !moduleName.isEmpty() && functionName != null && !functionName.isEmpty()) {
                functionIndex.add(new FunctionInfo(moduleName, functionName));
            }
        }
        return this;
    }

    public JsLogTraceFilter filterId(String... widgetIds) {
        idFilter.addAll(Arrays.asList(widgetIds));
        return this;
    }

    public boolean hasIdFilter() {
        return !idFilter.isEmpty();
    }

    public void renderIdFilter(JsWriter js) {
        js.append("(typeof id==='undefined')||[");
        int i = 0;
        for (String id : idFilter) {
            if (i > 0) {
                js.append(",");
            }
            js.append("'%s'", id);
            i++;
        }
        js.append("].includes(id)");
    }

    public JsLogTraceFilter enable(boolean enable) {
        this.enable = enable;
        return this;
    }
}
