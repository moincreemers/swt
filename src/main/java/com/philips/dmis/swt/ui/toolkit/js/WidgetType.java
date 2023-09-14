package com.philips.dmis.swt.ui.toolkit.js;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class WidgetType {
    private static final Map<String, WidgetType> VALUES = new LinkedHashMap<>();

    public static final WidgetType HR = new WidgetType("HR", "hr", false, false, "hr");
    public static final WidgetType BR = new WidgetType("BR", "br", false, false, "br");
    public static final WidgetType SPINNER = new WidgetType("SPINNER", "spinner-container", false, false, "div");
    public static final WidgetType BUTTON = new WidgetType("BUTTON", "button", false, false, "button");
    public static final WidgetType IMAGE_BUTTON = new WidgetType("IMAGE_BUTTON", "image-button", false, false, "input");
    public static final WidgetType IMAGE = new WidgetType("IMAGE", "image", false, false, "img");
    public static final WidgetType CHECK = new WidgetType("CHECK", "checkbox", false, false, "input");
    public static final WidgetType HEADING = new WidgetType("HEADING", "heading", false, false, "h");
    public static final WidgetType LABEL = new WidgetType("LABEL", "label", false, false, "label");
    public static final WidgetType CAPTION = new WidgetType("CAPTION", "caption", false, false, "div");
    public static final WidgetType LINK = new WidgetType("LINK", "link", false, false, "a");
    public static final WidgetType LIST_CONTAINER = new WidgetType("LIST_CONTAINER", "list", true, false, "ul");
    public static final WidgetType LIST = new WidgetType("LIST", "list", false, false, "ul");
    public static final WidgetType SELECT = new WidgetType("SELECT", "select", false, false, "select");
    public static final WidgetType MULTIPLE_CHOICE = new WidgetType("MULTIPLE_CHOICE", "multiple-choice", false, false, "ul");
    public static final WidgetType SINGLE_CHOICE = new WidgetType("SINGLE_CHOICE", "single-choice", false, false, "ul");
    public static final WidgetType PAGE = new WidgetType("PAGE", "page", true, false, "div");
    public static final WidgetType PANEL = new WidgetType("PANEL", "panel", true, false, "div");
    public static final WidgetType DIALOG = new WidgetType("DIALOG", "dialog", true, false, "dialog");
    public static final WidgetType GRID = new WidgetType("GRID", "grid", true, false, "div");
    public static final WidgetType SMART_GRID = new WidgetType("SMART_GRID", "smartgrid", true, false, "div");
    public static final WidgetType SINGLE_ROW = new WidgetType("SINGLE_ROW", "single-row", true, false, "div");
    public static final WidgetType OVERLAY = new WidgetType("OVERLAY", "overlay", true, false, "div");
    public static final WidgetType PARAGRAPH = new WidgetType("PARAGRAPH", "paragraph", false, false, "p");
    public static final WidgetType TEXT = new WidgetType("TEXT", "text", false, false, "span");
    public static final WidgetType PREFORMATTED = new WidgetType("PREFORMATTED", "preformatted", false, false, "pre");
    public static final WidgetType TEXT_FIELD = new WidgetType("TEXT_FIELD", "textbox", false, false, "input");
    public static final WidgetType TEXT_AREA = new WidgetType("TEXT_AREA", "textarea", false, false, "textarea");
    public static final WidgetType PASSWORD = new WidgetType("PASSWORD", "password", false, false, "input");
    public static final WidgetType DATE = new WidgetType("DATE", "datebox", false, false, "input");
    public static final WidgetType TIME = new WidgetType("TIME", "timebox", false, false, "input");
    public static final WidgetType DATETIME = new WidgetType("DATETIME", "datetimebox", false, false, "input");
    public static final WidgetType WEEK = new WidgetType("WEEK", "weekbox", false, false, "input");
    public static final WidgetType MONTH = new WidgetType("MONTH", "monthbox", false, false, "input");
    public static final WidgetType EMAIL = new WidgetType("EMAIL", "emailbox", false, false, "input");
    public static final WidgetType TELEPHONE = new WidgetType("TELEPHONE", "telephonebox", false, false, "input");
    public static final WidgetType URL = new WidgetType("URL", "urlbox", false, false, "input");
    public static final WidgetType NUMBER = new WidgetType("NUMBER", "numberbox", false, false, "input");
    public static final WidgetType COLOR = new WidgetType("COLOR", "colorbox", false, false, "input");
    public static final WidgetType RANGE = new WidgetType("RANGE", "rangewidget", false, false, "input");
    public static final WidgetType SEARCH = new WidgetType("SEARCH", "searchbox", false, false, "input");
    public static final WidgetType FILE = new WidgetType("FILE", "file", false, true, "input");
    public static final WidgetType FORM = new WidgetType("FORM", "form", true, true, "form");
    public static final WidgetType SECTION = new WidgetType("SECTION", "section", true, false, "section");
    public static final WidgetType TABLE = new WidgetType("TABLE", "table", true, false, "table");
    public static final WidgetType TABLE_HEADER = new WidgetType("TABLE_HEADER", "table-header", false, false, "thead");
    public static final WidgetType TABLE_FOOTER = new WidgetType("TABLE_FOOTER", "table-footer", false, false, "tfoot");
    public static final WidgetType TABLE_BODY = new WidgetType("TABLE_BODY", "table-body", false, false, "tbody");
    public static final WidgetType TABLE_CAPTION = new WidgetType("TABLE_CAPTION", "table-caption", true, false, "caption");
    public static final WidgetType FRAME = new WidgetType("FRAME", "frame", false, false, "iframe");
    public static final WidgetType DATA_LIST = new WidgetType("DATA_LIST", "datalist", false, true, "datalist");
    public static final WidgetType METER = new WidgetType("METER", "meter", false, false, "meter");
    public static final WidgetType PROGRESS = new WidgetType("PROGRESS", "progress", false, false, "progress");
    public static final WidgetType QUERY_SERVICE = new WidgetType("QUERY_SERVICE", "", false, true, "div");
    public static final WidgetType UPDATE_SERVICE = new WidgetType("UPDATE_SERVICE", "", false, true, "div");
    public static final WidgetType CALCULATED = new WidgetType("CALCULATED", "", false, true, "div");
    public static final WidgetType STATICDATA = new WidgetType("STATICDATA", "", false, true, "div");
    public static final WidgetType DATA = new WidgetType("DATA", "", false, true, "div");
    public static final WidgetType DATA_PROXY = new WidgetType("DATA_PROXY", "", false, true, "div");
    public static final WidgetType DATA_TEMPLATE = new WidgetType("DATA_TEMPLATE", "", false, true, "div");
    public static final WidgetType DATA_CONSUMER = new WidgetType("DATA_CONSUMER", "", false, true, "div");
    public static final WidgetType CODE = new WidgetType("CODE", "", false, true, "div");
    public static final WidgetType STYLE = new WidgetType("STYLE", "", false, true, "style");
    public static final WidgetType TIMER = new WidgetType("TIMER", "", false, true, "div");
    public static final WidgetType KEYSHORTCUT = new WidgetType("KEYSHORTCUT", "", false, true, "div");


    public static List<WidgetType> values() {
        return new ArrayList<>(VALUES.values());
    }

    private final String name;
    private final String className;
    private final boolean container;
    private final boolean hidden;
    private final String htmlTag;

    /**
     * Creates a new widget type.
     *
     * @param name      A unique name that identifies this widget type
     * @param className the CSS class name.
     * @param container true if the widget type is a container.
     * @param hidden    true if the widget is never visible to the user.
     * @param htmlTag   the HTML element to use.
     */
    public WidgetType(String name, String className, boolean container, boolean hidden, String htmlTag) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("invalid name: '" + name + "'");
        }
        if (VALUES.containsKey(name)) {
            throw new IllegalArgumentException("duplicate widget type: " + name);
        }

        this.name = name;
        this.className = className;
        this.container = container;
        this.hidden = hidden;
        this.htmlTag = htmlTag;

        VALUES.put(name, this);
    }

    public String name() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public boolean isContainer() {
        return container;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getHtmlTag() {
        return htmlTag;
    }
}
