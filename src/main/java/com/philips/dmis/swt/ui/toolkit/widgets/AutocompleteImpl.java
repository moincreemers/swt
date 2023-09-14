package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.Map;

@PageXmlElement({"autocomplete"})
public class AutocompleteImpl implements HasAutocomplete {
    private final Widget widget;
    private AutocompleteType autocomplete = AutocompleteType.OFF;

    public AutocompleteImpl(Widget widget) {
        this.widget = widget;
        widget.registerStaticHtmlImplementation(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public HasAutocomplete getAutocompleteImpl() {
        return this;
    }

    @Override
    public AutocompleteType getAutocomplete() {
        return autocomplete;
    }

    @Override
    public void setAutocomplete(AutocompleteType autocomplete) {
        if (autocomplete == null) {
            autocomplete = AutocompleteType.OFF;
        }
        this.autocomplete = autocomplete;
    }

    @Override
    public void renderStaticInnerHtml(Toolkit toolkit, StringBuffer html) {

    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        if (autocomplete != null) {
            htmlAttributes.put("autocomplete", autocomplete.getValue());
        }
    }

    @Override
    public void validate(Toolkit toolkit) throws WidgetConfigurationException {

    }
}
