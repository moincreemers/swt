package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.EventHandler;
import com.philips.dmis.swt.ui.toolkit.events.InitEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.js.state.JsStateModule;
import com.philips.dmis.swt.ui.toolkit.utils.JavaNameDetector;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.*;
import java.util.logging.Logger;

@PageXmlElement({"appearance", "visible", "backgroundColor", "layoutType"})
public abstract class Widget implements HasId, Validatable, HasClassNames, HasStaticHtmlImplementations {
    private static final Logger LOG = Logger.getLogger(Widget.class.getName());

    public static boolean isPageClassName(String className) {
        return className != null && className.startsWith("page-w");
    }

    public static String getPageClassName(String pageId) {
        return "page-" + pageId;
    }

    private final JsStateModule jsStateModule;
    final String id;
    final String srcName;
    final WidgetType widgetType;
    Set<WidgetAppearance> appearance = new HashSet<>();
    final List<Widget> siblings = new ArrayList<>();
    ContainerWidget<?> parent;
    final List<EventHandler> eventHandlers = new ArrayList<>();
    boolean validated;
    final List<String> classNames = new ArrayList<>();
    boolean visible = true;
    final List<HasStaticHTML> staticHtmlImplementations = new ArrayList<>();
    String backgroundColor = "";
    LayoutType layoutType = LayoutType.AUTO;

    public Widget(WidgetType widgetType) {
        this(null, widgetType);
    }

    public Widget(WidgetConfigurator widgetConfigurator, WidgetType widgetType) {
        if (widgetConfigurator == null) {
            widgetConfigurator = new DefaultWidgetConfigurator(getClass());
        }
        id = widgetConfigurator.getId();
        this.widgetType = widgetType;
        srcName = JavaNameDetector.detectDeclaredName(getClass());
        // IMPORTANT: this line must be here in the constructor
        this.jsStateModule = new JsStateModule(this);
    }

    public Widget asWidget() {
        return this;
    }

    public final WidgetType getWidgetType() {
        return widgetType;
    }

    @Override
    public final String getId() {
        return id;
    }

    public final String getParentId() {
        if (parent == null) {
            return "";
        }
        return getParent().getId();
    }

    public final String getPageId() {
        if (widgetType == WidgetType.PAGE) {
            return getId();
        }
        return getParent().getPageId();
    }

    boolean isParentSet() {
        return parent != null;
    }

    public ContainerWidget<?> getParent() {
        return parent;
    }

    void setParent(ContainerWidget<?> parent) {
        this.parent = parent;
    }

    void addSiblings(Widget... widgets) {
        if (widgets == null) {
            return;
        }
        for (Widget widget : widgets) {
            if (widget != null) {
                siblings.add(widget);
            }
        }
    }

    List<Widget> getSiblings() {
        return siblings;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        if (backgroundColor == null) {
            backgroundColor = "";
        }
        this.backgroundColor = backgroundColor;
    }

    public Set<WidgetAppearance> getAppearance() {
        return appearance;
    }

    public Widget setAppearance(WidgetAppearance... appearance) {
        if (appearance == null || appearance.length == 0 || (appearance.length == 1 && appearance[0] == WidgetAppearance.DEFAULT)) {
            for (WidgetAppearance a : this.appearance) {
                removeClassName(a.className);
            }
            return this;
        }
        for (WidgetAppearance a : appearance) {
            this.appearance.add(a);
            addClassName(a.className);
        }
        return this;
    }

    @Override
    public List<String> getClassNames() {
        return classNames;
    }

    @Override
    public void addClassName(String className) {
        if (className == null) {
            throw new IllegalArgumentException("missing className");
        }
        if (className.isEmpty()) {
            return;
        }
        if (classNames.contains(className)) {
            return;
        }
        classNames.add(className);
    }

    @Override
    public void removeClassName(String className) {
        if (className == null) {
            throw new IllegalArgumentException("missing className");
        }
        classNames.remove(className);
    }

    public List<EventHandler> getEventHandlers() {
        return eventHandlers;
    }

    public String getHtmlTag(String defaultHtmlTag) {
        return defaultHtmlTag;
    }

    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (!classNames.isEmpty()) {
            htmlAttributes.put("class", String.join(" ", classNames));
        }
        if (!visible) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("display", "none");
        }
        if (!backgroundColor.isEmpty()) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("background-color", backgroundColor);
        }
        for (HasStaticHTML hasStaticHTML : staticHtmlImplementations) {
            hasStaticHTML.getHtmlAttributes(htmlAttributes);
        }
    }

    @Override
    public void registerStaticHtmlImplementation(HasStaticHTML hasStaticHTML) {
        staticHtmlImplementations.add(hasStaticHTML);
    }

    @Override
    public List<HasStaticHTML> getStaticHtmlImplementations() {
        return staticHtmlImplementations;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        for (HasStaticHTML hasStaticHTML : staticHtmlImplementations) {
            hasStaticHTML.renderStaticInnerHtml(toolkit, html);
        }
    }

    public void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException {
        jsStateModule.renderJs(toolkit, js);
    }

    public void pack() throws Exception {
        for (String className : new ArrayList<>(classNames)) {
            if (isPageClassName(className)) {
                removeClassName(className);
            }
        }
        if (parent != null && !(this instanceof Page)) {
            addClassName(getPageClassName(getPageId()));
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {
        if (validated) {
            return;
        }
        validated = true;
        logPreValidateStatement(toolkit);
        if (widgetType != WidgetType.PAGE && parent == null) {
            LOG.severe("Widget not added to view: " + id + " (" + widgetType.name() + ")");
            throw new WidgetConfigurationException("widget not added to view: " + id + " (" + widgetType.name() + ")");
        }
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.validate(toolkit);
        }
        for (HasStaticHTML hasStaticHTML : staticHtmlImplementations) {
            hasStaticHTML.validate(toolkit);
        }
    }

    void logPreValidateStatement(Toolkit toolkit) {
        if (widgetType == WidgetType.PAGE) {
            LOG.info("validate " + widgetType.name() + ": " + id);
        } else {
            String pageClassName = "";
            Optional<Page> page = toolkit.getPages().stream().filter(p -> p.getId().equals(getPageId())).findAny();
            if (page.isPresent()) {
                pageClassName = page.get().getClass().getSimpleName();
            }
            // note: it is important that this log statement contains enough information to identify the exact location
            // of a widget or statement when a validation error is raised
            LOG.info("validate " + widgetType.name() + ": " + id + " <" + srcName + "> on page: " + pageClassName);
        }
    }

    public Widget onInit(InitEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public LayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(LayoutType layoutType) {
        this.layoutType = layoutType;
    }
}
