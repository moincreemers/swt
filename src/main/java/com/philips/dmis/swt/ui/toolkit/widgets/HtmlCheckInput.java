package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class HtmlCheckInput extends ValueWidget<HtmlCheckInput, ValueDataSourceUsage> implements
        HasType, HasValue<HtmlCheckInput>, HasRequired {
    private CheckInputType checkInputType = CheckInputType.DEFAULT;
    private String buttonText;

    public HtmlCheckInput() {
        this(CheckInputType.DEFAULT, "");
    }

    public HtmlCheckInput(String name) {
        this(CheckInputType.DEFAULT, name);
    }

    public HtmlCheckInput(CheckInputType checkInputType) {
        this(checkInputType, "");
    }

    public HtmlCheckInput(CheckInputType checkInputType, String name) {
        this(checkInputType, name, "");
    }

    public HtmlCheckInput(CheckInputType checkInputType, String name, String buttonText) {
        super(name, WidgetType.CHECK);
        setType(TypeType.CHECK);
        setCheckInputType(checkInputType);
        setButtonText(buttonText);
    }

    public CheckInputType getCheckInputType() {
        return checkInputType;
    }

    public void setCheckInputType(CheckInputType checkInputType) {
        if (checkInputType == null) {
            return;
        }
        if (this.checkInputType != CheckInputType.DEFAULT) {
            removeClassName(this.checkInputType.className);
        }
        this.checkInputType = checkInputType;
        addClassName(checkInputType.className);
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public HtmlCheckInput addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        // note: if we set an alternate element ID here, the toolkit will not overwrite it
        htmlAttributes.put("id", getId() + "_button");
        htmlAttributes.put("for", getId());
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        super.renderStaticInnerHtml(toolkit, html);
        if (checkInputType == CheckInputType.BUTTON) {
            html.append(String.format("<input id=\"%s\" type=\"checkbox\" name=\"%s\">",
                    getId(), getName()));
            if (buttonText != null && !buttonText.isEmpty()) {
                html.append(buttonText);
            }
        }
    }

    @Override
    public String getHtmlTag(String defaultHtmlTag) {
        if (checkInputType == CheckInputType.BUTTON) {
            return "label";
        }
        return super.getHtmlTag(defaultHtmlTag);
    }

    // TYPE

    private final TypeImpl typeImpl = new TypeImpl(this);

    @Override
    public HasType getTypeImpl() {
        return typeImpl;
    }

    @Override
    public TypeType getType() {
        return typeImpl.getType();
    }

    @Override
    public void setType(TypeType type) {
        typeImpl.setType(type);
    }


    // REQUIRED

    private final RequiredImpl requiredImpl = new RequiredImpl(this);

    @Override
    public HasRequired getRequiredImpl() {
        return requiredImpl;
    }

    @Override
    public boolean getRequired() {
        return requiredImpl.getRequired();
    }

    @Override
    public void setRequired(boolean required) {
        requiredImpl.setRequired(required);
    }
}
