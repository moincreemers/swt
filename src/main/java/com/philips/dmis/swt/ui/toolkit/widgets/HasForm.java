package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasForm extends HasStaticHTML {
    HasForm getFormImpl();

    String getForm();

    void setForm(String form);

    void setForm(HtmlForm htmlForm);
}
