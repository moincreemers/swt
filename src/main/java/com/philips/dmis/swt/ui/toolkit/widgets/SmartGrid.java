package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.js.WidgetType;

// todo: rename to Flex...
public class SmartGrid extends Panel {
    public static final String CSS_CLASS_CELL = "smart-cell";
    private int cells;

    public SmartGrid(int cells) {
        this(NAMELESS, cells);
    }

    public SmartGrid(String name, int cells) {
        super(name, WidgetType.SMART_GRID, PanelType.DEFAULT);
        if (cells < 1) {
            throw new IllegalArgumentException("expected cells > 0");
        }
        this.cells = cells;
    }

    public int getCells() {
        return cells;
    }
}
