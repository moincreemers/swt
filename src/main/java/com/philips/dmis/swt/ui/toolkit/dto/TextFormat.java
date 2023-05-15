package com.philips.dmis.swt.ui.toolkit.dto;

public class TextFormat extends Format {
    private TextAlignmentType cellAlignment = TextAlignmentType.MIDDLE_LEFT;
    private String cellMaxWidth;
    private String cellMaxHeight;
    private String cellColor;
    private String cellBackgroundColor;
    private String cellPadding;

    public TextAlignmentType getCellAlignment() {
        return cellAlignment;
    }

    public TextFormat setCellAlignment(TextAlignmentType cellAlignment) {
        this.cellAlignment = cellAlignment;
        return this;
    }

    public String getCellMaxWidth() {
        return cellMaxWidth;
    }

    public TextFormat setCellMaxWidth(String cellMaxWidth) {
        this.cellMaxWidth = cellMaxWidth;
        return this;
    }

    public String getCellMaxHeight() {
        return cellMaxHeight;
    }

    public TextFormat setCellMaxHeight(String cellMaxHeight) {
        this.cellMaxHeight = cellMaxHeight;
        return this;
    }

    public String getCellColor() {
        return cellColor;
    }

    public TextFormat setCellColor(String cellColor) {
        this.cellColor = cellColor;
        return this;
    }

    public String getCellBackgroundColor() {
        return cellBackgroundColor;
    }

    public TextFormat setCellBackgroundColor(String cellBackgroundColor) {
        this.cellBackgroundColor = cellBackgroundColor;
        return this;
    }

    public String getCellPadding() {
        return cellPadding;
    }

    public void setCellPadding(String cellPadding) {
        this.cellPadding = cellPadding;
    }

    @Override
    public DataType getFormatType() {
        return DataType.STRING;
    }

    @Override
    public boolean isValidDataType(DataType dataType) {
        return dataType == DataType.STRING;
    }

    @Override
    public void getProperties(CompactMap properties) {
        properties.put("cellAlignment", cellAlignment);
        properties.put("cellMaxWidth", cellMaxWidth);
        properties.put("cellMaxHeight", cellMaxHeight);
        properties.put("cellColor", cellColor);
        properties.put("cellBackgroundColor", cellBackgroundColor);
    }
}
