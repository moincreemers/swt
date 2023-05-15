package com.philips.dmis.swt.ui.toolkit.js;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;

public interface HasJS {
    /**
     * Renders javascript code.
     *
     * @param toolkit
     * @param js
     * @throws JsRenderException
     */
    void renderJs(Toolkit toolkit, JsWriter js) throws JsRenderException;
}
