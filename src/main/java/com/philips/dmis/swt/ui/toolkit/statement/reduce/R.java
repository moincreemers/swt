package com.philips.dmis.swt.ui.toolkit.statement.reduce;

import com.philips.dmis.swt.ui.toolkit.js.JsType;

public final class R {
    private R() {
    }

    public static ReducerStatement Sum() {
        return new Sum();
    }

    public static ReducerStatement Subtraction() {
        return new Subtraction();
    }

    public static ReducerStatement Product() {
        return new Product();
    }

    public static ReducerStatement Division() {
        return new Division();
    }


    public static ReducerStatement Code(String js, JsType jsType, Object initialValue) {
        return new CodeReducer(js, jsType, initialValue);
    }
}
