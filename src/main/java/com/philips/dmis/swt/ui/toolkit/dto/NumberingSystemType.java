package com.philips.dmis.swt.ui.toolkit.dto;

public enum NumberingSystemType implements HasValue {
    ADLM("adlm"),
    AHOM("ahom"),
    ARAB("arab"),
    ARABEXT("arabext"),
    BALI("bali"),
    BENG("beng"),
    BHKS("bhks"),
    BRAH("brah"),
    CAKM("cakm"),
    CHAM("cham"),
    DEVA("deva"),
    DIAK("diak"),
    FULLWIDE("fullwide"),
    GONG("gong"),
    GONM("gonm"),
    QUIR("gujr"),
    GURU("guru"),
    HANIDEC("hanidec"),
    HMNG("hmng"),
    HMNP("hmnp"),
    JAVA("java"),
    KALI("kali"),
    KHMR("khmr"),
    KNDA("knda"),
    LANA("lana"),
    LANATHAM("lanatham"),
    LAGO("laoo"),
    LATN("latn"),
    LEPC("lepc"),
    LIMB("limb"),
    MATHBOLD("mathbold"),
    MATHDBL("mathdbl"),
    MATHMONO("mathmono"),
    MATHSANB("mathsanb"),
    MATHSANS("mathsans"),
    MLYM("mlym"),
    MODI("modi"),
    MONG("mong"),
    MROO("mroo"),
    MTEI("mtei"),
    MYMR("mymr"),
    MYMRSHAN("mymrshan"),
    MYMRTLNG("mymrtlng"),
    NEWA("newa"),
    NKOO("nkoo"),
    OLCK("olck"),
    ORVA("orya"),
    OSMA("osma"),
    ROHG("rohg"),
    SAUR("saur"),
    SEGMENT("segment"),
    SHRD("shrd"),
    SIND("sind"),
    SINH("sinh"),
    SORA("sora"),
    SUND("sund"),
    TAKR("takr"),
    TALU("talu"),
    TAMLDEC("tamldec"),
    TELU("telu"),
    THAI("thai"),
    TIBT("tibt"),
    TIRH("tirh"),
    VAII("vaii"),
    WARA("wara"),
    WCHO("wcho"),

    ;

    final String value;

    NumberingSystemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
