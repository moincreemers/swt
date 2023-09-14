package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum ListType implements HasDefault<ListType> {
    UNORDERED("tk-list-ul"),
    ORDERED("tk-list-ol"),
    MENU("tk-list-menu"),
    INLINE_MENU("tk-list-inline-menu"),
    VERTICAL("tk-list-vertical"),
    HORIZONTAL("tk-list-horizontal"),

    PILLS_DEFAULT("tk-list-pills"),
    PILLS_SUCCESS("tk-list-pills-success"),
    PILLS_INFO("tk-list-pills-info"),
    PILLS_WARNING("tk-list-pills-warning"),
    PILLS_ERROR("tk-list-pills-error"),

    ;

    final String className;

    ListType(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public ListType getDefault() {
        return UNORDERED;
    }
}
