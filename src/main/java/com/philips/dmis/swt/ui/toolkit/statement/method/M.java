package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

/**
 * Statements
 */
public final class M {
    private M() {
    }

    public static MethodStatement AddClassName(Widget widget, ValueStatement className) {
        return new AddClassNameStatement(widget, className);
    }

    public static MethodStatement AddData(DataBoundWidget<?> dataBoundWidget) {
        return new AddDataStatement(dataBoundWidget);
    }

    public static MethodStatement AddData(DataBoundWidget<?> dataBoundWidget, ValueStatement data) {
        return new AddDataStatement(dataBoundWidget, data);
    }

    public static MethodStatement Alert(String message) {
        return new AlertStatement(message);
    }

    public static CallStatement Call(Code code, String name, ValueStatement... parameterValues) {
        return new CallStatement(code, name, parameterValues);
    }

    public static MethodStatement ClosePage() {
        return new ClosePageStatement();
    }

    public static MethodStatement ClosePage(boolean useDefaultPage) {
        return new ClosePageStatement(useDefaultPage);
    }

    public static MethodStatement ClosePage(ValueStatement valueStatement) {
        return new ClosePageStatement(valueStatement);
    }

    public static MethodStatement ClosePage(ValueStatement valueStatement, boolean useDefaultPage) {
        return new ClosePageStatement(valueStatement, useDefaultPage);
    }

    public static MethodStatement Code(String js, JsType jsType) {
        return new CodeStatement(js, jsType);
    }

    public static MethodStatement DeleteData(DataBoundWidget<?> dataBoundWidget, ValueStatement selection) {
        return new DeleteDataStatement(dataBoundWidget, selection);
    }

    public static MethodStatement DuplicateData(DataBoundWidget<?> dataBoundWidget, ValueStatement selection) {
        return new DuplicateDataStatement(dataBoundWidget, selection);
    }

    public static MethodStatement Focus(Widget widget) {
        return new FocusStatement(widget);
    }

    public static IfStatement If(ValueWidget<?> valueWidget) {
        return new IfStatement(valueWidget);
    }

    public static IfStatement If(ValueWidget<?> valueWidget, boolean breakOnMatch) {
        return new IfStatement(valueWidget, breakOnMatch);
    }

    public static IifStatement Iif(ValueStatement valueStatement) {
        return new IifStatement(valueStatement);
    }

    public static MethodStatement Let(ValueStatement name, ValueStatement value) {
        return new LetStatement(name, value);
    }

    public static MethodStatement Let(String name, ValueStatement value) {
        return new LetStatement(V.Const(name), value);
    }

    public static MethodStatement Let(String name, boolean value) {
        return new LetStatement(name, value);
    }

    public static MethodStatement Let(String name, int value) {
        return new LetStatement(name, value);
    }

    public static MethodStatement Let(String name, long value) {
        return new LetStatement(name, value);
    }

    public static MethodStatement Let(String name, double value) {
        return new LetStatement(name, value);
    }

    public static MethodStatement Let(String name, String value) {
        return new LetStatement(name, value);
    }

    public static MethodStatement Log(String message) {
        return new LogStatement(V.Const(message));
    }

    public static MethodStatement Log(ValueStatement... values) {
        return new LogStatement(values);
    }

    public static MethodStatement OpenPage(Class<? extends Page> pageClass) {
        return new OpenPageStatement(pageClass);
    }

    public static MethodStatement OpenPage(Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new OpenPageStatement(pageClass, valueStatement);
    }

    public static MethodStatement Refresh(Widget widget) {
        return new RefreshStatement(widget);
    }

    public static MethodStatement Refresh(Widget widget, String reason) {
        return new RefreshStatement(widget, reason);
    }

    public static MethodStatement Refresh(Widget widget, ValueStatement reason) {
        return new RefreshStatement(widget, reason);
    }

    public static MethodStatement RemoveClassName(Widget widget, ValueStatement className) {
        return new RemoveClassNameStatement(widget, className);
    }

    public static MethodStatement Reset(HtmlForm htmlForm) {
        return new ResetStatement(htmlForm);
    }

    public static MethodStatement ScrollTo(Widget widget) {
        return new ScrollToStatement(widget);
    }

    public static MethodStatement SetAutocomplete(Widget widget, ValueStatement autocomplete) {
        return new SetAutocompleteStatement(widget, autocomplete);
    }

    public static MethodStatement SetDataAdapterEnabled(Widget widget, DataAdapter dataAdapter, ValueStatement enabled) {
        return new SetDataAdapterEnabledStatement(widget, dataAdapter, enabled);
    }

    public static MethodStatement SetQueryParameter(HasURL hasURL, ValueStatement nameStatement, ValueStatement valueStatement) {
        return new SetQueryParameterStatement(hasURL, nameStatement, valueStatement);
    }

    public static MethodStatement SetQueryParameter(HasURL hasURL, String name, ValueStatement valueStatement) {
        return new SetQueryParameterStatement(hasURL, V.Const(name), valueStatement);
    }

