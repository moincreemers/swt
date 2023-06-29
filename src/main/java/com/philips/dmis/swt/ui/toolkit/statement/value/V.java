package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.global.GetOsName;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.statement.method.MethodStatement;
import com.philips.dmis.swt.ui.toolkit.statement.predicate.PredicateStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

/**
 * Value Statements
 */
@SuppressWarnings("unused")
public final class V {
    private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;
    private static final long DAY = 24 * HOUR;
    private static final long WEEK = 7 * DAY;

    public static final ValueStatement True = Const(JsGlobalModule.TRUE);
    public static final ValueStatement False = Const(JsGlobalModule.FALSE);
    public static final ValueStatement Zero = Const(0);
    public static final ValueStatement One = Const(1);
    public static final ValueStatement Ok = Const(JsGlobalModule.OK);
    public static final ValueStatement Cancel = Const(JsGlobalModule.CANCEL);
    public static final ValueStatement Yes = Const(JsGlobalModule.YES);
    public static final ValueStatement No = Const(JsGlobalModule.NO);

    public static final ValueStatement OneSecond = Const(SECOND);
    public static final ValueStatement OneMinute = Const(MINUTE);
    public static final ValueStatement OneHour = Const(HOUR);
    public static final ValueStatement OneDay = Const(DAY);
    public static final ValueStatement OneWeek = Const(WEEK);

    private V() {
    }

    // HTTP ERRORS

    public static ValueStatement HTTP_OK() {
        return new ConstantValue(200);
    }

    public static ValueStatement HTTP_BAD_REQUEST() {
        return new ConstantValue(400);
    }

    public static ValueStatement HTTP_UNAUTHORIZED() {
        return new ConstantValue(401);
    }

    public static ValueStatement HTTP_SERVER_ERROR() {
        return new ConstantValue(500);
    }

    // REF

    public static ValueStatement Reference(String name) {
        return new ReferenceValue(name);
    }

    // Constant

    public static ValueStatement Empty() {
        return new ConstantValue("");
    }

    public static ValueStatement Const(int value) {
        return new ConstantValue(value);
    }

    public static ValueStatement Const(long value) {
        return new ConstantValue(value);
    }

    public static ValueStatement Const(double value) {
        return new ConstantValue(value);
    }

    public static ValueStatement Const(boolean value) {
        return new ConstantValue(value);
    }

    public static ValueStatement Const(String value) {
        return new ConstantValue(value);
    }

    public static ValueStatement Const(Object[] value) {
        return new ConstantValue(value);
    }

    public static ValueStatement Const(Iterable<?> value) {
        return new ConstantValue(value);
    }

    public static ValueStatement Const(Object value, JsType jsType) {
        return new ConstantValue(value, jsType);
    }

    // Local

    public static ValueStatement Key() {
        return new GetLocalValue("key");
    }

    public static ValueStatement Value() {
        return new GetLocalValue("value");
    }

    public static ValueStatement Local(String name) {
        return new GetLocalValue(name);
    }

    public static ValueStatement This(String name) {
        return new GetThisValue(V.Const(name));
    }

    public static ValueStatement This(ValueStatement name) {
        return new GetThisValue(name);
    }


    // Event

    public static ValueStatement GetEventContext() {
        return new GetEventContext();
    }

    public static ValueStatement GetEventContext(ValueStatement property) {
        return new GetEventContext(property);
    }

    public static ValueStatement GetEvent() {
        return new GetEvent();
    }

    public static ValueStatement GetEvent(ValueStatement property) {
        return new GetEvent(property);
    }

    // String

    public static ValueStatement ToLowerCase(ValueStatement string) {
        return new ToLowerCase(string);
    }

    public static ValueStatement ToUpperCase(ValueStatement string) {
        return new ToUpperCase(string);
    }

    public static ValueStatement At(ValueStatement string, ValueStatement index) {
        return new At(string, index);
    }

