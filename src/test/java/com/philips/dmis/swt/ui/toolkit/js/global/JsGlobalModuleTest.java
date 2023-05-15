package com.philips.dmis.swt.ui.toolkit.js.global;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsGlobalModuleTest {
    @Test
    public void getFunctionId() {
        String id = JsGlobalModule.getId(InitFunction.class);
        Assertions.assertEquals("if0", id);
    }
}
