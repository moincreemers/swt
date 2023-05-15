package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasAccept extends HasStaticHTML {
    HasAccept getAcceptImpl();

    java.util.List<String> getAccept();

    void setAccept(java.util.List<String> accept);

    void addAccept(String accept);
}
