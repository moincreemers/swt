package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelImpl implements HasRel {
    private final Widget widget;
    private final List<String> rel = new ArrayList<>();
    private final boolean externalResource, anchor, form;

    public RelImpl(Widget widget, boolean externalResource, boolean anchor, boolean form) {
        this.widget = widget;
        this.externalResource = externalResource;
        this.anchor = anchor;
        this.form = form;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasRel getRelImpl() {
        return this;
    }

    @Override
    public List<String> getRel() {
        return rel;
    }

    @Override
    public void setRel(List<String> rel) {
        if (rel == null) {
            return;
        }
        this.rel.clear();
        this.rel.addAll(rel);
    }

    @Override
    public void addRel(String rel) {
        this.rel.add(rel);
    }

    @Override
    public void addRel(RelType rel) {
        if (externalResource && !rel.isExternalResource()) {
            throw new IllegalArgumentException("Rel type " + rel.name() + " not allowed");
        }
        if (anchor && !rel.isAnchor()) {
            throw new IllegalArgumentException("Rel type " + rel.name() + " not allowed");
        }
        if (form && !rel.isForm()) {
            throw new IllegalArgumentException("Rel type " + rel.name() + " not allowed");
        }
        this.rel.add(rel.getValue());
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (rel != null && !rel.isEmpty()) {
            htmlAttributes.put("rel", String.join(" ", rel));
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
