package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

public interface Validatable {
    void validate(Toolkit toolkit) throws WidgetConfigurationException;
}