    public static ValueStatement CharCodeAt(ValueStatement string, ValueStatement index) {
        return new CharCodeAt(string, index);
    }

    public static ValueStatement CodePointAt(ValueStatement string, ValueStatement index) {
        return new CodePointAt(string, index);
    }

    public static ValueStatement StringConcat(ValueStatement... valueStatements)  {
        return new ConcatValue(valueStatements);
    }

    public static ValueStatement StringIncludes(ValueStatement string, ValueStatement value)  {
        return new StringIncludes(string, value);
    }

    public static ValueStatement StringIndexOf(ValueStatement string, ValueStatement value)  {
        return new StringIndexOf(string, value);
    }

    public static ValueStatement StringLastIndexOf(ValueStatement string, ValueStatement value)  {
        return new StringLastIndexOf(string, value);
    }

    public static ValueStatement StringLength(ValueStatement string)  {
        return new StringLength(string);
    }

    public static ValueStatement StringRepeat(ValueStatement string, ValueStatement value)  {
        return new StringRepeat(string, value);
    }

    public static ValueStatement PadStart(ValueStatement string, ValueStatement maxLength)  {
        return new PadStart(string, maxLength);
    }

    public static ValueStatement PadStart(ValueStatement string, ValueStatement maxLength, ValueStatement fillString)  {
        return new PadStart(string, maxLength, fillString);
    }

    public static ValueStatement PadEnd(ValueStatement string, ValueStatement maxLength)  {
        return new PadEnd(string, maxLength);
    }

    public static ValueStatement PadEnd(ValueStatement string, ValueStatement maxLength, ValueStatement fillString)  {
        return new PadEnd(string, maxLength, fillString);
    }

    public static ValueStatement Trim(ValueStatement string)  {
        return new Trim(string);
    }

    public static ValueStatement TrimStart(ValueStatement string)  {
        return new TrimStart(string);
    }

    public static ValueStatement TrimEnd(ValueStatement string)  {
        return new TrimEnd(string);
    }

    public static ValueStatement TrimLeft(ValueStatement string)  {
        return new TrimLeft(string);
    }

    public static ValueStatement TrimRight(ValueStatement string)  {
        return new TrimRight(string);
    }

    public static ValueStatement StringMatch(ValueStatement string, ValueStatement regEx)  {
        return new StringMatch(string, regEx);
    }

    public static ValueStatement StringMatchAll(ValueStatement string, ValueStatement regEx)  {
        return new StringMatchAll(string, regEx);
    }

    public static ValueStatement StringSearch(ValueStatement string, ValueStatement regEx)  {
        return new StringSearch(string, regEx);
    }

    public static ValueStatement StringReplace(ValueStatement string, ValueStatement search, ValueStatement value)  {
        return new StringReplace(string, search, value);
    }

    public static ValueStatement StringReplaceAll(ValueStatement string, ValueStatement search, ValueStatement value)  {
        return new StringReplaceAll(string, search, value);
    }

    public static ValueStatement StringSlice(ValueStatement string, ValueStatement start)  {
        return new StringSlice(string, start);
    }

    public static ValueStatement StringSlice(ValueStatement string, ValueStatement start, ValueStatement end)  {
        return new StringSlice(string, start, end);
    }

    public static ValueStatement StringSubstr(ValueStatement string, ValueStatement from)  {
        return new StringSubstr(string, from);
    }

    public static ValueStatement StringSubstr(ValueStatement string, ValueStatement from, ValueStatement end)  {
        return new StringSubstr(string, from, end);
    }

    public static ValueStatement StringSubstring(ValueStatement string, ValueStatement start)  {
        return new StringSubstring(string, start);
    }

    public static ValueStatement StringSubstring(ValueStatement string, ValueStatement start, ValueStatement end)  {
        return new StringSubstring(string, start, end);
    }

    public static ValueStatement StringSplit(ValueStatement string, ValueStatement splitter)  {
        return new StringSplit(string, splitter);
    }

