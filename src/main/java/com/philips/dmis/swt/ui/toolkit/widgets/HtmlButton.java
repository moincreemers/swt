package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"enabled", "buttonType"})
public class HtmlButton extends DataBoundWidget<HtmlButton, ValueDataSourceUsage> implements
        HasType, IsClickable<HtmlButton>, HasIcon, HasText, HasAction, HasEncType, HasMethod,
        HasNoValidate, HasTarget, HasForm {
    public static HtmlButton openPage(String text, Class<? extends Page> pageClass) {
        return new HtmlButton(text).onClick(new ClickEventHandler(M.OpenPage(pageClass)));
    }

    public static HtmlButton openPage(ButtonType buttonType, String text, Class<? extends Page> pageClass) {
        return new HtmlButton(buttonType, text).onClick(new ClickEventHandler(M.OpenPage(pageClass)));
    }

    public static HtmlButton openPage(String text, Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new HtmlButton(text).onClick(new ClickEventHandler(M.OpenPage(pageClass, valueStatement)));
    }

    public static HtmlButton openPage(ButtonType buttonType, String text, Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new HtmlButton(buttonType, text).onClick(new ClickEventHandler(M.OpenPage(pageClass, valueStatement)));
    }

    public static HtmlButton closePage(String text) {
        return new HtmlButton(text).onClick(new ClickEventHandler(M.ClosePage()));
    }

    public static HtmlButton closePage(ButtonType buttonType, String text) {
        return new HtmlButton(buttonType, text).onClick(new ClickEventHandler(M.ClosePage()));
    }

    public static HtmlButton closePage(String text, ValueStatement valueStatement) {
        return new HtmlButton(text).onClick(new ClickEventHandler(M.ClosePage(valueStatement)));
    }

    public static HtmlButton closePage(ButtonType buttonType, String text, ValueStatement valueStatement) {
        return new HtmlButton(buttonType, text).onClick(new ClickEventHandler(M.ClosePage(valueStatement)));
    }

    private boolean enabled = true;
    private ButtonType buttonType = ButtonType.DEFAULT;

    public HtmlButton(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.BUTTON);
    }

    public HtmlButton() {
        this(ButtonType.DEFAULT, null, DEFAULT_VALUE_ICON, DEFAULT_VALUE_TEXT, true);
    }

    public HtmlButton(String text) {
        this(ButtonType.DEFAULT, null, DEFAULT_VALUE_ICON, text, true);
    }

    public HtmlButton(String text, boolean enabled) {
        this(ButtonType.DEFAULT, null, DEFAULT_VALUE_ICON, text, enabled);
    }

    public HtmlButton(ButtonType buttonType, String text) {
        this(buttonType, null, DEFAULT_VALUE_ICON, text, true);
    }

    public HtmlButton(IconsWidget iconsWidget, String icon) {
        this(ButtonType.DEFAULT, iconsWidget, icon, DEFAULT_VALUE_TEXT, true);
    }

    public HtmlButton(ButtonType buttonType, IconsWidget iconsWidget, String icon) {
        this(buttonType, iconsWidget, icon, DEFAULT_VALUE_TEXT, true);
    }

    public HtmlButton(IconsWidget iconsWidget, String icon, String text) {
        this(ButtonType.DEFAULT, iconsWidget, icon, text, true);
    }

    public HtmlButton(ButtonType buttonType, IconsWidget iconsWidget, String icon, String text) {
        this(buttonType, iconsWidget, icon, text, true);
    }

    public HtmlButton(ButtonType buttonType, IconsWidget iconsWidget, String icon, String text, boolean enabled) {
        super(WidgetType.BUTTON);
        setIconsWidget(iconsWidget);
        setIcon(icon);
        setText(text);
        setEnabled(enabled);
        setButtonType(buttonType);
    }

    public HtmlButton addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ButtonType getButtonType() {
        return buttonType;
    }

    public void setButtonType(ButtonType buttonType) {
        if (buttonType == null) {
            return;
        }
        if (this.buttonType != ButtonType.DEFAULT) {
            removeClassName(this.buttonType.className);
        }
        this.buttonType = buttonType;
        addClassName(buttonType.className);
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        if (!enabled) {
            htmlAttributes.put("disabled", "disabled");
        }
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.STRING;
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

    // CLICKABLE

    private final ClickableImpl<HtmlButton> clickableImpl = new ClickableImpl<>(this);

    @Override
    public IsClickable<HtmlButton> getClickableImpl() {
        return clickableImpl;
    }

    @Override
    public HtmlButton onClick(ClickEventHandler eventHandler) {
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
