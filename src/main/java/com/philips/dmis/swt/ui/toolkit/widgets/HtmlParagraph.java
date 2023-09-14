package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlParagraph extends DataBoundWidget<HtmlParagraph, ValueDataSourceUsage> implements HasText {
    public HtmlParagraph(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.PARAGRAPH);
    }

    public HtmlParagraph() {
        this(DEFAULT_VALUE_TEXT);
    }

    public HtmlParagraph(String text) {
        super(WidgetType.PARAGRAPH);
        setText(text);
    }

    public HtmlParagraph addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    // TEXT

    private final HasText textImpl = new TextImpl(this);

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

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.STRING;
    }
}
