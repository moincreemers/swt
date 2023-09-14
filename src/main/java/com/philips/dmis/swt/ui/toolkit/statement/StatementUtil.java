package com.philips.dmis.swt.ui.toolkit.statement;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.Collection;

public final class StatementUtil {
    private StatementUtil() {
    }

    public static void assertWidget(String parameterName, Object widget) throws WidgetConfigurationException {
        if (widget == null) {
            throw new WidgetConfigurationException("missing required widget: " + parameterName);
        }
    }

    public static void assertWidgetType(String parameterName, Object widget, Class<?> type) throws WidgetConfigurationException {
        assertWidget(parameterName, widget);
        if (type.isAssignableFrom(widget.getClass())) {
            throw new WidgetConfigurationException("expected widget: " + parameterName + " of type: " + type.getSimpleName());
        }
    }

    public static void assertDataAdapter(String parameterName, DataAdapter dataAdapter) throws WidgetConfigurationException {
        if (dataAdapter == null) {
            throw new WidgetConfigurationException("missing required data adapter: " + parameterName);
        }
    }

    public static void assertRequiredAndReturnType(String statementName, Statement statement, JsType jsType) throws WidgetConfigurationException {
        assertRequired(statementName, statement);
        assertReturnType(statementName, statement, jsType);
    }

    public static void assertSize(String statementName, Collection<?> statements, int minimum) throws WidgetConfigurationException {
        if (statements.size() < minimum) {
            throw new WidgetConfigurationException("expected at least one: " + statementName);
        }
    }

    public static void assertRequired(String parameterName, Statement statement) throws WidgetConfigurationException {
        if (statement == null) {
            throw new WidgetConfigurationException("missing required statement: " + parameterName);
        }
    }

    public static void assertReturnType(String parameterName, Statement statement, JsType jsType) throws WidgetConfigurationException {
        if (statement.getType() != jsType) {
            throw new WidgetConfigurationException("expected statement " + parameterName + " to return a " + jsType.name());
        }
    }
}
