package com.philips.dmis.swt.ui.toolkit.js;

import java.util.List;

/**
 * Defines a JS function which is a member of a JS module.
 */
public interface JsFunction extends JsMember {
    void getDependencies(List<Class<? extends JsMember>> dependencies);

    void getParameters(List<JsParameter> parameters);
}
