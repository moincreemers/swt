package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlPreformatted extends DataBoundWidget<HtmlPreformatted, ValueDataSourceUsage> implements HasText {
    public HtmlPreformatted(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.PREFORMATTED);
    }

    public HtmlPreformatted() {
        this(DEFAULT_VALUE_TEXT);
    }

    public HtmlPreformatted(String text) {
        this(TextFormatType.TEXT, text);
    }

    public HtmlPreformatted(TextFormatType textFormatType) {
        this(textFormatType, DEFAULT_VALUE_TEXT);
    }

    public HtmlPreformatted(TextFormatType textFormatType, String text) {
        super(WidgetType.PREFORMATTED);
        setTextFormat(textFormatType);
        setText(text);
    }

    public HtmlPreformatted addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.STRING;
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
