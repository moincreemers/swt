package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.Map;

public class HtmlFile extends ValueWidget<HtmlFile, Object, ValueDataSourceUsage> implements
        HasType, HasRequired, HasMultiple, HasAccept, HasCapture {
    public HtmlFile() {
        this(NAMELESS);
    }

    public HtmlFile(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.FILE, JsType.OBJECT);
        setType(TypeType.FILE);
    }

    public HtmlFile(String name) {
        super(name, WidgetType.FILE, JsType.OBJECT);
        setType(TypeType.FILE);
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        style.add("display", "none");
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

    // MULTIPLE

    private final MultipleImpl multipleImpl = new MultipleImpl(this);

    @Override
    public HasMultiple getMultipleImpl() {
        return multipleImpl;
    }

    @Override
    public boolean getMultiple() {
        return multipleImpl.getMultiple();
    }

    @Override
    public void setMultiple(boolean multiple) {
        multipleImpl.setMultiple(multiple);
    }

    // ACCEPT

    private final AcceptImpl acceptImpl = new AcceptImpl(this);

    @Override
    public HasAccept getAcceptImpl() {
        return acceptImpl;
    }

    @Override
    public java.util.List<String> getAccept() {
        return acceptImpl.getAccept();
    }

    @Override
    public void setAccept(java.util.List<String> capture) {
        acceptImpl.setAccept(capture);
    }

    @Override
    public void addAccept(String mimeType) {
        acceptImpl.addAccept(mimeType);
    }

    // CAPTURE

    private final CaptureImpl captureImpl = new CaptureImpl(this);

    @Override
    public HasCapture getCaptureImpl() {
        return captureImpl;
    }

    @Override
    public CaptureType getCapture() {
        return captureImpl.getCapture();
    }

    @Override
    public void setCapture(CaptureType capture) {
        captureImpl.setCapture(capture);
    }
}
