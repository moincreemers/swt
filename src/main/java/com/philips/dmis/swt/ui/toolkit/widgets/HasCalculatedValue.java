package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;

public interface HasCalculatedValue {
    void renderValue(Toolkit toolkit, JsWriter js);
}
