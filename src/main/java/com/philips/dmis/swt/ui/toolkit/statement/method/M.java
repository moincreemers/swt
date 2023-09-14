package com.philips.dmis.swt.ui.toolkit.statement.method;

import com.philips.dmis.swt.ui.toolkit.data.DataAdapter;
import com.philips.dmis.swt.ui.toolkit.js.JsType;
import com.philips.dmis.swt.ui.toolkit.statement.Statement;
import com.philips.dmis.swt.ui.toolkit.statement.value.V;
import com.philips.dmis.swt.ui.toolkit.statement.value.ValueStatement;
import com.philips.dmis.swt.ui.toolkit.widgets.*;

/**
 * Statements
 */
public final class M {
    private M() {
    }

    public static TryStatement Try(Statement... statements) {
        return new TryStatement(statements);
    }

    public static ForEachStatement ForEach(ValueStatement... valueStatements) {
        return new ForEachStatement(valueStatements);
    }

    public static AddClassNameStatement AddClassName(Widget widget, ValueStatement className) {
        return new AddClassNameStatement(widget, className);
    }

    public static SetStyleStatement SetStyle(Widget widget, ValueStatement property, ValueStatement value) {
        return new SetStyleStatement(widget, property, value);
    }


    public static AlertStatement Alert(String message) {
        return new AlertStatement(message);
    }

    public static AlertStatement Alert(ValueStatement message) {
        return new AlertStatement(message);
    }

    public static CallStatement Call(Code code, String name, ValueStatement... parameterValues) {
        return new CallStatement(code, name, parameterValues);
    }

    public static ClosePageStatement ClosePage() {
        return new ClosePageStatement();
    }

    public static ClosePageStatement ClosePage(boolean useDefaultPage) {
        return new ClosePageStatement(useDefaultPage);
    }

    public static ClosePageStatement ClosePage(ValueStatement valueStatement) {
        return new ClosePageStatement(valueStatement);
    }

    public static ClosePageStatement ClosePage(ValueStatement valueStatement, boolean useDefaultPage) {
        return new ClosePageStatement(valueStatement, useDefaultPage);
    }

    public static CodeStatement Code(String js, JsType jsType) {
        return new CodeStatement(js, jsType);
    }

    public static FocusStatement Focus(Widget widget) {
        return new FocusStatement(widget);
    }

    public static IfStatement If(ValueWidget<?, ?, ?> valueWidget) {
        return new IfStatement(valueWidget);
    }

    public static IfStatement If(ValueWidget<?, ?, ?> valueWidget, boolean breakOnMatch) {
        return new IfStatement(valueWidget, breakOnMatch);
    }

    public static IifStatement Iif(ValueStatement valueStatement) {
        return new IifStatement(valueStatement);
    }

    public static LetStatement Let(ValueStatement name, ValueStatement value) {
        return new LetStatement(name, value);
    }

    public static LetStatement Let(String name, ValueStatement value) {
        return new LetStatement(V.Const(name), value);
    }

    public static LetStatement Let(String name, boolean value) {
        return new LetStatement(name, value);
    }

    public static LetStatement Let(String name, int value) {
        return new LetStatement(name, value);
    }

    public static LetStatement Let(String name, long value) {
        return new LetStatement(name, value);
    }

    public static LetStatement Let(String name, double value) {
        return new LetStatement(name, value);
    }

    public static LetStatement Let(String name, String value) {
        return new LetStatement(name, value);
    }

    public static LogStatement Log(String message) {
        return new LogStatement(V.Const(message));
    }

    public static LogStatement Log(ValueStatement... values) {
        return new LogStatement(values);
    }

    public static OpenPageStatement OpenPage(Class<? extends Page> pageClass) {
        return new OpenPageStatement(pageClass);
    }

    public static OpenPageStatement OpenPage(Class<? extends Page> pageClass, ValueStatement valueStatement) {
        return new OpenPageStatement(pageClass, valueStatement);
    }

    public static RefreshStatement Refresh(Widget widget) {
        return new RefreshStatement(widget);
    }

    public static RefreshStatement Refresh(Widget widget, String reason) {
        return new RefreshStatement(widget, reason);
    }

    public static RefreshStatement Refresh(Widget widget, ValueStatement reason) {
        return new RefreshStatement(widget, reason);
    }

