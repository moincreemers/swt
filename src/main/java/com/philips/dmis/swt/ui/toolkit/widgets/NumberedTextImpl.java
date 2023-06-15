package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

public class NumberedTextImpl extends TextImpl implements HasNumberedText {
    protected int level = 1;
    protected boolean numbered;

    public NumberedTextImpl(Widget widget) {
        super(widget);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = Math.max(1, Math.min(level, 6));
    }

    @Override
    public boolean isNumberingEnabled() {
        return numbered;
    }

    @Override
    public void setNumberingEnabled(boolean numbered) {
        if (this.numbered) {
            widget.removeClassName(HasNumberedText.CSS_CLASS_NUMBERED);
        }
        this.numbered = numbered;
        if (numbered) {
            widget.addClassName(HasNumberedText.CSS_CLASS_NUMBERED);
        }
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {
        if (numbered) {
            html.append(String.format("<table class=\"%s\"><tr>",
                    HasNumberedText.CSS_CLASS_NUMBER_OUTER));
            html.append(String.format("<td id=\"${id}%s\" class=\"%s\">",
                    HasNumberedText.getNumberTextId(""), HasNumberedText.CSS_CLASS_NUMBER_TEXT));

            html.append("</td>");
            html.append(String.format("<td id=\"${id}%s\" class=\"%s\">",
                    HasText.getTextId(""), HasText.CSS_CLASS_TEXT));
            if (!(text == null || text.isEmpty())) {
                html.append(text);
            }
            html.append("</td>");
            html.append("</tr></table>");
        } else {
            super.renderStaticInnerHtml(toolkit, html);
        }
    }
}