    public static ValueStatement StringSplit(ValueStatement string, ValueStatement splitter, ValueStatement limit)  {
        return new StringSplit(string, splitter, limit);
    }

    public static ValueStatement Format(ValueStatement format, ValueStatement object) {
        return new FormatValue(format, object);
    }

    // NUMBER

    public static ValueStatement ParseFloat(ValueStatement valueStatement)  {
        return new ParseFloatValue(valueStatement);
    }

    public static ValueStatement ParseInt(ValueStatement valueStatement)  {
        return new ParseIntegerValue(valueStatement);
    }

    // Object

    public static ValueStatement ObjectProperty(ValueStatement object, String memberNameOrPath)  {
        return new ObjectPropertyValue(object, V.Const(memberNameOrPath));
    }

    public static ValueStatement ObjectProperty(ValueStatement object, ValueStatement memberNameOrPath)  {
        return new ObjectPropertyValue(object, memberNameOrPath);
    }

    // Array

    /**
     * Checks if the value is an array.
     *
     * @param value
     * @return
     * @
     */
    public static ValueStatement IsArray(ValueStatement value)  {
        return new ArrayIsArrayValue(value);
    }

    /**
     * Returns the length of the array.
     *
     * @param array
     * @return
     * @
     */
    public static ValueStatement ArrayLength(ValueStatement array)  {
        return new ArrayLengthValue(array);
    }

    /**
     * Creates an array of the specified length.
     *
     * @param length
     * @return
     * @
     */
    public static ValueStatement Array(ValueStatement length)  {
        return new ArrayValue(length);
    }

    /**
     * Creates an array from the specified values.
     *
     * @param valueStatements
     * @return
     * @
     */
    public static ValueStatement ArrayOf(ValueStatement... valueStatements)  {
        return new ArrayOfValue(valueStatements);
    }

    /**
     * Returns the keys of the array.
     *
     * @param array
     * @return
     * @
     */
    public static ValueStatement ArrayKeys(ValueStatement array)  {
        return new ArrayKeysValue(array);
    }

    /**
     * Returns the values of the array.
     *
     * @param array
     * @return
     * @
     */
    public static ValueStatement ArrayValues(ValueStatement array)  {
        return new ArrayValuesValue(array);
    }

    /**
     * Merges two or more arrays. This method does not change the existing arrays, but instead returns a new array.
     *
     * @param arrays
     * @return
     * @
     */
    public static ValueStatement ArrayConcat(ValueStatement... arrays)  {
        return new ArrayConcatValue(arrays);
    }

    /**
     * Shallow copies part of an array to another location in the same array and returns it without modifying its length.
     *
     * @param array
     * @param target
     * @return
     * @
     */
    public static ValueStatement ArrayCopyWithin(ValueStatement array, ValueStatement target) throws WidgetConfigurationException {
        return new ArrayCopyWithinValue(array, target);
    }

    /**
     * Shallow copies part of an array to another location in the same array and returns it without modifying its length.
     *
     * @param array
     * @param target
     * @param start
     * @return
     * @
     */
    public static ValueStatement ArrayCopyWithin(ValueStatement array, ValueStatement target, ValueStatement start) throws WidgetConfigurationException {
        return new ArrayCopyWithinValue(array, target, start);
    }

    /**
     * Shallow copies part of an array to another location in the same array and returns it without modifying its length.
     *
     * @param array
     * @param target
     * @param start
     * @param end
     * @return
     * @
     */
    public static ValueStatement ArrayCopyWithin(ValueStatement array, ValueStatement target, ValueStatement start, ValueStatement end) throws WidgetConfigurationException {
        return new ArrayCopyWithinValue(array, target, start, end);
    }