    public static RemoveClassNameStatement RemoveClassName(Widget widget, ValueStatement className) {
        return new RemoveClassNameStatement(widget, className);
    }

    public static ResetStatement Reset(HtmlForm htmlForm) {
        return new ResetStatement(htmlForm);
    }

    public static ScrollToStatement ScrollTo(Widget widget) {
        return new ScrollToStatement(widget);
    }

    public static SetAutocompleteStatement SetAutocomplete(Widget widget, ValueStatement autocomplete) {
        return new SetAutocompleteStatement(widget, autocomplete);
    }

    public static SetDataAdapterEnabledStatement SetDataAdapterEnabled(Widget widget, DataAdapter dataAdapter, ValueStatement enabled) {
        return new SetDataAdapterEnabledStatement(widget, dataAdapter, enabled, false);
    }

    public static SetDataAdapterEnabledStatement SetDataAdapterDisabled(Widget widget, DataAdapter dataAdapter, ValueStatement enabled) {
        return new SetDataAdapterEnabledStatement(widget, dataAdapter, enabled, true);
    }

    public static SetQueryParameterStatement SetQueryParameter(HasURL hasURL, ValueStatement nameStatement, ValueStatement valueStatement) {
        return new SetQueryParameterStatement(hasURL, nameStatement, valueStatement);
    }

    public static SetQueryParameterStatement SetQueryParameter(HasURL hasURL, String name, ValueStatement valueStatement) {
        return new SetQueryParameterStatement(hasURL, V.Const(name), valueStatement);
    }

    public static SetQueryParametersStatement SetQueryParameters(HasURL hasURL, ValueStatement object) {
        return new SetQueryParametersStatement(hasURL, object);
    }

    public static RemoveQueryParameterStatement RemoveQueryParameter(DataSourceSupplier dataSourceSupplier, ValueStatement nameStatement) {
        return new RemoveQueryParameterStatement(dataSourceSupplier, nameStatement);
    }

    public static RemoveQueryParameterStatement RemoveQueryParameter(DataSourceSupplier dataSourceSupplier, String name) {
        return new RemoveQueryParameterStatement(dataSourceSupplier, V.Const(name));
    }

    public static RemoveAllQueryParametersStatement RemoveAllQueryParameters(DataSourceSupplier dataSourceSupplier) {
        return new RemoveAllQueryParametersStatement(dataSourceSupplier);
    }

    public static SetDisabledStatement SetDisabled(Widget widget) {
        return new SetDisabledStatement(widget, V.True);
    }

    public static SetDisabledStatement SetDisabled(Widget widget, ValueStatement valueStatement) {
        return new SetDisabledStatement(widget, valueStatement);
    }

    public static SetVisibleStatement SetVisible(Widget widget, ValueStatement valueStatement) {
        return new SetVisibleStatement(widget, valueStatement);
    }

    public static SetEnabledStatement SetEnabled(Widget widget) {
        return new SetEnabledStatement(widget, V.True);
    }

    public static SetEnabledStatement SetEnabled(Widget widget, ValueStatement valueStatement) {
        return new SetEnabledStatement(widget, valueStatement);
    }

    public static SetIconStatement SetIcon(Widget widget, ValueStatement valueStatement) {
        return new SetIconStatement(widget, valueStatement);
    }

    public static SetLengthStatement SetLength(Widget widget, ValueStatement minLength, ValueStatement maxLength) {
        return new SetLengthStatement(widget, minLength, maxLength);
    }

    public static SetStylesheetDisabledStatement SetStylesheetDisabled(ValueStatement href, ValueStatement disabled) {
        return new SetStylesheetDisabledStatement(href, disabled);
    }

    public static SetListStatement SetList(Widget widget, ValueStatement list) {
        return new SetListStatement(widget, list);
    }

    public static SetMultipleStatement SetMultiple(Widget widget, ValueStatement multiple) {
        return new SetMultipleStatement(widget, multiple);
    }

    public static SetPatternStatement SetPattern(Widget widget, ValueStatement pattern) {
        return new SetPatternStatement(widget, pattern);
    }

    public static SetPlaceholderStatement SetPlaceholder(Widget widget, ValueStatement placeholder) {
        return new SetPlaceholderStatement(widget, placeholder);
    }

