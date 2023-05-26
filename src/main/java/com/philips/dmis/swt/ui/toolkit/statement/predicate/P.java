package com.philips.dmis.swt.ui.toolkit.statement.predicate;

import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;

public final class P {
    private P() {
    }

    public static PredicateStatement IsFalse() {
        return new IsFalsePredicate();
    }

    public static PredicateStatement IsTrue() {
        return new IsTruePredicate();
    }

    public static PredicateStatement And(PredicateStatement... predicates) {
        return new AndPredicate(predicates);
    }

    public static PredicateStatement NotAnd(PredicateStatement... predicates) {
        return new NotAndPredicate(predicates);
    }

    public static PredicateStatement Or(PredicateStatement... predicates) {
        return new OrPredicate(predicates);
    }

    public static PredicateStatement NotOr(PredicateStatement... predicates) {
        return new NotOrPredicate(predicates);
    }

    public static PredicateStatement ExclusiveOr(PredicateStatement... predicates) {
        return new ExclusiveOrPredicate(predicates);
    }

    public static PredicateStatement IsEqual(ValueStatement valueStatement) {
        return new IsEqualPredicate(valueStatement);
    }

    public static PredicateStatement IsNotEqual(ValueStatement valueStatement) {
        return new IsNotEqualPredicate(valueStatement);
    }

    public static PredicateStatement IsGreaterThan(ValueStatement valueStatement) {
        return new IsGreaterThanPredicate(valueStatement);
    }

    public static PredicateStatement IsGreaterThanOrEqual(ValueStatement valueStatement) {
        return new IsGreaterThanOrEqualPredicate(valueStatement);
    }

    public static PredicateStatement IsLessThan(ValueStatement valueStatement) {
        return new IsLessThanPredicate(valueStatement);
    }

    public static PredicateStatement IsLessThanOrEqual(ValueStatement valueStatement) {
        return new IsLessThanOrEqualPredicate(valueStatement);
    }

    public static PredicateStatement Contains(ValueStatement valueStatement) {
        return new ContainsPredicate(valueStatement);
    }

    public static PredicateStatement Includes(ValueStatement valueStatement) {
        return new IncludesPredicate(valueStatement);
    }

    public static PredicateStatement IsEven() {
        return new IsEvenPredicate();
    }

    public static PredicateStatement IsUneven() {
        return new IsUnevenPredicate();
    }

    public static PredicateStatement In(ValueStatement... valueStatements) {
        return new InPredicate(valueStatements);
    }

    public static PredicateStatement InArray(ValueStatement valueStatement) {
        return new InArrayPredicate(valueStatement);
    }

    public static PredicateStatement IsType(String type) {
        return new IsTypePredicate(type);
    }

    public static PredicateStatement IsString() {
        return new IsStringPredicate();
    }

    public static PredicateStatement IsNumber() {
        return new IsNumberPredicate();
    }

    public static PredicateStatement IsBoolean() {
        return new IsBooleanPredicate();
    }

    public static PredicateStatement IsNull() {
        return new IsNullPredicate();
    }

    public static PredicateStatement IsNotNull() {
        return new IsNullPredicate();
    }

    public static PredicateStatement IsUndefined() {
        return new IsUndefinedPredicate();
    }

    public static PredicateStatement IsNotUndefined() {
        return new IsNotUndefinedPredicate();
    }

    public static PredicateStatement IsNullOrUndefined() {
        return new IsNullOrUndefinedPredicate();
    }

    public static PredicateStatement IsNotNullOrUndefined() {
        return new IsNotNullOrUndefinedPredicate();
    }

    public static PredicateStatement IsNaN() {
        return new IsNaNPredicate();
    }

    public static PredicateStatement IsNotNaN() {
        return new IsNotNaNPredicate();
    }

    public static PredicateStatement IsInfinity() {
        return new IsInfinityPredicate();
    }

    public static PredicateStatement IsNotInfinity() {
        return new IsNotInfinityPredicate();
    }

    public static PredicateStatement IsFinite() {
        return new IsFinitePredicate();
    }

    public static PredicateStatement IsInteger() {
        return new IsIntegerPredicate();
    }

    public static PredicateStatement IsObject() {
        return new IsObjectPredicate();
    }

    public static PredicateStatement Code(String js) {
        return new CodePredicate(js);
    }
}
