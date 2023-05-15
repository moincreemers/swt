package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasAcceptCharSet extends HasStaticHTML {
    HasAcceptCharSet getAcceptCharSetImpl();

    java.util.List<String> getAcceptCharSet();

    void setAcceptCharSet(java.util.List<String> acceptCharSet);

    void addAcceptCharSet(String acceptCharSet);
}
