package com.philips.dmis.swt.ui.toolkit.statement.mapper;

import com.philips.dmis.swt.ui.toolkit.statement.method.CallStatement;
import com.philips.dmis.swt.ui.toolkit.statement.method.M;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.Code;

import java.util.Map;

public final class C {
    private C() {
    }

    public static MapStatement ValueOf(ValueStatement value) {
        return new ValueOf(value);
    }

    public static MapStatement ParseDate() {
        return new ParseDate();
    }

    public static MapStatement ParseInt() {
        return new ParseInt();
    }

    public static MapStatement ParseFloat() {
        return new ParseFloat();
    }

    public static MapStatement Replace(ValueStatement search, ValueStatement replace) {
        return new Replace(search, replace);
    }

    public static MapStatement Lookup(Map<Object, Object> table) {
        return new Lookup(table);
    }

    public static MapStatement Call(CallStatement callStatement) {
        return new Call(callStatement);
    }

    public static MapStatement Call(Code code, String name, ValueStatement... parameterValues) {
        return new Call(M.Call(code, name, parameterValues));
    }

    public static MapStatement ReplaceIfEmpty(ValueStatement replace) {
        return new ReplaceIfEmpty(replace);
    }

    public static MapStatement ReplaceWithFieldIfEmpty() {
        return new ReplaceWithFieldIfEmpty();
    }

    public static MapStatement Format(ValueStatement format) {
        return new Format(format);
    }

    public static Download Download() {
        return new Download();
    }
}
