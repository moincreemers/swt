package com.philips.dmis.swt.ui.toolkit.statement.value;

public enum TimeIntervalType {
    YEAR(31536000000l),
    MONTH(-1),
    DAY(86400000l),
    HOUR(3600000l),
    MINUTE(60000l),
    SECOND(1000l),
    MILLISECOND(1l),

    ;

    long millisPerUnit = 1l;

    TimeIntervalType(long millisPerUnit) {
        this.millisPerUnit = millisPerUnit;
    }

    public long getMillisPerUnit() {
        return millisPerUnit;
    }
}
