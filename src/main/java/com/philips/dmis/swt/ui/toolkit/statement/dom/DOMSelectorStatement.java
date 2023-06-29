package com.philips.dmis.swt.ui.toolkit.statement.dom;

import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;

/**
 * DOM statements operate directly on the document object model.
 */
public abstract class DOMSelectorStatement extends Statement {
    @Override
    public JsType getType() {
        return JsType.ARRAY;
    }
}
