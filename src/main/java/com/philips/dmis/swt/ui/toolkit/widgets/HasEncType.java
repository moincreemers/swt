package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasEncType extends HasStaticHTML {
    HasEncType getEncTypeImpl();

    String getEncType();

    void setEncType(String encType);

    void setEncType(MimeType mimeType);

}
