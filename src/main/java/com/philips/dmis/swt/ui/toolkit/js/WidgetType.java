package com.philips.dmis.swt.ui.toolkit.js;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class WidgetType {
    private static final Map<String, WidgetType> VALUES = new LinkedHashMap<>();

    public static final WidgetType BUTTON = new WidgetType("BUTTON", "b", "button", false, false, "button");
    public static final WidgetType IMAGE_BUTTON = new WidgetType("IMAGE_BUTTON", "ib", "image-button", false, false, "input");
    public static final WidgetType IMAGE = new WidgetType("IMAGE", "i", "image", false, false, "img");
    public static final WidgetType CHECK = new WidgetType("CHECK", "c", "checkbox", false, false, "input");
    public static final WidgetType HEADING = new WidgetType("HEADING", "h", "heading", false, false, "h");
    public static final WidgetType LABEL = new WidgetType("LABEL", "l", "label", false, false, "label");
    public static final WidgetType CAPTION = new WidgetType("CAPTION", "ca", "caption", false, false, "div");
    public static final WidgetType LINK = new WidgetType("LINK", "a", "link", false, false, "a");
    public static final WidgetType LIST_CONTAINER = new WidgetType("LIST_CONTAINER", "u", "list", true, false, "ul");
    public static final WidgetType LIST = new WidgetType("LIST", "ul", "list", false, false, "ul");
    public static final WidgetType SELECT = new WidgetType("SELECT", "s", "select", false, false, "select");
    public static final WidgetType MULTIPLE_CHOICE = new WidgetType("MULTIPLE_CHOICE", "mc", "multiple-choice", false, false, "ul");
    public static final WidgetType SINGLE_CHOICE = new WidgetType("SINGLE_CHOICE", "sc", "single-choice", false, false, "ul");
    public static final WidgetType PAGE = new WidgetType("PAGE", "v", "page", true, false, "div");
    public static final WidgetType PANEL = new WidgetType("PANEL", "x", "panel", true, false, "div");
    public static final WidgetType GRID = new WidgetType("GRID", "g", "grid", true, false, "div");
    public static final WidgetType SINGLE_ROW = new WidgetType("SINGLE_ROW", "sr", "single-row", true, false, "div");
    public static final WidgetType PARAGRAPH = new WidgetType("PARAGRAPH", "p", "paragraph", false, false, "p");
    public static final WidgetType PREFORMATTED = new WidgetType("PREFORMATTED", "pf", "preformatted", false, false, "pre");
    public static final WidgetType TEXT = new WidgetType("TEXT", "f", "textbox", false, false, "input");
    public static final WidgetType PASSWORD = new WidgetType("PASSWORD", "pw", "password", false, false, "input");
    public static final WidgetType DATE = new WidgetType("DATE", "d", "datebox", false, false, "input");
    public static final WidgetType TIME = new WidgetType("TIME", "t", "timebox", false, false, "input");
    public static final WidgetType DATETIME = new WidgetType("DATETIME", "dt", "datetimebox", false, false, "input");
    public static final WidgetType WEEK = new WidgetType("WEEK", "w", "weekbox", false, false, "input");
    public static final WidgetType MONTH = new WidgetType("MONTH", "m", "monthbox", false, false, "input");
    public static final WidgetType EMAIL = new WidgetType("EMAIL", "e", "emailbox", false, false, "input");
    public static final WidgetType TELEPHONE = new WidgetType("TELEPHONE", "tel", "telephonebox", false, false, "input");
    public static final WidgetType URL = new WidgetType("URL", "url", "urlbox", false, false, "input");
    public static final WidgetType NUMBER = new WidgetType("NUMBER", "num", "numberbox", false, false, "input");
    public static final WidgetType COLOR = new WidgetType("COLOR", "col", "colorbox", false, false, "input");
    public static final WidgetType RANGE = new WidgetType("RANGE", "r", "rangewidget", false, false, "input");
    public static final WidgetType SEARCH = new WidgetType("SEARCH", "search", "searchbox", false, false, "input");
    public static final WidgetType FILE = new WidgetType("FILE", "fi", "file", false, true, "input");
    public static final WidgetType FORM = new WidgetType("FORM", "fr", "form", true, true, "form");
    public static final WidgetType SECTION = new WidgetType("SECTION", "se", "section", true, false, "section");
    public static final WidgetType TABLE = new WidgetType("TABLE", "tbl", "table", true, false, "table");
    public static final WidgetType TABLE_HEADER = new WidgetType("TABLE_HEADER", "th", "table-header", false, false, "thead");
    public static final WidgetType TABLE_FOOTER = new WidgetType("TABLE_FOOTER", "tf", "table-footer", false, false, "tfoot");
    public static final WidgetType TABLE_BODY = new WidgetType("TABLE_BODY", "tb", "table-body", false, false, "tbody");
    public static final WidgetType TABLE_CAPTION = new WidgetType("TABLE_CAPTION", "tc", "table-caption", true, false, "caption");
    public static final WidgetType FRAME = new WidgetType("FRAME", "fr", "frame", false, false, "iframe");
    public static final WidgetType DATA_LIST = new WidgetType("DATA_LIST", "dl", "datalist", false, true, "datalist");
    public static final WidgetType METER = new WidgetType("METER", "me", "meter", false, false, "meter");
    public static final WidgetType PROGRESS = new WidgetType("PROGRESS", "pr", "progress", false, false, "progress");
    public static final WidgetType QUERY_SERVICE = new WidgetType("QUERY_SERVICE", "qs", "", false, true, "div");
    public static final WidgetType UPDATE_SERVICE = new WidgetType("UPDATE_SERVICE", "us", "", false, true, "div");
    public static final WidgetType CALCULATED = new WidgetType("CALCULATED", "cval", "", false, true, "div");
    public static final WidgetType STATICDATA = new WidgetType("STATICDATA", "staticdata", "", false, true, "div");
    public static final WidgetType DATA = new WidgetType("DATA", "data", "", false, true, "div");
    public static final WidgetType DATA_PROXY = new WidgetType("DATA_PROXY", "prx", "", false, true, "div");
    public static final WidgetType CODE = new WidgetType("CODE", "code", "", false, true, "div");
    public static final WidgetType STYLE = new WidgetType("STYLE", "style", "", false, true, "style");
    public static final WidgetType TIMER = new WidgetType("TIMER", "tmr", "", false, true, "div");
    public static final WidgetType KEYSHORTCUT = new WidgetType("KEYSHORTCUT", "ksc", "", false, true, "div");


    public static List<WidgetType> values() {
        return new ArrayList(VALUES.values());
    }

    private final String name;
    private final String shortName;
    private final String className;
    private final boolean container;
    private final boolean hidden;
    private final String htmlTag;

    /**
     * Creates a new widget type.
     *
     * @param name      A unique name that identifies this widget type
     * @param shortName an abbreviation that also uniquely identifies this type.
     * @param className the CSS class name.
     * @param container true if the widget type is a container.
     * @param hidden    true if the widget is never visible to the user.
     * @param htmlTag   the HTML element to use.
     */
    public WidgetType(String name, String shortName, String className, boolean container, boolean hidden, String htmlTag) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("invalid name: '" + name + "'");
        }
        if (shortName == null || shortName.isEmpty()) {
            throw new IllegalArgumentException("invalid shortName: '" + shortName + "'");
        }
        if (VALUES.containsKey(shortName)) {
            throw new IllegalArgumentException("duplicate widget type: " + name);
        }

        this.name = name;
        this.shortName = shortName;
        this.className = className;
        this.container = container;
        this.hidden = hidden;
        this.htmlTag = htmlTag;

        VALUES.put(name, this);
    }

    public String name() {
        return name;
    }

    public String getShortName() {
        return shortName;
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


    public boolean isOneOf(WidgetType... values) {
        for (WidgetType widgetType : values) {
            if (this == widgetType) {
                return true;
            }
        }
        return false;
    }

    public boolean isNot(WidgetType... values) {
        return !isOneOf(values);
    }
}
