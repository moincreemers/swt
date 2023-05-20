package com.philips.dmis.swt.ui.toolkit.js.pages;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.*;
import com.philips.dmis.swt.ui.toolkit.utils.ProtectedHashMap;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import com.philips.dmis.swt.ui.toolkit.widgets.Widget;

import java.util.*;

/**
 * Composite function.
 */
public class JsPagesModule implements JsModule {
    public static final String REASON_INIT = "init";
    public static final String REASON_SHOW = "show";
    public static final String REASON_USER = "user";
    public static final String REASON_LOCAL = "local";
    public static final String REASON_DATA_SOURCE = "datasource";

    private final Widget widget;
    private final WidgetType widgetType;

    private static final Map<String, Map<Class<? extends JsMember>, Pair<String, JsMember>>> INDEX_PER_WIDGET = new HashMap<>();
    private final List<Pair<String, JsMember>> members = new ArrayList<>();

    public static JsMember getMemberInstance(Widget widget, Class<? extends JsMember> memberClass) {
        if (!INDEX_PER_WIDGET.containsKey(widget.getId())) {
            throw new IllegalArgumentException("no index for: " + widget.getId());
        }
        return JsModule.getMemberInstance(memberClass, INDEX_PER_WIDGET.get(widget.getId()));
    }

    public static String getId(Widget widget, Class<? extends JsMember> memberClass) throws JsRenderException {
        return getId(widget.getId(), memberClass);
    }

    public static String getId(String id, Class<? extends JsMember> memberClass) throws JsRenderException {
        if (!INDEX_PER_WIDGET.containsKey(id)) {
            throw new IllegalArgumentException("no index for: " + id);
        }
        return JsModule.getId(memberClass, INDEX_PER_WIDGET.get(id));
    }

    public static String getQualifiedId(Widget widget, Class<? extends JsMember> memberClass) throws JsRenderException {
        return getQualifiedId(widget.getId(), memberClass);
    }

    public static String getQualifiedId(String id, Class<? extends JsMember> memberClass) throws JsRenderException {
        if (!INDEX_PER_WIDGET.containsKey(id)) {
            throw new IllegalArgumentException("no index for: " + id);
        }
        return JsModule.getQualifiedId(id, memberClass, INDEX_PER_WIDGET.get(id));
    }

