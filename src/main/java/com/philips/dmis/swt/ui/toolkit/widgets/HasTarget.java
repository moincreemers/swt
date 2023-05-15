package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasTarget extends HasStaticHTML {
    HasTarget getTargetImpl();

    String getTarget();

    void setTarget(String target);

    void setTarget(TargetType target);
}
