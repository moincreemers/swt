package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlHeading extends DataBoundWidget<HtmlHeading,TextDataSourceUsage> implements HasIcon, HasText, HasNumberedText, IsFocusable {
    public HtmlHeading(String text) {
        this(text, 1, false);
    }

    public HtmlHeading(IconsWidget iconsWidget, String icon, String text) {
        this(iconsWidget, icon, text, 1);
    }

    public HtmlHeading(IconsWidget iconsWidget, String icon, String text, int level) {
        this(iconsWidget, icon, text, level, false);
    }

    public HtmlHeading(IconsWidget iconsWidget, String icon, String text, int level, boolean numbered) {
        this(text, level, numbered);
        setIconsWidget(iconsWidget);
        setIcon(icon);
    }

    public HtmlHeading(String text, int level) {
        this(text, level, false);
    }

    public HtmlHeading(String text, int level, boolean numbered) {
        super(WidgetType.HEADING);
        setText(text);
        setLevel(level);
        setNumberingEnabled(numbered);
    }

    public HtmlHeading addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(TextDataSourceUsage.TEXT, dataSourceSupplier, dataAdapters);
        return this;
    }

    @Override
    public String getHtmlTag(String defaultHtmlTag) {
        return "h" + numberedTextImpl.getLevel();
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


    // NUMBERED TEXT

    private NumberedTextImpl numberedTextImpl = new NumberedTextImpl(this);

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public HasText getTextImpl() {
        return numberedTextImpl.getTextImpl();
    }

    @Override
    public TextFormatType getTextFormat() {
        return numberedTextImpl.getTextFormat();
    }

    @Override
    public void setTextFormat(TextFormatType textFormatType) {
        numberedTextImpl.setTextFormat(textFormatType);
    }

    @Override
    public String getText() {
        return numberedTextImpl.getText();
    }

    @Override
    public void setText(String text) {
        numberedTextImpl.setText(text);
    }

    @Override
    public boolean isText() {
        return numberedTextImpl.isText();
    }

    @Override
    public int getLevel() {
        return numberedTextImpl.getLevel();
    }

    @Override
    public void setLevel(int level) {
        numberedTextImpl.setLevel(level);
    }

    @Override
    public boolean isNumberingEnabled() {
        return numberedTextImpl.isNumberingEnabled();
    }

    @Override
    public void setNumberingEnabled(boolean numbered) {
        numberedTextImpl.setNumberingEnabled(numbered);
    }

    // FOCUSABLE

    private final IsFocusable focusableImpl = new FocusableImpl(this);

    @Override
    public IsFocusable getFocusableImpl() {
        return focusableImpl;
    }

    @Override
    public boolean isFocusable() {
        return focusableImpl.isFocusable();
    }

    @Override
    public void setFocusable(boolean focusable) {
        focusableImpl.setFocusable(focusable);
    }
}
