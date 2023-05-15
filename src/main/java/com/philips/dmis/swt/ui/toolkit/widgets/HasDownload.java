package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasDownload extends HasStaticHTML {
    HasDownload getDownloadImpl();

    String getDownload();

    void setDownload(String download);
}
