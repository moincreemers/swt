package com.philips.dmis.swt.ui.toolkit.js.global;

import com.philips.dmis.swt.ui.toolkit.TestJsFunction;
import com.philips.dmis.swt.ui.toolkit.TestToolkit;
import com.philips.dmis.swt.ui.toolkit.dto.Hash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPatientDocumentsHashFunctionTest {
    @Test
    public void test() {
        TestJsFunction f = new TestJsFunction(new TestToolkit(),
                new JsGlobalModuleDependencyResolver(), GetDocumentHashFunction.class);
        Hash hash = new Hash();
        hash.getP().add("w0");
        hash.getO().add("w1");
        hash.getO().add("w2");
        f.getWindow().document.location.hash = hash.toString();
        Object returnValue = f.apply(null);
        Assertions.assertEquals("#p=w0o=w1&o=w2", returnValue);
    }
}
