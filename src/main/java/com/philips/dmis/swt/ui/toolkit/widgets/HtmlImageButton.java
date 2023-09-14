package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"imageSize", "imageType", "enabled"})
public class HtmlImageButton extends ValueWidget<HtmlImageButton, String, ValueDataSourceUsage> implements
        HasType, IsClickable<HtmlImageButton>, HasAction, HasEncType, HasMethod,
        HasNoValidate, HasTarget, HasForm {

    public static HtmlImageButton openPage(String src, Class<? extends Page> pageClass) {
        return new HtmlImageButton(src).onClick(new ClickEventHandler(M.OpenPage(pageClass)));
    }

    public static HtmlImageButton openPage(ImageType buttonType, String src, Class<? extends Page> pageClass) {
        return new HtmlImageButton(buttonType, src).onClick(new ClickEventHandler(M.OpenPage(pageClass)));
    }

    public static HtmlImageButton openPage(String src, Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new HtmlImageButton(src).onClick(new ClickEventHandler(M.OpenPage(pageClass, valueStatement)));
    }

    public static HtmlImageButton openPage(ImageType buttonType, String src, Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new HtmlImageButton(buttonType, src).onClick(new ClickEventHandler(M.OpenPage(pageClass, valueStatement)));
    }

    public static HtmlImageButton closePage(String src) {
        return new HtmlImageButton(src).onClick(new ClickEventHandler(M.ClosePage()));
    }

    public static HtmlImageButton closePage(ImageType buttonType, String src) {
        return new HtmlImageButton(buttonType, src).onClick(new ClickEventHandler(M.ClosePage()));
    }

    public static HtmlImageButton closePage(String src, ValueStatement valueStatement) {
        return new HtmlImageButton(src).onClick(new ClickEventHandler(M.ClosePage(valueStatement)));
    }

    public static HtmlImageButton closePage(ImageType buttonType, String src, ValueStatement valueStatement) {
        return new HtmlImageButton(buttonType, src).onClick(new ClickEventHandler(M.ClosePage(valueStatement)));
    }

    private ImageSize imageSize = ImageSize.DEFAULT;
    private ImageType imageType = ImageType.DEFAULT;
    private boolean enabled = true;

    public HtmlImageButton(WidgetConfigurator widgetConfigurator, String name) {
        super(widgetConfigurator, name, WidgetType.IMAGE_BUTTON, JsType.STRING);
        setType(TypeType.IMAGE);
    }

    public HtmlImageButton() {
        this(NAMELESS, ImageType.DEFAULT, DEFAULT_VALUE_STRING, true);
    }

    public HtmlImageButton(String src) {
        this(NAMELESS, ImageType.DEFAULT, src, true);
    }

    public HtmlImageButton(String name, String src) {
        this(name, ImageType.DEFAULT, src, true);
    }

    public HtmlImageButton(String src, boolean enabled) {
        this(NAMELESS, ImageType.DEFAULT, src, enabled);
    }

    public HtmlImageButton(String name, String src, boolean enabled) {
        this(name, ImageType.DEFAULT, src, enabled);
    }

    public HtmlImageButton(ImageType imageType, String src) {
        this(NAMELESS, imageType, src, true);
    }

    public HtmlImageButton(String name, ImageType imageType, String src) {
        this(name, imageType, src, true);
    }

    public HtmlImageButton(String name, ImageType imageType, String src, boolean enabled) {
        super(name, WidgetType.IMAGE_BUTTON, JsType.STRING);
        setType(TypeType.IMAGE);
        setImageType(imageType);
        setValue(src);
        setEnabled(enabled);
    }

    public HtmlImageButton addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        if (imageType == null) {
            return;
        }
        if (this.imageType != ImageType.DEFAULT) {
            removeClassName(this.imageType.className);
        }
        this.imageType = imageType;
        addClassName(imageType.className);
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public HtmlImageButton setImageSize(ImageSize imageSize) {
        if (imageSize == null) {
            return this;
        }
        if (!this.imageSize.className.isEmpty()) {
            removeClassName(this.imageSize.className);
        }
        this.imageSize = imageSize;
        addClassName(imageSize.className);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (!enabled) {
            htmlAttributes.put("disabled", "disabled");
        }
        if (!imageSize.width.isEmpty()) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("width", imageSize.width);
        }
        if (!imageSize.height.isEmpty()) {
            StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
            style.add("height", imageSize.height);
        }
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

    // CLICKABLE

    private final ClickableImpl<HtmlImageButton> clickableImpl = new ClickableImpl<>(this);

    @Override
    public IsClickable<HtmlImageButton> getClickableImpl() {
        return clickableImpl;
    }

    @Override
    public HtmlImageButton onClick(ClickEventHandler eventHandler) {
        return clickableImpl.onClick(eventHandler);
    }

    // ACTION

    private final ActionImpl actionImpl = new ActionImpl(this);

    @Override
    public HasAction getActionImpl() {
        return actionImpl;
    }

    @Override
    public String getAction() {
        return actionImpl.getAction();
    }

    @Override
    public void setAction(String action) {
        actionImpl.setAction(action);
    }

    // ENCTYPE

    private final EncTypeImpl encTypeImpl = new EncTypeImpl(this);

    @Override
    public HasEncType getEncTypeImpl() {
        return encTypeImpl;
    }

    @Override
    public String getEncType() {
        return encTypeImpl.getEncType();
    }

    @Override
    public void setEncType(String encType) {
        encTypeImpl.setEncType(encType);
    }

    @Override
    public void setEncType(MimeType mimeType) {
        encTypeImpl.setEncType(mimeType);
    }

    // METHOD

    private final MethodImpl methodImpl = new MethodImpl(this);

    @Override
    public HasMethod getMethodImpl() {
        return methodImpl;
    }

    @Override
    public String getMethod() {
        return methodImpl.getMethod();
    }

    @Override
    public void setMethod(String method) {
        methodImpl.setMethod(method);
    }

    @Override
    public void setMethod(FormMethodType formMethod) {
        methodImpl.setMethod(formMethod);
    }

    // NOVALIDATE

    private final NoValidateImpl noValidateImpl = new NoValidateImpl(this);

    @Override
    public HasNoValidate getNoValidateImpl() {
        return noValidateImpl;
    }

    @Override
    public Boolean getNoValidate() {
        return noValidateImpl.getNoValidate();
    }

    @Override
    public void setNoValidate(Boolean noValidate) {
        noValidateImpl.setNoValidate(noValidate);
    }

    // TARGET

    private final TargetImpl targetImpl = new TargetImpl(this);

    @Override
    public HasTarget getTargetImpl() {
        return targetImpl;
    }

    @Override
    public String getTarget() {
        return targetImpl.getTarget();
    }

    @Override
    public void setTarget(String target) {
        targetImpl.setTarget(target);
    }

    @Override
    public void setTarget(TargetType target) {
        targetImpl.setTarget(target);
    }

    // FORM

    private final FormImpl formImpl = new FormImpl(this);

    @Override
    public HasForm getFormImpl() {
        return formImpl;
    }

    @Override
    public String getForm() {
        return formImpl.getForm();
    }

    @Override
    public void setForm(String form) {
        formImpl.setForm(form);
    }

    @Override
    public void setForm(HtmlForm htmlForm) {
        formImpl.setForm(htmlForm);
    }
}
