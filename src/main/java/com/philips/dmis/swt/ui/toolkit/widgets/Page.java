package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.GlobalEvents;
import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.events.*;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.js.pages.PageRefreshType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Page extends Panel {
    private static final Logger LOG = Logger.getLogger(Page.class.getName());
    private final boolean isDefault;
    private PageRefreshType pageRefresh = PageRefreshType.SHOW;
    private final ViewType viewType;
    private ViewPosition viewPosition = ViewPosition.DEFAULT;

    public static String getInnerDivId(String pageId) {
        return pageId + "_inner";
    }

    public Page() throws Exception {
        this(ViewType.DEFAULT, false);
    }

    public Page(String name) throws Exception {
        this(name, ViewType.DEFAULT, false);
    }

    public Page(boolean isDefault) throws Exception {
        this(ViewType.DEFAULT, isDefault);
    }

    public Page(String name, boolean isDefault) throws Exception {
        this(name, ViewType.DEFAULT, isDefault);
    }

    public Page(ViewType viewType) throws Exception {
        this(viewType, false);
    }

    public Page(String name, ViewType viewType) throws Exception {
        this(name, viewType, false);
    }

    public Page(ViewType viewType, boolean isDefault) throws Exception {
        this(viewType, ViewPosition.DEFAULT, isDefault);
    }

    public Page(String name, ViewType viewType, boolean isDefault) throws Exception {
        this(name, viewType, ViewPosition.DEFAULT, isDefault);
    }

    public Page(ViewType viewType, ViewPosition viewPosition) throws Exception {
        this(viewType, viewPosition, false);
    }

    public Page(String name, ViewType viewType, ViewPosition viewPosition) throws Exception {
        this(name, viewType, viewPosition, false);
    }

    public Page(ViewType viewType, ViewPosition viewPosition, boolean isDefault) throws Exception {
        this("", viewType, viewPosition, isDefault);
    }

    public Page(String name, ViewType viewType, ViewPosition viewPosition, boolean isDefault) throws Exception {
        super(name, WidgetType.PAGE);
        this.viewType = viewType;
        this.isDefault = isDefault;
        setViewPosition(viewPosition);
        addClassName(viewType.classNameOuter);
        build();
        pack();
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        if (viewType == ViewType.DIALOG) {
            if (widget.widgetType == WidgetType.PANEL) {
                Panel panel = (Panel) widget;
                if (panel.getPanelType() == PanelType.PAGE_HEADER || panel.getPanelType() == PanelType.PAGE_FOOTER) {
                    return false;
                }
            }
        }
        return true;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public ViewPosition getViewPosition() {
        return viewPosition;
    }

    public Page setViewPosition(ViewPosition viewPosition) throws WidgetConfigurationException {
        if (!viewPosition.viewTypes.isEmpty() && !viewPosition.viewTypes.contains(viewType)) {
            throw new WidgetConfigurationException(
                    String.format("view-position %s not applicable to view-type %s", viewPosition.name(),
                            viewType.getClass().getSimpleName()));
        }
        if (this.viewPosition != null) {
            removeClassName(this.viewPosition.classNameOuter);
        }
        this.viewPosition = viewPosition;
        addClassName(viewPosition.classNameOuter);
        return this;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public PageRefreshType getPageRefresh() {
        return pageRefresh;
    }

    public void setPageRefresh(PageRefreshType pageRefresh) {
        this.pageRefresh = pageRefresh;
    }

    protected abstract void build() throws Exception;

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        super.renderStaticInnerHtml(toolkit, html);
        List<String> classNames = new ArrayList();
        classNames.add("tk-page-inner");
        if (!getViewType().getClassNameInner().isEmpty()) {
            classNames.add(getViewType().getClassNameInner());
        }
        if (!getViewPosition().getClassNameInner().isEmpty()) {
            classNames.add(getViewPosition().getClassNameInner());
        }

        String tabIndex = (viewType == ViewType.DIALOG || viewType == ViewType.SIDEBAR_DIALOG) ? "tabindex=\"0\"" : "";
        html.append(String.format("<div id=\"%s\" class=\"%s\"%s></div>",
                getInnerDivId(getId()), String.join(" ", classNames), tabIndex));
    }

    public Page onShow(ShowEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public Page onActivate(ActivateEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public Page onDeactivate(DeactivateEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public Page onHide(HideEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public Page onClickOutsideDialog(ClickOutsideDialogEventHandler eventHandler) {
        eventHandlers.add(eventHandler);
        return this;
    }

    public Page onBeforePrint(BeforePrintEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onBeforePrint(eventHandler);
        return this;
    }

    public Page onAfterPrint(AfterPrintEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onAfterPrint(eventHandler);
        return this;
    }

    public Page onBeforeUnload(BeforeUnloadEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onBeforeUnload(eventHandler);
        return this;
    }

    public Page onUnload(UnloadEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onUnload(eventHandler);
        return this;
    }

    public Page onKeyPress(KeyPressEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onKeyPress(eventHandler);
        return this;
    }

    public Page onKeyDown(KeyDownEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onKeyDown(eventHandler);
        return this;
    }

    public Page onKeyUp(KeyUpEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onKeyUp(eventHandler);
        return this;
    }

    public Page onColorSchemeChange(ColorSchemeChangeEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onColorSchemeChange(eventHandler);
        return this;
    }

    public Page onBlur(BlurEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onBlur(eventHandler);
        return this;
    }

    public Page onFocus(FocusEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onFocus(eventHandler);
        return this;
    }

    public Page onError(ErrorEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onError(eventHandler);
        return this;
    }

    public Page onLanguageChange(LanguageChangeEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onLanguageChange(eventHandler);
        return this;
    }

    public Page onMessage(MessageEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onMessage(eventHandler);
        return this;
    }

    public Page onOffline(OfflineEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onOffline(eventHandler);
        return this;
    }

    public Page onOnline(OnlineEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onOnline(eventHandler);
        return this;
    }

    public Page onUndo(UndoEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onUndo(eventHandler);
        return this;
    }

    public Page onRedo(RedoEventHandler eventHandler) {
        eventHandler.setPageId(getId());
        GlobalEvents.onRedo(eventHandler);
        return this;
    }
}
