package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;

public interface HasCode {
    String getId();

    String getModuleName();

    void isolatedRenderJs(Toolkit toolkit, JsWriter js);
}
