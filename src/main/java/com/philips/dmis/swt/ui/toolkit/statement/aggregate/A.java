package com.philips.dmis.swt.ui.toolkit.statement.aggregate;

public final class A {
    private A() {
    }

    public static AggregateStatement Sum() {
        return new Sum();
    }

    public static AggregateStatement Variance() {
        return new Variance();
    }

    public static AggregateStatement StDev() {
        return new StDev();
    }

    public static AggregateStatement Median() {
        return new Median();
    }

    public static AggregateStatement Average() {
        return new Average();
    }

    public static AggregateStatement Min() {
        return new Minimum();
    }

    public static AggregateStatement Max() {
        return new Maximum();
    }

    public static AggregateStatement Code(String js) {
        return new CodeStatement(js);
    }
}
