package com.philips.dmis.swt.ui.toolkit.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Composite implements HasWidgets, Iterable<Widget> {
    private final List<Widget> widgets = new ArrayList<>();

    protected <T extends Widget> T add(T widget) {
        widgets.add(widget);
        return widget;
    }

    protected HasWidgets add(HasWidgets hasWidgets) throws Exception {
        widgets.addAll(hasWidgets.getWidgets());
        return hasWidgets;
    }

    @Override
    public List<Widget> getWidgets() throws Exception {
        widgets.clear();
        build();
        return widgets;
    }

    protected abstract void build() throws Exception;

    @Override
    public Iterator<Widget> iterator() {
        return widgets.iterator();
    }
}
