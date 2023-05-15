package com.philips.dmis.swt.ui.toolkit.widgets;

public interface HasAutocomplete extends HasStaticHTML {
    HasAutocomplete getAutocompleteImpl();

    AutocompleteType getAutocomplete();

    void setAutocomplete(AutocompleteType autocomplete);
}
