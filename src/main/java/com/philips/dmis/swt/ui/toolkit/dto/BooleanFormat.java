package com.philips.dmis.swt.ui.toolkit.dto;

public class BooleanFormat extends TextFormat {
    private BooleanAppearanceType appearance = BooleanAppearanceType.CIRCLE;

    public BooleanAppearanceType getAppearance() {
        return appearance;
    }

    public BooleanFormat setAppearance(BooleanAppearanceType appearance) {
        this.appearance = appearance;
        return this;
    }

    @Override
    public DataType getFormatType() {
        return DataType.BOOLEAN;
    }

    @Override
    public boolean isValidDataType(DataType dataType) {
        return dataType == DataType.BOOLEAN;
    }

    @Override
    public void getProperties(CompactMap properties) {
        super.getProperties(properties);
        properties.put("appearance", appearance);
    }
}
