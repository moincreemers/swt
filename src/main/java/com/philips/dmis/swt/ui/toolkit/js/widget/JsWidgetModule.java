package com.philips.dmis.swt.ui.toolkit.js.widget;

import com.philips.dmis.swt.ui.toolkit.Toolkit;
import com.philips.dmis.swt.ui.toolkit.js.JsMember;
import com.philips.dmis.swt.ui.toolkit.js.JsModule;
import com.philips.dmis.swt.ui.toolkit.js.JsWriter;
import com.philips.dmis.swt.ui.toolkit.js.Pair;
import com.philips.dmis.swt.ui.toolkit.widgets.JsRenderException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class JsWidgetModule implements JsModule {
    public static final String ID = "W";

    private static final List<JsMember> MEMBERS = Arrays.asList(
            new AddDataKeyFunction(),
            new AddSlaveFunction(),
            new AddSubstitutionFunction(),
            new AppendElementFunction(),
            new AppendListItemFunction(),
            new AppendOptionFunction(),
            new BeforeEventFunction(),
            new BeforeUpdateDataProxyFunction(),
            new BeforeUpdateFunction(),
            new BeforeUpdateListItemsFunction(),
            new BeforeUpdateOptionsFunction(),
            new BeforeUpdateSubscribersFunction(),
            new BeforeUpdateTableBodyFunction(),
            new BeforeUpdateTableFooterFunction(),
            new BeforeUpdateTableHeaderFunction(),
            new BeforeUpdateTemplateListItemsFunction(),
            new BeforeUpdateTextFunction(),
            new BeforeUpdateValueFunction(),
            new BlobToElementFunction(),
            new CloneFunction(),
            new DeclarePageVariableFunction(),
            new EventHandlerFunction.OnApplicationStartEventHandlerFunction(),
            new EventHandlerFunction.OnAfterPrintEventHandlerFunction(),
            new EventHandlerFunction.OnBeforePrintEventHandlerFunction(),
            new EventHandlerFunction.OnBeforeUnloadEventHandlerFunction(),
            new EventHandlerFunction.OnBlurEventHandlerFunction(),
            new EventHandlerFunction.OnErrorEventHandlerFunction(),
            new EventHandlerFunction.OnFocusEventHandlerFunction(),
            new EventHandlerFunction.OnLanguageChangeEventHandlerFunction(),
            new EventHandlerFunction.OnMessageEventHandlerFunction(),
            new EventHandlerFunction.OnOfflineEventHandlerFunction(),
            new EventHandlerFunction.OnOnlineEventHandlerFunction(),
            new EventHandlerFunction.OnRedoEventHandlerFunction(),
            new EventHandlerFunction.OnUndoEventHandlerFunction(),
            new EventHandlerFunction.OnUnloadEventHandlerFunction(),
            new EventHandlerFunction.OnKeyPressEventHandlerFunction(),
            new EventHandlerFunction.OnKeyDownEventHandlerFunction(),
            new EventHandlerFunction.OnKeyUpEventHandlerFunction(),
            new EventHandlerFunction.OnColorSchemeChangeEventHandlerFunction(),
            new EventHandlerFunction.OnActivateEventHandlerFunction(),
            new EventHandlerFunction.OnAppendEventHandlerFunction(),
            new EventHandlerFunction.OnChangeEventHandlerFunction(),
            new EventHandlerFunction.OnClickEventHandlerFunction(),
            new EventHandlerFunction.OnDeactivateEventHandlerFunction(),
            new EventHandlerFunction.OnElapsedEventHandlerFunction(),
            new EventHandlerFunction.OnHideEventHandlerFunction(),
            new EventHandlerFunction.OnInitEventHandlerFunction(),
            new EventHandlerFunction.OnInputEventHandlerFunction(),
            new EventHandlerFunction.OnRemoveEventHandlerFunction(),
            new EventHandlerFunction.OnResetEventHandlerFunction(),
            new EventHandlerFunction.OnResponseEventHandlerFunction(),
            new EventHandlerFunction.OnRefreshEventHandlerFunction(),
            new EventHandlerFunction.OnShowEventHandlerFunction(),
            new EventHandlerFunction.OnStartEventHandlerFunction(),
            new EventHandlerFunction.OnStopEventHandlerFunction(),
            new EventHandlerFunction.OnBeforeUpdateEventHandlerFunction(),
            new EventHandlerFunction.OnUpdateEventHandlerFunction(),
            new EventHandlerFunction.OnOrderChangeEventHandlerFunction(),
            new EventHandlerFunction.OnClickOutsideDialogEventHandlerFunction(),
            new EventHandlerFunction.OnOpenEventHandlerFunction(),
            new EventHandlerFunction.OnSelectionChangeEventHandlerFunction(),
            new EventHandlerFunction.OnPageVariableChangeEventHandlerFunction(),
            new GetElementFunction(),
            new GetFunction(),
            new GetInnerElementFunction(),
            new GetMultiLevelFunction(),
            new GetOrderingFunction(),
            new GetPageVariableFunction(),
            new GetParameterFunction(),
            new GetParametersFunction(),
            new GetWidgetTypeFunction(),
            new IconElementIdFunction(),
            new InitFunction(),
            new InitWidgetFunction(),
            new InnerElementIdFunction(),
            new IsContainerFunction(),
            new NumberTextIdFunction(),
            new PostFunction(),
            new ProcessResourceResponseFunction(),
            new ProcessResponseFunction(),
            new RaiseEventFunction(),
            new RefreshFunction(),
            new RefreshPageFunction(),
            new RemoveAllClonesFunction(),
            new RemoveAllListItemsFunction(),
            new RemoveAllParametersFunction(),
            new RemoveClassNameFunction(),
            new RemoveCloneFunction(),
            new RemoveDataKeyFunction(),
            new RemoveElementFunction(),
            new RemoveOptionsFunction(),
            new RemoveParameterFunction(),
            new RemoveSlaveFunction(),
            new RemoveSubstitutionFunction(),
            new RemoveWidgetFunction(),
            new RenderElementFunction(),
            new RenderInnerHTMLFunction(),
            new ResetFunction(),
            new SelectAllFunction(),
            new SelectIndexFunction(),
            new SelectNoneFunction(),
            new SendHttpRequestFunction(),
            new SubstituteFunction(),
            new SetAutocompleteFunction(),
            new SetDataAdapterEnabledFunction(),
            new SetDisabledFunction(),
            new SetIconFunction(),
            new SetLengthFunction(),
            new SetListFunction(),
            new SetMultipleFunction(),
            new SetNumberFunction(),
            new SetOrderFunction(),
            new SetPageVariableFunction(),
            new SetParameterFunction(),
            new SetParametersFunction(),
            new SetPatternFunction(),
            new SetPlaceholderFunction(),
            new SetRangeFunction(),
            new SetReadonlyFunction(),
            new SetRequiredFunction(),
            new SetStepFunction(),
            new SetTextFunction(),
            new SetValueFunction(),
            new StartFunction(),
            new StopFunction(),
            new SubstitutionsVariable(),
            new TextElementIdFunction(),
            new UpdateDataProxyFunction(),
            new UpdateFunction(),
            new UpdateListItemsFunction(),
            new UpdateNumberingFunction(),
            new UpdateOptionsFunction(),
            new UpdateSubscribersFunction(),
            new UpdateTableBodyFunction(),
            new UpdateTableFooterFunction(),
            new UpdateTableHeaderFunction(),
            new UpdateTemplateListItemsFunction(),
            new UpdateTextFunction(),
            new UpdateValueFunction(),
            new WidgetTypesVariable()
    );

    private static final Map<Class<? extends JsMember>, Pair<String, JsMember>> INDEX =
            JsModule.createIndex(MEMBERS);

    public static JsMember getMemberInstance(Class<? extends JsMember> memberClass) {
        return JsModule.getMemberInstance(memberClass, INDEX);
    }

    public static String getId(Class<? extends JsMember> memberClass) throws JsRenderException {
        return JsModule.getId(memberClass, INDEX);
    }

    public static String getQualifiedId(Class<? extends JsMember> memberClass) {
        return JsModule.getQualifiedId(ID, memberClass, INDEX);
    }

    @Override
    public void renderJs(Toolkit toolkit, JsWriter js) {
        JsModule.renderModule(toolkit, js, ID, INDEX.values());
    }

    @Override
    public String getInitFunctionId() {
        return getQualifiedId(InitFunction.class);
    }
}
