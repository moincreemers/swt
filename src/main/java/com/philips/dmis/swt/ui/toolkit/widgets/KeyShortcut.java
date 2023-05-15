package com.philips.dmis.swt.ui.toolkit.widgets;

public class KeyShortcut {
    final int keyCode;
    final boolean meta;
    final boolean shift;
    final boolean control;
    final boolean altOption;

    public KeyShortcut(int keyCode) {
        this(keyCode, false, false, true, false);
    }

    public KeyShortcut(int keyCode, boolean meta, boolean shift, boolean control, boolean altOption) {
        this.keyCode = keyCode;
        this.meta = meta;
        this.shift = shift;
        this.control = control;
        this.altOption = altOption;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean isMeta() {
        return meta;
    }

    public boolean isShift() {
        return shift;
    }

    public boolean isControl() {
        return control;
    }

    public boolean isAltOption() {
        return altOption;
    }
}
