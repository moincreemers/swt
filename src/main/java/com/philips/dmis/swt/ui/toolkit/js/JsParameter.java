package com.philips.dmis.swt.ui.toolkit.js;

public interface JsParameter {
    String getName();

    JsType getType();

    public static JsParameter getInstance(final String name, final JsType jsType) {
        return new JsParameter() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public JsType getType() {
                return jsType;
            }
        };
    }
}
