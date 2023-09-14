package com.philips.dmis.swt.ui.toolkit.widgets;

import com.philips.dmis.swt.ui.toolkit.css.CssLength;
import com.philips.dmis.swt.ui.toolkit.css.CssUnit;
import com.philips.dmis.swt.ui.toolkit.css.CssValue;
import com.philips.dmis.swt.ui.toolkit.js.WidgetType;
import com.philips.dmis.swt.ui.toolkit.utils.PageXmlElement;

import java.util.HashMap;
import java.util.Map;

@PageXmlElement({"columns", "rows", "columnGap", "rowGap"})
public class Grid extends ContainerWidget<Grid> {
    public static final String CSS_CLASS_CELL = "grid-cell";
    public static final CssValue DEFAULT_SIZE = new CssLength(1d, CssUnit.FRACTION);
    private final Map<Integer, CssValue> columnTemplate = new HashMap<>();
    private final Map<Integer, CssValue> rowTemplate = new HashMap<>();
    private CssValue columnGap = CssLength.rem(1);
    private CssValue rowGap = CssLength.rem(1);

    public Grid(WidgetConfigurator widgetConfigurator, int columns, int rows) {
        super(widgetConfigurator, WidgetType.GRID);
        setColumns(columns);
        setRows(rows);
    }

    public Grid(int columns) {
        this(NAMELESS, columns, 0);
    }

    public Grid(String name, int columns) {
        this(name, columns, 0);
    }

    public Grid(int columns, int rows) {
        this(NAMELESS, columns, rows);
    }

    public Grid(String name, int columns, int rows) {
        super(name, WidgetType.GRID);
        setColumns(columns);
        setRows(rows);
    }

    public Grid setColumnWidth(int index, CssValue cssValue) {
        if (cssValue != null && columnTemplate.containsKey(index)) {
            columnTemplate.put(index, cssValue);
        }
        return this;
    }

    public Grid setRowHeight(int index, CssValue cssValue) {
        if (cssValue != null && rowTemplate.containsKey(index)) {
            rowTemplate.put(index, cssValue);
        }
        return this;
    }

    public CssValue getColumnGap() {
        return columnGap;
    }

    public void setColumnGap(CssValue columnGap) {
        this.columnGap = columnGap;
    }

    public CssValue getRowGap() {
        return rowGap;
    }

    public void setRowGap(CssValue rowGap) {
        this.rowGap = rowGap;
    }

    public int getColumns() {
        return columnTemplate.size();
    }

    public void setColumns(int columns) {
        this.columnTemplate.clear();
        for (int c = 0; c < columns; c++) {
            this.columnTemplate.put(c, DEFAULT_SIZE);
        }
    }

    public int getRows() {
        return rowTemplate.size();
    }

    public void setRows(int rows) {
        this.rowTemplate.clear();
        for (int r = 0; r < rows; r++) {
            this.columnTemplate.put(r, DEFAULT_SIZE);
        }
    }

    @Override
    protected boolean acceptWidget(Widget widget) {
        return true;
    }

    @Override
    public void getHtmlAttributes(Map<String, String> htmlAttributes) {
        super.getHtmlAttributes(htmlAttributes);
        StyleAttribute style = new StyleAttribute(htmlAttributes, "style");
        if (getColumns() > 0) {
            StringBuilder columnTemplateCss = new StringBuilder();
            for (int c = 0; c < columnTemplate.size(); c++) {
                if (c > 0) {
                    columnTemplateCss.append(" ");
                }
                columnTemplateCss.append(columnTemplate.get(c).toString());
            }
            style.add("grid-template-columns", columnTemplateCss.toString());
        }
        if (getRows() > 0) {
            StringBuilder rowTemplateCss = new StringBuilder();
            for (int r = 0; r < rowTemplate.size(); r++) {
                if (r > 0) {
                    rowTemplateCss.append(" ");
                }
                rowTemplateCss.append(rowTemplate.get(r).toString());
            }
            style.add("grid-template-row", rowTemplateCss.toString());
        }
        if (columnGap != null) {
            style.add("grid-column-gap", columnGap.toString());
        }
        if (rowGap != null) {
            style.add("grid-row-gap", rowGap.toString());
        }
    }
}
