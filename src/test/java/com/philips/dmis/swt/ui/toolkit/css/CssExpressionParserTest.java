package com.philips.dmis.swt.ui.toolkit.css;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CssExpressionParserTest {
    @Test
    public void shouldParseLength() {
        CssValue value = CssExpressionParser.getInstance().parse("1.2em");
        Assertions.assertEquals("1.2em", value.toString());
    }

    @Test
    public void shouldParseConcatenatedValues() {
        CssValue value = CssExpressionParser.getInstance().parse("\"a\" 'b' \"c\"");
        Assertions.assertEquals("'a' 'b' 'c'", value.toString());
    }

    @Test
    public void shouldParseFunction() {
        CssValue value = CssExpressionParser.getInstance().parse("calc(1.2em*2)");
        Assertions.assertEquals("calc(1.2em * 2)", value.toString());
    }
}
