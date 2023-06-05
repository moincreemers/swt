package com.philips.dmis.swt.ui.toolkit;

public enum JsLogLevel {
    DEBUG(100),
    TRACE(200),
    INFO(300),
    WARNING(400),
    ERROR(500),
    NONE(1000);

    private int level;

    JsLogLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
