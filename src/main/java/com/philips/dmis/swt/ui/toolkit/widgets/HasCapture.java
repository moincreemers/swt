package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasCapture extends HasStaticHTML {
    HasCapture getCaptureImpl();

    CaptureType getCapture();

    void setCapture(CaptureType capture);
}
