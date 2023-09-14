package com.philips.dmis.swt.ui.toolkit.css;

public abstract class CssValue {
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CssValue) {
            return obj.toString().equals(toString());
        }
        return super.equals(obj);
    }
}