    /**
     * Returns true if every element in the array matches using the specified predicate.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArrayEvery(ValueStatement array, PredicateStatement predicate)  {
        return new ArrayEveryValue(array, predicate);
    }

    /**
     * Returns true if at least one element in the array matches using the specified predicate.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArraySome(ValueStatement array, PredicateStatement predicate)  {
        return new ArraySomeValue(array, predicate);
    }

    /**
     * Returns a new array which is the specified array filtered using the specified predicate.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArrayFilter(ValueStatement array, PredicateStatement predicate)  {
        return new ArrayEveryValue(array, predicate);
    }

    /**
     * Map followed by Flat(1).
     *
     * @param array
     * @param filter
     * @return
     * @
     */
    public static ValueStatement ArrayFlatMap(ValueStatement array, MethodStatement filter)  {
        return new ArrayFlatMapValue(array, filter);
    }

    /**
     * Creates a new array using a map function on each element of the array.
     *
     * @param array
     * @param filter
     * @return
     * @
     */
    public static ValueStatement ArrayMap(ValueStatement array, MethodStatement filter)  {
        return new ArrayMapValue(array, filter);
    }

    /**
     * Reverses the array.
     *
     * @param array
     * @return
     * @
     */
    public static ValueStatement ArrayReverse(ValueStatement array)  {
        return new ArrayReverseValue(array);
    }

    /**
     * Sorts the array.
     *
     * @param array
     * @return
     * @
     */
    public static ValueStatement ArraySort(ValueStatement array)  {
        return new ArraySortValue(array);
    }

    /**
     * Fills the array with the specified value.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ValueStatement ArrayFill(ValueStatement array, ValueStatement value) throws WidgetConfigurationException {
        return new ArrayFillValue(array, value);
    }

    /**
     * Fills the array with the specified value.
     *
     * @param array
     * @param value
     * @param start
     * @return
     * @
     */
    public static ValueStatement ArrayFill(ValueStatement array, ValueStatement value, ValueStatement start) throws WidgetConfigurationException {
        return new ArrayFillValue(array, value, start);
    }

    /**
     * Fills the array with the specified value.
     *
     * @param array
     * @param value
     * @param start
     * @return
     * @
     */
    public static ValueStatement ArrayFill(ValueStatement array, ValueStatement value, ValueStatement start, ValueStatement end) throws WidgetConfigurationException {
        return new ArrayFillValue(array, value, start, end);
    }