    public JsPagesModule(Widget widget) {
        this.widget = widget;
        this.widgetType = widget.getWidgetType();

        members.add(new Pair<>(AddClassNameFunction.ID, new AddClassNameFunction(widget)));
        members.add(new Pair<>(AddDataFunction.ID, new AddDataFunction(widget)));
        members.add(new Pair<>(AppendElementFunction.ID, new AppendElementFunction(widget)));
        members.add(new Pair<>(AutoRefreshVariable.ID, new AutoRefreshVariable(widget)));
        members.add(new Pair<>(BeforeEventFunction.ID, new BeforeEventFunction(widget)));
        members.add(new Pair<>(BeforeUpdateFunction.ID, new BeforeUpdateFunction(widget)));
        members.add(new Pair<>(BeforeUpdateListItemsFunction.ID, new BeforeUpdateListItemsFunction(widget)));
        members.add(new Pair<>(BeforeUpdateOptionsFunction.ID, new BeforeUpdateOptionsFunction(widget)));
        members.add(new Pair<>(BeforeUpdateSubscribersFunction.ID, new BeforeUpdateSubscribersFunction(widget)));
        members.add(new Pair<>(BeforeUpdateTableBodyFunction.ID, new BeforeUpdateTableBodyFunction(widget)));
        members.add(new Pair<>(BeforeUpdateTableFooterFunction.ID, new BeforeUpdateTableFooterFunction(widget)));
        members.add(new Pair<>(BeforeUpdateTableHeaderFunction.ID, new BeforeUpdateTableHeaderFunction(widget)));
        members.add(new Pair<>(BeforeUpdateTextFunction.ID, new BeforeUpdateTextFunction(widget)));
        members.add(new Pair<>(BeforeUpdateValueFunction.ID, new BeforeUpdateValueFunction(widget)));
        members.add(new Pair<>(ButtonTypeVariable.ID, new ButtonTypeVariable(widget)));
        members.add(new Pair<>(ColumnsVariable.ID, new ColumnsVariable(widget)));
        members.add(new Pair<>(ContentTypeVariable.ID, new ContentTypeVariable(widget)));
        members.add(new Pair<>(CreateDataAdaptersFunction.ID, new CreateDataAdaptersFunction(widget)));
        members.add(new Pair<>(CreateRowFunction.ID, new CreateRowFunction(widget)));
        members.add(new Pair<>(DataAdaptersVariable.ID, new DataAdaptersVariable(widget)));
        members.add(new Pair<>(DataVariable.ID, new DataVariable(widget)));
        members.add(new Pair<>(DeleteDataFunction.ID, new DeleteDataFunction(widget)));
        members.add(new Pair<>(DisabledDataAdaptersVariable.ID, new DisabledDataAdaptersVariable(widget)));
        members.add(new Pair<>(DuplicateDataFunction.ID, new DuplicateDataFunction(widget)));
        members.add(new Pair<>(EmptyTextVariable.ID, new EmptyTextVariable(widget)));
        members.add(new Pair<>(EventHandlerFunction.OnActivateEventHandlerFunction.ID, new EventHandlerFunction.OnActivateEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnAppendEventHandlerFunction.ID, new EventHandlerFunction.OnAppendEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnChangeEventHandlerFunction.ID, new EventHandlerFunction.OnChangeEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnClickEventHandlerFunction.ID, new EventHandlerFunction.OnClickEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnDeactivateEventHandlerFunction.ID, new EventHandlerFunction.OnDeactivateEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnElapsedEventHandlerFunction.ID, new EventHandlerFunction.OnElapsedEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnErrorEventHandlerFunction.ID, new EventHandlerFunction.OnErrorEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnHideEventHandlerFunction.ID, new EventHandlerFunction.OnHideEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnInitEventHandlerFunction.ID, new EventHandlerFunction.OnInitEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnInputEventHandlerFunction.ID, new EventHandlerFunction.OnInputEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnRemoveEventHandlerFunction.ID, new EventHandlerFunction.OnRemoveEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnResetEventHandlerFunction.ID, new EventHandlerFunction.OnResetEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnResponseEventHandlerFunction.ID, new EventHandlerFunction.OnResponseEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnRefreshEventHandlerFunction.ID, new EventHandlerFunction.OnRefreshEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnShowEventHandlerFunction.ID, new EventHandlerFunction.OnShowEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnStartEventHandlerFunction.ID, new EventHandlerFunction.OnStartEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnStopEventHandlerFunction.ID, new EventHandlerFunction.OnStopEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnUpdateEventHandlerFunction.ID, new EventHandlerFunction.OnUpdateEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnOrderChangeEventHandlerFunction.ID, new EventHandlerFunction.OnOrderChangeEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnClickOutsideDialogEventHandlerFunction.ID, new EventHandlerFunction.OnClickOutsideDialogEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnOpenEventHandlerFunction.ID, new EventHandlerFunction.OnOpenEventHandlerFunction()));
        members.add(new Pair<>(EventHandlerFunction.OnSelectionChangeEventHandlerFunction.ID, new EventHandlerFunction.OnSelectionChangeEventHandlerFunction()));
        members.add(new Pair<>(EventHandlersVariable.ID, new EventHandlersVariable(widget)));
        members.add(new Pair<>(ExpectServiceResponseVariable.ID, new ExpectServiceResponseVariable(widget)));
        members.add(new Pair<>(GetCacheFunction.ID, new GetCacheFunction(widget)));
        members.add(new Pair<>(GetElementFunction.ID, new GetElementFunction(widget)));
        members.add(new Pair<>(GetFunction.ID, new GetFunction(widget)));
        members.add(new Pair<>(GetInnerElementFunction.ID, new GetInnerElementFunction(widget)));
        members.add(new Pair<>(GetMultiLevelFunction.ID, new GetMultiLevelFunction(widget)));
        members.add(new Pair<>(GetOrderingFunction.ID, new GetOrderingFunction(widget)));
        members.add(new Pair<>(GetParameterFunction.ID, new GetParameterFunction(widget)));
        members.add(new Pair<>(GetWidgetSpecFunction.ID, new GetWidgetSpecFunction(widget)));
        members.add(new Pair<>(GetWidgetTypeFunction.ID, new GetWidgetTypeFunction(widget)));
        members.add(new Pair<>(HttpHeadersVariable.ID, new HttpHeadersVariable(widget)));
        members.add(new Pair<>(InitFunction.ID, new InitFunction(widget)));
        members.add(new Pair<>(IsMultiLevelVariable.ID, new IsMultiLevelVariable(widget)));
        members.add(new Pair<>(IsNumberedVariable.ID, new IsNumberedVariable(widget)));
        members.add(new Pair<>(UpdateSubscribersFunction.ID, new UpdateSubscribersFunction(widget)));
        members.add(new Pair<>(NotifySubscribersVariable.ID, new NotifySubscribersVariable(widget)));
        members.add(new Pair<>(NumberingLevelVariable.ID, new NumberingLevelVariable(widget)));
        members.add(new Pair<>(OrderingVariable.ID, new OrderingVariable(widget)));
        members.add(new Pair<>(PageIdVariable.ID, new PageIdVariable(widget)));
        members.add(new Pair<>(PageRefreshVariable.ID, new PageRefreshVariable(widget)));
        members.add(new Pair<>(PanelTypeVariable.ID, new PanelTypeVariable(widget)));
        members.add(new Pair<>(ParametersVariable.ID, new ParametersVariable(widget)));
        members.add(new Pair<>(ParentWidgetIdVariable.ID, new ParentWidgetIdVariable(widget)));
        members.add(new Pair<>(PostFunction.ID, new PostFunction(widget)));
        members.add(new Pair<>(ProcessResponseFunction.ID, new ProcessResponseFunction(widget)));
        members.add(new Pair<>(PutCacheFunction.ID, new PutCacheFunction(widget)));
        members.add(new Pair<>(RefreshFunction.ID, new RefreshFunction(widget)));
        members.add(new Pair<>(RefreshPageFunction.ID, new RefreshPageFunction(widget)));
        members.add(new Pair<>(RemoveAllParametersFunction.ID, new RemoveAllParametersFunction(widget)));
        members.add(new Pair<>(RemoveClassNameFunction.ID, new RemoveClassNameFunction(widget)));
        members.add(new Pair<>(RenderElementFunction.ID, new RenderElementFunction(widget)));
        members.add(new Pair<>(RemoveOptionsFunction.ID, new RemoveOptionsFunction(widget)));
        members.add(new Pair<>(RemoveParameterFunction.ID, new RemoveParameterFunction(widget)));
        members.add(new Pair<>(RenderInnerHTMLFunction.ID, new RenderInnerHTMLFunction(widget)));
        members.add(new Pair<>(RenderOuterHTMLFunction.ID, new RenderOuterHTMLFunction(widget)));
        members.add(new Pair<>(ResetFunction.ID, new ResetFunction(widget)));
        members.add(new Pair<>(SetAutocompleteFunction.ID, new SetAutocompleteFunction(widget)));
        members.add(new Pair<>(SetDataAdapterEnabledFunction.ID, new SetDataAdapterEnabledFunction(widget)));
        members.add(new Pair<>(SetDisabledFunction.ID, new SetDisabledFunction(widget)));
        members.add(new Pair<>(SetIconFunction.ID, new SetIconFunction(widget)));
        members.add(new Pair<>(SetLengthFunction.ID, new SetLengthFunction(widget)));
        members.add(new Pair<>(SetListFunction.ID, new SetListFunction(widget)));
        members.add(new Pair<>(SetMultipleFunction.ID, new SetMultipleFunction(widget)));
        members.add(new Pair<>(SetNumberedFunction.ID, new SetNumberedFunction(widget)));
        members.add(new Pair<>(SetNumberFunction.ID, new SetNumberFunction(widget)));
        members.add(new Pair<>(SetOrderFunction.ID, new SetOrderFunction(widget)));
        members.add(new Pair<>(SetParameterFunction.ID, new SetParameterFunction(widget)));
        members.add(new Pair<>(SetParametersFunction.ID, new SetParametersFunction(widget)));
        members.add(new Pair<>(SetPatternFunction.ID, new SetPatternFunction(widget)));
        members.add(new Pair<>(SetPlaceholderFunction.ID, new SetPlaceholderFunction(widget)));
        members.add(new Pair<>(SetRangeFunction.ID, new SetRangeFunction(widget)));
        members.add(new Pair<>(SetReadonlyFunction.ID, new SetReadonlyFunction(widget)));
        members.add(new Pair<>(SetRequiredFunction.ID, new SetRequiredFunction(widget)));
        members.add(new Pair<>(SetStepFunction.ID, new SetStepFunction(widget)));
        members.add(new Pair<>(SetTextFunction.ID, new SetTextFunction(widget)));
        members.add(new Pair<>(SetValueFunction.ID, new SetValueFunction(widget)));
        members.add(new Pair<>(StartFunction.ID, new StartFunction(widget)));
        members.add(new Pair<>(StopFunction.ID, new StopFunction(widget)));
        members.add(new Pair<>(TextFormatVariable.ID, new TextFormatVariable(widget)));
        members.add(new Pair<>(TimerHandleVariable.ID, new TimerHandleVariable(widget)));
        members.add(new Pair<>(UpdateFunction.ID, new UpdateFunction(widget)));
        members.add(new Pair<>(UpdateListItemsFunction.ID, new UpdateListItemsFunction(widget)));
        members.add(new Pair<>(UpdateNumberingFunction.ID, new UpdateNumberingFunction(widget)));
        members.add(new Pair<>(UpdateOptionsFunction.ID, new UpdateOptionsFunction(widget)));
        members.add(new Pair<>(UpdateTableBodyFunction.ID, new UpdateTableBodyFunction(widget)));
        members.add(new Pair<>(UpdateTableFooterFunction.ID, new UpdateTableFooterFunction(widget)));
        members.add(new Pair<>(UpdateTableHeaderFunction.ID, new UpdateTableHeaderFunction(widget)));
        members.add(new Pair<>(UpdateTextFunction.ID, new UpdateTextFunction(widget)));
        members.add(new Pair<>(UpdateValueFunction.ID, new UpdateValueFunction(widget)));
        members.add(new Pair<>(ViewPositionVariable.ID, new ViewPositionVariable(widget)));
        members.add(new Pair<>(ViewTypeVariable.ID, new ViewTypeVariable(widget)));
        members.add(new Pair<>(WidgetIdVariable.ID, new WidgetIdVariable(widget)));
        members.add(new Pair<>(WidgetTypeVariable.ID, new WidgetTypeVariable(widget)));


        // we cannot use JsModule.updateIndex because we use our own function id's
        // JsModule.updateIndex(members, INDEX);

        INDEX_PER_WIDGET.remove(widget.getId());
        Map<Class<? extends JsMember>, Pair<String, JsMember>> m = new ProtectedHashMap<>();
        for (Pair<String, JsMember> pair : members) {
            m.put(pair.getValue().getClass(), pair);
        }
        INDEX_PER_WIDGET.put(widget.getId(), m);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        // NOTE: assignment goes here
        js.append("var %s=(function(){", widget.getId());

        List<Pair<String, JsMember>> membersInScope = members.stream()
                .filter(m -> m.getValue().isMemberOf(widget, widgetType)).toList();

        // functions
        List<Pair<String, JsMember>> sorted = membersInScope.stream()
                .sorted(Comparator.comparingInt(m0 -> JsModule.getOrder(m0.getValue())))
                .toList();

        for (Pair<String, JsMember> pair : sorted) {
            JsMember jsMember = pair.getValue();
            if (jsMember instanceof JsComposite) {
                js.comment("begin composite: " + ((JsComposite) jsMember).getName());
                jsMember.renderJs(toolkit, js);
                js.comment("end composite: " + ((JsComposite) jsMember).getName());
            } else if (jsMember instanceof JsVariable) {
                js.append("var %s=", pair.getKey());
                jsMember.renderJs(toolkit, js);
                js.append(";");
            } else {
                js.append("const %s=", pair.getKey());
                jsMember.renderJs(toolkit, js);
                js.append(";");
            }
        }

        // export:

        // state
        js.append("return {");

        int i = 0;
        for (Pair<String, JsMember> pair : membersInScope) {
            if (!pair.getValue().isPublic()) {
                continue;
            }
            if (i > 0) {
                js.append(",");
            }
            js.cr();
            if (pair.getValue() instanceof JsSymbol) {
                js.append("%s:", pair.getKey());
                pair.getValue().renderJs(toolkit, js);
            } else {
                js.append("%s:%s",
                        pair.getValue().getPublicName(pair.getKey()),
                        pair.getKey());
            }
            i++;
        }

        js.append("};"); // end export

        js.append("})();"); // end module
    }

    @Override
    public String getInitFunctionId() {
        return getQualifiedId(widget, InitFunction.class);
    }
}
