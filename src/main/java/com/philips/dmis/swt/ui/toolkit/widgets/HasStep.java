package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasStep extends HasStaticHTML {
    HasStep getStepImpl();

    boolean isStepAny();

    void setStepAny(boolean stepAny);

    Float getStep();

    void setStep(Float step);
}
