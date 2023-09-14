package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.events.ClickEventHandler;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

import java.util.List;
import java.util.Map;

public class HtmlLink extends DataBoundWidget<HtmlLink, ValueDataSourceUsage> implements
        HasEncType, IsClickable<HtmlLink>, HasIcon, HasText, HasRel, HasTarget, HasReferrerPolicy, HasDownload {
    public static HtmlLink openPage(String text, Class<? extends Page> pageClass) {
        return new HtmlLink(text).onClick(new ClickEventHandler(M.OpenPage(pageClass)));
    }

    public static HtmlLink openPage(String text, Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new HtmlLink(text).onClick(new ClickEventHandler(M.OpenPage(pageClass, valueStatement)));
    }

    public static HtmlLink closePage(String text) {
        return new HtmlLink(text).onClick(new ClickEventHandler(M.ClosePage()));
    }

    public static HtmlLink closePage(String text, ValueStatement valueStatement) {
        return new HtmlLink(text).onClick(new ClickEventHandler(M.ClosePage(valueStatement)));
    }

    public HtmlLink(WidgetConfigurator widgetConfigurator) {
        super(widgetConfigurator, WidgetType.LINK);
    }

    public HtmlLink() {
        this(DEFAULT_VALUE_TEXT);
    }

    public HtmlLink(String text) {
        super(WidgetType.LINK);
        setText(text);
    }

    public HtmlLink(IconsWidget iconsWidget, String icon, String text) {
        this(text);
        setIconsWidget(iconsWidget);
        setIcon(icon);
    }

    public HtmlLink addDataSource(DataSourceSupplier dataSourceSupplier, DataAdapter... dataAdapters) throws WidgetConfigurationException {
        super.addDataSource(ValueDataSourceUsage.VALUE, dataSourceSupplier, dataAdapters);
        return this;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        htmlAttributes.put("href", "javascript:void(0);");
    }

    // HASVALUETYPE

    @Override
    public JsType getReturnType() {
        return JsType.STRING;
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

    // REL

    private final RelImpl relImpl = new RelImpl(this, false, true, false);

    @Override
    public HasRel getRelImpl() {
        return relImpl;
    }

    @Override
    public java.util.List<String> getRel() {
        return relImpl.getRel();
    }

    @Override
    public void setRel(List<String> rel) {
        relImpl.setRel(rel);
    }

    @Override
    public void addRel(String rel) {
        relImpl.addRel(rel);
    }

    @Override
    public void addRel(RelType rel) {
        relImpl.addRel(rel);
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

    // REFERRER-POLICY

    private final ReferrerPolicyImpl referrerPolicyImpl = new ReferrerPolicyImpl(this);

    @Override
    public HasReferrerPolicy getReferrerPolicyImpl() {
        return referrerPolicyImpl;
    }

    @Override
    public ReferrerPolicyType getReferrerPolicy() {
        return referrerPolicyImpl.getReferrerPolicy();
    }

    @Override
    public void setReferrerPolicy(ReferrerPolicyType referrerPolicy) {
        referrerPolicyImpl.setReferrerPolicy(referrerPolicy);
    }

    // DOWNLOAD

    private final DownloadImpl downloadImpl = new DownloadImpl(this);

    @Override
    public HasDownload getDownloadImpl() {
        return downloadImpl;
    }

    @Override
    public String getDownload() {
        return downloadImpl.getDownload();
    }

    @Override
    public void setDownload(String download) {
        downloadImpl.setDownload(download);
    }

    // CLICKABLE

    private final ClickableImpl<HtmlLink> clickableImpl = new ClickableImpl<>(this);

    @Override
    public IsClickable<HtmlLink> getClickableImpl() {
        return clickableImpl;
    }

    @Override
    public HtmlLink onClick(ClickEventHandler eventHandler) {
        return clickableImpl.onClick(eventHandler);
    }
}
