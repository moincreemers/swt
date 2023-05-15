package com.philips.dmis.swt.ui.toolkit.dto;

public class NumberFormat extends TextFormat {
    private LocaleMatcherType localeMatcher;
    private CompactDisplayType compactDisplay;
    private String currency; // ISO 4217 currency codes
    private CurrencyDisplayType currencyDisplay;
    private CurrencySignType currencySign;
    private NotationType notation;
    private NumberingSystemType numberingSystem;
    private SignDisplayType signDisplay;
    private StyleType style;
    private String unit;
    private UnitDisplayType unitDisplay;
    private Integer minimumIntegerDigits;// 1-21
    private Integer minimumFractionDigits;// 0-20
    private Integer maximumFractionDigits; // 0-20
    private Integer minimumSignificantDigits; // 1-21
    private Integer maximumSignificantDigits; // 1-21

    public LocaleMatcherType getLocaleMatcher() {
        return localeMatcher;
    }

    public NumberFormat setLocaleMatcher(LocaleMatcherType localeMatcher) {
        this.localeMatcher = localeMatcher;
        return this;
    }

    public CompactDisplayType getCompactDisplay() {
        return compactDisplay;
    }

    public NumberFormat setCompactDisplay(CompactDisplayType compactDisplay) {
        this.compactDisplay = compactDisplay;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public NumberFormat setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public CurrencyDisplayType getCurrencyDisplay() {
        return currencyDisplay;
    }

    public NumberFormat setCurrencyDisplay(CurrencyDisplayType currencyDisplay) {
        this.currencyDisplay = currencyDisplay;
        return this;
    }

    public CurrencySignType getCurrencySign() {
        return currencySign;
    }

    public NumberFormat setCurrencySign(CurrencySignType currencySign) {
        this.currencySign = currencySign;
        return this;
    }

    public NotationType getNotation() {
        return notation;
    }

    public NumberFormat setNotation(NotationType notation) {
        this.notation = notation;
        return this;
    }

    public NumberingSystemType getNumberingSystem() {
        return numberingSystem;
    }

    public NumberFormat setNumberingSystem(NumberingSystemType numberingSystem) {
        this.numberingSystem = numberingSystem;
        return this;
    }

    public SignDisplayType getSignDisplay() {
        return signDisplay;
    }

    public NumberFormat setSignDisplay(SignDisplayType signDisplay) {
        this.signDisplay = signDisplay;
        return this;
    }

    public StyleType getStyle() {
        return style;
    }

    public NumberFormat setStyle(StyleType style) {
        this.style = style;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public NumberFormat setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public UnitDisplayType getUnitDisplay() {
        return unitDisplay;
    }

    public NumberFormat setUnitDisplay(UnitDisplayType unitDisplay) {
        this.unitDisplay = unitDisplay;
        return this;
    }

    public Integer getMinimumIntegerDigits() {
        return minimumIntegerDigits;
    }

    public NumberFormat setMinimumIntegerDigits(Integer minimumIntegerDigits) {
        this.minimumIntegerDigits = minimumIntegerDigits != null ?
                Math.max(1, Math.min(21, minimumIntegerDigits))
                : null;
        return this;
    }

    public Integer getMinimumFractionDigits() {
        return minimumFractionDigits;
    }

    public NumberFormat setMinimumFractionDigits(Integer minimumFractionDigits) {
        this.minimumFractionDigits = minimumFractionDigits != null ?
                Math.max(0, Math.min(20, minimumFractionDigits))
                : null;
        return this;
    }

    public Integer getMaximumFractionDigits() {
        return maximumFractionDigits;
    }

    public NumberFormat setMaximumFractionDigits(Integer maximumFractionDigits) {
        this.maximumFractionDigits = maximumFractionDigits != null ?
                Math.max(0, Math.min(20, maximumFractionDigits))
                : null;
        return this;
    }

    public Integer getMinimumSignificantDigits() {
        return minimumSignificantDigits;
    }

    public NumberFormat setMinimumSignificantDigits(Integer minimumSignificantDigits) {
        this.minimumSignificantDigits = minimumSignificantDigits != null ?
                Math.max(1, Math.min(21, minimumSignificantDigits))
                : null;
        return this;
    }

    public Integer getMaximumSignificantDigits() {
        return maximumSignificantDigits;
    }

    public NumberFormat setMaximumSignificantDigits(Integer maximumSignificantDigits) {
        this.maximumSignificantDigits = maximumSignificantDigits != null ?
                Math.max(1, Math.min(21, maximumSignificantDigits))
                : null;
        return this;
    }

    @Override
    public DataType getFormatType() {
        return DataType.NUMBER;
    }

    @Override
    public boolean isValidDataType(DataType dataType) {
        return dataType == DataType.NUMBER;
    }

    @Override
    public void getProperties(CompactMap properties) {
        super.getProperties(properties);
        properties.put("localeMatcher", localeMatcher);
        properties.put("compactDisplay", compactDisplay);
        properties.put("currency", currency); // ISO 4217 currency codes
        properties.put("currencyDisplay", currencyDisplay);
        properties.put("currencySign", currencySign);
        properties.put("notation", notation);
        properties.put("numberingSystem", numberingSystem);
        properties.put("signDisplay", signDisplay);
        properties.put("style", style);
        properties.put("unit", unit);
        properties.put("unitDisplay", unitDisplay);
        properties.put("minimumIntegerDigits", minimumIntegerDigits);// 1-21
        properties.put("minimumFractionDigits", minimumFractionDigits);// 0-20
        properties.put("maximumFractionDigits", maximumFractionDigits); // 0-20
        properties.put("minimumSignificantDigits", minimumSignificantDigits); // 1-21
        properties.put("maximumSignificantDigits", maximumSignificantDigits); // 1-21
    }
}