    public static SetRangeStatement SetRange(Widget widget, ValueStatement min, ValueStatement max) {
        return new SetRangeStatement(widget, min, max);
    }

    public static SetReadonlyStatement SetReadonly(Widget widget, ValueStatement readonly) {
        return new SetReadonlyStatement(widget, readonly);
    }

    public static SetRequiredStatement SetRequired(Widget widget, ValueStatement required) {
        return new SetRequiredStatement(widget, required);
    }

    public static SetStepStatement SetStep(HasStep widget, ValueStatement required) {
        return new SetStepStatement(widget, required);
    }

    public static SetTextStatement SetText(HasText widget, ValueStatement text) {
        return new SetTextStatement(widget, text);
    }

    public static SetValueStatement SetValue(HasValue<?, ?> widget, ValueStatement valueStatement) {
        return new SetValueStatement(widget, valueStatement);
    }

    public static SetSrcStatement SetSrc(HasSrc<?> widget, ValueStatement valueStatement) {
        return new SetSrcStatement(widget, valueStatement);
    }

    public static SelectAllStatement SelectAll(HasOptions widget) {
        return new SelectAllStatement(widget);
    }

    public static SelectIndexStatement SelectIndex(HasOptions widget, ValueStatement index) {
        return new SelectIndexStatement(widget, index);
    }

    public static SelectNoneStatement SelectNone(HasOptions widget) {
        return new SelectNoneStatement(widget);
    }

    public static DeclarePageVariableStatement DeclarePageVariable(ValueStatement name) {
        return new DeclarePageVariableStatement(name);
    }

    public static SetPageVariableStatement SetPageVariable(ValueStatement name, ValueStatement value, Operator operator) {
        return new SetPageVariableStatement(name, value, operator);
    }

    public static SetGlobalValueStatement SetGlobalValue(String key, ValueStatement value) {
        return new SetGlobalValueStatement(V.Const(key), value);
    }

    public static SetGlobalValueStatement SetGlobalValue(ValueStatement key, ValueStatement value) {
        return new SetGlobalValueStatement(key, value);
    }

    public static SetAccessTokenStatement SetAccessToken(ValueStatement accessToken) {
        return new SetAccessTokenStatement(accessToken);
    }

    public static RemoveAccessTokenStatement RemoveAccessToken() {
        return new RemoveAccessTokenStatement();
    }

    public static StartStatement Start(Widget widget) {
        return new StartStatement(widget);
    }

    public static StopStatement Stop(Widget widget) {
        return new StopStatement(widget);
    }

    public static SelectStatement Select(Widget targetWidget) {
        return new SelectStatement(targetWidget);
    }

    public static ClickStatement Click(Widget targetWidget) {
        return new ClickStatement(targetWidget);
    }

    public static ClearCacheStatement ClearCache() {
        return new ClearCacheStatement();
    }

    public static OpenURLStatement OpenURL(ValueStatement url) {
        return new OpenURLStatement(url);
    }

    public static PreventDefaultStatement PreventDefault() {
        return new PreventDefaultStatement();
    }

    public static CloneWidgetStatement CloneWidget(Widget template, ContainerWidget<?> target, ValueStatement dataKey) {
        return new CloneWidgetStatement(template, target, dataKey);
    }

    public static RemoveCloneStatement RemoveClone(Widget template, ValueStatement dataKey) {
        return new RemoveCloneStatement(template, dataKey);
    }

    public static RemoveAllClonesStatement RemoveAllClones(Widget template) {
        return new RemoveAllClonesStatement(template);
    }

    public static WithDataKey WithDataKey(ValueStatement dataKey, Statement... statements) {
        return new WithDataKey(dataKey, statements);
    }

    public static DownloadStatement Download() {
        return new DownloadStatement();
    }

    public static AppendItemStatement AppendItems(HasListItems hasListItems, ValueStatement... values) {
        return new AppendItemStatement(hasListItems, values);
    }

    public static RemoveAllItemsStatement RemoveAllItems(HasListItems hasListItems) {
        return new RemoveAllItemsStatement(hasListItems);
    }

    public static OpenDialogStatement OpenDialog(HtmlDialog dialog) {
        return new OpenDialogStatement(dialog);
    }

    public static CloseDialogStatement CloseDialog(HtmlDialog dialog, ValueStatement returnValue) {
        return new CloseDialogStatement(dialog, returnValue);
    }
}
