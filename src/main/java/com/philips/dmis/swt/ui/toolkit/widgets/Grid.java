package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

public class Grid extends Panel {
    public static final String CSS_CLASS_ROW = "grid-row";
    public static final String CSS_CLASS_CELL = "grid-cell";
    private final int columns;

    public Grid(int columns) {
        this("", columns);
    }

    public Grid(String name, int columns) {
        super(name, WidgetType.GRID, PanelType.DEFAULT);
        if (columns < 1) {
            throw new IllegalArgumentException("columns > 0 expected");
        }
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }
}
