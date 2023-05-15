package com.philips.dmis.swt.ui.toolkit.html;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaCodeFormatterTest {
    @Test
    public void formatShouldWork() {
        String code = "function calculateSum(a,b){return a+b;};";
        String expected = "function calculateSum (a, b) {\n" +
                "  return a+b;\n" +
                "}\n;\n";
        String actual = JavaCodeFormatter.format(code);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }
}
