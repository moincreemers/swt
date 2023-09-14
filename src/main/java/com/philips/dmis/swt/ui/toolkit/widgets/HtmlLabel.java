package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlLabel extends DataBoundWidget<HtmlLabel, ValueDataSourceUsage> implements HasFor, HasIcon, HasText {
    public HtmlLabel(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.LABEL);
    }

    public HtmlLabel(Widget widget) {
        this(widget, DEFAULT_VALUE_TEXT);
    }

    public HtmlLabel(String text) {
        this(null, DEFAULT_VALUE_ICON, text);
    }

    public HtmlLabel() {
        this(null, DEFAULT_VALUE_ICON, DEFAULT_VALUE_TEXT);
    }

    public HtmlLabel(IconsWidget iconsWidget, String icon) {
        this(null, iconsWidget, icon, DEFAULT_VALUE_TEXT);
    }

    public HtmlLabel(IconsWidget iconsWidget, String icon, String text) {
        this(null, iconsWidget, icon, text);
    }

    public HtmlLabel(Widget widget, IconsWidget iconsWidget, String icon, String text) {
        this(widget, text);
        setIconsWidget(iconsWidget);
        setIcon(icon);
    }

    public HtmlLabel(Widget forWidget, String text) {
        super(WidgetType.LABEL);
        addSiblings(forWidget);
        setFor(forWidget);
        setText(text);
    }

    public HtmlLabel addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.STRING;
    }

    // FOR

    private final ForImpl forImpl = new ForImpl(this);

    @Override
    public HasFor getForImpl() {
        return forImpl;
    }

    @Override
    public String getFor() {
        return forImpl.getFor();
    }

    @Override
    public void setFor(String _for) {
        forImpl.setFor(_for);
    }

    @Override
    public void setFor(Widget widget) {
        forImpl.setFor(widget);
    }

    // ICON

    private HasIcon iconImpl = new IconImpl(this);

    @Override
    public HasIcon getIconImpl() {
        return iconImpl.getIconImpl();
    }

    @Override
    public IconsWidget getIconsWidget() {
        return iconImpl.getIconsWidget();
    }

    @Override
    public void setIconsWidget(IconsWidget iconsWidget) {
        iconImpl.setIconsWidget(iconsWidget);
    }

    @Override
    public String getIcon() {
        return iconImpl.getIcon();
    }

    @Override
    public void setIcon(String icon) {
        iconImpl.setIcon(icon);
    }

    @Override
    public boolean isIcon() {
        return iconImpl.isIcon();
    }


    // TEXT

    private HasText textImpl = new TextImpl(this);

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasText getTextImpl() {
        return textImpl.getTextImpl();
    }

    @Override
    public TextFormatType getTextFormat() {
        return textImpl.getTextFormat();
    }

    @Override
    public void setTextFormat(TextFormatType textFormatType) {
        textImpl.setTextFormat(textFormatType);
    }

    @Override
    public String getText() {
        return textImpl.getText();
    }

    @Override
    public void setText(String text) {
        textImpl.setText(text);
    }

    @Override
    public boolean isText() {
        return textImpl.isText();
    }
}
