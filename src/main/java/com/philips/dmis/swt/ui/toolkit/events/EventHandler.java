package com.philips.dmis.swt.ui.toolkit.events;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.widgets.WidgetConfigurationException;

import java.util.ArrayList;
import java.util.List;

public abstract class EventHandler {
    String pageId = "";
    String widgetId = "";
    final String name;
    final List<Statement> statements = new ArrayList<>();

    public EventHandler(String name, Statement... statements) {
        this("", "", name, statements);
    }

    public EventHandler(String pageId, String widgetId, String name, Statement... statements) {
        this.pageId = pageId;
        this.widgetId = widgetId;
        this.name = name;
        for (Statement statement : statements) {
            this.statements.add(statement);
        }
    }

    public String getPageId() {
        return pageId == null ? "" : pageId;
    }

    public void setPageId(String pageId) {
        if (this.pageId.isEmpty()) {
            this.pageId = pageId;
        }
    }

    public String getWidgetId() {
        return widgetId == null ? "" : widgetId;
    }

    public void setWidgetId(String widgetId) {
        if (this.widgetId.isEmpty()) {
            this.widgetId = widgetId;
        }
    }

    public String getName() {
        return name;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        for (Statement statement : statements) {
            statement.validate(toolkit);
        }
    }
}