    /**
     * Returns true if the array contains the specified value.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ValueStatement ArrayIncludes(ValueStatement array, ValueStatement value)  {
        return new ArrayIncludesValue(array, value);
    }

    /**
     * Returns the first occurrence of the specified value or -1.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ValueStatement ArrayIndexOf(ValueStatement array, ValueStatement value)  {
        return new ArrayIndexOfValue(array, value);
    }

    /**
     * Returns the last occurrence of the specified value or -1.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ValueStatement ArrayLastIndexOf(ValueStatement array, ValueStatement value)  {
        return new ArrayLastIndexOfValue(array, value);
    }

    /**
     * Finds a value using the specified predicate and returns the value.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArrayFind(ValueStatement array, PredicateStatement predicate)  {
        return new ArrayFindValue(array, predicate);
    }

    /**
     * Finds the first occurrence of a value using the specified predicate and returns the index or -1.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArrayFindIndex(ValueStatement array, PredicateStatement predicate)  {
        return new ArrayFindIndexValue(array, predicate);
    }

    /**
     * Finds the last value using the specified predicate and returns the value.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArrayFindLast(ValueStatement array, PredicateStatement predicate)  {
        return new ArrayFindLastValue(array, predicate);
    }

    /**
     * Finds the last occurrence of a value using the specified predicate and returns the index or -1.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArrayFindLastIndex(ValueStatement array, PredicateStatement predicate)  {
        return new ArrayFindLastIndexValue(array, predicate);
    }

    public static ValueStatement ArrayForEach(ValueStatement array, MethodStatement method)  {
        return new ArrayForEachValue(array, method);
    }

    public static ValueStatement ArrayJoin(ValueStatement array, ValueStatement value)  {
        return new ArrayJoinValue(array, value);
    }

    public static ValueStatement ArrayPop(ValueStatement array)  {
        return new ArrayPopValue(array);
    }

    public static ValueStatement ArrayPush(ValueStatement array, ValueStatement... values)  {
        return new ArrayPushValue(array, values);
    }

    public static ValueStatement ArrayReduce(ValueStatement array, ValueStatement reducer) throws WidgetConfigurationException {
        return new ArrayReduceValue(array, reducer);
    }

    public static ValueStatement ArrayReduce(ValueStatement array, ValueStatement reducer, ValueStatement initialValue) throws WidgetConfigurationException {
        return new ArrayReduceValue(array, reducer, initialValue);
    }

    public static ValueStatement ArrayReduceRight(ValueStatement array, ValueStatement reducer) throws WidgetConfigurationException {
        return new ArrayReduceRightValue(array, reducer);
    }

    public static ValueStatement ArrayReduceRight(ValueStatement array, ValueStatement reducer, ValueStatement initialValue) throws WidgetConfigurationException {
        return new ArrayReduceRightValue(array, reducer, initialValue);
    }

    public static ValueStatement ArrayShift(ValueStatement array)  {
        return new ArrayShiftValue(array);
    }

    public static ValueStatement ArrayUnshift(ValueStatement array, ValueStatement... values)  {
        return new ArrayUnshiftValue(array, values);
    }

    public static ValueStatement ArraySlice(ValueStatement array)  {
        return new ArraySliceValue(array);
    }

    public static ValueStatement ArraySlice(ValueStatement array, ValueStatement start)  {
        return new ArraySliceValue(array, start);
    }

    public static ValueStatement ArraySlice(ValueStatement array, ValueStatement start, ValueStatement end)  {
        return new ArraySliceValue(array, start, end);
    }

    public static ValueStatement ArraySplice(ValueStatement array, ValueStatement start)  {
        return new ArraySpliceValue(array, start);
    }

    public static ValueStatement ArraySplice(ValueStatement array, ValueStatement start, ValueStatement deleteCount)  {
        return new ArraySpliceValue(array, start, deleteCount);
    }

    public static ValueStatement ArraySplice(ValueStatement array, ValueStatement start, ValueStatement deleteCount, ValueStatement... values)  {
        return new ArraySpliceValue(array, start, deleteCount, values);
    }

    //

    public static ValueStatement GetGlobalValue(String key)  {
        return new GetGlobalValue(V.Const(key));
    }

    public static ValueStatement GetGlobalValue(ValueStatement key)  {
        return new GetGlobalValue(key);
    }

    public static ValueStatement GetGlobalValue(String key, ValueStatement defaultValue)  {
        return new GetGlobalValue(V.Const(key), defaultValue);
    }

    public static ValueStatement GetGlobalValue(ValueStatement key, ValueStatement defaultValue)  {
        return new GetGlobalValue(key, defaultValue);
    }

    public static ValueStatement GetValue(Widget widget)  {
        return new GetValueValue(widget);
    }

    public static ValueStatement IsSelected(String name) {
        return new IsSelectionValue(name);
    }

    public static ValueStatement GetSelection(String name) {
        return new GetSelectionValue(name);
    }

    public static ValueStatement GetSelection(String name, int top)  {
        return new GetSelectionValue(name, top);
    }

    public static ValueStatement GetPage(Class<? extends Page> pageClass) {
        return new GetPageValue(pageClass);
    }

    public static ValueStatement GetPage(Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new GetPageValue(pageClass, valueStatement);
    }

    public static ValueStatement GetDisplay(Widget widget) {
        return new GetDisplayValue(widget);
    }

    public static ValueStatement GetEnabled(Widget widget) {
        return new GetEnabledValue(widget);
    }

    public static ValueStatement GetPageArgument() {
        return new GetPageArgumentValue();
    }

    public static ValueStatement GetJSON(ValueStatement valueStatement) {
        return new GetJSONValue(valueStatement);
    }

    public static ValueStatement ParseJSON(ValueStatement valueStatement) {
        return new ParseJSONValue(valueStatement);
    }

    // DATE

    public static ValueStatement DateNow() {
        return new DateNowValue();
    }

    public static ValueStatement ParseDate(ValueStatement valueStatement) {
        return new ParseDateValue(valueStatement);
    }

    public static ValueStatement Date(ValueStatement dateValue)  {
        return new DateCreateValue(dateValue);
    }

    public static ValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex)  {
        return new DateCreateValue(
                year,
                monthIndex);
    }

    public static ValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day)  {
        return new DateCreateValue(
                year,
                monthIndex,
                day);
    }

    public static ValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour)  {
        return new DateCreateValue(
                year,
                monthIndex,
                day,
                hour);
    }

    public static ValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute)  {
        return new DateCreateValue(
                year,
                monthIndex,
                day,
                hour,
                minute);
    }

    public static ValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second)  {
        return new DateCreateValue(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second);
    }

    public static ValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second,
            ValueStatement milliSecond)  {
        return new DateCreateValue(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second,
                milliSecond);
    }

    public static ValueStatement ModifyDate(ValueStatement date, ValueStatement value)  {
        return new DateModifyValue(date, value);
    }

    public static ValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex)  {
        return new DateUTCValue(
                year,
                monthIndex);
    }

    public static ValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day)  {
        return new DateUTCValue(
                year,
                monthIndex,
                day);
    }

    public static ValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour)  {
        return new DateUTCValue(
                year,
                monthIndex,
                day,
                hour);
    }

    public static ValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute)  {
        return new DateUTCValue(
                year,
                monthIndex,
                day,
                hour,
                minute);
    }

    public static ValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second)  {
        return new DateUTCValue(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second);
    }

    public static ValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second,
            ValueStatement milliSecond)  {
        return new DateUTCValue(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second,
                milliSecond);
    }

    public static ValueStatement Year(ValueStatement date) {
        return new DateYearValue(date, false);
    }

    public static ValueStatement Year(ValueStatement date, boolean utc) {
        return new DateYearValue(date, utc);
    }

    public static ValueStatement Month(ValueStatement date) {
        return new DateMonthValue(date, false);
    }

    public static ValueStatement Month(ValueStatement date, boolean utc) {
        return new DateMonthValue(date, utc);
    }

    public static ValueStatement DayOfMonth(ValueStatement date) {
        return new DateDayOfMonthValue(date, false);
    }

    public static ValueStatement DayOfMonth(ValueStatement date, boolean utc) {
        return new DateDayOfMonthValue(date, utc);
    }

    public static ValueStatement DayOfWeek(ValueStatement date) {
        return new DateDayOfWeekValue(date, false);
    }

    public static ValueStatement DayOfWeek(ValueStatement date, boolean utc) {
        return new DateDayOfWeekValue(date, utc);
    }

    public static ValueStatement Hour(ValueStatement date) {
        return new DateHourValue(date, false);
    }

    public static ValueStatement Hour(ValueStatement date, boolean utc) {
        return new DateHourValue(date, utc);
    }

    public static ValueStatement Minute(ValueStatement date) {
        return new DateMinuteValue(date, false);
    }

    public static ValueStatement Minute(ValueStatement date, boolean utc) {
        return new DateMinuteValue(date, utc);
    }

    public static ValueStatement Second(ValueStatement date) {
        return new DateSecondValue(date, false);
    }

    public static ValueStatement Second(ValueStatement date, boolean utc) {
        return new DateSecondValue(date, utc);
    }

    public static ValueStatement MilliSecond(ValueStatement date) {
        return new DateMilliSecondValue(date, false);
    }

    public static ValueStatement MilliSecond(ValueStatement date, boolean utc) {
        return new DateMilliSecondValue(date, utc);
    }

    public static ValueStatement TimeZoneOffset(ValueStatement date) {
        return new DateTimeZoneOffsetValue(date);
    }

    public static ValueStatement DateValue(ValueStatement date) {
        return new DateValueValue(date);
    }

    public static ValueStatement WeekOfYear(ValueStatement date) {
        return new DateWeekOfYearValue(date);
    }

    public static ValueStatement WeekOfYearString(ValueStatement date) {
        return new WeekOfYearStringFromDateValue(date);
    }

    /**
     * Returns the first day of the specified week in the specified year.
     * This returns the ISO8601 definition which means that the first day
     * is a Monday.
     *
     * @param weekOfYear a Javascript 'week-string', i.e. "2022-W04"
     * @return
     */
    public static ValueStatement WeekOfYearToDate(ValueStatement weekOfYear) {
        return new DateFromWeekOfYearValue(weekOfYear);
    }

