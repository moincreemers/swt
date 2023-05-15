package com.philips.dmis.swt.ui.toolkit.dto;

public class ArrayFormat extends TextFormat {
    private ArrayAppearanceType appearance = ArrayAppearanceType.JOIN;
    private String separator = ", ";

    public ArrayAppearanceType getAppearance() {
        return appearance;
    }

    public ArrayFormat setAppearance(ArrayAppearanceType appearance) {
        this.appearance = appearance;
        return this;
    }

    public String getSeparator() {
        return separator;
    }

    public ArrayFormat setSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    @Override
    public DataType getFormatType() {
        return DataType.ARRAY;
    }

    @Override
    public boolean isValidDataType(DataType dataType) {
        return dataType == DataType.ARRAY;
    }

    @Override
    public void getProperties(CompactMap properties) {
        super.getProperties(properties);
        properties.put("appearance", appearance);
    }
}
