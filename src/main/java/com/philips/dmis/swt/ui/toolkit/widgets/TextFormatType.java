package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.utils.HasDefault;

public enum TextFormatType implements HasDefault<TextFormatType> {
    TEXT,
    JSON,
    JAVA_AND_JS;

    @Override
    public TextFormatType getDefault() {
        return TextFormatType.TEXT;
    }
}