    public static ValueStatement DateDiff(ValueStatement date0, ValueStatement date1, TimeIntervalType timeIntervalType) {
        return new DateDiffValue(date0, date1, timeIntervalType);
    }

    // MATH

    public static ValueStatement Sum(ValueStatement left, ValueStatement right) {
        return new MathSumValue(left, right);
    }

    public static ValueStatement Subtract(ValueStatement left, ValueStatement right) {
        return new MathSubtractionValue(left, right);
    }

    public static ValueStatement Product(ValueStatement left, ValueStatement right) {
        return new MathProductValue(left, right);
    }

    public static ValueStatement Division(ValueStatement left, ValueStatement right) {
        return new MathValue(left, right);
    }

    // CONVERSION TO BOOLEAN

    public static ValueStatement And(ValueStatement... values) {
        return new AndValue(values);
    }

    public static ValueStatement Or(ValueStatement... values) {
        return new OrValue(values);
    }

    public static ValueStatement Is(ValueStatement left, ValueStatement right) {
        return new Is(left, right);
    }

    public static ValueStatement IsEmpty(ValueStatement value) {
        return new IsEmpty(value);
    }

    public static ValueStatement IsNotEmpty(ValueStatement value) {
        return new IsNotEmpty(value);
    }

    public static ValueStatement Contains(ValueStatement left, ValueStatement right) {
        return new Contains(left, right);
    }

