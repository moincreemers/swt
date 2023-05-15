package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasRequired extends HasStaticHTML {
    HasRequired getRequiredImpl();

    boolean getRequired();

    void setRequired(boolean required);
}
