package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class HtmlText extends DataBoundWidget<HtmlText, ValueDataSourceUsage> implements HasText {
    public HtmlText(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.TEXT);
    }

    public HtmlText() {
        this(DEFAULT_VALUE_TEXT);
    }

    public HtmlText(String text) {
        super(WidgetType.TEXT);
        setText(text);
    }

    public HtmlText addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
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