    public static ValueStatement StartsWith(ValueStatement left, ValueStatement right) {
        return new StartsWith(left, right);
    }

    public static ValueStatement EndsWith(ValueStatement left, ValueStatement right) {
        return new EndsWith(left, right);
    }

    public static ValueStatement IsFocused(Widget widget) {
        return new IsFocused(widget);
    }

    // MISCELLANEOUS

    public static ValueStatement Platform() {
        return new PlatformNameValue();
    }

    public static ValueStatement IsMac() {
        return new Is(new PlatformNameValue(), Const(GetOsName.MACOS));
    }

    public static ValueStatement Browser() {
        return new BrowserNameValue();
    }

    public static ValueStatement Call(Code code, String name, ValueStatement... parameterValues) {
        return new CallValue(code, name, parameterValues);
    }

    public static ValueStatement Code(String js, JsType jsType) {
        return new CodeValue(js, jsType);
    }

    public static ValueStatement GetAuthorizationHeaderBearer()  {
        return new GetAuthorizationHeaderBearerValue();
    }

    public static ValueStatement Random() {
        return new RandomValue();
    }

    public static ValueStatement Random(ValueStatement max) {
        return new RandomValue(max);
    }

    public static ValueStatement GetQueryStringAsObject(ValueStatement url) {
        return new GetQueryStringAsObjectValue(url);
    }

    public static ValueStatement GetDataKey() {
        return new GetDataKeyValue();
    }

    public static ValueStatement GetDataKey(ValueStatement defaultValue) {
        return new GetDataKeyValue(defaultValue);
    }

    public static ValueStatement GetPageVariable(String name) {
        return GetPageVariable(V.Const(name));
    }

    public static ValueStatement GetPageVariable(ValueStatement name) {
        return new GetPageVariableValue(name);
    }

}