    public static MethodStatement SetQueryParameters(HasURL hasURL, ValueStatement object) {
        return new SetQueryParametersStatement(hasURL, object);
    }

    public static MethodStatement RemoveQueryParameter(DataSourceSupplier dataSourceSupplier, ValueStatement nameStatement) {
        return new RemoveQueryParameterStatement(dataSourceSupplier, nameStatement);
    }

    public static MethodStatement RemoveQueryParameter(DataSourceSupplier dataSourceSupplier, String name) {
        return new RemoveQueryParameterStatement(dataSourceSupplier, V.Const(name));
    }

    public static MethodStatement RemoveAllQueryParameters(DataSourceSupplier dataSourceSupplier) {
        return new RemoveAllQueryParametersStatement(dataSourceSupplier);
    }

    public static MethodStatement SetDisabled(Widget widget) {
        return new SetDisabledStatement(widget, V.True);
    }

    public static MethodStatement SetDisabled(Widget widget, ValueStatement valueStatement) {
        return new SetDisabledStatement(widget, valueStatement);
    }

    public static MethodStatement SetDisplay(Widget widget, ValueStatement valueStatement) {
        return new SetDisplayStatement(widget, valueStatement);
    }

    public static MethodStatement SetEnabled(Widget widget) {
        return new SetEnabledStatement(widget, V.True);
    }

    public static MethodStatement SetEnabled(Widget widget, ValueStatement valueStatement) {
        return new SetEnabledStatement(widget, valueStatement);
    }

    public static MethodStatement SetIcon(Widget widget, ValueStatement valueStatement) {
        return new SetIconStatement(widget, valueStatement);
    }

    public static MethodStatement SetLength(Widget widget, ValueStatement minLength, ValueStatement maxLength) {
        return new SetLengthStatement(widget, minLength, maxLength);
    }

    public static MethodStatement SetStylesheetDisabled(ValueStatement href, ValueStatement disabled) {
        return new SetStylesheetDisabledStatement(href, disabled);
    }

    public static MethodStatement SetList(Widget widget, ValueStatement list) {
        return new SetListStatement(widget, list);
    }

    public static MethodStatement SetMultiple(Widget widget, ValueStatement multiple) {
        return new SetMultipleStatement(widget, multiple);
    }

    public static MethodStatement SetPattern(Widget widget, ValueStatement pattern) {
        return new SetPatternStatement(widget, pattern);
    }

    public static MethodStatement SetPlaceholder(Widget widget, ValueStatement placeholder) {
        return new SetPlaceholderStatement(widget, placeholder);
    }

    public static MethodStatement SetRange(Widget widget, ValueStatement min, ValueStatement max) {
        return new SetRangeStatement(widget, min, max);
    }

    public static MethodStatement SetReadonly(Widget widget, ValueStatement readonly) {
        return new SetReadonlyStatement(widget, readonly);
    }

    public static MethodStatement SetRequired(Widget widget, ValueStatement required) {
        return new SetRequiredStatement(widget, required);
    }

    public static MethodStatement SetStep(HasStep widget, ValueStatement required) {
        return new SetStepStatement(widget, required);
    }

    public static MethodStatement SetText(HasText widget, ValueStatement text) {
        return new SetTextStatement(widget, text);
    }

    public static MethodStatement SetValue(HasValue<?> widget, ValueStatement valueStatement) {
        return new SetValueStatement(widget, valueStatement);
    }

    public static MethodStatement SetGlobalValue(String key, ValueStatement value) {
        return new SetGlobalValueStatement(V.Const(key), value);
    }

    public static MethodStatement SetGlobalValue(ValueStatement key, ValueStatement value) {
        return new SetGlobalValueStatement(key, value);
    }

    public static MethodStatement SetAccessToken(ValueStatement accessToken) {
        return new SetAccessTokenStatement(accessToken);
    }

    public static MethodStatement RemoveAccessToken() {
        return new RemoveAccessTokenStatement();
    }

    public static MethodStatement Start(Widget widget) {
        return new StartStatement(widget);
    }

    public static MethodStatement Stop(Widget widget) {
        return new StopStatement(widget);
    }

    public static MethodStatement Select(Widget targetWidget) {
        return new SelectStatement(targetWidget);
    }

    public static MethodStatement Click(Widget targetWidget) {
        return new ClickStatement(targetWidget);
    }

    public static MethodStatement ClearCache() {
        return new ClearCacheStatement();
    }

    public static MethodStatement OpenURL(ValueStatement url) {
        return new OpenURLStatement(url);
    }

    public static MethodStatement PreventDefault() {
        return new PreventDefaultStatement();
    }

    public static MethodStatement ShowProgress(Widget targetWidget) {
        return new ShowProgressStatement(targetWidget);
    }

    public static MethodStatement HideProgress(Widget targetWidget) {
        return new HideProgressStatement(targetWidget);
    }

}
