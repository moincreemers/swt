package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;

import java.util.Map;

public class StepImpl implements HasStep {
    private final Widget widget;
    private boolean stepAny;
    private Float step;

    public StepImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasStep getStepImpl() {
        return this;
    }

    @Override
    public boolean isStepAny() {
        return stepAny;
    }

    @Override
    public void setStepAny(boolean stepAny) {
        this.stepAny = stepAny;
    }

    @Override
    public Float getStep() {
        return step;
    }

    @Override
    public void setStep(Float step) {
        this.step = step;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (stepAny) {
            htmlAttributes.put("step", "any");
        } else if (step != null) {
            htmlAttributes.put("step", step.toString());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
