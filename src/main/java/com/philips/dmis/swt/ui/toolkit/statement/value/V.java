package com.philips.dmis.swt.ui.toolkit.statement.value;

import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.js.global.GetOsName;
import com.philips.dmis.swt.ui.toolkit.js.global.JsGlobalModule;
import com.philips.dmis.swt.ui.toolkit.statement.Description;
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

    @Description("Returns Boolean True")
    public static final ConstantValueStatement True = Const(JsGlobalModule.TRUE);
    @Description("Returns Boolean False")
    public static final ConstantValueStatement False = Const(JsGlobalModule.FALSE);
    @Description("Returns Integer 0")
    public static final ConstantValueStatement Zero = Const(0);
    @Description("Returns Integer 1")
    public static final ConstantValueStatement One = Const(1);
    @Description("Returns OK")
    public static final ConstantValueStatement Ok = Const(JsGlobalModule.OK);
    @Description("Returns Cancel")
    public static final ConstantValueStatement Cancel = Const(JsGlobalModule.CANCEL);
    @Description("Returns Yes")
    public static final ConstantValueStatement Yes = Const(JsGlobalModule.YES);
    @Description("Returns No")
    public static final ConstantValueStatement No = Const(JsGlobalModule.NO);
    @Description("Returns 1 second timestamp")
    public static final ConstantValueStatement OneSecond = Const(SECOND);
    @Description("Returns 1 minute timestamp")
    public static final ConstantValueStatement OneMinute = Const(MINUTE);
    @Description("Returns 1 hour timestamp")
    public static final ConstantValueStatement OneHour = Const(HOUR);
    @Description("Returns 1 day timestamp")
    public static final ConstantValueStatement OneDay = Const(DAY);
    @Description("Returns 1 week timestamp")
    public static final ConstantValueStatement OneWeek = Const(WEEK);

    private V() {
    }

    // HTTP ERRORS

    @Description("Returns 200")
    public static ConstantValueStatement HTTP_OK() {
        return new ConstantValueStatement(200);
    }

    @Description("Returns 400")
    public static ConstantValueStatement HTTP_BAD_REQUEST() {
        return new ConstantValueStatement(400);
    }

    @Description("Returns 401")
    public static ConstantValueStatement HTTP_UNAUTHORIZED() {
        return new ConstantValueStatement(401);
    }

    @Description("Returns 403")
    public static ConstantValueStatement HTTP_FORBIDDEN() {
        return new ConstantValueStatement(403);
    }

    @Description("Returns 404")
    public static ConstantValueStatement HTTP_NOT_FOUND() {
        return new ConstantValueStatement(404);
    }

    @Description("Returns 405")
    public static ConstantValueStatement HTTP_METHOD_NOT_ALLOWED() {
        return new ConstantValueStatement(405);
    }

    @Description("Returns 406")
    public static ConstantValueStatement HTTP_NOT_ACCEPTABLE() {
        return new ConstantValueStatement(406);
    }

    @Description("Returns 408")
    public static ConstantValueStatement HTTP_REQUEST_TIMEOUT() {
        return new ConstantValueStatement(408);
    }

    @Description("Returns 500")
    public static ConstantValueStatement HTTP_SERVER_ERROR() {
        return new ConstantValueStatement(500);
    }

    // REF

    public static ReferenceValue Reference(String name) {
        return new ReferenceValue(name);
    }

    // Constant

    @Description("Returns an empty string")
    public static ConstantValueStatement Empty() {
        return new ConstantValueStatement("");
    }

    @Description("Returns an integer")
    public static ConstantValueStatement Const(int value) {
        return new ConstantValueStatement(value);
    }

    @Description("Returns a long")
    public static ConstantValueStatement Const(long value) {
        return new ConstantValueStatement(value);
    }

    @Description("Returns a double")
    public static ConstantValueStatement Const(double value) {
        return new ConstantValueStatement(value);
    }

    @Description("Returns a boolean")
    public static ConstantValueStatement Const(boolean value) {
        return new ConstantValueStatement(value);
    }

    @Description("Returns a string")
    public static ConstantValueStatement Const(String value) {
        return new ConstantValueStatement(value);
    }

    @Description("Returns an array containing the provided values")
    public static ConstantValueStatement Const(Object[] value) {
        return new ConstantValueStatement(value);
    }

    @Description("Returns an array containing the provided values")
    public static ConstantValueStatement Const(Iterable<?> value) {
        return new ConstantValueStatement(value);
    }

    @Description("Returns a constant of the specified type and value")
    public static ConstantValueStatement Const(Object value, JsType jsType) {
        return new ConstantValueStatement(value, jsType);
    }

    // Local

    public static GetLocalValueStatement Key() {
        return new GetLocalValueStatement("key");
    }

    public static GetLocalValueStatement Value() {
        return new GetLocalValueStatement("value");
    }

    public static GetLocalValueStatement Local(String name) {
        return new GetLocalValueStatement(name);
    }

    public static GetThisValueStatement This(String name) {
        return new GetThisValueStatement(V.Const(name));
    }

    public static GetThisValueStatement This(ValueStatement name) {
        return new GetThisValueStatement(name);
    }


    // Event

    public static GetEventContextValueStatement GetEventContext() {
        return new GetEventContextValueStatement();
    }

    public static GetEventContextValueStatement GetEventContext(ValueStatement property) {
        return new GetEventContextValueStatement(property);
    }

    public static GetEventValueStatement GetEvent() {
        return new GetEventValueStatement();
    }

    public static GetEventValueStatement GetEvent(ValueStatement property) {
        return new GetEventValueStatement(property);
    }

    // String

    public static ValueStatement ToLowerCase(ValueStatement string) {
        return new ToLowerCaseValueStatement(string);
    }

    public static ValueStatement ToUpperCase(ValueStatement string) {
        return new ToUpperCaseValueStatement(string);
    }

    public static ValueStatement At(ValueStatement string, ValueStatement index) {
        return new AtValueStatement(string, index);
    }

    public static ValueStatement CharCodeAt(ValueStatement string, ValueStatement index) {
        return new CharCodeAtValueStatement(string, index);
    }

    public static ValueStatement CodePointAt(ValueStatement string, ValueStatement index) {
        return new CodePointAtValueStatement(string, index);
    }

    public static ValueStatement StringConcat(ValueStatement... valueStatements) {
        return new ConcatValueStatement(valueStatements);
    }

    public static ValueStatement StringIncludes(ValueStatement string, ValueStatement value) {
        return new StringIncludesValueStatement(string, value);
    }

    public static ValueStatement StringIndexOf(ValueStatement string, ValueStatement value) {
        return new StringIndexOfValueStatement(string, value);
    }

    public static ValueStatement StringLastIndexOf(ValueStatement string, ValueStatement value) {
        return new StringLastIndexOfValueStatement(string, value);
    }

    public static ValueStatement StringLength(ValueStatement string) {
        return new StringLengthValueStatement(string);
    }

    public static ValueStatement StringRepeat(ValueStatement string, ValueStatement value) {
        return new StringRepeatValueStatement(string, value);
    }

    public static ValueStatement PadStart(ValueStatement string, ValueStatement maxLength) {
        return new PadStartValueStatement(string, maxLength);
    }

    public static ValueStatement PadStart(ValueStatement string, ValueStatement maxLength, ValueStatement fillString) {
        return new PadStartValueStatement(string, maxLength, fillString);
    }

    public static ValueStatement PadEnd(ValueStatement string, ValueStatement maxLength) {
        return new PadEndValueStatement(string, maxLength);
    }

    public static ValueStatement PadEnd(ValueStatement string, ValueStatement maxLength, ValueStatement fillString) {
        return new PadEndValueStatement(string, maxLength, fillString);
    }

    public static ValueStatement Trim(ValueStatement string) {
        return new TrimValueStatement(string);
    }

    public static ValueStatement TrimStart(ValueStatement string) {
        return new TrimStartValueStatement(string);
    }

    public static ValueStatement TrimEnd(ValueStatement string) {
        return new TrimEndValueStatement(string);
    }

    public static ValueStatement TrimLeft(ValueStatement string) {
        return new TrimLeftValueStatement(string);
    }

    public static ValueStatement TrimRight(ValueStatement string) {
        return new TrimRightValueStatement(string);
    }

    public static ValueStatement StringMatch(ValueStatement string, ValueStatement regEx) {
        return new StringMatchValueStatement(string, regEx);
    }

    public static ValueStatement StringMatchAll(ValueStatement string, ValueStatement regEx) {
        return new StringMatchAllValueStatement(string, regEx);
    }

    public static ValueStatement StringSearch(ValueStatement string, ValueStatement regEx) {
        return new StringSearchValueStatement(string, regEx);
    }

    public static ValueStatement StringReplace(ValueStatement string, ValueStatement search, ValueStatement value) {
        return new StringReplaceValueStatement(string, search, value);
    }

    public static ValueStatement StringReplaceAll(ValueStatement string, ValueStatement search, ValueStatement value) {
        return new StringReplaceAllValueStatement(string, search, value);
    }

    public static ValueStatement StringSlice(ValueStatement string, ValueStatement start) {
        return new StringSliceValueStatement(string, start);
    }

    public static ValueStatement StringSlice(ValueStatement string, ValueStatement start, ValueStatement end) {
        return new StringSliceValueStatement(string, start, end);
    }

    public static ValueStatement StringSubstr(ValueStatement string, ValueStatement from) {
        return new StringSubstrValueStatement(string, from);
    }

    public static ValueStatement StringSubstr(ValueStatement string, ValueStatement from, ValueStatement end) {
        return new StringSubstrValueStatement(string, from, end);
    }

    public static ValueStatement StringSubstring(ValueStatement string, ValueStatement start) {
        return new StringSubstringValueStatement(string, start);
    }

    public static ValueStatement StringSubstring(ValueStatement string, ValueStatement start, ValueStatement end) {
        return new StringSubstringValueStatement(string, start, end);
    }

    public static ValueStatement StringSplit(ValueStatement string, ValueStatement splitter) {
        return new StringSplitValueStatement(string, splitter);
    }

    public static ValueStatement StringSplit(ValueStatement string, ValueStatement splitter, ValueStatement limit) {
        return new StringSplitValueStatement(string, splitter, limit);
    }

    public static ValueStatement Format(ValueStatement format, ValueStatement object) {
        return new FormatValueStatement(format, object);
    }

    // NUMBER

    public static ValueStatement ParseFloat(ValueStatement valueStatement) {
        return new ParseFloatValueStatement(valueStatement);
    }

    public static ValueStatement ParseInt(ValueStatement valueStatement) {
        return new ParseIntegerValueStatement(valueStatement);
    }

    // Object

    public static ValueStatement ObjectProperty(ValueStatement object, String memberNameOrPath) {
        return new ObjectPropertyValueStatement(object, V.Const(memberNameOrPath));
    }

    public static ValueStatement ObjectProperty(ValueStatement object, ValueStatement memberNameOrPath) {
        return new ObjectPropertyValueStatement(object, memberNameOrPath);
    }

    // Array

    /**
     * Checks if the value is an array.
     *
     * @param value
     * @return
     * @
     */
    public static ArrayIsArrayValueStatement IsArray(ValueStatement value) {
        return new ArrayIsArrayValueStatement(value);
    }

    /**
     * Returns the length of the array.
     *
     * @param array
     * @return
     * @
     */
    public static ArrayLengthValueStatement ArrayLength(ValueStatement array) {
        return new ArrayLengthValueStatement(array);
    }

    /**
     * Creates an array of the specified length.
     *
     * @param length
     * @return
     * @
     */
    public static ArrayWithLengthValueStatement ArrayWithLength(ValueStatement length) {
        return new ArrayWithLengthValueStatement(length);
    }

    /**
     * Creates an array.
     *
     * @return
     * @
     */
    public static ArrayValueStatement Array() {
        return new ArrayValueStatement();
    }

    /**
     * Creates an array from the specified values.
     *
     * @param valueStatements
     * @return
     * @
     */
    public static ArrayOfValueStatement ArrayOf(ValueStatement... valueStatements) {
        return new ArrayOfValueStatement(valueStatements);
    }

    /**
     * Returns the keys of the array.
     *
     * @param array
     * @return
     * @
     */
    public static ArrayKeysValueStatement ArrayKeys(ValueStatement array) {
        return new ArrayKeysValueStatement(array);
    }

    /**
     * Returns the values of the array.
     *
     * @param array
     * @return
     * @
     */
    public static ArrayValuesValueStatement ArrayValues(ValueStatement array) {
        return new ArrayValuesValueStatement(array);
    }

    /**
     * Merges two or more arrays. This method does not change the existing arrays, but instead returns a new array.
     *
     * @param arrays
     * @return
     * @
     */
    public static ArrayConcatValueStatement ArrayConcat(ValueStatement... arrays) {
        return new ArrayConcatValueStatement(arrays);
    }

    /**
     * Shallow copies part of an array to another location in the same array and returns it without modifying its length.
     *
     * @param array
     * @param target
     * @return
     * @
     */
    public static ArrayCopyWithinValueStatement ArrayCopyWithin(ValueStatement array, ValueStatement target) throws WidgetConfigurationException {
        return new ArrayCopyWithinValueStatement(array, target);
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
    public static ArrayCopyWithinValueStatement ArrayCopyWithin(ValueStatement array, ValueStatement target, ValueStatement start) throws WidgetConfigurationException {
        return new ArrayCopyWithinValueStatement(array, target, start);
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
    public static ArrayCopyWithinValueStatement ArrayCopyWithin(ValueStatement array, ValueStatement target, ValueStatement start, ValueStatement end) throws WidgetConfigurationException {
        return new ArrayCopyWithinValueStatement(array, target, start, end);
    }

    /**
     * Returns true if every element in the array matches using the specified predicate.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ArrayEveryValueStatement ArrayEvery(ValueStatement array, PredicateStatement predicate) {
        return new ArrayEveryValueStatement(array, predicate);
    }


    /**
     * Returns true if at least one element in the array matches using the specified predicate.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ValueStatement ArraySome(ValueStatement array, PredicateStatement predicate) {
        return new ArraySomeValueStatement(array, predicate);
    }

    /**
     * Returns a new array which is the specified array filtered using the specified predicate.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ArrayFilterValueStatement ArrayFilter(ValueStatement array, PredicateStatement predicate) {
        return new ArrayFilterValueStatement(array, predicate);
    }

    /**
     * Performs a map function followed by Flat(1).
     *
     * @param array
     * @param filter
     * @return
     * @
     */
    public static ArrayFlatMapValueStatement ArrayFlatMap(ValueStatement array, MethodStatement filter) {
        return new ArrayFlatMapValueStatement(array, filter);
    }

    /**
     * Creates a new array using a map function on each element of the array.
     *
     * @param array
     * @param filter
     * @return
     * @
     */
    public static ArrayMapValueStatement ArrayMap(ValueStatement array, MethodStatement filter) {
        return new ArrayMapValueStatement(array, filter);
    }

    /**
     * Reverses the array.
     *
     * @param array
     * @return
     * @
     */
    public static ArrayReverseValueStatement ArrayReverse(ValueStatement array) {
        return new ArrayReverseValueStatement(array);
    }

    /**
     * Sorts the array.
     *
     * @param array
     * @return
     * @
     */
    public static ArraySortValueStatement ArraySort(ValueStatement array) {
        return new ArraySortValueStatement(array);
    }

    /**
     * Fills the array with the specified value.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ArrayFillValueStatement ArrayFill(ValueStatement array, ValueStatement value) throws WidgetConfigurationException {
        return new ArrayFillValueStatement(array, value);
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
    public static ArrayFillValueStatement ArrayFill(ValueStatement array, ValueStatement value, ValueStatement start) throws WidgetConfigurationException {
        return new ArrayFillValueStatement(array, value, start);
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
    public static ArrayFillValueStatement ArrayFill(ValueStatement array, ValueStatement value, ValueStatement start, ValueStatement end) throws WidgetConfigurationException {
        return new ArrayFillValueStatement(array, value, start, end);
    }

    /**
     * Returns true if the array contains the specified value.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ArrayIncludesValueStatement ArrayIncludes(ValueStatement array, ValueStatement value) {
        return new ArrayIncludesValueStatement(array, value);
    }

    /**
     * Returns the first occurrence of the specified value or -1.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ArrayIndexOfValueStatement ArrayIndexOf(ValueStatement array, ValueStatement value) {
        return new ArrayIndexOfValueStatement(array, value);
    }

    /**
     * Returns the last occurrence of the specified value or -1.
     *
     * @param array
     * @param value
     * @return
     * @
     */
    public static ArrayLastIndexOfValueStatement ArrayLastIndexOf(ValueStatement array, ValueStatement value) {
        return new ArrayLastIndexOfValueStatement(array, value);
    }

    /**
     * Finds a value using the specified predicate and returns the value.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ArrayFindValueStatement ArrayFind(ValueStatement array, PredicateStatement predicate) {
        return new ArrayFindValueStatement(array, predicate);
    }

    /**
     * Finds the first occurrence of a value using the specified predicate and returns the index or -1.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ArrayFindIndexValueStatement ArrayFindIndex(ValueStatement array, PredicateStatement predicate) {
        return new ArrayFindIndexValueStatement(array, predicate);
    }

    /**
     * Finds the last value using the specified predicate and returns the value.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ArrayFindLastValueStatement ArrayFindLast(ValueStatement array, PredicateStatement predicate) {
        return new ArrayFindLastValueStatement(array, predicate);
    }

    /**
     * Finds the last occurrence of a value using the specified predicate and returns the index or -1.
     *
     * @param array
     * @param predicate
     * @return
     * @
     */
    public static ArrayFindLastIndexValueStatement ArrayFindLastIndex(ValueStatement array, PredicateStatement predicate) {
        return new ArrayFindLastIndexValueStatement(array, predicate);
    }

    /**
     * Performs the provided method statement for each of the elements in the array
     *
     * @param array
     * @param method
     * @return
     */
    public static ArrayForEachValueStatement ArrayForEach(ValueStatement array, MethodStatement method) {
        return new ArrayForEachValueStatement(array, method);
    }

    /**
     * Returns a string that is the concatenation of each element in the array separated by the provided value
     *
     * @param array
     * @param value
     * @return
     */
    public static ArrayJoinValueStatement ArrayJoin(ValueStatement array, ValueStatement value) {
        return new ArrayJoinValueStatement(array, value);
    }

    /**
     * Removes the last element in the array and returns that element
     *
     * @param array
     * @return
     */
    public static ArrayPopValueStatement ArrayPop(ValueStatement array) {
        return new ArrayPopValueStatement(array);
    }

    /**
     * Adds one or more elements to the end of the array and returns the new length
     *
     * @param array
     * @param values
     * @return
     */
    public static ArrayPushValueStatement ArrayPush(ValueStatement array, ValueStatement... values) {
        return new ArrayPushValueStatement(array, values);
    }

    /**
     * Executes a reducer function on each element of the array and finally returns the result
     *
     * @param array
     * @param reducer
     * @return
     * @throws WidgetConfigurationException
     */
    public static ArrayReduceValueStatement ArrayReduce(ValueStatement array, ValueStatement reducer) throws WidgetConfigurationException {
        return new ArrayReduceValueStatement(array, reducer);
    }

    /**
     * Executes a reducer function on each element of the array and finally returns the result
     *
     * @param array
     * @param reducer
     * @param initialValue
     * @return
     * @throws WidgetConfigurationException
     */
    public static ArrayReduceValueStatement ArrayReduce(ValueStatement array, ValueStatement reducer, ValueStatement initialValue) throws WidgetConfigurationException {
        return new ArrayReduceValueStatement(array, reducer, initialValue);
    }

    /**
     * Executes a reducer function on each element of the array from right to left and finally returns the result
     *
     * @param array
     * @param reducer
     * @return
     * @throws WidgetConfigurationException
     */
    public static ArrayReduceRightValueStatement ArrayReduceRight(ValueStatement array, ValueStatement reducer) throws WidgetConfigurationException {
        return new ArrayReduceRightValueStatement(array, reducer);
    }

    /**
     * Executes a reducer function on each element of the array from right to left and finally returns the result
     *
     * @param array
     * @param reducer
     * @param initialValue
     * @return
     * @throws WidgetConfigurationException
     */
    public static ArrayReduceRightValueStatement ArrayReduceRight(ValueStatement array, ValueStatement reducer, ValueStatement initialValue) throws WidgetConfigurationException {
        return new ArrayReduceRightValueStatement(array, reducer, initialValue);
    }

    /**
     * Removes the first element from an array and returns that element
     *
     * @param array
     * @return
     */
    public static ArrayShiftValueStatement ArrayShift(ValueStatement array) {
        return new ArrayShiftValueStatement(array);
    }

    /**
     * Adds one or more elements to the start of the array and returns the new length
     *
     * @param array
     * @param values
     * @return
     */
    public static ArrayUnshiftValueStatement ArrayUnshift(ValueStatement array, ValueStatement... values) {
        return new ArrayUnshiftValueStatement(array, values);
    }

    /**
     * Returns a shallow copy of an array or a part of it
     *
     * @param array
     * @return
     */
    public static ArraySliceValueStatement ArraySlice(ValueStatement array) {
        return new ArraySliceValueStatement(array);
    }

    /**
     * Returns a shallow copy of an array or a part of it
     *
     * @param array
     * @param start
     * @return
     */
    public static ArraySliceValueStatement ArraySlice(ValueStatement array, ValueStatement start) {
        return new ArraySliceValueStatement(array, start);
    }

    /**
     * Returns a shallow copy of an array or a part of it
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static ArraySliceValueStatement ArraySlice(ValueStatement array, ValueStatement start, ValueStatement end) {
        return new ArraySliceValueStatement(array, start, end);
    }

    /**
     * Removes or replaces existing elements and/or adds new elements to the array
     *
     * @param array
     * @param start
     * @return
     */
    public static ArraySpliceValueStatement ArraySplice(ValueStatement array, ValueStatement start) {
        return new ArraySpliceValueStatement(array, start);
    }

    /**
     * Removes or replaces existing elements and/or adds new elements to the array
     *
     * @param array
     * @param start
     * @param deleteCount
     * @return
     */
    public static ArraySpliceValueStatement ArraySplice(ValueStatement array, ValueStatement start, ValueStatement deleteCount) {
        return new ArraySpliceValueStatement(array, start, deleteCount);
    }

    /**
     * Removes or replaces existing elements and/or adds new elements to the array
     *
     * @param array
     * @param start
     * @param deleteCount
     * @param values
     * @return
     */
    public static ArraySpliceValueStatement ArraySplice(ValueStatement array, ValueStatement start, ValueStatement deleteCount, ValueStatement... values) {
        return new ArraySpliceValueStatement(array, start, deleteCount, values);
    }

    //

    /**
     * Returns a named value from the current session
     *
     * @param key
     * @return
     */
    public static GetGlobalValueStatement GetGlobalValue(String key) {
        return new GetGlobalValueStatement(V.Const(key));
    }

    /**
     * Returns a named value from the current session
     *
     * @param key
     * @return
     */
    public static GetGlobalValueStatement GetGlobalValue(ValueStatement key) {
        return new GetGlobalValueStatement(key);
    }

    /**
     * Returns a named value from the current session
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static GetGlobalValueStatement GetGlobalValue(String key, ValueStatement defaultValue) {
        return new GetGlobalValueStatement(V.Const(key), defaultValue);
    }

    /**
     * Returns a named value from the current session
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static GetGlobalValueStatement GetGlobalValue(ValueStatement key, ValueStatement defaultValue) {
        return new GetGlobalValueStatement(key, defaultValue);
    }

    /**
     * Returns the value of the specified widget
     *
     * @param hasValueType
     * @return
     */
    public static GetValueStatement GetValue(HasValueType<?> hasValueType) {
        return new GetValueStatement(hasValueType);
    }

    /**
     * Returns the return value of the provided HtmlDialog
     *
     * @param dialog
     * @return
     */
    public static GetReturnValueStatement GetReturnValue(HtmlDialog dialog) {
        return new GetReturnValueStatement(dialog);
    }

    // todo: remove?

    /**
     * @param name
     * @return
     */
    public static IsSelectionValueStatement IsSelected(String name) {
        return new IsSelectionValueStatement(name);
    }

    // todo: remove?

    /**
     * @param name
     * @return
     */
    public static GetSelectionValueStatement GetSelection(String name) {
        return new GetSelectionValueStatement(name);
    }

    // todo: remove?

    /**
     * @param name
     * @param top
     * @return
     */
    public static GetSelectionValueStatement GetSelection(String name, int top) {
        return new GetSelectionValueStatement(name, top);
    }

    /**
     * Returns a relative URL to open a Page
     *
     * @param pageClass
     * @return
     */
    public static GetPageValueStatement GetPage(Class<? extends Page> pageClass) {
        return new GetPageValueStatement(pageClass);
    }

    /**
     * Returns a relative URL to open a Page
     *
     * @param pageClass
     * @param valueStatement
     * @return
     */
    public static GetPageValueStatement GetPage(Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new GetPageValueStatement(pageClass, valueStatement);
    }

    /**
     * Returns a boolean indicating whether the provided widget is displayed
     *
     * @param widget
     * @return
     */
    public static IsDisplayedValueStatement GetDisplay(Widget widget) {
        return new IsDisplayedValueStatement(widget);
    }

    /**
     * Returns a boolean indicating whether the provided widget is enabled
     *
     * @param widget
     * @return
     */
    public static GetEnabledValueStatement GetEnabled(Widget widget) {
        return new GetEnabledValueStatement(widget);
    }

    /**
     * Returns the argument specified for the current page
     *
     * @return
     */
    public static GetPageArgumentValueStatement GetPageArgument() {
        return new GetPageArgumentValueStatement();
    }

    /**
     * Converts an object into a JSON string
     *
     * @param valueStatement
     * @return
     */
    public static GetJSONValueStatement GetJSON(ValueStatement valueStatement) {
        return new GetJSONValueStatement(valueStatement);
    }

    /**
     * Converts a JSON string into an object
     *
     * @param valueStatement
     * @return
     */
    public static ParseJSONValueStatement ParseJSON(ValueStatement valueStatement) {
        return new ParseJSONValueStatement(valueStatement);
    }

    // DATE

    /**
     * Returns the current date.
     *
     * @return
     */
    public static DateNowValueStatement DateNow() {
        return new DateNowValueStatement();
    }

    /**
     * Parses a string containing a date into a timestamp
     *
     * @param valueStatement
     * @return
     */
    public static ParseDateValueStatement ParseDate(ValueStatement valueStatement) {
        return new ParseDateValueStatement(valueStatement);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param dateValue
     * @return
     */
    public static DateCreateValueStatement Date(ValueStatement dateValue) {
        return new DateCreateValueStatement(dateValue);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @return
     */
    public static DateCreateValueStatement Date(ValueStatement year, ValueStatement monthIndex) {
        return new DateCreateValueStatement(
                year,
                monthIndex);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @return
     */
    public static DateCreateValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day) {
        return new DateCreateValueStatement(
                year,
                monthIndex,
                day);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @return
     */
    public static DateCreateValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour) {
        return new DateCreateValueStatement(
                year,
                monthIndex,
                day,
                hour);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @param minute
     * @return
     */
    public static DateCreateValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute) {
        return new DateCreateValueStatement(
                year,
                monthIndex,
                day,
                hour,
                minute);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static DateCreateValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second) {
        return new DateCreateValueStatement(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param milliSecond
     * @return
     */
    public static DateCreateValueStatement Date(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second,
            ValueStatement milliSecond) {
        return new DateCreateValueStatement(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second,
                milliSecond);
    }

    /**
     * Returns a new Date object that is the sum of the provided Date object and the provided timestamp
     *
     * @param date
     * @param value
     * @return
     */
    public static DateModifyValueStatement ModifyDate(ValueStatement date, ValueStatement value) {
        return new DateModifyValueStatement(date, value);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @return
     */
    public static DateUTCValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex) {
        return new DateUTCValueStatement(
                year,
                monthIndex);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @return
     */
    public static DateUTCValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day) {
        return new DateUTCValueStatement(
                year,
                monthIndex,
                day);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @return
     */
    public static DateUTCValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour) {
        return new DateUTCValueStatement(
                year,
                monthIndex,
                day,
                hour);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @param minute
     * @return
     */
    public static DateUTCValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute) {
        return new DateUTCValueStatement(
                year,
                monthIndex,
                day,
                hour,
                minute);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static DateUTCValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second) {
        return new DateUTCValueStatement(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second);
    }

    /**
     * Parses string values containing parts of a date into a Date object
     *
     * @param year
     * @param monthIndex
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param milliSecond
     * @return
     */
    public static DateUTCValueStatement DateUTC(
            ValueStatement year,
            ValueStatement monthIndex,
            ValueStatement day,
            ValueStatement hour,
            ValueStatement minute,
            ValueStatement second,
            ValueStatement milliSecond) {
        return new DateUTCValueStatement(
                year,
                monthIndex,
                day,
                hour,
                minute,
                second,
                milliSecond);
    }

    /**
     * Returns the full year of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateYearValueStatement Year(ValueStatement date) {
        return new DateYearValueStatement(date, false);
    }

    /**
     * Returns the full year of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static DateYearValueStatement Year(ValueStatement date, boolean utc) {
        return new DateYearValueStatement(date, utc);
    }

    /**
     * Returns the month of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateMonthValueStatement Month(ValueStatement date) {
        return new DateMonthValueStatement(date, false);
    }

    /**
     * Returns the month of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static DateMonthValueStatement Month(ValueStatement date, boolean utc) {
        return new DateMonthValueStatement(date, utc);
    }

    /**
     * Returns the day of the month of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateDayOfMonthValueStatement DayOfMonth(ValueStatement date) {
        return new DateDayOfMonthValueStatement(date, false);
    }

    /**
     * Returns the day of the month of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static ValueStatement DayOfMonth(ValueStatement date, boolean utc) {
        return new DateDayOfMonthValueStatement(date, utc);
    }

    /**
     * Returns the day of the week of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateDayOfWeekValueStatement DayOfWeek(ValueStatement date) {
        return new DateDayOfWeekValueStatement(date, false);
    }

    /**
     * Returns the day of the week of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static DateDayOfWeekValueStatement DayOfWeek(ValueStatement date, boolean utc) {
        return new DateDayOfWeekValueStatement(date, utc);
    }

    /**
     * Returns the hours of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateHourValueStatement Hour(ValueStatement date) {
        return new DateHourValueStatement(date, false);
    }

    /**
     * Returns the hours of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static DateHourValueStatement Hour(ValueStatement date, boolean utc) {
        return new DateHourValueStatement(date, utc);
    }

    /**
     * Returns the minutes of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateMinuteValueStatement Minute(ValueStatement date) {
        return new DateMinuteValueStatement(date, false);
    }

    /**
     * Returns the minutes of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static DateMinuteValueStatement Minute(ValueStatement date, boolean utc) {
        return new DateMinuteValueStatement(date, utc);
    }

    /**
     * Returns the seconds of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateSecondValueStatement Second(ValueStatement date) {
        return new DateSecondValueStatement(date, false);
    }

    /**
     * Returns the seconds of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static DateSecondValueStatement Second(ValueStatement date, boolean utc) {
        return new DateSecondValueStatement(date, utc);
    }

    /**
     * Returns the timestamp of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateTimestampValueStatement Timestamp(ValueStatement date) {
        return new DateTimestampValueStatement(date, false);
    }

    /**
     * Returns the timestamp of the provided Date object
     *
     * @param date
     * @param utc
     * @return
     */
    public static DateTimestampValueStatement Timestamp(ValueStatement date, boolean utc) {
        return new DateTimestampValueStatement(date, utc);
    }

    /**
     * Returns the timezone offset in minutes of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateTimeZoneOffsetValueStatement TimeZoneOffset(ValueStatement date) {
        return new DateTimeZoneOffsetValueStatement(date);
    }

    /**
     * Returns the timestamp of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateValueStatement DateValue(ValueStatement date) {
        return new DateValueStatement(date);
    }

    /**
     * Returns the week of the year of the provided Date object
     *
     * @param date
     * @return
     */
    public static DateWeekOfYearValueStatement WeekOfYear(ValueStatement date) {
        return new DateWeekOfYearValueStatement(date);
    }

    /**
     * Returns the ISO8601 week as a Javascript week-string for the specified Date object
     *
     * @param date
     * @return
     */
    public static WeekOfYearStringFromDateValueStatement WeekOfYearString(ValueStatement date) {
        return new WeekOfYearStringFromDateValueStatement(date);
    }

    /**
     * Returns the first day of the specified week in the specified year. This returns the ISO8601 definition which means that the first day is a Monday.
     *
     * @param weekOfYear a Javascript 'week-string', i.e. "2022-W04"
     * @return
     */
    public static DateFromWeekOfYearValueStatement WeekOfYearToDate(ValueStatement weekOfYear) {
        return new DateFromWeekOfYearValueStatement(weekOfYear);
    }

    /**
     * Returns the difference between two dates expressed in the specified interval
     *
     * @param date0
     * @param date1
     * @param timeIntervalType
     * @return
     */
    public static DateDiffValueStatement DateDiff(ValueStatement date0, ValueStatement date1, TimeIntervalType timeIntervalType) {
        return new DateDiffValueStatement(date0, date1, timeIntervalType);
    }

    // MATH

    /**
     * Returns the sum of two values
     *
     * @param left
     * @param right
     * @return
     */
    public static MathSumValueStatement Sum(ValueStatement left, ValueStatement right) {
        return new MathSumValueStatement(left, right);
    }

    /**
     * Returns the subtraction of two values
     *
     * @param left
     * @param right
     * @return
     */
    public static MathSubtractionValueStatement Subtract(ValueStatement left, ValueStatement right) {
        return new MathSubtractionValueStatement(left, right);
    }

    /**
     * Returns the product of two values
     *
     * @param left
     * @param right
     * @return
     */
    public static MathProductValueStatement Product(ValueStatement left, ValueStatement right) {
        return new MathProductValueStatement(left, right);
    }

    /**
     * Returns the division of two values
     *
     * @param left
     * @param right
     * @return
     */
    public static MathDivisionValueStatement Division(ValueStatement left, ValueStatement right) {
        return new MathDivisionValueStatement(left, right);
    }

    // CONVERSION TO BOOLEAN

    /**
     * Returns true if and only if all the provided values are true
     *
     * @param values
     * @return
     */
    public static AndValueStatement And(ValueStatement... values) {
        return new AndValueStatement(values);
    }

    /**
     * Returns true if one the provided values is true
     *
     * @param values
     * @return
     */
    public static OrValueStatement Or(ValueStatement... values) {
        return new OrValueStatement(values);
    }

    /**
     * Negates the provided value
     *
     * @param value
     * @return
     */
    public static NotValueStatement Not(ValueStatement value) {
        return new NotValueStatement(value);
    }

    /**
     * Returns true if the provided values are equal
     *
     * @param left
     * @param right
     * @return
     */
    public static IsValueStatement Is(ValueStatement left, ValueStatement right) {
        return new IsValueStatement(left, right);
    }

    /**
     * Returns true if the provided value is an empty string
     *
     * @param value
     * @return
     */
    public static IsEmptyValueStatement IsEmpty(ValueStatement value) {
        return new IsEmptyValueStatement(value);
    }

    /**
     * Returns true if the provided value is not an empty string
     *
     * @param value
     * @return
     */
    public static IsNotEmptyValueStatement IsNotEmpty(ValueStatement value) {
        return new IsNotEmptyValueStatement(value);
    }

    /**
     * Returns true if the provided value is not null or an empty string
     *
     * @param value
     * @return
     */
    public static IsNotNullOrEmptyValueStatement IsNotNullOrEmpty(ValueStatement value) {
        return new IsNotNullOrEmptyValueStatement(value);
    }

    /**
     * Returns true if the provided string contains another string
     *
     * @param left
     * @param right
     * @return
     */
    public static ContainsValueStatement Contains(ValueStatement left, ValueStatement right) {
        return new ContainsValueStatement(left, right);
    }

    /**
     * Returns true if the provided string starts with another string
     *
     * @param left
     * @param right
     * @return
     */
    public static StartsWithValueStatement StartsWith(ValueStatement left, ValueStatement right) {
        return new StartsWithValueStatement(left, right);
    }

    /**
     * Returns true if the provided string ends with another string
     *
     * @param left
     * @param right
     * @return
     */
    public static EndsWithValueStatement EndsWith(ValueStatement left, ValueStatement right) {
        return new EndsWithValueStatement(left, right);
    }

    /**
     * Returns true if the provided widget is the active element on the page
     *
     * @param widget
     * @return
     */
    public static IsFocusedValueStatement IsFocused(Widget widget) {
        return new IsFocusedValueStatement(widget);
    }

    // MISCELLANEOUS

    /**
     * Returns the current platform name
     *
     * @return
     */
    public static PlatformNameValueStatement Platform() {
        return new PlatformNameValueStatement();
    }

    /**
     * Returns true if the current platform is macOS
     *
     * @return
     */
    @Description("Returns true if the current platform is macOS")
    public static IsValueStatement IsMac() {
        return new IsValueStatement(new PlatformNameValueStatement(), Const(GetOsName.MACOS));
    }

    /**
     * Returns true if the current platform is Windows
     *
     * @return
     */
    @Description("Returns true if the current platform is Windows")
    public static IsValueStatement IsWindows() {
        return new IsValueStatement(new PlatformNameValueStatement(), Const(GetOsName.WINDOWS));
    }

    /**
     * Returns true if the current platform is Linux
     *
     * @return
     */
    @Description("Returns true if the current platform is Linux")
    public static IsValueStatement IsLinux() {
        return new IsValueStatement(new PlatformNameValueStatement(), Const(GetOsName.LINUX));
    }

    /**
     * Returns the Browser name
     *
     * @return
     */
    public static BrowserNameValueStatement Browser() {
        return new BrowserNameValueStatement();
    }

    /**
     * Calls a function in an external code library and returns its value
     *
     * @param code
     * @param name
     * @param parameterValues
     * @return
     */
    public static CallValueStatement Call(Code code, String name, ValueStatement... parameterValues) {
        return new CallValueStatement(code, name, parameterValues);
    }

    /**
     * Prompts the user for input and returns that value
     *
     * @param text
     * @return
     */
    public static PromptValueStatement Prompt(ValueStatement text) {
        return new PromptValueStatement(text);
    }

    /**
     * Returns the value of a Javascript expression
     *
     * @param js
     * @param jsType
     * @return
     */
    public static CodeValueStatement Code(String js, JsType jsType) {
        return new CodeValueStatement(js, jsType);
    }

    /**
     * Returns the access token in the current session as a Bearer token
     *
     * @return
     */
    public static GetAuthorizationHeaderBearerValueStatement GetAuthorizationHeaderBearer() {
        return new GetAuthorizationHeaderBearerValueStatement();
    }

    /**
     * Returns a random value
     *
     * @return
     */
    public static RandomValueStatement Random() {
        return new RandomValueStatement();
    }

    /**
     * Returns a random value
     *
     * @param max
     * @return
     */
    public static RandomValueStatement Random(ValueStatement max) {
        return new RandomValueStatement(max);
    }

    /**
     * Returns the query string arguments as an object
     *
     * @param url
     * @return
     */
    public static GetQueryStringAsObjectValueStatement GetQueryStringAsObject(ValueStatement url) {
        return new GetQueryStringAsObjectValueStatement(url);
    }

    /**
     * Returns the data key value in the current event context
     *
     * @return
     */
    public static GetDataKeyValueStatement GetDataKey() {
        return new GetDataKeyValueStatement();
    }

    /**
     * Returns the data key value in the current event context
     *
     * @param defaultValue
     * @return
     */
    public static GetDataKeyValueStatement GetDataKey(ValueStatement defaultValue) {
        return new GetDataKeyValueStatement(defaultValue);
    }

    /**
     * Returns the value of a named page variable
     *
     * @param name
     * @return
     */
    public static GetPageVariableValueStatement GetPageVariable(String name) {
        return new GetPageVariableValueStatement(name);
    }

    /**
     * Returns the value of a named page variable
     *
     * @param name
     * @return
     */
    public static GetPageVariableValueStatement GetPageVariable(ValueStatement name) {
        return new GetPageVariableValueStatement(name);
    }

    /**
     * Returns the object builder
     *
     * @return
     */
    public static ObjectValueStatement ObjectBuilder() {
        return new ObjectValueStatement();
    }
}
