package com.philips.dmis.swt.ui.toolkit.widgets;

import java.util.List;

public interface HasClassNames {
    List<String> getClassNames();

    void addClassName(String className);

    void removeClassName(String className);
}
