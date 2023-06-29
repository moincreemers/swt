package com.philips.dmis.swt.ui.toolkit.statement.dom;

import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public final class D {
    private D() {
    }

    public static DOMSelectorStatement GetElementsByName(String name) {
        return GetElementsByName(V.Const(name));
    }

    public static DOMSelectorStatement GetElementsByName(ValueStatement name) {
        return new GetElementsByNameStatement(name);
    }

    public static DOMSelectorStatement GetElementById(String id) {
        return GetElementById(V.Const(id));
    }

    public static DOMSelectorStatement GetElementById(ValueStatement id) {
        return new GetElementByIdStatement(id);
    }

    public static DOMSelectorStatement GetElementsByClassName(String className) {
        return GetElementsByClassName(V.Const(className));
    }

    public static DOMSelectorStatement GetElementsByClassName(ValueStatement className) {
        return new GetElementsByClassNameStatement(className);
    }

    public static DOMStatement AddClassName(DOMSelectorStatement selector, String className) {
        return AddClassName(selector, V.Const(className));
    }

    public static DOMStatement AddClassName(DOMSelectorStatement selector, ValueStatement className) {
        return new AddClassNameStatement(selector, className);
    }

    public static DOMStatement RemoveClassName(DOMSelectorStatement selector, String className) {
        return RemoveClassName(selector, V.Const(className));
    }

    public static DOMStatement RemoveClassName(DOMSelectorStatement selector, ValueStatement className) {
        return new RemoveClassNameStatement(selector, className);
    }
}
