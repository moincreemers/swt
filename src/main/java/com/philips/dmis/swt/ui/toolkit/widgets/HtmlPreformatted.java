package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlPreformatted extends DataBoundWidget<HtmlPreformatted> implements HasText {
    public HtmlPreformatted() {
        this("");
    }

    public HtmlPreformatted(String text) {
        this(TextFormatType.TEXT, text);
    }

    public HtmlPreformatted(TextFormatType textFormatType) {
        this(textFormatType, "");
    }

    public HtmlPreformatted(TextFormatType textFormatType, String text) {
        super(WidgetType.PREFORMATTED);
        setTextFormat(textFormatType);
        setText(text);
    }

    public HtmlPreformatted addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(DataSourceUsage.TEXT, dataSourceSupplier, dataAdapters);
        return this;
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
}
