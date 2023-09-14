package com.philips.dmis.swt.ui.toolkit.css;

import java.util.ArrayList;
import java.util.List;

public class CssFunction extends CssValue {
    public static final CssFunction abs(CssValue... terms) {
        return new CssFunction("abs", terms);
    }

    public static final CssFunction acos(CssValue... terms) {
        return new CssFunction("acos", terms);
    }

    public static final CssFunction asin(CssValue... terms) {
        return new CssFunction("asin", terms);
    }

    public static final CssFunction atan(CssValue... terms) {
        return new CssFunction("atan", terms);
    }

    public static final CssFunction atan2(CssValue... terms) {
        return new CssFunction("atan2", terms);
    }

    public static final CssFunction attr(String attribute) {
        return new CssFunction("attr", new CssLiteral(attribute));
    }

    public static final CssFunction calc(CssValue... terms) {
        return new CssFunction("calc", terms);
    }

    public static final CssFunction clamp(CssValue min, CssValue preferred, CssValue max) {
        return new CssFunction("clamp", min, preferred, max);
    }

    public static final CssFunction cos(CssValue... terms) {
        return new CssFunction("cos", terms);
    }

    public static final CssFunction counter(String name, CssValue counterStyle) {
        return new CssFunction("counter", new CssLiteral(name), counterStyle);
    }

    public static final CssFunction crossFade(CssValue... terms) {
        return new CssFunction("cross-fade", terms);
    }

    public static final CssFunction element(String id) {
        return new CssFunction("element", new CssLiteral(id));
    }

    public static final CssFunction env(CssValue... terms) {
        return new CssFunction("env", terms);
    }

    public static final CssFunction exp(CssValue... terms) {
        return new CssFunction("exp", terms);
    }

    public static final CssFunction fitContent(CssValue... terms) {
        return new CssFunction("fit-content", terms);
    }

    public static final CssFunction hypot(CssValue... terms) {
        return new CssFunction("hypot", terms);
    }

    public static final CssFunction log(CssValue... terms) {
        return new CssFunction("log", terms);
    }

    public static final CssFunction max(CssValue... terms) {
        return new CssFunction("max", terms);
    }

    public static final CssFunction min(CssValue... terms) {
        return new CssFunction("min", terms);
    }

    public static final CssFunction minMax(CssValue min, CssValue max) {
        return new CssFunction("minmax", min, max);
    }

    public static final CssFunction mod(CssValue... terms) {
        return new CssFunction("mod", terms);
    }

    public static final CssFunction path(CssValue... terms) {
        return new CssFunction("path", terms);
    }

    public static final CssFunction pow(CssValue... terms) {
        return new CssFunction("pow", terms);
    }

    public static final CssFunction ray(CssValue... terms) {
        return new CssFunction("ray", terms);
    }

    public static final CssFunction rem(CssValue... terms) {
        return new CssFunction("rem", terms);
    }

    public static final CssFunction repeat(CssValue... terms) {
        return new CssFunction("repeat", terms);
    }

    public static final CssFunction round(CssValue... terms) {
        return new CssFunction("round", terms);
    }

    public static final CssFunction sign(CssValue... terms) {
        return new CssFunction("sign", terms);
    }

    public static final CssFunction sin(CssValue... terms) {
        return new CssFunction("sin", terms);
    }

    public static final CssFunction sqrt(CssValue... terms) {
        return new CssFunction("sqrt", terms);
    }

    public static final CssFunction symbols(CssValue... terms) {
        return new CssFunction("symbols", terms);
    }

    public static final CssFunction tan(CssValue... terms) {
        return new CssFunction("tan", terms);
    }

    public static final CssFunction url(CssValue... terms) {
        return new CssFunction("url", terms);
    }

    public static final CssFunction var(CssValue... terms) {
        return new CssFunction("var", terms);
    }

    private final String name;
    private final List<CssValue> terms = new ArrayList<>();

    public CssFunction(String name, CssValue... terms) {
        this.name = name;
        for (CssValue term : terms) {
            addTerm(term);
        }
    }

    public String getName() {
        return name;
    }

    boolean hasName() {
        return (name != null && !name.isEmpty());
    }

    public void addTerm(CssValue term) {
        if (term == null) {
            return;
        }
        terms.add(term);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (hasName()) {
            s.append(name);
            s.append("(");
        }
        int i = 0;
        for (CssValue term : terms) {
            if (i > 0) {
                s.append(" ");
            }
            s.append(term.toString());
            i++;
        }
        if (hasName()) {
            s.append(")");
        }
        return s.toString();
    }
}
