package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.List;

public interface HasStaticHtmlImplementations {
    void registerStaticHtmlImplementation(HasStaticHTML hasStaticHTML);

    List<HasStaticHTML> getStaticHtmlImplementations();

    void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html);
}
